package com.platform.publicze_platform.Service;

import java.util.List;
import com.platform.publicze_platform.Dao.Region;

public interface RegionService {


    int deleteByPrimaryKey(Integer id);

    int insert(Region record);

    int insertSelective(Region record);

    Region selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Region record);

    int updateByPrimaryKey(Region record);

    List<Region> getAllByParenRegionNo(String parenRegionNo);

    List<Region> getByAll(Region region);
}

