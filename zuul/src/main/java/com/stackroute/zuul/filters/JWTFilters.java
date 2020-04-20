package com.stackroute.zuul.filters;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTFilters extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest httpRequest=(HttpServletRequest) servletRequest;
        final HttpServletResponse httpResponse=(HttpServletResponse) servletResponse;

        final String authHeader=httpRequest.getHeader("authorization");

        if("OPTIONS".equals(httpRequest.getMethod())){
            httpResponse.setStatus(HttpServletResponse.SC_OK);
            filterChain.doFilter(httpRequest,httpResponse);
        }else{
            if(authHeader==null || !authHeader.startsWith("Bearer")){
                throw new ServletException("Missing or invalid authorization header.");
            }
            final String token=authHeader.substring(7);
            final Claims claims= Jwts.parser().setSigningKey("s9cr3at_k3y").parseClaimsJws(token).getBody();
            httpRequest.setAttribute("claims",claims);
            filterChain.doFilter(httpRequest,httpResponse);
        }
    }
}
