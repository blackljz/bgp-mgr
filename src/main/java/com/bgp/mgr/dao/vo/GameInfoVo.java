package com.bgp.mgr.dao.vo;

import com.bgp.mgr.dao.domain.GameInfo;
import org.apache.commons.lang.StringUtils;

public class GameInfoVo extends GameInfo {

    private int commentCount;

    private int ownerCount;

    private int recordCount;

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public int getOwnerCount() {
        return ownerCount;
    }

    public void setOwnerCount(int ownerCount) {
        this.ownerCount = ownerCount;
    }

    public int getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
    }

    public void setTypes(String[] types) {
        this.setType(StringUtils.join(types, ","));
    }

    public String[] getTypes() {
        return this.getType() != null ? this.getType().split(",") : null;
    }

    public void setLabels(String[] labels) {
        this.setLabel(StringUtils.join(labels, ","));
    }

    public String[] getLabels() {
        return this.getLabel() != null ? this.getLabel().split(",") : null;
    }

    public void setDesigners(String[] designers) {
        this.setDesigner(StringUtils.join(designers, ","));
    }

    public String[] getDesigners() {
        return this.getDesigner() != null ? this.getDesigner().split(",") : null;
    }

    public void setArtists(String[] artists) {
        this.setArtist(StringUtils.join(artists, ","));
    }

    public String[] getArtists() {
        return this.getArtist() != null ? this.getArtist().split(",") : null;
    }

    public void setPublishers(String[] publishers) {
        this.setPublisher(StringUtils.join(publishers, ","));
    }

    public String[] getPublishers() {
        return this.getPublisher() != null ? this.getPublisher().split(",") : null;
    }

    public void setMechanisms(String[] mechanisms) {
        this.setMechanism(StringUtils.join(mechanisms, ","));
    }

    public String[] getMechanisms() {
        return this.getMechanism() != null ? this.getMechanism().split(",") : null;
    }

    public void setCategorys(String[] categorys) {
        this.setCategory(StringUtils.join(categorys, ","));
    }

    public String[] getCategorys() {
        return this.getCategory() != null ? this.getCategory().split(",") : null;
    }
}
