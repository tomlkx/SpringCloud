package com.atguigu.cloud.predicate;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

/**
 * 需求说明: 自定义配置会员等级userType,按照钻/金/银yml配置的会员等级,以适配是否可以访问
 */
@Component
public class MyRoutePredicateFactory extends AbstractRoutePredicateFactory<MyRoutePredicateFactory.Config> {

    public MyRoutePredicateFactory(){
        super(MyRoutePredicateFactory.Config.class);
    }

    /**
     * 接收YML配置的值
     */
    public static class Config {
        @Setter@Getter@NotEmpty
        private String userType; // 钻/金/银
    }

    /**
     * 短格式的支持
     * @return
     */
    @Override
    public List<String> shortcutFieldOrder() {
        return Collections.singletonList("userType");
    }

    /**
     * 完整格式的支持
     * @param config
     * @return
     */
    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        return new Predicate<ServerWebExchange>() {
            @Override
            public boolean test(ServerWebExchange serverWebExchange) {
                // 检查Request的参数里面个,userType是否为指定的值,符合配置就通过
                String userType = serverWebExchange.getRequest().getQueryParams().getFirst("userType");
                if(userType == null){
                    return false;
                }
                // 如果说参数存在,就和config的数据进行比较
                if(userType.equalsIgnoreCase(config.userType)){
                    return true;
                }
                return false;
            }
        };
    }
}
