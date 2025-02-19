package com.atguigu.cloud.config;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.RequestOriginParser;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

/**
 * Sentinel 提供的接口，用于获取请求来源
 * 通过这个即可偶可以自定义请i去来源的解析逻辑,从而更好地进行来源维度的限流和降级
 */
@Component
public class MyRequestOriginParser implements RequestOriginParser
{
    @Override
    public String parseOrigin(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getParameter("serverName");
    }
}
 