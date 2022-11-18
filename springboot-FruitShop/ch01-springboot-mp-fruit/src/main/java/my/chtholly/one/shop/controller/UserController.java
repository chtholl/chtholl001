package my.chtholly.one.shop.controller;


import my.chtholly.one.shop.entity.User;
import my.chtholly.one.shop.service.UserService;
import my.chtholly.one.shop.util.CheckCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author chtholly
 * @since 2022-11-03
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public void login(String username, String password, String checkcode, HttpServletResponse response, HttpSession session) throws IOException {
        User flag = userService.checkUser(username);
        String info;
        String sessionCode = (String) session.getAttribute("checkCode");
        if (checkcode.equals(sessionCode)) {

            if (flag == null) {
                info = "no";
                System.out.println("该用户名不存在");
            } else {
                if (flag.getPassword().equals(password)) {
                    session.setAttribute("user1",flag);
                    info = "yes";
                    System.out.println("登陆成功");
                } else {
                    info = "no_p";
                    System.out.println("密码错误");
                }
            }
        } else {
            info = "err";
            System.out.println("验证码输入错误，请重新输入！");

        }
        response.getWriter().write(info);

    }

    @RequestMapping("/fruitShow")
    public String fruitShow(){
        return "redirect:/fruit.html";
    }

    @GetMapping("/checkCode")
    public void checkCode(@RequestParam String checkcode, HttpServletResponse response, HttpSession session)throws IOException{
        //1. 接收验证码
        String sessionCode = (String)session.getAttribute("checkCode");
        //2. 调用service查询User对象
        boolean flag;
        if (checkcode.equalsIgnoreCase(sessionCode)){
            flag=true;
            System.out.println("验证码"+checkcode+"正确");
        }else{
            flag=false;
            System.out.println("验证码"+checkcode+"错误");
        }
        //3. 响应标记
        response.getWriter().write("" + flag);
    }
    @RequestMapping("/checkCodeImage")
    public void checkCodeImage(HttpServletResponse response, HttpSession session)throws IOException{
//        CheckCodeUtil checkCodeUtil=new CheckCodeUtil();
        OutputStream fos=response.getOutputStream();
        String checkCode= CheckCodeUtil.outputVerifyImage(100,40,fos,4);
        session.setAttribute("checkCode",checkCode);
    }

    @GetMapping("/selectByName")
    public void selectByName(@RequestParam String username, HttpServletResponse response) throws IOException {
        boolean f=false;
        User flag = userService.checkUser(username);
        if (flag==null){
            f=true;
        }
        response.getWriter().write("" + f);
    }

    @PostMapping("/register")
    public void register(String username, String password, String checkcode, HttpServletResponse response, HttpSession session) throws IOException {
        boolean flag = userService.checkUser(username)==null;
        String info;
        String sessionCode = (String) session.getAttribute("checkCode");
        if (checkcode.equals(sessionCode)) {
            if (flag) {
                User user = new User();
                user.setUsername(username);
                user.setPassword(password);
                boolean save = userService.save(user);
                if (save) {
                    info = "ok";
                    System.out.println("注册成功");

                } else {
                    info = "err1";
                    System.out.println("注册失败");

                }
            } else {
                info = "err2";
                System.out.println("用户名已存在，请重新输入！");
            }
        } else {
            info = "err3";
            System.out.println("验证码输入错误，请重新输入！");
        }

        response.getWriter().write(info);
    }

    @GetMapping("/outLogin")
    public void outLogin(@RequestParam Boolean resoult,HttpServletResponse response, HttpSession session)throws IOException{
        String info;
        if(resoult){
            session.removeAttribute("user1");

            session.invalidate();
            info="yes";
            System.out.println("成功退出登录状态");

        }else{
            info="no";
            System.out.println("没有退出登录状态");

        }
        response.getWriter().write(info);
    }
    @RequestMapping("/loginShow")
    public String LoginShow(){
        return "redirect:/login.html";
    }
}

