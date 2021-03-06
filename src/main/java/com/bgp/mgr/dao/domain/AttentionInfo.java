package com.bgp.mgr.dao.domain;

public class AttentionInfo {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column attentionInfo.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column attentionInfo.userId
     *
     * @mbggenerated
     */
    private Long userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column attentionInfo.friendId
     *
     * @mbggenerated
     */
    private Long friendId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column attentionInfo.friendType
     *
     * @mbggenerated
     */
    private String friendType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column attentionInfo.type
     *
     * @mbggenerated
     */
    private String type;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column attentionInfo.id
     *
     * @return the value of attentionInfo.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column attentionInfo.id
     *
     * @param id the value for attentionInfo.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column attentionInfo.userId
     *
     * @return the value of attentionInfo.userId
     *
     * @mbggenerated
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column attentionInfo.userId
     *
     * @param userId the value for attentionInfo.userId
     *
     * @mbggenerated
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column attentionInfo.friendId
     *
     * @return the value of attentionInfo.friendId
     *
     * @mbggenerated
     */
    public Long getFriendId() {
        return friendId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column attentionInfo.friendId
     *
     * @param friendId the value for attentionInfo.friendId
     *
     * @mbggenerated
     */
    public void setFriendId(Long friendId) {
        this.friendId = friendId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column attentionInfo.friendType
     *
     * @return the value of attentionInfo.friendType
     *
     * @mbggenerated
     */
    public String getFriendType() {
        return friendType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column attentionInfo.friendType
     *
     * @param friendType the value for attentionInfo.friendType
     *
     * @mbggenerated
     */
    public void setFriendType(String friendType) {
        this.friendType = friendType == null ? null : friendType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column attentionInfo.type
     *
     * @return the value of attentionInfo.type
     *
     * @mbggenerated
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column attentionInfo.type
     *
     * @param type the value for attentionInfo.type
     *
     * @mbggenerated
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }
}