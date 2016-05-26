# java webspider
## 查看效果 http://lxming.pub/zhihu
## 技术选型
|名称|版本|
|----|----|
|webmagic|0.5.2|
|spring boot|1.2.7.RELEASE|
|spring mvc|4.2.0.RC2|
|mybats|3.2.8|
|通用maper|3.3.0|
|百度echart|2.0|
|layer|2.1|
|bootstrap|3.0.3|
## 启动方法
* 爬虫都放在spider 包下,启动main方法就好
    * 需要使用Redis做队列,充当做调度器的功能 ，也可以修改Scheduler为FileCacheQueueScheduler,如
    ```
    (new FileCacheQueueScheduler("/usr/zhihu/cache"))
    ```
    * 使用mysql ,配置文件在db.properties
    * 爬取用户详细信息需要带上cookie,获取cookie 可以通过浏览器，也可以用我的[SimulateLogin](https://github.com/QiuMing/SimulateLogin)，获取之后修改spider site对象 中cookie的值
* 启动web 则直接运行app 即可
* 如果是在linux 下，则在配置好数据库的情况下，运行spider.sh 和app.sh 俩脚本就行

## 截图
![](https://github.com/QiuMing/zhihuWebSpider/blob/master/screenshot/1.png)
![](https://github.com/QiuMing/zhihuWebSpider/blob/master/screenshot/2.png)
![](https://github.com/QiuMing/zhihuWebSpider/blob/master/screenshot/3.png)
