package com.platform.publicze_platform.Service.ServiceImpl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.platform.publicze_platform.Dao.VideoCompanyInfo;
import com.platform.publicze_platform.Pojo.VideoCompanyInfoMapper;
import com.platform.publicze_platform.Service.VideoCompanyInfoService;import java.util.List;

@Service
public class VideoCompanyInfoServiceImpl implements VideoCompanyInfoService {

    @Resource
    private VideoCompanyInfoMapper videoCompanyInfoMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return videoCompanyInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(VideoCompanyInfo record) {
        return videoCompanyInfoMapper.insert(record);
    }

    @Override
    public int insertSelective(VideoCompanyInfo record) {
        return videoCompanyInfoMapper.insertSelective(record);
    }

    @Override
    public VideoCompanyInfo selectByPrimaryKey(Integer id) {
        return videoCompanyInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<VideoCompanyInfo> selectByCompanyNo(String companyNo)
    {
        return videoCompanyInfoMapper.selectByCompanyNo(companyNo);
    }
    @Override
    public int updateByPrimaryKeySelective(VideoCompanyInfo record) {
        return videoCompanyInfoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(VideoCompanyInfo record) {
        return videoCompanyInfoMapper.updateByPrimaryKey(record);
    }

    @Override
    public int insertList(List<VideoCompanyInfo> list) {
        return videoCompanyInfoMapper.insertList(list);
    }
}


