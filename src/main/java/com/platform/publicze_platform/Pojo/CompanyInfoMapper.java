package com.platform.publicze_platform.Pojo;

import com.platform.publicze_platform.Dao.CompanyInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CompanyInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CompanyInfo record);

    int insertSelective(CompanyInfo record);

    CompanyInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CompanyInfo record);

    int updateByPrimaryKey(CompanyInfo record);
    int updateImgPathByCompanyNo(@Param("companyNo") String companyNo,@Param("imgPath") String imgPath);
    CompanyInfo selectByCompanyInfo(String companyNo);
    List<CompanyInfo> selectCompanys(CompanyInfo info);
}