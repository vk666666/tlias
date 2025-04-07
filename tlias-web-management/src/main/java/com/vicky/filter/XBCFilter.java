package com.vicky.filter;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

/*@WebFilter(urlPatterns = "/*")*/
public class XBCFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("ABC Filter拦截到请求，放行前逻辑");

        //放行
        filterChain.doFilter(servletRequest,servletResponse); //放行到下一

        System.out.println("ABC Filter放行后逻辑");
    }
}
