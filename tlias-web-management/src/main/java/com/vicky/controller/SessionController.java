package com.vicky.controller;

import com.vicky.pojo.Result;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: SessionController
 * Description:
 *
 * @Author Vicky
 * @Create 2025/4/2-13:17
 * @Version 1.0
 */
@Slf4j
@RestController
public class SessionController {
    // 设置cookie
    @GetMapping("/c1")
    public Result cookie1(HttpServletResponse response){
        response.addCookie(new Cookie("login_username","vicky")); //设置cookie响应cookie
        return Result.success();
    }

    // 获取cookie
    @GetMapping("/c2")
    public Result cookie2(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("login_username")){
                System.out.println("login_username"+cookie.getValue());
            }
        }
        return Result.success();
    }

    // 往HttpSession存储值
    @GetMapping("/s1")
    public Result session1(HttpSession session){ //会话对象session
        log.info("HttpSession-s1:{}", session.hashCode());
        session.setAttribute("loginUser","ohmygod"); //往session存储数据
        return Result.success();
    }

    // 获取HttpSession的值
    @GetMapping("/s2")
    public Result session2(HttpServletRequest request) {
        HttpSession session = request.getSession();
        log.info("HttpSession-s2:{}",session.hashCode());
        Object loginUser = session.getAttribute("loginUser"); //从session获取数据
        log.info("loginUser：{}",loginUser);
        return Result.success(loginUser);
    }
}
