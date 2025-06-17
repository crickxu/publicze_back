package com.platform.publicze_platform.Pojo;

import com.platform.publicze_platform.Dao.Region;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RegionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Region record);

    int insertSelective(Region record);

    Region selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Region record);

    int updateByPrimaryKey(Region record);

    List<Region> getAllByParenRegionNo(@Param("parenRegionNo") String parenRegionNo);

    List<Region> getByAll(Region region);
}