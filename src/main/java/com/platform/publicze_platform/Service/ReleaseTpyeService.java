package com.platform.publicze_platform.Service;

import com.platform.publicze_platform.Dao.ReleaseTpye;import java.util.List;

public interface ReleaseTpyeService {


    int deleteByPrimaryKey(Integer id);

    int insert(ReleaseTpye record);

    int insertSelective(ReleaseTpye record);

    ReleaseTpye selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ReleaseTpye record);

    int updateByPrimaryKey(ReleaseTpye record);

    List<ReleaseTpye> getByAll(ReleaseTpye releaseTpye);
}

