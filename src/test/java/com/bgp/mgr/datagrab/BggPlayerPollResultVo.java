package com.bgp.mgr.datagrab;

public class BggPlayerPollResultVo {

    private Integer notRecommended;

    private Boolean numPlayersIsAndHigher;

    private Integer best;

    private Integer numPlayers;

    private Integer recommended;

    public Integer getNotRecommended() {
        return notRecommended;
    }

    public void setNotRecommended(Integer notRecommended) {
        this.notRecommended = notRecommended;
    }

    public Boolean getNumPlayersIsAndHigher() {
        return numPlayersIsAndHigher;
    }

    public void setNumPlayersIsAndHigher(Boolean numPlayersIsAndHigher) {
        this.numPlayersIsAndHigher = numPlayersIsAndHigher;
    }

    public Integer getBest() {
        return best;
    }

    public void setBest(Integer best) {
        this.best = best;
    }

    public Integer getNumPlayers() {
        return numPlayers;
    }

    public void setNumPlayers(Integer numPlayers) {
        this.numPlayers = numPlayers;
    }

    public Integer getRecommended() {
        return recommended;
    }

    public void setRecommended(Integer recommended) {
        this.recommended = recommended;
    }
}
