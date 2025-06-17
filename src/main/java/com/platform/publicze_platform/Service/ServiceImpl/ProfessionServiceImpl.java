package com.platform.publicze_platform.Service.ServiceImpl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

import com.platform.publicze_platform.Pojo.ProfessionMapper;
import com.platform.publicze_platform.Dao.Profession;
import com.platform.publicze_platform.Service.ProfessionService;
@Service
public class ProfessionServiceImpl implements ProfessionService{

    @Resource
    private ProfessionMapper professionMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return professionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Profession record) {
        return professionMapper.insert(record);
    }

    @Override
    public int insertSelective(Profession record) {
        return professionMapper.insertSelective(record);
    }

    @Override
    public Profession selectByPrimaryKey(Integer id) {
        return professionMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Profession record) {
        return professionMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Profession record) {
        return professionMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Profession> getByAll(Profession profession)
    {
        List<Profession> professionList = professionMapper.getByAll(profession);
        for (Profession pro:professionList)
        {
            pro.children = professionList.stream().filter(x->x.professionParentNo.equals(pro.professionNo)).collect(Collectors.toList());
        }
        List<Profession> pros=null;
        if(professionList!=null)
        {
            pros = professionList.stream().filter(x->x.professionParentNo.equals("00000")).collect(Collectors.toList());
        }
        return pros;
    }

}
