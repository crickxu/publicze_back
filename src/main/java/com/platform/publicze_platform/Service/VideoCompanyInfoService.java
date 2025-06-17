package com.platform.publicze_platform.Service;

import com.platform.publicze_platform.Dao.VideoCompanyInfo;import java.util.List;

public interface VideoCompanyInfoService {


    int deleteByPrimaryKey(Integer id);

    int insert(VideoCompanyInfo record);

    int insertSelective(VideoCompanyInfo record);

    VideoCompanyInfo selectByPrimaryKey(Integer id);

    List<VideoCompanyInfo> selectByCompanyNo(String companyNo);

    int updateByPrimaryKeySelective(VideoCompanyInfo record);

    int updateByPrimaryKey(VideoCompanyInfo record);

    int insertList(List<VideoCompanyInfo> list);
}


