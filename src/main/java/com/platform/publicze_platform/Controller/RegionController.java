package com.platform.publicze_platform.Controller;

import com.platform.publicze_platform.Dao.Region;
import com.platform.publicze_platform.Service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/region")
public class RegionController {
    @Autowired
    RegionService regionService;
    @GetMapping("getParent")
    public List<Region> getParentRegions()
    {
        List<Region> regions = regionService.getAllByParenRegionNo("0000");
        return regions;
    }
    @GetMapping("getRegions")
    public List<Region> getRegions()
    {
        List<Region> regions = regionService.getByAll(new Region());
        return regions;
    }
}
