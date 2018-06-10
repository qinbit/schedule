package com.pnshsh;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 白名单拦截器
 */
@Component
public class IPHandlerInterceptor implements HandlerInterceptor {
    private static Logger logger = LoggerFactory.getLogger(IPHandlerInterceptor.class);

    @Value("${trigger.addr.white.list}")
    private String[] whiteList;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String address = request.getRemoteAddr();
        if (ArrayUtils.isEmpty(whiteList) || ArrayUtils.contains(whiteList, address)) {
            return true;
        }
        logger.warn("invalid client request: {} , denial of service!", address);
        response.getWriter().append("invalid request, denial of service!");
        return false;
    }
}
