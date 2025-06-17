package com.platform.publicze_platform.Service;

import com.platform.publicze_platform.Dao.ImgCompanyInfo;import java.util.List;

public interface ImgCompanyInfoService {


    int deleteByPrimaryKey(Integer id);

    int insert(ImgCompanyInfo record);

    int insertSelective(ImgCompanyInfo record);

    ImgCompanyInfo selectByPrimaryKey(Integer id);
    List<ImgCompanyInfo> selectByCompanyNo(String companyNo);

    int updateByPrimaryKeySelective(ImgCompanyInfo record);

    int updateByPrimaryKey(ImgCompanyInfo record);

    int insertList(List<ImgCompanyInfo> list);
}

