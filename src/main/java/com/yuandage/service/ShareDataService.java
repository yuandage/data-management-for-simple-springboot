package com.yuandage.service;

import com.yuandage.entity.ShareData;

import java.util.Date;
import java.util.List;

public interface ShareDataService {
    List<ShareData> queryAll();

    List<ShareData> fuzzyQuery(String name, String values);

    ShareData queryDataInfo(Integer id);

    boolean addData(ShareData data);

    boolean deleDataById(Integer id);

    void updateBrowseCount(Integer id);

    boolean updateDataSummary(Integer id, String dataSummary, Date updateDate);
}
