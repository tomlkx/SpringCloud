package com.atguigu.cloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class RateLimitController
{
    @GetMapping("/rateLimit/byUrl")
    public String byUrl()
    {
        return "按rest地址限流测试OK";
    }

    @GetMapping("/rateLimit/byResource")
    @SentinelResource(value = "byResourceSentinelResource",blockHandler = "handleException")
    public String byResource()
    {
        return "按资源名称SentinelResource限流测试OK";
    }

    /**
     * 定义限流方法
     * @param exception
     * @return
     */
    public String handleException(BlockException exception)
    {
        return "服务不可用@SentinelResource启动"+"\t"+"o(╥﹏╥)o";
    }
    @GetMapping("/rateLimit/doAction/{p1}")
    @SentinelResource(
            value="doActionSentinelResource",      //定义资源名
            blockHandler = "doActionBlockHandler", //指定当资源被限流或堵塞时调用的方法
            fallback = "doActionFallback"          //指定当资源执行过程中发生异常时调用的方法
    )
    public String doAction(@PathVariable("p1")Integer p1){
        if(p1==0){
            throw new RuntimeException("p1等于零直接异常");
        }
        return "doAction";
    }
    public String doActionBlockHandler(@PathVariable("p1") Integer p1,BlockException e){
        log.error("sentinel配置自定义限流了:{}", e);
        return "sentinel配置自定义限流了";
    }
    public String doActionFallback(@PathVariable("p1") Integer p1,Throwable e){
        log.error("程序逻辑异常了:{}",e);
        return "程序逻辑异常了" + e.getMessage();
    }
    @GetMapping("/testHotKey")
    @SentinelResource(value="testHot",blockHandler = "dealHandler_testHotKey")
    public String testHostKey(@RequestParam(value="p1",required = false)String p1, @RequestParam(value = "p2",required = false)String p2){
        log.info("{},{}",p1,p2);
        return "------testHotKey";
    }
    public String dealHandler_testHotKey(String p1, String p2, BlockException exception){
        return "-----dealHandler_testHotKey";
    }
}
 