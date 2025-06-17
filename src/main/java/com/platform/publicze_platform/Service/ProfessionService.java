package com.platform.publicze_platform.Service;

import java.util.List;
import com.platform.publicze_platform.Dao.Profession;
public interface ProfessionService{


    int deleteByPrimaryKey(Integer id);

    int insert(Profession record);

    int insertSelective(Profession record);

    Profession selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Profession record);

    int updateByPrimaryKey(Profession record);

    List<Profession> getByAll(Profession profession);

}
