package com.ming.zhihuWebSpider.extend;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.scheduler.DuplicateRemovedScheduler;
import us.codecraft.webmagic.scheduler.MonitorableScheduler;
import us.codecraft.webmagic.scheduler.component.DuplicateRemover;

import com.alibaba.fastjson.JSON;

public class RedisSchedulerExtend2 extends DuplicateRemovedScheduler implements
		MonitorableScheduler, DuplicateRemover {

	private JedisPool pool;

	private Integer index;

	//在插入redis的队列中时，对url进行处理
	private String queueName = null;
	
	private static final String QUEUE_PREFIX = "queue_";

	private static final String SET_PREFIX = "set_";

	private static final String ITEM_PREFIX = "item_";
	
	public RedisSchedulerExtend2(JedisPool pool, Integer index,String queueName) {
		this.pool = pool;
		this.index = index;
		this.queueName = queueName;
		setDuplicateRemover(this);
	}

	public RedisSchedulerExtend2(JedisPool pool, Integer index ) {
		this.pool = pool;
		this.index = index;
		setDuplicateRemover(this);
	}
	
	public Jedis getJedis() {
		Jedis jedis = pool.getResource();
		jedis.select(index);
		return jedis;
	}

	@Override
	public void resetDuplicateCheck(Task task) {
		Jedis jedis = getJedis();
		try {
			jedis.del(getSetKey(task));
		} finally {
			pool.returnResource(jedis);
		}
	}

	@Override
	public boolean isDuplicate(Request request, Task task) {
		Jedis jedis = getJedis();
		try {
			boolean isDuplicate = jedis.sismember(getSetKey(task),
					request.getUrl());
			if (!isDuplicate) {
				jedis.sadd(getSetKey(task), request.getUrl());
			}
			return isDuplicate;
		} finally {
			pool.returnResource(jedis);
		}

	}

	@Override
	protected void pushWhenNoDuplicate(Request request, Task task) {
		Jedis jedis = getJedis();
		try {
			//jedis.rpush(getQueueKey(task), request.getUrl());
			jedis.rpush(QueueNameConstant.QUEUE_USER_BASE_INFO   + task.getUUID(), request.getUrl());
			jedis.rpush(QueueNameConstant.QUEUE_USER_DETAIL_INFO + task.getUUID(), request.getUrl()+"/about");
			if (request.getExtras() != null) {
				String field = DigestUtils.shaHex(request.getUrl());
				String value = JSON.toJSONString(request);
				jedis.hset((ITEM_PREFIX + task.getUUID()), field, value);
			}
		} finally {
			pool.returnResource(jedis);
		}
	}

	@Override
	public synchronized Request poll(Task task) {
		Jedis jedis = getJedis();
		try {
			String url = jedis.lpop(getQueueKey(task));
			if (url == null) {
				return null;
			}
			String key = ITEM_PREFIX + task.getUUID();
			String field = DigestUtils.shaHex(url);
			byte[] bytes = jedis.hget(key.getBytes(), field.getBytes());
			if (bytes != null) {
				Request o = JSON.parseObject(new String(bytes), Request.class);
				return o;
			}
			Request request = new Request(url);
			return request;
		} finally {
			pool.returnResource(jedis);
		}
	}

	protected String getSetKey(Task task) {
		return SET_PREFIX + task.getUUID();
	}

	protected String getQueueKey(Task task) {
		if(StringUtils.isNotBlank(queueName))
			return queueName + task.getUUID();
		else
			return QUEUE_PREFIX + task.getUUID();
	}

	@Override
	public int getLeftRequestsCount(Task task) {
		Jedis jedis = getJedis();
		try {
			Long size = jedis.llen(getQueueKey(task));
			return size.intValue();
		} finally {
			pool.returnResource(jedis);
		}
	}

	@Override
	public int getTotalRequestsCount(Task task) {
		Jedis jedis = getJedis();
		try {
			Long size = jedis.scard(getQueueKey(task));
			return size.intValue();
		} finally {
			pool.returnResource(jedis);
		}
	}
}
