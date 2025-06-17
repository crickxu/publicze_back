package com.platform.publicze_platform.Controller;

import com.platform.publicze_platform.Dao.Profession;
import com.platform.publicze_platform.Service.ProfessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/profession")
public class ProfessionController {
    @Autowired
    ProfessionService professionService;
    @GetMapping("getProfession")
    public List<Profession> getProfession()
    {
        List<Profession> professions = professionService.getByAll(new Profession());
        return professions;
    }
}
