package com.platform.publicze_platform.Service.ServiceImpl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.platform.publicze_platform.Pojo.RegionMapper;
import com.platform.publicze_platform.Dao.Region;
import com.platform.publicze_platform.Service.RegionService;

@Service
public class RegionServiceImpl implements RegionService {

    @Resource
    private RegionMapper regionMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return regionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Region record) {
        return regionMapper.insert(record);
    }

    @Override
    public int insertSelective(Region record) {
        return regionMapper.insertSelective(record);
    }

    @Override
    public Region selectByPrimaryKey(Integer id) {
        return regionMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Region record) {
        return regionMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Region record) {
        return regionMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Region> getAllByParenRegionNo(String parenRegionNo) {
        return regionMapper.getAllByParenRegionNo(parenRegionNo);
    }

    @Override
    public List<Region> getByAll(Region region) {
        List<Region> regionList = regionMapper.getByAll(region);
        for(Region re:regionList)
        {
            re.children = regionList.stream().filter(x->x.parenRegionNo.equals(re.regionNo)).collect(Collectors.toList());
        }
        List<Region> regions = null;
        if(regionList!=null)
        {
            regions = regionList.stream().filter(x->x.parenRegionNo.equals("0000")).collect(Collectors.toList());
        }
        return regions;
    }
}

