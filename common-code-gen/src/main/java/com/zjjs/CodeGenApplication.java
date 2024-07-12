package com.zjjs;


import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import com.zjjs.common.core.utils.StringUtils;
import com.zjjs.common.security.annotation.EnableCustomConfig;
import com.zjjs.common.swagger.annotation.EnableCustomSwagger2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.endpoint.web.CorsEndpointProperties;
import org.springframework.boot.actuate.autoconfigure.endpoint.web.WebEndpointProperties;
import org.springframework.boot.actuate.autoconfigure.web.server.ManagementPortType;
import org.springframework.boot.actuate.endpoint.ExposableEndpoint;
import org.springframework.boot.actuate.endpoint.web.*;
import org.springframework.boot.actuate.endpoint.web.annotation.ControllerEndpointsSupplier;
import org.springframework.boot.actuate.endpoint.web.annotation.ServletEndpointsSupplier;
import org.springframework.boot.actuate.endpoint.web.servlet.WebMvcEndpointHandlerMapping;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.zjjs.common.security.annotation.EnableCusFeignClients ;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 代码生成
 *
 * @author zjjs
 */
@EnableCustomConfig
@EnableCustomSwagger2
@EnableCusFeignClients
@SpringBootApplication
@Slf4j
public class CodeGenApplication {
    public static void main(String[] args) {
        SpringApplication.run(CodeGenApplication.class, args);
    }
}
