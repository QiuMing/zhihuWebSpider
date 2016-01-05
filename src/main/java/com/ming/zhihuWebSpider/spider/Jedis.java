package com.ming.zhihuWebSpider.spider;

import java.util.HashSet;
import java.util.Set;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class Jedis {

	public Jedis() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		JedisPool pool = new JedisPool(new JedisPoolConfig(), "127.0.0.1");
		redis.clients.jedis.Jedis jedis = pool.getResource();
		redis.clients.jedis.Jedis jedis2 = pool.getResource();
		jedis.select(0);

		Set<String> links = jedis.smembers("set_www.zhihu.com");
		System.err.println(links.size());

		jedis.select(1);

		Set<String> link2 = new HashSet<String>();
		Boolean isExists = jedis2.exists("set_www.zhihu.com");
		if (isExists) {
			System.err.println("存在");
			link2 = jedis2.smembers("set_www.zhihu.com");
		} else {
			jedis.sadd("set_www.zhihu.com", "ling");
			System.out.println(jedis.scard("set_www.zhihu.com"));
		}

		while (links.size() >= link2.size()) {
			for (String s : links) {
				System.err.println(s);
				String aa = s + "/about";
				jedis.sadd("set_www.zhihu.com", aa);
			}
		}
		/*
		 * Set s = jedis.keys("*"); Iterator it = s.iterator();
		 * 
		 * 
		 * while (it.hasNext()) { String key = (String) it.next();
		 * System.err.println(key); // String value = jedis.get(key);
		 * if(key.equals("set_www.zhihu.com")){
		 * 
		 * } // System.out.println(key + value); }
		 */
	}

}
