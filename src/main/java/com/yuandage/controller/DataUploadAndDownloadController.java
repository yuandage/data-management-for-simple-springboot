package com.yuandage.controller;

import com.yuandage.entity.ShareData;
import com.yuandage.service.ShareDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/dataUploadAndDownload")
public class DataUploadAndDownloadController {

    @Autowired
    private ShareDataService shareDataService;

    @RequestMapping("/show")
    public String show() {
        return "/view/upload";
    }

    @RequestMapping("/downloadAll")
    public String downloadAll(Model model) {
        List<ShareData> list = shareDataService.queryAll();
        model.addAttribute("shareDataList", list);
        return "/view/download";
    }

    @RequestMapping("/upload")
    public String addData(ShareData data, @RequestParam("file") MultipartFile multipartFile, HttpServletRequest request, Model model) {
        boolean suc = false;
        Date date = new Date();
        String ip = request.getRemoteAddr();
        data.setIp(ip);
        data.setCreateDate(date);
        data.setUpdateDate(date);
        suc = shareDataService.addData(data);
        List<ShareData> list = shareDataService.queryAll();
        model.addAttribute("shareDataList", list);
        model.addAttribute("addDataFlag", suc);
        String uploadDir = request.getServletContext().getRealPath("/") + "file/";
        String originalFileName = multipartFile.getOriginalFilename();
        System.out.println("文件存放路径:" + uploadDir);
        System.out.println("文件原始名称:" + originalFileName);
        String fileNamePrefix = data.getDataName() + "-" +data.getId();
        String newfileName = fileNamePrefix + originalFileName.substring(originalFileName.lastIndexOf("."));
        File dir = new File(uploadDir);
        if (!dir.exists())  //如果文件目录不存在
            dir.mkdirs();
        File serverFile = new File(uploadDir + newfileName);
        try { //上传
            multipartFile.transferTo(serverFile);
            model.addAttribute("fileName", newfileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "index";
    }

    @RequestMapping("/download/{id}")
    public void download(@PathVariable("id") Integer id, HttpServletRequest request, HttpServletResponse response)throws IOException {
        String path = request.getServletContext().getRealPath("/") + "file/";
        ShareData shareData = shareDataService.queryDataInfo(id);
        String fileNamePrefix = shareData.getDataName() + "-" + shareData.getId();
        System.out.println("文件名:" + fileNamePrefix);
        File file = new File(path);
        File[] files = file.listFiles();
        String fileSearchName;
        for (File filePathAndName : files) {
            fileSearchName = filePathAndName.getName();
            if (fileNamePrefix.equals(fileSearchName.substring(0, fileSearchName.lastIndexOf(".")))) {
                fileNamePrefix = fileSearchName;
                file=filePathAndName;
                break;
            }
        }
        System.out.println(fileNamePrefix);
        try {  //判断文件是否存在
            fileNamePrefix = new String(fileNamePrefix.getBytes("gbk"), "ISO8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        OutputStream outputStream = response.getOutputStream(); // 使用response 获取字节输出
        response.setContentType("application/x-download");   // 进行文件下载
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileNamePrefix);
        outputStream.write(FileCopyUtils.copyToByteArray(file));   // outputStream 写入到输出流
        outputStream.flush();   // 刷新流
        outputStream.close();   // 关闭流
    }


}
