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
    private Long userid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column attentionInfo.friendId
     *
     * @mbggenerated
     */
    private Long friendid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column attentionInfo.friendType
     *
     * @mbggenerated
     */
    private String friendtype;

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
    public Long getUserid() {
        return userid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column attentionInfo.userId
     *
     * @param userid the value for attentionInfo.userId
     *
     * @mbggenerated
     */
    public void setUserid(Long userid) {
        this.userid = userid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column attentionInfo.friendId
     *
     * @return the value of attentionInfo.friendId
     *
     * @mbggenerated
     */
    public Long getFriendid() {
        return friendid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column attentionInfo.friendId
     *
     * @param friendid the value for attentionInfo.friendId
     *
     * @mbggenerated
     */
    public void setFriendid(Long friendid) {
        this.friendid = friendid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column attentionInfo.friendType
     *
     * @return the value of attentionInfo.friendType
     *
     * @mbggenerated
     */
    public String getFriendtype() {
        return friendtype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column attentionInfo.friendType
     *
     * @param friendtype the value for attentionInfo.friendType
     *
     * @mbggenerated
     */
    public void setFriendtype(String friendtype) {
        this.friendtype = friendtype == null ? null : friendtype.trim();
    }
}