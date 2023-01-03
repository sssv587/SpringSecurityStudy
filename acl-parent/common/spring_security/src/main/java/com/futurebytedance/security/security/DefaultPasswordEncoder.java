package com.futurebytedance.security.security;

import com.futurebytedance.utils.utils.MD5;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author yuhang.sun
 * @version 1.0
 * @date 2023/1/3 - 23:06
 * @Description 密码处理工具类
 */
@Component
public class DefaultPasswordEncoder implements PasswordEncoder {
    public DefaultPasswordEncoder() {
        this(-1);
    }

    public DefaultPasswordEncoder(int strength) {

    }

    //进行MD5加密
    @Override
    public String encode(CharSequence charSequence) {
        return MD5.encrypt(charSequence.toString());
    }

    //进行密码比对
    @Override
    public boolean matches(CharSequence charSequence, String encodePassword) {
        return encodePassword.equals(MD5.encrypt(charSequence.toString()));
    }
}
