package com.platform.publicze_platform.Service.ServiceImpl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.platform.publicze_platform.Dao.ReleaseTpye;
import com.platform.publicze_platform.Pojo.ReleaseTpyeMapper;
import com.platform.publicze_platform.Service.ReleaseTpyeService;import java.util.List;

@Service
public class ReleaseTpyeServiceImpl implements ReleaseTpyeService {

    @Resource
    private ReleaseTpyeMapper releaseTpyeMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return releaseTpyeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(ReleaseTpye record) {
        return releaseTpyeMapper.insert(record);
    }

    @Override
    public int insertSelective(ReleaseTpye record) {
        return releaseTpyeMapper.insertSelective(record);
    }

    @Override
    public ReleaseTpye selectByPrimaryKey(Integer id) {
        return releaseTpyeMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(ReleaseTpye record) {
        return releaseTpyeMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(ReleaseTpye record) {
        return releaseTpyeMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<ReleaseTpye> getByAll(ReleaseTpye releaseTpye) {
        return releaseTpyeMapper.getByAll(releaseTpye);
    }
}

