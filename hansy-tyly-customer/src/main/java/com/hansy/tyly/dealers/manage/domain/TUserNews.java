package com.hansy.tyly.dealers.manage.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_user_news")
public class TUserNews {
    /**
     * 主键
     */
    @Id
    @Column(name = "table_key")
    private String tableKey;

    /**
     * 用户编号
     */
    @Column(name = "user_no")
    private String userNo;

    /**
     * 用户类型(经销商|商户)
     */
    @Column(name = "user_type")
    private String userType;

    /**
     * 消息类型
     */
    @Column(name = "news_type")
    private String newsType;

    /**
     * 消息内容
     */
    @Column(name = "news_content")
    private String newsContent;

    /**
     * 消息时间
     */
    @Column(name = "news_date")
    private Date newsDate;

    /**
     * 是否已读
     */
    @Column(name = "is_read")
    private String isRead;

    /**
     * 获取主键
     *
     * @return table_key - 主键
     */
    public String getTableKey() {
        return tableKey;
    }

    /**
     * 设置主键
     *
     * @param tableKey 主键
     */
    public void setTableKey(String tableKey) {
        this.tableKey = tableKey;
    }

    /**
     * 获取用户编号
     *
     * @return user_no - 用户编号
     */
    public String getUserNo() {
        return userNo;
    }

    /**
     * 设置用户编号
     *
     * @param userNo 用户编号
     */
    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    /**
     * 获取用户类型(经销商|商户)
     *
     * @return user_type - 用户类型(经销商|商户)
     */
    public String getUserType() {
        return userType;
    }

    /**
     * 设置用户类型(经销商|商户)
     *
     * @param userType 用户类型(经销商|商户)
     */
    public void setUserType(String userType) {
        this.userType = userType;
    }

    /**
     * 获取消息类型
     *
     * @return news_type - 消息类型
     */
    public String getNewsType() {
        return newsType;
    }

    /**
     * 设置消息类型
     *
     * @param newsType 消息类型
     */
    public void setNewsType(String newsType) {
        this.newsType = newsType;
    }

    /**
     * 获取消息内容
     *
     * @return news_content - 消息内容
     */
    public String getNewsContent() {
        return newsContent;
    }

    /**
     * 设置消息内容
     *
     * @param newsContent 消息内容
     */
    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }

    /**
     * 获取消息时间
     *
     * @return news_date - 消息时间
     */
    public Date getNewsDate() {
        return newsDate;
    }

    /**
     * 设置消息时间
     *
     * @param newsDate 消息时间
     */
    public void setNewsDate(Date newsDate) {
        this.newsDate = newsDate;
    }

    /**
     * 获取是否已读
     *
     * @return is_read - 是否已读
     */
    public String getIsRead() {
        return isRead;
    }

    /**
     * 设置是否已读
     *
     * @param isRead 是否已读
     */
    public void setIsRead(String isRead) {
        this.isRead = isRead;
    }
}