package com.winterchen.web.base;

import com.winterchen.domain.QQUser;
import com.winterchen.domain.UserEntity;
import com.winterchen.service.user.UserService;
import com.winterchen.utils.ErrorCodeInfo;
import com.winterchen.utils.ResultBean;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * index控制类
 * Created by winterchen on 2017/11/29.
 */
@Controller
public class HomeController {


    @Autowired
    private UserService userService;


    @ApiOperation(value = "index页面的跳转", notes = "")
    @GetMapping({"/","/index", "home"})
    public String root(){
        return "/index";
    }

    @ApiOperation(value = "登录页跳转", notes = "进入登录页")
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @ApiOperation(value = "用户注册", notes = "进入注册页面")
    @GetMapping("/register")
    public String register(){
        return "register";
    }


    @ApiOperation(value = "用户注册", notes = "用户注册提交")
    @ApiImplicitParam(name = "userEntity",value = "用户名和密码", paramType = "body", required = true, dataType = "UserEntity")
    @PostMapping("/register")
    public String doRegister(UserEntity userEntity, Model model){

        if (userService.findUserByName(userEntity.getUserName()) != null){
            model.addAttribute("userEntity",userEntity);
            model.addAttribute("error","该用户名已被注册");
            return "/register";
        }
        if (userService.saveUserEntity(userEntity) != null){
            model.addAttribute("result","注册成功，请登录");
            return "/login";
        }
        model.addAttribute("result","注册失败，请重试");
        return "register";
    }


    @ApiOperation(value = "判断用户名", notes = "判断用户名是否已经使用")
    @ApiImplicitParam(name = "userName", value = "用户名", required = true, paramType = "path", dataType = "String")
    @ResponseBody
    @PostMapping("/exist/{userName}")
    public ResultBean<?> userNameExist(@PathVariable String userName){
        ResultBean<?> rs = null;
        if(userService.findUserByName(userName) != null){
            rs = new ResultBean<Object>(ErrorCodeInfo.USERNAME_EXIST,"该用户名已经被注册了");
        }else{
            rs = new ResultBean<Object>(ErrorCodeInfo.SUCCESS,(Object) "该用户名可以使用");
        }
        return rs;
    }


}
