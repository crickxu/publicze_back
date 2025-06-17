package com.platform.publicze_platform.Controller;
import com.alibaba.fastjson.JSON;
import com.platform.publicze_platform.Comment.EsOprate;
import com.platform.publicze_platform.Dao.ImgCompanyInfo;
import com.platform.publicze_platform.Dao.Product;
import com.platform.publicze_platform.Dao.VideoCompanyInfo;
import com.platform.publicze_platform.Service.*;
import com.platform.publicze_platform.Vto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/file")
public class FileOprateController {
    @Autowired
    ImgCompanyInfoService imgCompanyInfoService;
    @Autowired
    VideoCompanyInfoService videoCompanyInfoService;
    @Autowired
    EsOprate esOprate;
    @Autowired
    CompanyInfoService companyInfoService;
    @Autowired
    MinioService minioService;
    @Value("${image.server}")
    String imageServer;
    @Value("${minio.imageBucket}")
    String imagebucket;
    @Value("${minio.videoBucket}")
    String videobucket;
    @PostMapping("upload")
    public void upload(@RequestParam("uploadimg") List<MultipartFile> imgs ,
                       @RequestParam("uploadvideo") List<MultipartFile> videos ,
                       @RequestPart(value = "imgspro") List<imgPropoty> imgspro,
                       @RequestPart(value = "videospro") List<videoPropoty> videospro,
                       @RequestParam("companyNo") String companyNo)
    {
        List<ImgCompanyInfo> imgPros = new ArrayList<>();
        List<VideoCompanyInfo> videoPros = new ArrayList<>();
        long timestamp = Instant.now().toEpochMilli();
        try {
            //标志是否是上传产品图片
            boolean flag = true;
            List<String> imgs_path = new ArrayList<>();
            for (int i = 0; i < imgs.size(); i++) {
                String fileName = imgs.get(i).getOriginalFilename();
                String oldFileName = fileName.substring(0);
                if (fileName.equals("uploadimg"))
                    continue;
                //byte[] bytes =imgs.get(i).getBytes();
                imgPropoty pro = imgspro.stream().filter(x -> x.name.equals(oldFileName)).findFirst().get();
                String[] splits = fileName.split("\\.");
                String name= splits[0];
                String ext =splits[1];

                Base64.Encoder urlEncoder = Base64.getUrlEncoder(); // 使用URL安全的Base64编码器
                String encodedString = urlEncoder.encodeToString(name.getBytes(StandardCharsets.UTF_8));
                fileName = encodedString+':'+String.valueOf(timestamp)+'.'+ext;

                minioService.uploadFile(imgs.get(i), imagebucket, fileName);
                ImgCompanyInfo imgPro = new ImgCompanyInfo();
                imgPro.companyNo = companyNo;
                imgPro.imgPath = imagebucket+'/'+fileName;
                imgPro.imgTitle = pro.title;
                imgPro.discription = pro.discrip;
                imgPro.enable = true;
                imgPro.isMainImg = pro.isMainImg == 1 ? true : false;
                imgPro.createTime = new Date();
                imgPros.add(imgPro);
                //更改公司主图片
                if (imgPro.isMainImg) {
                    flag =false;
                    companyInfoService.updateImgPathByCompanyNo(companyNo, imgPro.imgPath);
                }
                //添加产品图片路径
                else
                    imgs_path.add(imgPro.imgPath);
            }
            if(flag)
                esOprate.modifyImgPath(companyNo,imgs_path);
            for (int j = 0; j < videos.size(); j++) {
                String fileName = videos.get(j).getOriginalFilename();
                String oldFileName = fileName.substring(0);
                if (fileName.equals("uploadvideo"))
                    continue;
                //byte[] bytes =videos.get(j).getBytes();
                String[] splits = fileName.split("\\.");
                String name= splits[0];
                String ext =splits[1];

                Base64.Encoder urlEncoder = Base64.getUrlEncoder(); // 使用URL安全的Base64编码器
                String encodedString = urlEncoder.encodeToString(name.getBytes(StandardCharsets.UTF_8));
                fileName = encodedString+':'+String.valueOf(timestamp)+'.'+ext;
                minioService.uploadFile(imgs.get(j), videobucket, fileName);
                videoPropoty pro = videospro.stream().filter(x -> x.name.equals(oldFileName)).findFirst().get();
                VideoCompanyInfo videoPro = new VideoCompanyInfo();
                videoPro.companyNo = companyNo;
                videoPro.videoPath =videobucket+'/'+ fileName;
                videoPro.videoTitle = pro.title;
                videoPro.discription = pro.discrip;
                videoPro.createTime = new Date();
                videoPros.add(videoPro);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        /*
        try
        {
            if(imgPros.size()>0) {
                int imgcount = imgCompanyInfoService.insertList(imgPros);
            }
            if(videoPros.size() > 0) {
                int videocount = videoCompanyInfoService.insertList(videoPros);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }*/
    }
    @GetMapping("filesInfo")
    public List<filePropoty> filesInfo(String companyNo)
    {
        try
        {
            List<Product> products = esOprate.searchByNos(companyNo);
            //List<ImgCompanyInfo> imgCompanyInfos = imgCompanyInfoService.selectByCompanyNo(companyNo);
            //List<VideoCompanyInfo> videoCompanyInfos = videoCompanyInfoService.selectByCompanyNo(companyNo);
            List<filePropoty> filePropoties = new ArrayList<>();
            if(products!=null)
                for(Product pro : products)
                {
                    filePropoty filePro = new filePropoty();
                    filePro.id = pro.productNo;
                    filePro.title = pro.productName;
                    filePro.fileType =0;
                    filePro.companyNo = pro.companyNo;
                    filePro.discrip = pro.productDes;
                    filePro.isMainImg = false;
                    filePro.proparams = JSON.parseArray(String.valueOf(pro.proParam)).toJavaList(proParam.class);
                    if(pro.imgSrc!=null)
                    {
                        List<String> paths = new ArrayList<>();
                        for (String img : pro.imgSrc) {
                            String imgpath = imageServer + img;
                            paths.add(imgpath);
                        }
                        filePro.filePaths =paths;
                        //pro.imgSrc.stream().forEach(m->{
                        //    m= imageServer+m;
                        //});
                        //pro.imgSrc.stream().map(m->"imageServer"+m).collect(Collectors.toList());
                    }

                    filePropoties.add(filePro);
                }
            /*
                products.forEach(x->{
                    filePropoty filePro = new filePropoty();
                    filePro.id = x.productNo;
                    filePro.title = x.productName;
                    filePro.fileType =0;
                    filePro.companyNo = x.companyNo;
                    filePro.discrip = x.productDes;
                    filePro.isMainImg = false;
                    filePro.proparams = JSON.parseArray(String.valueOf(x.proParam)).toJavaList(proParam.class);
                    x.imgSrc.stream().map(m->imageServer+m);
                    filePro.filePaths =x.imgSrc;
                    filePropoties.add(filePro);
                });*/
            /* 视频文件管理
            if(videoCompanyInfos!=null)
            {
                videoCompanyInfos.forEach(m->{
                    filePropoty filePro = new filePropoty();
                    filePro.id = m.id.toString();
                    filePro.fileType =1;
                    filePro.companyNo = m.companyNo;
                    filePro.title = m.videoTitle;
                    filePro.discrip = m.discription;
                    filePro.isMainImg = false;
                    filePro.groupName = m.groupName;
                    filePro.filePath =imageServer+m.videoPath;
                    filePropoties.add(filePro);
                });
            }*/
            return filePropoties;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
