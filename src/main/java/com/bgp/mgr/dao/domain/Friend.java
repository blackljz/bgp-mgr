package com.bgp.mgr.dao.domain;

import java.util.Date;

public class Friend {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column friend.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column friend.userId
     *
     * @mbggenerated
     */
    private Long userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column friend.friendId
     *
     * @mbggenerated
     */
    private Long friendId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column friend.type
     *
     * @mbggenerated
     */
    private String type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column friend.modified_date
     *
     * @mbggenerated
     */
    private Date modified_date;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column friend.id
     *
     * @return the value of friend.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column friend.id
     *
     * @param id the value for friend.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column friend.userId
     *
     * @return the value of friend.userId
     *
     * @mbggenerated
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column friend.userId
     *
     * @param userId the value for friend.userId
     *
     * @mbggenerated
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column friend.friendId
     *
     * @return the value of friend.friendId
     *
     * @mbggenerated
     */
    public Long getFriendId() {
        return friendId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column friend.friendId
     *
     * @param friendId the value for friend.friendId
     *
     * @mbggenerated
     */
    public void setFriendId(Long friendId) {
        this.friendId = friendId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column friend.type
     *
     * @return the value of friend.type
     *
     * @mbggenerated
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column friend.type
     *
     * @param type the value for friend.type
     *
     * @mbggenerated
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column friend.modified_date
     *
     * @return the value of friend.modified_date
     *
     * @mbggenerated
     */
    public Date getModified_date() {
        return modified_date;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column friend.modified_date
     *
     * @param modified_date the value for friend.modified_date
     *
     * @mbggenerated
     */
    public void setModified_date(Date modified_date) {
        this.modified_date = modified_date;
    }
}