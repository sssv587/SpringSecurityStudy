package com.futurebytedance.securitydemo1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuhang.sun
 * @version 1.0
 * @date 2022/12/29 - 0:09
 * @Description
 */
@RestController
@RequestMapping("/test")
public class TestController {
    //默认用户名:user 密码会自动生成在控制台
    @GetMapping("hello")
    public String add() {
        return "hello security";
    }
}
