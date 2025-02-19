package com.atguigu.cloud.config;

// 导入 Sentinel 相关类和接口
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayFlowRule;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayRuleManager;
import com.alibaba.csp.sentinel.adapter.gateway.sc.SentinelGatewayFilter;
import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.BlockRequestHandler;
import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.GatewayCallbackManager;
import com.alibaba.csp.sentinel.adapter.gateway.sc.exception.SentinelGatewayBlockExceptionHandler;

// 导入 Spring Cloud Gateway 和其他相关类
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.result.view.ViewResolver;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * 使用时只需注入对应的 SentinelGatewayFilter 实例以及 SentinelGatewayBlockExceptionHandler 实例即可
 */
@Configuration // 标记该类为配置类，用于配置 Spring Bean
public class GatewayConfiguration {

    private final List<ViewResolver> viewResolvers; // 视图解析器列表
    private final ServerCodecConfigurer serverCodecConfigurer; // 服务器编码解码配置

    // 构造函数注入视图解析器和服务器编码解码配置
    public GatewayConfiguration(ObjectProvider<List<ViewResolver>> viewResolversProvider, ServerCodecConfigurer serverCodecConfigurer)
    {
        this.viewResolvers = viewResolversProvider.getIfAvailable(Collections::emptyList); // 获取视图解析器列表，如果没有则返回空列表
        this.serverCodecConfigurer = serverCodecConfigurer; // 设置服务器编码解码配置
    }

    // 注册限流异常处理器，并设置其优先级为最高
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SentinelGatewayBlockExceptionHandler sentinelGatewayBlockExceptionHandler() {
        // 返回一个新的限流异常处理器实例
        return new SentinelGatewayBlockExceptionHandler(viewResolvers, serverCodecConfigurer);
    }

    // 注册全局过滤器，并设置其优先级为 -1（较高优先级）
    @Bean
    @Order(-1)
    public GlobalFilter sentinelGatewayFilter() {
        // 返回一个新的 Sentinel 全局过滤器实例
        return new SentinelGatewayFilter();
    }

    // 在构造完成后初始化限流规则和处理程序
    @PostConstruct //javax.annotation.PostConstruct
    public void doInit() {
        initBlockHandler(); // 初始化限流处理程序
    }

    // 处理/自定义返回的例外信息
    private void initBlockHandler() {
        Set<GatewayFlowRule> rules = new HashSet<>(); // 创建一个限流规则集合
        // 添加一个名为 "pay_routh1" 的限流规则，每秒最多允许 2 次请求
        rules.add(new GatewayFlowRule("pay_routh1").setCount(2).setIntervalSec(1));

        GatewayRuleManager.loadRules(rules); // 加载限流规则到 Sentinel 管理器中
        // 自定义限流处理程序
        BlockRequestHandler handler = new BlockRequestHandler() {
            @Override
            public Mono<ServerResponse> handleRequest(ServerWebExchange exchange, Throwable t) {
                Map<String, String> map = new HashMap<>(); // 创建响应数据映射

                // 设置错误代码和消息
                map.put("errorCode", HttpStatus.TOO_MANY_REQUESTS.getReasonPhrase());
                map.put("errorMessage", "请求太过频繁，系统忙不过来，触发限流(sentinel+gataway整合Case)");

                // 返回带有状态码 429 (Too Many Requests) 和 JSON 响应体的 ServerResponse
                return ServerResponse.status(HttpStatus.TOO_MANY_REQUESTS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(map));
            }
        };
        GatewayCallbackManager.setBlockHandler(handler); // 设置自定义限流处理程序
    }

}
