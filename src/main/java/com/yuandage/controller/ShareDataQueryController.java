package com.yuandage.controller;

import com.yuandage.entity.ShareData;
import com.yuandage.entity.User;
import com.yuandage.service.ShareDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/shareData")
public class ShareDataQueryController {

    @Autowired
    ShareDataService shareDataService;

    //查询所有资料信息
    @RequestMapping("/dataAllInfo")
    public String dataAllInfo(Model model) {
        List<ShareData> list = shareDataService.queryAll();
        model.addAttribute("shareDataList", list);
        return "index";
    }

    //模糊查询
    @RequestMapping("/fuzzyQuery")
    public String fuzzyQuery(@RequestParam(value = "name", required = false) String name,
       @RequestParam(value = "values", required = false) String values, Model model) {
        if (name.equals("groupName")) {
            List<ShareData> list = shareDataService.fuzzyQuery(name, values);
            model.addAttribute("shareDataList", list);
        } else if (name.equals("dataName")) {
            List<ShareData> list = shareDataService.fuzzyQuery(name, values);
            model.addAttribute("shareDataList", list);
        } else {
            List<ShareData> list = shareDataService.queryAll();
            model.addAttribute("shareDataList", list);
        }
        return "index";
    }

    //资料简介
    @RequestMapping("/dataInfo/{id}")
    public String queryDataInfo(@PathVariable("id") Integer id, Model model) {
        shareDataService.updateBrowseCount(id);
        ShareData shareData = shareDataService.queryDataInfo(id);
        System.out.println(shareData);
        model.addAttribute("shareData", shareData);
        return "view/dataInfo";
    }

    //删除资料
    @RequestMapping("/deleteData/{id}")
    public String deleteDataById(@PathVariable("id") Integer id,
                             Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        ShareData shareData = shareDataService.queryDataInfo(id);
        boolean suc = false;
        if (user.getType().equals("admin")) {
            suc = shareDataService.deleDataById(id);
            model.addAttribute("deleteFlag", suc);
            List<ShareData> list = shareDataService.queryAll();
            model.addAttribute("shareDataList", list);
            return "index";
        } else {
            if (shareData.getUpName().equals(user.getName())) {
                suc = shareDataService.deleDataById(id);
                model.addAttribute("deleteFlag", suc);
                List<ShareData> list = shareDataService.queryAll();
                model.addAttribute("shareDataList", list);
                return "index";
            } else {
                model.addAttribute("shareData", shareData);
                model.addAttribute("deleteFlag", suc);
                return "view/dataInfo";
            }
        }
    }


    //修改资料简介
    @RequestMapping("/updateData")
    public String updateDataSummary(@RequestParam("id") Integer id, HttpSession session,
     @RequestParam(value = "dataSummary", required = false) String dataSummary, Model model) {
        User user = (User) session.getAttribute("user");
        ShareData shareData = shareDataService.queryDataInfo(id);
        boolean updateDataSummaryFlag=false;
        if (user.getType().equals("admin")) {
            updateDataSummaryFlag = shareDataService.updateDataSummary(id, dataSummary, new Date());
            model.addAttribute("updateDataSummaryFlag", updateDataSummaryFlag);
            shareData = shareDataService.queryDataInfo(id);
            model.addAttribute("shareData", shareData);
            return "view/dataInfo";
        } else {
            if (shareData.getUpName().equals(user.getName())) {
                updateDataSummaryFlag = shareDataService.updateDataSummary(id, dataSummary, new Date());
                model.addAttribute("updateDataSummaryFlag", updateDataSummaryFlag);
                shareData = shareDataService.queryDataInfo(id);
                model.addAttribute("shareData", shareData);
                return "view/dataInfo";
            } else {
                model.addAttribute("shareData", shareData);
                model.addAttribute("updateDataSummaryFlag", updateDataSummaryFlag);
                return "view/dataInfo";
            }
        }

    }
}
