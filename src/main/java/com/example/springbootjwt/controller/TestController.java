package com.example.springbootjwt.controller;

import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@RestController
public class TestController {
    @GetMapping("/test/test")
    public String test(HttpServletRequest request) {
        request.getSession().setAttribute("username", "haihong");
        Base64.Encoder encoder = Base64.getEncoder();
        String s = encoder.encode("haihong".getBytes(StandardCharsets.UTF_8)).toString();
        System.out.println(s);
        return "login ok";
    }


}
