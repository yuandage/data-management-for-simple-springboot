package com.yuandage.controller;

import com.yuandage.entity.ShareData;
import com.yuandage.entity.User;
import com.yuandage.service.ShareDataService;
import com.yuandage.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private ShareDataService shareDataService;
    @Autowired
    private UserService userService;

    //主页
    @RequestMapping("/index")
    public String toIndex(Model model, HttpSession session){
        User user=new User();
        session.setAttribute("user",user);
        session.setAttribute("status","未登录!");
        List<ShareData> list = shareDataService.queryAll();
        model.addAttribute("shareDataList", list);
        return "index";
    }

    //跳转到登录页面
    @RequestMapping("/loginView")
    public String loginStudentView(){
        return "view/login";
    }

    //跳转到管理员登录页面
    @RequestMapping("/loginAdminView")
    public String loginAdminView(){
        return "view/adminLogin";
    }

    //注销
    @RequestMapping("/out")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "view/login";
    }

    //登录
    @RequestMapping("/login")
    public String login(String name, String password, Model model,HttpSession session) {
        Subject subject = SecurityUtils.getSubject(); //认证
        password= userService.encryptPassword(password);
        UsernamePasswordToken token = new UsernamePasswordToken(name, password);
        User user=userService.findByName(name);
        try {   //登录成功
            subject.login(token);  //执行登录方法
            List<ShareData> list = shareDataService.queryAll();
            model.addAttribute("shareDataList", list);
            session.setAttribute("user",user);
            session.setAttribute("status","你已登录!");
            return "index";
        } catch (UnknownAccountException e) {  //登录失败
            model.addAttribute("msg", "用户名不存在");
            System.out.println("用户名不存在");
            return "view/login";
        } catch (IncorrectCredentialsException e) {
            model.addAttribute("msg", "密码错误");
            System.out.println("密码错误");
            return "view/login";
        }
    }

    //注册
    @RequestMapping("/signup")
    public String signup(User user, Model model) {
        System.out.println("注册");
        System.out.println(user);
        if(user==null){
            model.addAttribute("signupFlag",-2);
            return "view/login";
        }
        User user1 = userService.findById(user.getId());
        boolean signupFlag = false;
        if (user1==null) {
            user.setType("student");
            signupFlag = userService.signup(user);
        }
        if (signupFlag) {
            model.addAttribute("signupFlag", signupFlag);
        } else {
            model.addAttribute("signupFlag", signupFlag);
        }
        return "view/login";
    }

}
