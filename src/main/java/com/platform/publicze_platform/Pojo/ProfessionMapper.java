package com.platform.publicze_platform.Pojo;

import com.platform.publicze_platform.Dao.Profession;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProfessionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Profession record);

    int insertSelective(Profession record);

    Profession selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Profession record);

    int updateByPrimaryKey(Profession record);

    List<Profession> getByAll(Profession profession);
}