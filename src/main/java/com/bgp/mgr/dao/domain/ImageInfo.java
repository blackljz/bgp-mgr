package com.bgp.mgr.dao.domain;

public class ImageInfo {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column imageInfo.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column imageInfo.userId
     *
     * @mbggenerated
     */
    private Long userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column imageInfo.friendsCircleId
     *
     * @mbggenerated
     */
    private Long friendsCircleId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column imageInfo.gameId
     *
     * @mbggenerated
     */
    private Long gameId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column imageInfo.image
     *
     * @mbggenerated
     */
    private String image;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column imageInfo.type
     *
     * @mbggenerated
     */
    private String type;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column imageInfo.id
     *
     * @return the value of imageInfo.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column imageInfo.id
     *
     * @param id the value for imageInfo.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column imageInfo.userId
     *
     * @return the value of imageInfo.userId
     *
     * @mbggenerated
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column imageInfo.userId
     *
     * @param userId the value for imageInfo.userId
     *
     * @mbggenerated
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column imageInfo.friendsCircleId
     *
     * @return the value of imageInfo.friendsCircleId
     *
     * @mbggenerated
     */
    public Long getFriendsCircleId() {
        return friendsCircleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column imageInfo.friendsCircleId
     *
     * @param friendsCircleId the value for imageInfo.friendsCircleId
     *
     * @mbggenerated
     */
    public void setFriendsCircleId(Long friendsCircleId) {
        this.friendsCircleId = friendsCircleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column imageInfo.gameId
     *
     * @return the value of imageInfo.gameId
     *
     * @mbggenerated
     */
    public Long getGameId() {
        return gameId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column imageInfo.gameId
     *
     * @param gameId the value for imageInfo.gameId
     *
     * @mbggenerated
     */
    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column imageInfo.image
     *
     * @return the value of imageInfo.image
     *
     * @mbggenerated
     */
    public String getImage() {
        return image;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column imageInfo.image
     *
     * @param image the value for imageInfo.image
     *
     * @mbggenerated
     */
    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column imageInfo.type
     *
     * @return the value of imageInfo.type
     *
     * @mbggenerated
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column imageInfo.type
     *
     * @param type the value for imageInfo.type
     *
     * @mbggenerated
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }
}