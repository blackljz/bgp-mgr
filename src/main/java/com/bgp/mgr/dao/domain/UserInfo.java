package com.bgp.mgr.dao.domain;

import java.util.Date;

public class UserInfo {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column userInfo.userId
     *
     * @mbggenerated
     */
    private Long userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column userInfo.userName
     *
     * @mbggenerated
     */
    private String userName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column userInfo.userImage
     *
     * @mbggenerated
     */
    private String userImage;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column userInfo.age
     *
     * @mbggenerated
     */
    private String age;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column userInfo.area
     *
     * @mbggenerated
     */
    private String area;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column userInfo.sign
     *
     * @mbggenerated
     */
    private String sign;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column userInfo.modified_id
     *
     * @mbggenerated
     */
    private String modified_id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column userInfo.modified_date
     *
     * @mbggenerated
     */
    private Date modified_date;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column userInfo.userId
     *
     * @return the value of userInfo.userId
     *
     * @mbggenerated
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column userInfo.userId
     *
     * @param userId the value for userInfo.userId
     *
     * @mbggenerated
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column userInfo.userName
     *
     * @return the value of userInfo.userName
     *
     * @mbggenerated
     */
    public String getUserName() {
        return userName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column userInfo.userName
     *
     * @param userName the value for userInfo.userName
     *
     * @mbggenerated
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column userInfo.userImage
     *
     * @return the value of userInfo.userImage
     *
     * @mbggenerated
     */
    public String getUserImage() {
        return userImage;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column userInfo.userImage
     *
     * @param userImage the value for userInfo.userImage
     *
     * @mbggenerated
     */
    public void setUserImage(String userImage) {
        this.userImage = userImage == null ? null : userImage.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column userInfo.age
     *
     * @return the value of userInfo.age
     *
     * @mbggenerated
     */
    public String getAge() {
        return age;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column userInfo.age
     *
     * @param age the value for userInfo.age
     *
     * @mbggenerated
     */
    public void setAge(String age) {
        this.age = age == null ? null : age.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column userInfo.area
     *
     * @return the value of userInfo.area
     *
     * @mbggenerated
     */
    public String getArea() {
        return area;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column userInfo.area
     *
     * @param area the value for userInfo.area
     *
     * @mbggenerated
     */
    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column userInfo.sign
     *
     * @return the value of userInfo.sign
     *
     * @mbggenerated
     */
    public String getSign() {
        return sign;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column userInfo.sign
     *
     * @param sign the value for userInfo.sign
     *
     * @mbggenerated
     */
    public void setSign(String sign) {
        this.sign = sign == null ? null : sign.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column userInfo.modified_id
     *
     * @return the value of userInfo.modified_id
     *
     * @mbggenerated
     */
    public String getModified_id() {
        return modified_id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column userInfo.modified_id
     *
     * @param modified_id the value for userInfo.modified_id
     *
     * @mbggenerated
     */
    public void setModified_id(String modified_id) {
        this.modified_id = modified_id == null ? null : modified_id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column userInfo.modified_date
     *
     * @return the value of userInfo.modified_date
     *
     * @mbggenerated
     */
    public Date getModified_date() {
        return modified_date;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column userInfo.modified_date
     *
     * @param modified_date the value for userInfo.modified_date
     *
     * @mbggenerated
     */
    public void setModified_date(Date modified_date) {
        this.modified_date = modified_date;
    }
}