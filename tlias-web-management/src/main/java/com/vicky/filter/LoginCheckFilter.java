package com.vicky.filter;

import com.alibaba.fastjson.JSONObject;
import com.vicky.pojo.Result;
import com.vicky.utils.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Slf4j
/*@WebFilter(urlPatterns = "/*")*/
public class LoginCheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //1.获取请求url
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        String url = req.getRequestURL().toString();
        log.info("请求的url：{}",url);

        //2.判断请求url是否包含login，如果包含就是登录，放行
        if(url.contains("login")){
            log.info("登录操作，直接放行。。。。");
            filterChain.doFilter(servletRequest,servletResponse);
            return; //如果是登陆，就不继续执行下面逻辑，放行
        }

        //3.获取请求头中的令牌token
        String jwt = req.getHeader("token");//请求头的名字叫token

        //4.判断令牌是否存在，不存在返回未登录错误
        if(!StringUtils.hasLength(jwt)){
            log.info("请求头token为空，直接返回未登录信息");
            Result error = Result.error("NOT_LOGIN");
            //手动转换。对象-json数据响应（alibaba的fastjson）
            String notLogin = JSONObject.toJSONString(error);
            res.getWriter().write(notLogin); //响应给浏览器
            return;
        }

        //5.解析token，如果解析失败返回未登录错误opt cmd t快捷键
        try {
            JwtUtils.parseJWT(jwt);
        } catch (Exception e) {
            e.printStackTrace();
            Result error = Result.error("NOT_LOGIN");
            //手动转换。对象-json数据响应（alibaba的fastjson）
            String notLogin = JSONObject.toJSONString(error);
            res.getWriter().write(notLogin); //响应给浏览器
            return;
        }

        //6.放行
        log.info("令牌合法，放行");
        filterChain.doFilter(servletRequest,servletResponse);
    }
}