package com.platform.publicze_platform.Controller;
import com.platform.publicze_platform.Dao.ImgCompanyInfo;
import com.platform.publicze_platform.Dao.VideoCompanyInfo;
import com.platform.publicze_platform.Service.CompanyInfoService;
import com.platform.publicze_platform.Service.FastDSFClient;
import com.platform.publicze_platform.Service.ImgCompanyInfoService;
import com.platform.publicze_platform.Service.VideoCompanyInfoService;
import com.platform.publicze_platform.Vto.StreamFileInfo;
import com.platform.publicze_platform.Vto.filePropoty;
import com.platform.publicze_platform.Vto.imgPropoty;
import com.platform.publicze_platform.Vto.videoPropoty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/file1")
public class FileOprateController1 {
    @Autowired
    ImgCompanyInfoService imgCompanyInfoService;
    @Autowired
    VideoCompanyInfoService videoCompanyInfoService;
    @Autowired
    FastDSFClient fastDSFClient;
    @Autowired
    CompanyInfoService companyInfoService;
    @Value("${fastdfs.server1}")
    String fastdfsServer1;
    @PostMapping("upload")
    public void upload(@RequestParam("uploadimg") List<MultipartFile> imgs ,
                       @RequestParam("uploadvideo") List<MultipartFile> videos ,
                       @RequestPart(value = "imgspro") List<imgPropoty> imgspro,
                       @RequestPart(value = "videospro") List<videoPropoty> videospro,
                       @RequestParam("companyNo") String companyNo)
    {
        List<ImgCompanyInfo> imgPros = new ArrayList<>();
        for(int i=0;i<imgs.size();i++)
        {
            String fileName = imgs.get(i).getOriginalFilename();
            if(fileName.equals("uploadimg"))
                continue;
            try {
                byte[] bytes =imgs.get(i).getBytes();
                String[] infos= fastDSFClient.upload(bytes,fileName,"crick");
                imgPropoty pro = imgspro.stream().filter(x->x.name.equals(fileName)).findFirst().get();
                ImgCompanyInfo imgPro = new ImgCompanyInfo();
                imgPro.companyNo = companyNo;
                imgPro.groupName = infos[0];
                imgPro.imgPath = infos[1];
                imgPro.imgTitle = pro.title;
                imgPro.discription = pro.discrip;
                imgPro.enable =true;
                imgPro.isMainImg = pro.isMainImg==1?true:false;
                imgPro.createTime = new Date();
                imgPros.add(imgPro);
                //更改公司主图片
                if(imgPro.isMainImg)
                {
                    companyInfoService.updateImgPathByCompanyNo(companyNo,imgPro.groupName+"/"+imgPro.imgPath);
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        List<VideoCompanyInfo> videoPros = new ArrayList<>();
        for(int j=0;j<videos.size();j++)
        {
            try {
                String fileName = videos.get(j).getOriginalFilename();
                if(fileName.equals("uploadvideo"))
                    continue;
                byte[] bytes =videos.get(j).getBytes();

                String[] infos= fastDSFClient.upload(bytes,fileName,"crick");
                videoPropoty pro = videospro.stream().filter(x->x.name.equals(fileName)).findFirst().get();
                VideoCompanyInfo videoPro = new VideoCompanyInfo();
                videoPro.companyNo = companyNo;
                videoPro.groupName = infos[0];
                videoPro.videoPath = infos[1];
                videoPro.videoTitle =pro.title;
                videoPro.discription = pro.discrip;
                videoPro.createTime = new Date();
                videoPros.add(videoPro);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
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
        }
    }
    @GetMapping("filesInfo")
    public List<filePropoty> filesInfo(String companyNo)
    {
        try
        {
            List<ImgCompanyInfo> imgCompanyInfos = imgCompanyInfoService.selectByCompanyNo(companyNo);
            List<VideoCompanyInfo> videoCompanyInfos = videoCompanyInfoService.selectByCompanyNo(companyNo);
            List<filePropoty> filePropoties = new ArrayList<>();
            if(imgCompanyInfos!=null)
                imgCompanyInfos.forEach(x->{
                    filePropoty filePro = new filePropoty();
                    filePro.id = x.id.toString();
                    filePro.fileType =0;
                    filePro.companyNo = x.companyNo;
                    filePro.title = x.imgTitle;
                    filePro.discrip = x.discription;
                    filePro.isMainImg = x.isMainImg;
                    filePro.groupName = x.groupName;
                    //filePro.filePath =fastdfsServer1+ x.groupName+"/"+x.imgPath;
                    filePropoties.add(filePro);
                });
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
                    //filePro.filePath =fastdfsServer1+ m.groupName+"/"+m.videoPath;
                    filePropoties.add(filePro);
                });
            }
            return filePropoties;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    //文件流方式获取图片或视频
    @GetMapping("getFile")
    public String getFileInfo(HttpServletResponse response, String companyNo)
    {
        List<StreamFileInfo> streamFileInfos;
        try
        {
            List<ImgCompanyInfo> imgCompanyInfos = imgCompanyInfoService.selectByCompanyNo(companyNo);
            List<VideoCompanyInfo> videoCompanyInfos = videoCompanyInfoService.selectByCompanyNo(companyNo);
            streamFileInfos = new ArrayList<>();
            response.setHeader("Content-Disposition", "attachment; ");
            response.setContentType("application/octet-stream");
            List<InputStream> inputStreams = new ArrayList<>();
            if(imgCompanyInfos!=null&&imgCompanyInfos.size()>0) {
                for (int i = 0; i < imgCompanyInfos.size(); i++) {
                    String groupName= imgCompanyInfos.get(i).groupName;
                    String filePathName = imgCompanyInfos.get(i).imgPath;
                    InputStream stream = fastDSFClient.downStreamFile(groupName,filePathName);
                    StreamFileInfo streamFileInfo = new StreamFileInfo();
                    streamFileInfo.title =imgCompanyInfos.get(i).imgTitle;
                    streamFileInfo.streamType = "img";
                    streamFileInfo.imgStream = stream;
                    inputStreams.add(stream);
                    //IOUtils.copy(stream,response.getOutputStream());
                    streamFileInfos.add(streamFileInfo);
                }
            }
            try
            {
               ServletOutputStream outStream=  response.getOutputStream();

               for (InputStream imageStream : inputStreams) {
                  byte[] buffer = new byte[1024];
                  int bytesRead;

                // 写入图片的HTTP头信息
                // 例如: Content-Type: image/jpeg
                // ...

                  while ((bytesRead = imageStream.read(buffer)) != -1) {
                      outStream.write(buffer, 0, bytesRead);
                  }

                // 重置缓冲区以写入下一个图片
                  outStream.flush();
                  imageStream.close();
              }
               outStream.flush();
               outStream.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } ;
            if(videoCompanyInfos!=null&&videoCompanyInfos.size()>0)
            {
                for (int m = 0; m < videoCompanyInfos.size(); m++) {
                    String groupName= videoCompanyInfos.get(m).groupName;
                    String filePathName = videoCompanyInfos.get(m).videoPath;
                    InputStream stream = fastDSFClient.downStreamFile(groupName,filePathName);
                    StreamFileInfo streamFileInfo = new StreamFileInfo();
                    streamFileInfo.title =videoCompanyInfos.get(m).videoTitle;
                    streamFileInfo.streamType = "video";
                    streamFileInfo.imgStream = stream;
                    streamFileInfos.add(streamFileInfo);
                }
            }
            return "inputStream";
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
