package com.bgp.mgr.dao.domain;

import java.util.Date;

public class RecordInfo {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column recordInfo.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column recordInfo.gameId
     *
     * @mbggenerated
     */
    private Long gameId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column recordInfo.userId
     *
     * @mbggenerated
     */
    private Long userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column recordInfo.gameName
     *
     * @mbggenerated
     */
    private String gameName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column recordInfo.gameTeam
     *
     * @mbggenerated
     */
    private String gameTeam;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column recordInfo.role
     *
     * @mbggenerated
     */
    private String role;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column recordInfo.position
     *
     * @mbggenerated
     */
    private String position;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column recordInfo.duration
     *
     * @mbggenerated
     */
    private String duration;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column recordInfo.content
     *
     * @mbggenerated
     */
    private String content;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column recordInfo.result
     *
     * @mbggenerated
     */
    private String result;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column recordInfo.ranking
     *
     * @mbggenerated
     */
    private String ranking;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column recordInfo.score
     *
     * @mbggenerated
     */
    private String score;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column recordInfo.type
     *
     * @mbggenerated
     */
    private String type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column recordInfo.gameWin
     *
     * @mbggenerated
     */
    private String gameWin;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column recordInfo.gameType
     *
     * @mbggenerated
     */
    private String gameType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column recordInfo.createdDate
     *
     * @mbggenerated
     */
    private Date createdDate;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column recordInfo.id
     *
     * @return the value of recordInfo.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column recordInfo.id
     *
     * @param id the value for recordInfo.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column recordInfo.gameId
     *
     * @return the value of recordInfo.gameId
     *
     * @mbggenerated
     */
    public Long getGameId() {
        return gameId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column recordInfo.gameId
     *
     * @param gameId the value for recordInfo.gameId
     *
     * @mbggenerated
     */
    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column recordInfo.userId
     *
     * @return the value of recordInfo.userId
     *
     * @mbggenerated
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column recordInfo.userId
     *
     * @param userId the value for recordInfo.userId
     *
     * @mbggenerated
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column recordInfo.gameName
     *
     * @return the value of recordInfo.gameName
     *
     * @mbggenerated
     */
    public String getGameName() {
        return gameName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column recordInfo.gameName
     *
     * @param gameName the value for recordInfo.gameName
     *
     * @mbggenerated
     */
    public void setGameName(String gameName) {
        this.gameName = gameName == null ? null : gameName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column recordInfo.gameTeam
     *
     * @return the value of recordInfo.gameTeam
     *
     * @mbggenerated
     */
    public String getGameTeam() {
        return gameTeam;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column recordInfo.gameTeam
     *
     * @param gameTeam the value for recordInfo.gameTeam
     *
     * @mbggenerated
     */
    public void setGameTeam(String gameTeam) {
        this.gameTeam = gameTeam == null ? null : gameTeam.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column recordInfo.role
     *
     * @return the value of recordInfo.role
     *
     * @mbggenerated
     */
    public String getRole() {
        return role;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column recordInfo.role
     *
     * @param role the value for recordInfo.role
     *
     * @mbggenerated
     */
    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column recordInfo.position
     *
     * @return the value of recordInfo.position
     *
     * @mbggenerated
     */
    public String getPosition() {
        return position;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column recordInfo.position
     *
     * @param position the value for recordInfo.position
     *
     * @mbggenerated
     */
    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column recordInfo.duration
     *
     * @return the value of recordInfo.duration
     *
     * @mbggenerated
     */
    public String getDuration() {
        return duration;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column recordInfo.duration
     *
     * @param duration the value for recordInfo.duration
     *
     * @mbggenerated
     */
    public void setDuration(String duration) {
        this.duration = duration == null ? null : duration.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column recordInfo.content
     *
     * @return the value of recordInfo.content
     *
     * @mbggenerated
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column recordInfo.content
     *
     * @param content the value for recordInfo.content
     *
     * @mbggenerated
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column recordInfo.result
     *
     * @return the value of recordInfo.result
     *
     * @mbggenerated
     */
    public String getResult() {
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column recordInfo.result
     *
     * @param result the value for recordInfo.result
     *
     * @mbggenerated
     */
    public void setResult(String result) {
        this.result = result == null ? null : result.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column recordInfo.ranking
     *
     * @return the value of recordInfo.ranking
     *
     * @mbggenerated
     */
    public String getRanking() {
        return ranking;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column recordInfo.ranking
     *
     * @param ranking the value for recordInfo.ranking
     *
     * @mbggenerated
     */
    public void setRanking(String ranking) {
        this.ranking = ranking == null ? null : ranking.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column recordInfo.score
     *
     * @return the value of recordInfo.score
     *
     * @mbggenerated
     */
    public String getScore() {
        return score;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column recordInfo.score
     *
     * @param score the value for recordInfo.score
     *
     * @mbggenerated
     */
    public void setScore(String score) {
        this.score = score == null ? null : score.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column recordInfo.type
     *
     * @return the value of recordInfo.type
     *
     * @mbggenerated
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column recordInfo.type
     *
     * @param type the value for recordInfo.type
     *
     * @mbggenerated
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column recordInfo.gameWin
     *
     * @return the value of recordInfo.gameWin
     *
     * @mbggenerated
     */
    public String getGameWin() {
        return gameWin;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column recordInfo.gameWin
     *
     * @param gameWin the value for recordInfo.gameWin
     *
     * @mbggenerated
     */
    public void setGameWin(String gameWin) {
        this.gameWin = gameWin == null ? null : gameWin.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column recordInfo.gameType
     *
     * @return the value of recordInfo.gameType
     *
     * @mbggenerated
     */
    public String getGameType() {
        return gameType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column recordInfo.gameType
     *
     * @param gameType the value for recordInfo.gameType
     *
     * @mbggenerated
     */
    public void setGameType(String gameType) {
        this.gameType = gameType == null ? null : gameType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column recordInfo.createdDate
     *
     * @return the value of recordInfo.createdDate
     *
     * @mbggenerated
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column recordInfo.createdDate
     *
     * @param createdDate the value for recordInfo.createdDate
     *
     * @mbggenerated
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}