package com.futurebytedance.securitydemo1.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author yuhang.sun
 * @version 1.0
 * @date 2023/1/1 - 18:06
 * @Description
 */
@Data
@TableName("user")
public class Users {
    private Integer id;
    private String username;
    private String password;
}
