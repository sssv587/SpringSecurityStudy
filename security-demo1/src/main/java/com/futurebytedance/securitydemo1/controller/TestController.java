package com.futurebytedance.securitydemo1.controller;

import com.futurebytedance.securitydemo1.entity.Users;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuhang.sun
 * @version 1.0
 * @date 2022/12/29 - 0:09
 * @Description
 * 用户认证:
 * 1)配置文件:在application.properties中添加
 *      spring.security.user.name=root
 *      spring.security.user.password=root
 * 2)配置类:比如SecurityConfig
 * 3)自定义编写实现类:
 *    第一步 创建配置类，设置使用哪个userDetailsService实现类   SecurityConfigTest.java
 *    第二步 编写实现类，返回User对象，User对象有用户名密码和操作权限  MyUserDetailsService.java
 * 扩展：从数据库中查询密码登录
 *     1.导入lombok、mybatis-plus-boot-starter、mysql-connector-java 依赖
 *     2.创建数据库表
 *     3.创建实体类
 *     4.继承MybatisPlus的BaseMapper接口
 *     5.在MyUserDetailsService调用mapper里面的方法查询
 *     6.启动类标注mapperScan
 *     7.配置数据库信息
 */
@RestController
@RequestMapping("/test")
public class TestController {
    //默认用户名:user 密码会自动生成在控制台
    @GetMapping("hello")
    public String hello() {
        return "hello security";
    }

    @GetMapping("index")
    public String index() {
        return "hello index";
    }

    @GetMapping("/update")
    //@Secured({"ROLE_sale","ROLE_manager"})
    //@PreAuthorize("hasAnyAuthority('admins')")
    @PostAuthorize("hasAnyAuthority('admins')")
    public String update() {
        System.out.println("update ......");
        return "hello update";
    }

    @GetMapping("/getAll")
    @PostAuthorize("hasAnyAuthority('admins')")
    @PostFilter("filterObject.username='root'")
    public List<Users> getAllUser(String username) {
        ArrayList<Users> list = new ArrayList<>();
        list.add(new Users(1,"root","admin1"));
        list.add(new Users(2,"admin2","admin2"));
        System.out.println(list);
        return list;
    }
}
