package com.winterchen.web.home;

import com.winterchen.domain.QQUser;
import com.winterchen.domain.UserEntity;
import com.winterchen.service.user.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 系统首页控制器
 * Created by winterchen on 2017/11/30.
 */
@Controller
@RequestMapping("/home")
public class IndexController {


    @Autowired
    private UserService userService;

   /* @ApiOperation(value = "系统的首页", notes = "进入系统首页的控制器")
    @GetMapping("/home")
    public String Index(){
        return "/home/index";
    }*/

    @ApiOperation(value = "QQ登录成功", notes = "QQ登录成功之后，首先需要进行判断该用户是否第一次授权，如果是进行数据库的添加")
    @GetMapping("/home")
    public String ToIndex(@AuthenticationPrincipal UsernamePasswordAuthenticationToken userAuthentication, Model model){

        Object uu =  userAuthentication.getPrincipal();

        if(uu instanceof QQUser){

            QQUser user = (QQUser) userAuthentication.getPrincipal();
            model.addAttribute("nikename", user.getNickname());
            model.addAttribute("avatar", user.getAvatar());
            //进行用户是否是第一次qq授权登录 唯一标示 openId
            UserEntity userEntity = userService.findUserByQQOpenId(user.getOpenId());
            if(userEntity == null){
                userService.saveUserByQQUser(user);
            }else{
                userService.updateUserByQQUser(user);
            }
            model.addAttribute("id",userEntity.getId());

        }else{
            User user = (User) userAuthentication.getPrincipal();
            UserEntity entity = userService.findUserByName(user.getUsername());
            if(entity != null){
                model.addAttribute("nikename",entity.getNikeName() == null ? "" : entity.getNikeName());
                model.addAttribute("avatar", entity.getAvatar() == null ? "" : entity.getAvatar());
                model.addAttribute("id", entity.getId());
            }
        }

        return "/home/index";
    }

    
}
