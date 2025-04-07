package com.vicky.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.vicky.pojo.Result;
import com.vicky.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Component //IOC容器管理
//HandlerInterceptor 是 Spring MVC 提供的一个接口，它是在 Spring 容器中管理的
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override //目标资源方法运行前，true放行、false拦截
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
        System.out.println("preHandle....");

        //1.获取请求url
        String url = req.getRequestURL().toString();
        log.info("请求的url：{}",url);

        //2.判断请求url是否包含login，如果包含就是登录，放行
        if(url.contains("login")){
            log.info("登录操作，直接放行。。。。");
            return true;
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
            return false;
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
            return false;
        }

        //6.放行
        log.info("令牌合法，放行");
        return true;
    }

    @Override//目标资源方法运行后运行
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle...");
    }

    @Override//视图渲染完毕后运行，最后运行
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion...");
    }
}
