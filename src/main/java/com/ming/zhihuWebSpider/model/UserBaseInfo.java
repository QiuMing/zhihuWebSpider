package com.ming.zhihuWebSpider.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.TargetUrl;

//@HelpUrl("https://github.com/\\w+")
@JsonInclude(JsonInclude.Include.NON_NULL)
@TargetUrl(value="http://www.zhihu.com/people/[\\w-]+")
@Table(name = "user_base_info")
public class UserBaseInfo {

	@Id
    private Integer id;
 
    @ExtractBy(value = "//div[@class='profile-navbar clearfix']/a[1]/@href", notNull = true)
    @Column(name = "pageUrl")
    private String pageurl;


    /**
     * 昵称
     */
    @ExtractBy(value ="//div[@class='title-section ellipsis']/span[1]/text()", notNull = true)
    private String nickname;

    /**
     * 居住地
     */
    @ExtractBy(value = "//span[@class='location item']/@title")
    private String location;

    /**
     * 微博地址
     */
    @ExtractBy(value = "//div[@class='weibo-wrap']//a/@href")
    @Column(name = "weiboUrl")
    private String weibourl;

    /**
     * 一句话介绍
     */
    @ExtractBy(value = "//div[@class='title-section ellipsis']/span[@class='bio']/@title")
    private String headline;


    /**
     * 关注了
     */
    @ExtractBy(value = "//div[@class='zm-profile-side-following zg-clear']/a[1]/strong/text()")
    private Integer followees;

    /**
     * 关注者
     */
    @ExtractBy(value = "//div[@class='zm-profile-side-following zg-clear']/a[2]/strong/text()")
    private Integer followers;

    /**
     * 关注专栏
     */
    @ExtractBy(value = "//div[@class='zm-side-section-inner zg-clear']/div/a/strong/text()/regex('[1-9]\\d*')")
    private Integer columns;

    /**
     * 关注话题
     */
    @ExtractBy("/regex('[0-9]+\\s个话题')/regex('[1-9]\\d*')")
    private Integer topics;

    /**
     * 主页被多少人浏览
     */
    @ExtractBy(value = "//span[@class='zg-gray-normal']/strong/text()")
    @Column(name = "pageViews")
    private Integer pageviews;

    /**
     * 提问
     */
    @ExtractBy(value = "//div[@class='profile-navbar clearfix']/a[2]/span/text()")
    private Integer questions;

    /**
     * 回答
     */
    @ExtractBy(value = "//div[@class='profile-navbar clearfix']/a[3]/span/text()")
    private Integer answers;

    /**
     * 专栏文章
     */
    @ExtractBy(value = "//div[@class='profile-navbar clearfix']/a[4]/span/text()")
    private Integer articles;

    /**
     * 收藏了
     */
    @ExtractBy(value = "//div[@class='profile-navbar clearfix']/a[5]/span/text()")
    private Integer collections;

    /**
     * 公共编辑
     */
    @ExtractBy(value = "//div[@class='profile-navbar clearfix']/a[6]/span/text()")
    private Integer edits;

    /**
     * 赞同
     */
    @ExtractBy(value = "//span[@class='zm-profile-header-user-agree']/strong/text()")
    @Column(name = "agreeNums")
    private Integer agreenums;

    /**
     * 感谢
     */
    @ExtractBy(value = "//span[@class='zm-profile-header-user-thanks']/strong/text()")
    @Column(name = "thanksNums")
    private Integer thanksnums;

    /**
     * 添加时间
     */
    private Date addtime;

    
    /**
     * 个人简介
     */
    @ExtractBy(value = "//span[@class='content']")
    private String description;

    /**
     * 最近动态（未处理后）
     */
    @ExtractBy(value="//span[@class='zm-profile-setion-time zg-gray zg-right']/text()")
    @Column(name = "lastDynamic")
    private String lastdynamic;
    
	/**
     * 最近动态（处理）
     */
    @Column(name = "lastDynamicTime")
    private Date lastdynamictime;
    /**
     * limit  查询参数
     */
    
    private Integer selectLimitAmount;

    /**
     * 查询结果数量
     */
    private Integer itemResultAmount;
    
    /**
     * 获取ID
     *
     * @return id - ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置ID
     *
     * @param id ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取抓取页URL
     *
     * @return pageUrl - 抓取页URL
     */
    public String getPageurl() {
        return pageurl;
    }

    /**
     * 设置抓取页URL
     *
     * @param pageurl 抓取页URL
     */
    public void setPageurl(String pageurl) {
        this.pageurl = pageurl == null ? null : pageurl.trim();
    }

    /**
     * 获取用户名
     *
     * @return nickname - 用户名
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 设置用户名
     *
     * @param nickname 用户名
     */
    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    /**
     * 获取居住地
     *
     * @return location - 居住地
     */
    public String getLocation() {
        return location;
    }

    /**
     * 设置居住地
     *
     * @param location 居住地
     */
    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }


    /**
     * 获取微博地址
     *
     * @return weiboUrl - 微博地址
     */
    public String getWeibourl() {
        return weibourl;
    }

    /**
     * 设置微博地址
     *
     * @param weibourl 微博地址
     */
    public void setWeibourl(String weibourl) {
        this.weibourl = weibourl == null ? null : weibourl.trim();
    }

    /**
     * 获取一句话介绍
     *
     * @return headline - 一句话介绍
     */
    public String getHeadline() {
        return headline;
    }

    /**
     * 设置一句话介绍
     *
     * @param headline 一句话介绍
     */
    public void setHeadline(String headline) {
        this.headline = headline == null ? null : headline.trim();
    }


    /**
     * 获取关注了
     *
     * @return followees - 关注了
     */
    public Integer getFollowees() {
        return followees;
    }

    /**
     * 设置关注了
     *
     * @param followees 关注了
     */
    public void setFollowees(Integer followees) {
        this.followees = followees;
    }

    /**
     * 获取关注者
     *
     * @return followers - 关注者
     */
    public Integer getFollowers() {
        return followers;
    }

    /**
     * 设置关注者
     *
     * @param followers 关注者
     */
    public void setFollowers(Integer followers) {
        this.followers = followers;
    }

    /**
     * 获取关注专栏
     *
     * @return columns - 关注专栏
     */
    public Integer getColumns() {
        return columns;
    }

    /**
     * 设置关注专栏
     *
     * @param columns 关注专栏
     */
    public void setColumns(Integer columns) {
        this.columns = columns;
    }

    /**
     * 获取关注话题
     *
     * @return topics - 关注话题
     */
    public Integer getTopics() {
        return topics;
    }

    /**
     * 设置关注话题
     *
     * @param topics 关注话题
     */
    public void setTopics(Integer topics) {
        this.topics = topics;
    }

    /**
     * 获取主页被多少人浏览
     *
     * @return pageViews - 主页被多少人浏览
     */
    public Integer getPageviews() {
        return pageviews;
    }

    /**
     * 设置主页被多少人浏览
     *
     * @param pageviews 主页被多少人浏览
     */
    public void setPageviews(Integer pageviews) {
        this.pageviews = pageviews;
    }

    /**
     * 获取提问
     *
     * @return questions - 提问
     */
    public Integer getQuestions() {
        return questions;
    }

    /**
     * 设置提问
     *
     * @param questions 提问
     */
    public void setQuestions(Integer questions) {
        this.questions = questions;
    }

    /**
     * 获取回答
     *
     * @return answers - 回答
     */
    public Integer getAnswers() {
        return answers;
    }

    /**
     * 设置回答
     *
     * @param answers 回答
     */
    public void setAnswers(Integer answers) {
        this.answers = answers;
    }

    /**
     * 获取专栏文章
     *
     * @return articles - 专栏文章
     */
    public Integer getArticles() {
        return articles;
    }

    /**
     * 设置专栏文章
     *
     * @param articles 专栏文章
     */
    public void setArticles(Integer articles) {
        this.articles = articles;
    }

    /**
     * 获取收藏了
     *
     * @return collections - 收藏了
     */
    public Integer getCollections() {
        return collections;
    }

    /**
     * 设置收藏了
     *
     * @param collections 收藏了
     */
    public void setCollections(Integer collections) {
        this.collections = collections;
    }

    /**
     * 获取公共编辑
     *
     * @return edits - 公共编辑
     */
    public Integer getEdits() {
        return edits;
    }

    /**
     * 设置公共编辑
     *
     * @param edits 公共编辑
     */
    public void setEdits(Integer edits) {
        this.edits = edits;
    }

    /**
     * 获取赞同
     *
     * @return agreeNums - 赞同
     */
    public Integer getAgreenums() {
        return agreenums;
    }

    /**
     * 设置赞同
     *
     * @param agreenums 赞同
     */
    public void setAgreenums(Integer agreenums) {
        this.agreenums = agreenums;
    }

    /**
     * 获取感谢
     *
     * @return thanksNums - 感谢
     */
    public Integer getThanksnums() {
        return thanksnums;
    }

    /**
     * 设置感谢
     *
     * @param thanksnums 感谢
     */
    public void setThanksnums(Integer thanksnums) {
        this.thanksnums = thanksnums;
    }

    /**
     * 获取添加时间
     *
     * @return addtime - 添加时间
     */
    public Date getAddtime() {
        return addtime;
    }

    /**
     * 设置添加时间
     *
     * @param addtime 添加时间
     */
    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    /**
     * 获取个人简介
     *
     * @return description - 个人简介
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置个人简介
     *
     * @param description 个人简介
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

	public Integer getItemResultAmount() {
		return itemResultAmount;
	}

	public void setItemResultAmount(Integer itemResultAmount) {
		this.itemResultAmount = itemResultAmount;
	}

	public Integer getSelectLimitAmount() {
		return selectLimitAmount;
	}

	public void setSelectLimitAmount(Integer selectLimitAmount) {
		this.selectLimitAmount = selectLimitAmount;
	}

	public Date getLastdynamictime() {
		return lastdynamictime;
	}

	public void setLastdynamictime(Date lastdynamictime) {
		this.lastdynamictime = lastdynamictime;
	}
	public String getLastdynamic() {
		return lastdynamic;
	}

	public void setLastdynamic(String lastdynamic) {
		this.lastdynamic = lastdynamic;
	}
}