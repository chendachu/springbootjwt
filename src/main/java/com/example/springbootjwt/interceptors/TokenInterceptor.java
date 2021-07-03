package com.example.springbootjwt.interceptors;

import ch.qos.logback.classic.Logger;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.example.springbootjwt.utils.JWTUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@Slf4j
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("拦截器执行");
        // 从请求头获取token
        String token = request.getHeader("token");
        HashMap<String, Object> map = new HashMap<>();
        try {
            JWTUtils.verify(token);
            return true;
        } catch (SignatureVerificationException e) {
            map.put("msg", "签名失败");
        } catch (TokenExpiredException e) {
            map.put("msg", "token过期");
        } catch (AlgorithmMismatchException e) {
            map.put("msg", "算法错误");
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("state", false);
        String json = new ObjectMapper().writeValueAsString(map);
        response.getWriter().print(json);
        return false;
    }
}
