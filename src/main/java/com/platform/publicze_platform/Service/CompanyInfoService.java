package com.platform.publicze_platform.Service;

import com.platform.publicze_platform.Dao.CompanyInfo;

import java.util.List;

public interface CompanyInfoService {


    int deleteByPrimaryKey(Integer id);

    String insert(CompanyInfo record);

    int insertSelective(CompanyInfo record);

    CompanyInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CompanyInfo record);

    int updateByPrimaryKey(CompanyInfo record);
    int updateImgPathByCompanyNo(String companyNo,String imgPath);
    CompanyInfo selectByCompanyInfo(String companyNo);
    List<CompanyInfo> selectCompanys(CompanyInfo info);
}

