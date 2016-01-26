package com.ming.zhihuWebSpider.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.HelpUrl;
import us.codecraft.webmagic.model.annotation.TargetUrl;

@JsonInclude(JsonInclude.Include.NON_NULL)
@HelpUrl("http://www.zhihu.com/people/[\\w-]+/followers")
@TargetUrl(value="http://www.zhihu.com/people/[\\w-]+/about")
@Table(name = "user_detail_info")
public class UserDetailInfo {
    /**
     * ID
     */
    @Id
    @Column(name = "detail_user_id")
    private Integer detailUserId;

    /**
     * 抓取页URL
     */
    @ExtractBy(value="//div[@class='profile-navbar clearfix']/a[1]/@href")
    @Column(name = "pageUrl")
    private String pageurl;

    /**
     * 用户名
     */
    @ExtractBy(value="//div[@class='title-section ellipsis']/span[1]/text()")
    private String nickname;

    /**
     * 所在行业
     */
    @ExtractBy(value="//span[@class='business item']/a/text()")
    private String business;

    /**
     * 公司或组织名称
     */
    @ExtractBy(value="//span[@class='employment item']/@title")
    private String employment;

    /**
     * 职位
     */
    @ExtractBy(value="//span[@class='position item']/@title")
    private String position;

    /**
     * 学校或教育机构名
     */
    @ExtractBy(value="//span[@class='education item']/a/text()")
    private String education;

    /**
     * 专业方向
     */
    @Column(name = "educationExtra")
    @ExtractBy(value="//span[@class='education-extra item']/a/text()")
    private String educationextra;

    /**
     * 收藏者
     */
    @ExtractBy(value="//div[@class='zm-profile-module-desc']/span[4]/strong/text()")
    private Integer collecters;

    /**
     * 分享
     */
    @ExtractBy(value="//div[@class='zm-profile-module-desc']/span[5]/strong/text()")
    private Integer shares;

    /**
     * 添加时间
     */
    private Date addtime;

    /**
     * 性别
     */
    @ExtractBy(value="//span[@class='item gender']/i/@class")
    private String gender;
    
    /**
     * 状态
     */
    @ExtractBy(value="//div[@class='zh-profile-account-status']/text()")
    private String status;
   
    
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
     * @return detail_user_id - ID
     */
    public Integer getDetailUserId() {
        return detailUserId;
    }

    /**
     * 设置ID
     *
     * @param detailUserId ID
     */
    public void setDetailUserId(Integer detailUserId) {
        this.detailUserId = detailUserId;
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
     * 获取所在行业
     *
     * @return business - 所在行业
     */
    public String getBusiness() {
        return business;
    }

    /**
     * 设置所在行业
     *
     * @param business 所在行业
     */
    public void setBusiness(String business) {
        this.business = business == null ? null : business.trim();
    }

    /**
     * 获取公司或组织名称
     *
     * @return employment - 公司或组织名称
     */
    public String getEmployment() {
        return employment;
    }

    /**
     * 设置公司或组织名称
     *
     * @param employment 公司或组织名称
     */
    public void setEmployment(String employment) {
        this.employment = employment == null ? null : employment.trim();
    }

    /**
     * 获取职位
     *
     * @return position - 职位
     */
    public String getPosition() {
        return position;
    }

    /**
     * 设置职位
     *
     * @param position 职位
     */
    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    /**
     * 获取学校或教育机构名
     *
     * @return education - 学校或教育机构名
     */
    public String getEducation() {
        return education;
    }

    /**
     * 设置学校或教育机构名
     *
     * @param education 学校或教育机构名
     */
    public void setEducation(String education) {
        this.education = education == null ? null : education.trim();
    }

    /**
     * 获取专业方向
     *
     * @return educationExtra - 专业方向
     */
    public String getEducationextra() {
        return educationextra;
    }

    /**
     * 设置专业方向
     *
     * @param educationextra 专业方向
     */
    public void setEducationextra(String educationextra) {
        this.educationextra = educationextra == null ? null : educationextra.trim();
    }

    /**
     * 获取收藏者
     *
     * @return collecters - 收藏者
     */
    public Integer getCollecters() {
        return collecters;
    }

    /**
     * 设置收藏者
     *
     * @param collecters 收藏者
     */
    public void setCollecters(Integer collecters) {
        this.collecters = collecters;
    }

    /**
     * 获取分享
     *
     * @return shares - 分享
     */
    public Integer getShares() {
        return shares;
    }

    /**
     * 设置分享
     *
     * @param shares 分享
     */
    public void setShares(Integer shares) {
        this.shares = shares;
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
     * 获取性别
     *
     * @return gender - 性别
     */
    public String getGender() {
        return gender;
    }

    /**
     * 设置性别
     *
     * @param gender 性别
     */
    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }
    
    /**
     * 获取状态
     *
     * @return status - 状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置状态
     *
     * @param status 状态
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

	public Integer getSelectLimitAmount() {
		return selectLimitAmount;
	}

	public void setSelectLimitAmount(Integer selectLimitAmount) {
		this.selectLimitAmount = selectLimitAmount;
	}

	public Integer getItemResultAmount() {
		return itemResultAmount;
	}

	public void setItemResultAmount(Integer itemResultAmount) {
		this.itemResultAmount = itemResultAmount;
	}

}