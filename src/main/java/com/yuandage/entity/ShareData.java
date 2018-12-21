package com.yuandage.entity;

import java.util.Date;

public class ShareData {
    private Integer id;
    private String groupName;
    private String dataName;
    private String ip;
    private String upName;
    private String dataSummary;
    private Date createDate;
    private Date updateDate;
    private Integer browseCount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUpName() {
        return upName;
    }

    public void setUpName(String upName) {
        this.upName = upName;
    }

    public String getDataSummary() {
        return dataSummary;
    }

    public void setDataSummary(String dataSummary) {
        this.dataSummary = dataSummary;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getBrowseCount() {
        return browseCount;
    }

    public void setBrowseCount(Integer browseCount) {
        this.browseCount = browseCount;
    }

    @Override
    public String toString() {
        return "ShareData{" +
                "id=" + id +
                ", groupName='" + groupName + '\'' +
                ", dataName='" + dataName + '\'' +
                ", ip='" + ip + '\'' +
                ", upName='" + upName + '\'' +
                ", dataSummary='" + dataSummary + '\'' +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ", browseCount=" + browseCount +
                '}';
    }
}
