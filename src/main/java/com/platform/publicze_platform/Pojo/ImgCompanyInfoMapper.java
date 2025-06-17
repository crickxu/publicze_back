package com.platform.publicze_platform.Pojo;

import com.platform.publicze_platform.Dao.ImgCompanyInfo;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ImgCompanyInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ImgCompanyInfo record);

    int insertList(@Param("list") List<ImgCompanyInfo> list);

    int insertSelective(ImgCompanyInfo record);

    ImgCompanyInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ImgCompanyInfo record);

    int updateByPrimaryKey(ImgCompanyInfo record);

    List<ImgCompanyInfo> selectByCompanyNo(String companyNo);
}