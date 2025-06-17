package com.platform.publicze_platform.Controller;
import com.platform.publicze_platform.Dao.CompanyInfo;
import com.platform.publicze_platform.Dao.User;
import com.platform.publicze_platform.Service.CompanyInfoService;
import com.platform.publicze_platform.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    CompanyInfoService companyInfoService;
    @Value("${image.server}")
    String fastdfsServer1;
    @GetMapping("getCompany")
    public CompanyInfo getCompany(Integer id)
    {
        CompanyInfo companyInfo= companyInfoService.selectByPrimaryKey(id);
        return companyInfo;
    }
    @GetMapping("getCompanys")
    public List<CompanyInfo> getCompanys()
    {
        CompanyInfo companyInfo = new CompanyInfo();
        List<CompanyInfo> companyInfos= companyInfoService.selectCompanys(companyInfo);
        if(companyInfos!=null)
            companyInfos.forEach(x->{
                x.provinceAddrName=x.provinceAddrName+x.cityAddrName;
                x.imgPath = fastdfsServer1+x.imgPath;
            });
        return companyInfos;
    }
    @GetMapping("getCompanysByCon")
    public List<CompanyInfo> getCompanysByCon(String provinceAddr,String cityAddr,String industry,String subIndustry)
    {
        CompanyInfo companyInfo = new CompanyInfo();
        if(provinceAddr !=null)
            companyInfo.provinceAddr = provinceAddr;
        if(cityAddr !=null)
            companyInfo.cityAddr = cityAddr;
        if(industry!=null)
            companyInfo.industry =industry;
        if(subIndustry!=null)
            companyInfo.subIndustry = subIndustry;
        List<CompanyInfo> companyInfos= companyInfoService.selectCompanys(companyInfo);
        if(companyInfos!=null)
            companyInfos.forEach(x->{
                x.provinceAddrName=x.provinceAddrName+x.cityAddrName;
                x.imgPath = fastdfsServer1+x.imgPath;
            });
        return companyInfos;
    }
    @PostMapping("addCompany")
    public String addCompany(@RequestBody CompanyInfo info)
    {
        try {
            return companyInfoService.insert(info);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    @GetMapping("getCompanyByNo")
    public CompanyInfo getCompanyByNo(String companyNo)
    {
        CompanyInfo companyInfo = companyInfoService.selectByCompanyInfo(companyNo);
        companyInfo.imgPath = fastdfsServer1+companyInfo.imgPath;
        return companyInfo;
    }
}
