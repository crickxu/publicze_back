package com.platform.publicze_platform.Pojo;

import com.platform.publicze_platform.Dao.VideoCompanyInfo;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface VideoCompanyInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VideoCompanyInfo record);

    int insertList(@Param("list") List<VideoCompanyInfo> list);

    int insertSelective(VideoCompanyInfo record);

    VideoCompanyInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VideoCompanyInfo record);

    int updateByPrimaryKey(VideoCompanyInfo record);

    List<VideoCompanyInfo> selectByCompanyNo(String companyNo);
}