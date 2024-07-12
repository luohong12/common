package com.zjjs.common.swagger.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.net.InetAddress;

/**
 * ClassName:PrintSwaggerInfo
 * Package:com.zjjs.common.swagger.config
 * Description: 打印swagger启动信息
 * Datetime: 2024/7/4:8:48
 * Author: 石德斌
 */
@Slf4j
@Component
public class PrintSwaggerInfo implements InitializingBean, ApplicationContextAware {
    private static Environment env = null;
    @Value("${knife4j.enable}")
    Boolean enable;
    @Override
    public void afterPropertiesSet() throws Exception {
        String title = enable ? "knife4j.enable:"+enable+",knife4j启动成功，访问地址: http://{}:{}/doc.html\n"
                :"knife4j.enable:"+enable+",knife4j已关闭\n";

        log.info("\n----------------------------------------------------------\n\t" +
                        title+
                  "----------------------------------------------------------",
//                env.getProperty("spring.application.name"),
                InetAddress.getLocalHost().getHostAddress(),
                env.getProperty("server.port"));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.env = applicationContext.getEnvironment();
    }
}
