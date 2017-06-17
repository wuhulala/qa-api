package com.wuhulala.auth;

import com.wuhulala.cache.CacheService;
import com.wuhulala.util.FilterUtils;
import com.wuhulala.util.ReturnCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * author： wuhulala
 * date： 2017/6/7
 * version: 1.0
 * description: 作甚的
 */
@ConfigurationProperties(prefix = "ip.auth")
public class IpFilter implements Filter{

    @Autowired
    CacheService<String,Integer> cacheService;


    private boolean enabled;
    private int interval;
    private int count;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("IpFilter--------init----------------------" + cacheService);

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("IpFilter-----------doFilter-------------------" + cacheService);

        if(this.enabled){
            String ip = servletRequest.getRemoteAddr();
            if(validIpPass(ip)){
                filterChain.doFilter(servletRequest,servletResponse);
            }else{
                FilterUtils.writeErrorMsg((HttpServletResponse)servletResponse, ReturnCode.IP_MAX_ERROR);
                return;
            }
        }else{
            filterChain.doFilter(servletRequest,servletResponse);
        }

        System.out.println("IpFilter-----------end-------------------" + cacheService);

    }

    private boolean validIpPass(String ip) {
        Integer useNumber = this.cacheService.getValue(ip);
        if(useNumber == null){
            this.cacheService.setValue(ip, 2, interval);
            return true;
        }else if(useNumber <= this.count){
            long remainSeconds = this.cacheService.getExpireSeconds(ip);
            if(remainSeconds != 0){
                this.cacheService.setValue(ip, useNumber + 1, remainSeconds);
            }
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void destroy() {
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
