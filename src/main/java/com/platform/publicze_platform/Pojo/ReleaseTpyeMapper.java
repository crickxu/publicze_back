package com.platform.publicze_platform.Pojo;

import com.platform.publicze_platform.Dao.ReleaseTpye;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReleaseTpyeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ReleaseTpye record);

    int insertSelective(ReleaseTpye record);

    ReleaseTpye selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ReleaseTpye record);

    int updateByPrimaryKey(ReleaseTpye record);

    List<ReleaseTpye> getByAll(ReleaseTpye releaseTpye);
}