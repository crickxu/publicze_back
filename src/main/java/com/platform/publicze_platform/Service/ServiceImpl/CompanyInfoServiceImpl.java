package com.platform.publicze_platform.Service.ServiceImpl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.platform.publicze_platform.Dao.CompanyInfo;
import com.platform.publicze_platform.Pojo.CompanyInfoMapper;
import com.platform.publicze_platform.Service.CompanyInfoService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class CompanyInfoServiceImpl implements CompanyInfoService {

    @Resource
    private CompanyInfoMapper companyInfoMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return companyInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public String insert(CompanyInfo record) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String timestamp = "CP" + sdf.format(new Date());
            record.companyNo = timestamp;
            record.createTime = new Date();
            int count = companyInfoMapper.insert(record);
            if (count > 0)
                return record.companyNo;
            return null;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int insertSelective(CompanyInfo record) {
        return companyInfoMapper.insertSelective(record);
    }

    @Override
    public CompanyInfo selectByPrimaryKey(Integer id) {
        return companyInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(CompanyInfo record) {
        return companyInfoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CompanyInfo record) {
        return companyInfoMapper.updateByPrimaryKey(record);
    }
    @Override
    public CompanyInfo selectByCompanyInfo(String companyNo)
    {
        return companyInfoMapper.selectByCompanyInfo(companyNo);
    }
    @Override
    public int updateImgPathByCompanyNo(String companyNo,String imgPath)
    {
        return companyInfoMapper.updateImgPathByCompanyNo(companyNo,imgPath);
    }
    @Override
    public List<CompanyInfo> selectCompanys(CompanyInfo info)
    {
        return companyInfoMapper.selectCompanys(info);
    }
}

