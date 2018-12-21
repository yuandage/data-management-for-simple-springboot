package com.yuandage.mapper;

import com.yuandage.entity.ShareData;
import org.apache.ibatis.annotations.Param;
import java.util.Date;
import java.util.List;


public interface ShareDataMapper {
     List<ShareData> queryAll();

    List<ShareData> fuzzyQuery(@Param("name") String name, @Param("values") String values);

    ShareData queryDataInfo(@Param("id") Integer id);

    boolean addData(ShareData data);

    boolean deleDataById(@Param("id") Integer id);

    void updateBrowseCount(@Param("id") Integer id);

    boolean updateDataSummary(@Param("id") Integer id, @Param("dataSummary") String dataSummary, @Param("updateDate") Date updateDate);
}
