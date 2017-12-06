package com.winterchen.web.error;

import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 错误页面控制器
 * Created by Administrator on 2017/11/30.
 */
@Controller
public class ErrorController {

    @ApiOperation(value = "400错误页面", notes = "")
    @GetMapping("/400")
    public String badRequest(){
        return "/error/400";
    }

    @ApiOperation(value = "500错误页面", notes = "")
    @GetMapping("/500")
    public String serverError(){
        return "/error/500";
    }

    @ApiOperation(value = "404错误页面", notes = "")
    @GetMapping("/404")
    public String notFound(){
        return "/error/404";
    }
}
