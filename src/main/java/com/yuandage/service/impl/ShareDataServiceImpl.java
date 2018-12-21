package com.yuandage.service.impl;

import com.yuandage.mapper.ShareDataMapper;
import com.yuandage.entity.ShareData;
import com.yuandage.service.ShareDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ShareDataServiceImpl implements ShareDataService {

    @Autowired
    private ShareDataMapper shareDataMapper;

    @Override
    public List<ShareData> queryAll() {
        return shareDataMapper.queryAll();
    }

    @Override
    public List<ShareData> fuzzyQuery(String name,String values) {
        return shareDataMapper.fuzzyQuery(name,values);
    }

    @Override
    public   ShareData queryDataInfo(Integer id) {
        return shareDataMapper.queryDataInfo(id);
    }

    @Override
    public boolean addData(ShareData data) {
        return shareDataMapper.addData(data);
    }

    @Override
    public boolean deleDataById(Integer id) {
        return shareDataMapper.deleDataById(id);
    }

    @Override
    public void updateBrowseCount(Integer id) {
        shareDataMapper.updateBrowseCount(id);
    }

    @Override
    public boolean updateDataSummary(Integer id, String dataSummary, Date updateDate) {
        return shareDataMapper.updateDataSummary(id,dataSummary,updateDate);
    }
}
