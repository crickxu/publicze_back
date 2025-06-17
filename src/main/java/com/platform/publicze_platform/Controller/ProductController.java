package com.platform.publicze_platform.Controller;
import com.platform.publicze_platform.Comment.EsOprate;
import com.platform.publicze_platform.Dao.CompanyInfo;
import com.platform.publicze_platform.Dao.Product;
import com.platform.publicze_platform.Service.CompanyInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    CompanyInfoService companyInfoService;

    @Autowired
    EsOprate esOprate;

    @Value("${image.server}")
    String fastdfsServer1;
    @GetMapping("getProduct")
    public Product getProduct(Integer id)
    {
        return null;
    }
    @PostMapping("addProduct")
    public String addProduct(@RequestBody Product info)
    {
        try {
            return esOprate.save(info);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    @GetMapping("getProductsBycon")
    public List<Product> getProductsBycon(String con)
    {
        return null;
    }
}
