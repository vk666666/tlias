package com.vicky.filter;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import java.io.IOException;

/*@WebFilter(urlPatterns = "/*")*/
public class DemoFilter implements Filter {
    @Override // 初始化方法，调用一次
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        System.out.println("init初始化");
    }

    @Override// 拦截请求之后调用，调用多次
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("demo Filter拦截了，放行前逻辑");
        //放行
        chain.doFilter(request,response); //filterchain的dofilter可以放行，允许访问对应资源
        System.out.println("demo Filter放行后逻辑");
    }

    @Override // 销毁方法，调用一次
    public void destroy() {
        Filter.super.destroy();
        System.out.println("destroy销毁了");
    }
}
