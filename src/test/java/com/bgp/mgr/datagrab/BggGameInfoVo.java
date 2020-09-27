package com.bgp.mgr.datagrab;

import java.util.List;

public class BggGameInfoVo {

    private Long gameId;

    private String image;

    private String thumbnail;

    private List<String> designers;

    private Integer maxPlayers;

    private Integer minPlayers;

    private List<BggPlayerPollResultVo> playerPollResults;

    private Integer playingTime;

    private String description;

    private Integer yearPublished;

    private List<String> mechanics;

    private List<BggExpansionVo> expands;

    private List<String> artists;

    private Float bggRating;

    private Float averageRating;

    private String name;

    private Integer rank;

    private List<String> publishers;

    private Boolean isExpansion;

    private List<BggExpansionVo> expansions;

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public List<String> getDesigners() {
        return designers;
    }

    public void setDesigners(List<String> designers) {
        this.designers = designers;
    }

    public Integer getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(Integer maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public Integer getMinPlayers() {
        return minPlayers;
    }

    public void setMinPlayers(Integer minPlayers) {
        this.minPlayers = minPlayers;
    }

    public List<BggPlayerPollResultVo> getPlayerPollResults() {
        return playerPollResults;
    }

    public void setPlayerPollResults(List<BggPlayerPollResultVo> playerPollResults) {
        this.playerPollResults = playerPollResults;
    }

    public Integer getPlayingTime() {
        return playingTime;
    }

    public void setPlayingTime(Integer playingTime) {
        this.playingTime = playingTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getYearPublished() {
        return yearPublished;
    }

    public void setYearPublished(Integer yearPublished) {
        this.yearPublished = yearPublished;
    }

    public List<String> getMechanics() {
        return mechanics;
    }

    public void setMechanics(List<String> mechanics) {
        this.mechanics = mechanics;
    }

    public List<BggExpansionVo> getExpands() {
        return expands;
    }

    public void setExpands(List<BggExpansionVo> expands) {
        this.expands = expands;
    }

    public List<String> getArtists() {
        return artists;
    }

    public void setArtists(List<String> artists) {
        this.artists = artists;
    }

    public Float getBggRating() {
        return bggRating;
    }

    public void setBggRating(Float bggRating) {
        this.bggRating = bggRating;
    }

    public Float getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Float averageRating) {
        this.averageRating = averageRating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public List<String> getPublishers() {
        return publishers;
    }

    public void setPublishers(List<String> publishers) {
        this.publishers = publishers;
    }

    public Boolean getExpansion() {
        return isExpansion;
    }

    public void setExpansion(Boolean expansion) {
        isExpansion = expansion;
    }

    public List<BggExpansionVo> getExpansions() {
        return expansions;
    }

    public void setExpansions(List<BggExpansionVo> expansions) {
        this.expansions = expansions;
    }
}
