package com.swagger.demo;

import io.swagger.models.Info;
import org.noear.solon.annotation.Bean;
import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.Inject;

/**
 * @author noear 2022/4/13 created
 */
@Controller
public class Config {
    @Bean
    public Info swaggerInfo(@Inject("${swagger.info}") Info info){
        return info;
    }
}
