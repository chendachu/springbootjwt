package com.example.springbootjwt.controller;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.springbootjwt.entity.User;
import com.example.springbootjwt.service.UserService;
import com.example.springbootjwt.utils.JWTUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/user/login")
    public Map<String, Object> login(User user) {
        log.info("用户名:[{}]", user.getName());
        log.info("密码:[{}]", user.getPwd());
        Map<String, Object> map = new HashMap<>();
        try {
            User login = userService.login(user.getName(), user.getPwd());
            HashMap<String, String> payload = new HashMap<>();
            payload.put("name", user.getName());
            payload.put("pwd", user.getPwd());
            // 生成token
            String token = JWTUtils.getToken(payload);
            map.put("state", true);
            map.put("token", token);
        } catch (Exception e) {
            map.put("state", false);
            map.put("msg", e.getMessage());
        }
        return map;
    }

    @PostMapping("/order/test")
    public Map<String, Object> test(HttpServletRequest request) {
        String token = request.getHeader("token");
        log.info("当前token为:[{}]", token);
        DecodedJWT verify = JWTUtils.verify(token);
        HashMap<String, Object> map = new HashMap<>();
        map.put("state", true);
        map.put("name", verify.getClaim("name").asString());
        return map;
    }
}
