package com.platform.publicze_platform.Controller;

import com.platform.publicze_platform.Dao.ReleaseTpye;
import com.platform.publicze_platform.Service.ReleaseTpyeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/releaseTpye")
public class ReleaseTypeContrller {
    @Autowired
    ReleaseTpyeService releaseTpyeService;
    @GetMapping("getAll")
    public List<ReleaseTpye> getAllReleaseTpye()
    {
        return releaseTpyeService.getByAll(null);
    }

}
