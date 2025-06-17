package com.platform.publicze_platform.Service.ServiceImpl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.platform.publicze_platform.Dao.ImgCompanyInfo;
import com.platform.publicze_platform.Pojo.ImgCompanyInfoMapper;
import com.platform.publicze_platform.Service.ImgCompanyInfoService;import java.util.List;

@Service
public class ImgCompanyInfoServiceImpl implements ImgCompanyInfoService {

    @Resource
    private ImgCompanyInfoMapper imgCompanyInfoMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return imgCompanyInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(ImgCompanyInfo record) {
        return imgCompanyInfoMapper.insert(record);
    }

    @Override
    public int insertSelective(ImgCompanyInfo record) {
        return imgCompanyInfoMapper.insertSelective(record);
    }

    @Override
    public ImgCompanyInfo selectByPrimaryKey(Integer id) {
        return imgCompanyInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<ImgCompanyInfo> selectByCompanyNo(String companyNo)
    {
        return imgCompanyInfoMapper.selectByCompanyNo(companyNo);
    }
    @Override
    public int updateByPrimaryKeySelective(ImgCompanyInfo record) {
        return imgCompanyInfoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(ImgCompanyInfo record) {
        return imgCompanyInfoMapper.updateByPrimaryKey(record);
    }

    @Override
    public int insertList(List<ImgCompanyInfo> list) {
        return imgCompanyInfoMapper.insertList(list);
    }
}

