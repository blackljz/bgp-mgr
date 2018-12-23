package com.bgp.mgr.dao.domain;

import java.util.Date;

public class GameInfo {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gameInfo.Id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gameInfo.userId
     *
     * @mbggenerated
     */
    private Long userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gameInfo.gameImage
     *
     * @mbggenerated
     */
    private String gameImage;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gameInfo.gameName
     *
     * @mbggenerated
     */
    private String gameName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gameInfo.type
     *
     * @mbggenerated
     */
    private String type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gameInfo.duration
     *
     * @mbggenerated
     */
    private String duration;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gameInfo.peopleNumber
     *
     * @mbggenerated
     */
    private String peopleNumber;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gameInfo.rating
     *
     * @mbggenerated
     */
    private String rating;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gameInfo.severe
     *
     * @mbggenerated
     */
    private String severe;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gameInfo.gameType
     *
     * @mbggenerated
     */
    private String gameType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gameInfo.gameIntroduction
     *
     * @mbggenerated
     */
    private String gameIntroduction;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gameInfo.designer
     *
     * @mbggenerated
     */
    private String designer;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gameInfo.languageDependence
     *
     * @mbggenerated
     */
    private String languageDependence;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gameInfo.artist
     *
     * @mbggenerated
     */
    private String artist;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gameInfo.language
     *
     * @mbggenerated
     */
    private String language;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gameInfo.publishingAge
     *
     * @mbggenerated
     */
    private String publishingAge;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gameInfo.publisher
     *
     * @mbggenerated
     */
    private String publisher;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gameInfo.chinesePublisher
     *
     * @mbggenerated
     */
    private String chinesePublisher;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gameInfo.modifiedDate
     *
     * @mbggenerated
     */
    private Date modifiedDate;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gameInfo.Id
     *
     * @return the value of gameInfo.Id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column gameInfo.Id
     *
     * @param id the value for gameInfo.Id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gameInfo.userId
     *
     * @return the value of gameInfo.userId
     *
     * @mbggenerated
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column gameInfo.userId
     *
     * @param userId the value for gameInfo.userId
     *
     * @mbggenerated
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gameInfo.gameImage
     *
     * @return the value of gameInfo.gameImage
     *
     * @mbggenerated
     */
    public String getGameImage() {
        return gameImage;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column gameInfo.gameImage
     *
     * @param gameImage the value for gameInfo.gameImage
     *
     * @mbggenerated
     */
    public void setGameImage(String gameImage) {
        this.gameImage = gameImage == null ? null : gameImage.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gameInfo.gameName
     *
     * @return the value of gameInfo.gameName
     *
     * @mbggenerated
     */
    public String getGameName() {
        return gameName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column gameInfo.gameName
     *
     * @param gameName the value for gameInfo.gameName
     *
     * @mbggenerated
     */
    public void setGameName(String gameName) {
        this.gameName = gameName == null ? null : gameName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gameInfo.type
     *
     * @return the value of gameInfo.type
     *
     * @mbggenerated
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column gameInfo.type
     *
     * @param type the value for gameInfo.type
     *
     * @mbggenerated
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gameInfo.duration
     *
     * @return the value of gameInfo.duration
     *
     * @mbggenerated
     */
    public String getDuration() {
        return duration;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column gameInfo.duration
     *
     * @param duration the value for gameInfo.duration
     *
     * @mbggenerated
     */
    public void setDuration(String duration) {
        this.duration = duration == null ? null : duration.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gameInfo.peopleNumber
     *
     * @return the value of gameInfo.peopleNumber
     *
     * @mbggenerated
     */
    public String getPeopleNumber() {
        return peopleNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column gameInfo.peopleNumber
     *
     * @param peopleNumber the value for gameInfo.peopleNumber
     *
     * @mbggenerated
     */
    public void setPeopleNumber(String peopleNumber) {
        this.peopleNumber = peopleNumber == null ? null : peopleNumber.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gameInfo.rating
     *
     * @return the value of gameInfo.rating
     *
     * @mbggenerated
     */
    public String getRating() {
        return rating;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column gameInfo.rating
     *
     * @param rating the value for gameInfo.rating
     *
     * @mbggenerated
     */
    public void setRating(String rating) {
        this.rating = rating == null ? null : rating.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gameInfo.severe
     *
     * @return the value of gameInfo.severe
     *
     * @mbggenerated
     */
    public String getSevere() {
        return severe;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column gameInfo.severe
     *
     * @param severe the value for gameInfo.severe
     *
     * @mbggenerated
     */
    public void setSevere(String severe) {
        this.severe = severe == null ? null : severe.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gameInfo.gameType
     *
     * @return the value of gameInfo.gameType
     *
     * @mbggenerated
     */
    public String getGameType() {
        return gameType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column gameInfo.gameType
     *
     * @param gameType the value for gameInfo.gameType
     *
     * @mbggenerated
     */
    public void setGameType(String gameType) {
        this.gameType = gameType == null ? null : gameType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gameInfo.gameIntroduction
     *
     * @return the value of gameInfo.gameIntroduction
     *
     * @mbggenerated
     */
    public String getGameIntroduction() {
        return gameIntroduction;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column gameInfo.gameIntroduction
     *
     * @param gameIntroduction the value for gameInfo.gameIntroduction
     *
     * @mbggenerated
     */
    public void setGameIntroduction(String gameIntroduction) {
        this.gameIntroduction = gameIntroduction == null ? null : gameIntroduction.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gameInfo.designer
     *
     * @return the value of gameInfo.designer
     *
     * @mbggenerated
     */
    public String getDesigner() {
        return designer;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column gameInfo.designer
     *
     * @param designer the value for gameInfo.designer
     *
     * @mbggenerated
     */
    public void setDesigner(String designer) {
        this.designer = designer == null ? null : designer.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gameInfo.languageDependence
     *
     * @return the value of gameInfo.languageDependence
     *
     * @mbggenerated
     */
    public String getLanguageDependence() {
        return languageDependence;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column gameInfo.languageDependence
     *
     * @param languageDependence the value for gameInfo.languageDependence
     *
     * @mbggenerated
     */
    public void setLanguageDependence(String languageDependence) {
        this.languageDependence = languageDependence == null ? null : languageDependence.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gameInfo.artist
     *
     * @return the value of gameInfo.artist
     *
     * @mbggenerated
     */
    public String getArtist() {
        return artist;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column gameInfo.artist
     *
     * @param artist the value for gameInfo.artist
     *
     * @mbggenerated
     */
    public void setArtist(String artist) {
        this.artist = artist == null ? null : artist.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gameInfo.language
     *
     * @return the value of gameInfo.language
     *
     * @mbggenerated
     */
    public String getLanguage() {
        return language;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column gameInfo.language
     *
     * @param language the value for gameInfo.language
     *
     * @mbggenerated
     */
    public void setLanguage(String language) {
        this.language = language == null ? null : language.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gameInfo.publishingAge
     *
     * @return the value of gameInfo.publishingAge
     *
     * @mbggenerated
     */
    public String getPublishingAge() {
        return publishingAge;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column gameInfo.publishingAge
     *
     * @param publishingAge the value for gameInfo.publishingAge
     *
     * @mbggenerated
     */
    public void setPublishingAge(String publishingAge) {
        this.publishingAge = publishingAge == null ? null : publishingAge.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gameInfo.publisher
     *
     * @return the value of gameInfo.publisher
     *
     * @mbggenerated
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column gameInfo.publisher
     *
     * @param publisher the value for gameInfo.publisher
     *
     * @mbggenerated
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher == null ? null : publisher.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gameInfo.chinesePublisher
     *
     * @return the value of gameInfo.chinesePublisher
     *
     * @mbggenerated
     */
    public String getChinesePublisher() {
        return chinesePublisher;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column gameInfo.chinesePublisher
     *
     * @param chinesePublisher the value for gameInfo.chinesePublisher
     *
     * @mbggenerated
     */
    public void setChinesePublisher(String chinesePublisher) {
        this.chinesePublisher = chinesePublisher == null ? null : chinesePublisher.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gameInfo.modifiedDate
     *
     * @return the value of gameInfo.modifiedDate
     *
     * @mbggenerated
     */
    public Date getModifiedDate() {
        return modifiedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column gameInfo.modifiedDate
     *
     * @param modifiedDate the value for gameInfo.modifiedDate
     *
     * @mbggenerated
     */
    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}