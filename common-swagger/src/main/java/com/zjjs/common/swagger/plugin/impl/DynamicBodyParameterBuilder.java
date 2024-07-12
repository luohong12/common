package com.zjjs.common.swagger.plugin.impl;

import com.google.common.base.CaseFormat;
import com.zjjs.common.swagger.plugin.annotation.MyRequestBody;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.ResolvedMethodParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.OperationBuilderPlugin;
import springfox.documentation.spi.service.contexts.OperationContext;
import springfox.documentation.spi.service.contexts.ParameterContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 构建操作接口参数对象的插件。
 *
 * @author Jerry
 * @date 2023-09-01
 */
@Slf4j
@Component
@Order(Ordered.HIGHEST_PRECEDENCE + 102)
@ConditionalOnProperty(prefix = "swagger", name = "enabled")
public class DynamicBodyParameterBuilder implements OperationBuilderPlugin {

    @Override
    public void apply(OperationContext context) {
        List<ResolvedMethodParameter> methodParameters = context.getParameters();
        List<Parameter> parameters = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(methodParameters)) {
            List<ResolvedMethodParameter> bodyParameter = methodParameters.stream()
                    .filter(p -> p.hasParameterAnnotation(MyRequestBody.class)).collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(bodyParameter)) {
                // 构造model
                String groupName = CaseFormat.LOWER_HYPHEN.to(CaseFormat.UPPER_CAMEL, context.getGroupName());
                String clazzName = groupName + StringUtils.capitalize(context.getName());

                for (ResolvedMethodParameter parameter : bodyParameter) {
                    Optional<String> paramName=parameter.defaultName();
                    if(paramName.isPresent()){
                        log.info("param:{}",paramName.get());
                        ParameterContext parameterContext = new ParameterContext(parameter,
                                new ParameterBuilder(),
                                context.getDocumentationContext(),
                                context.getGenericsNamingStrategy(),
                                context);
                        Parameter parameter1 = parameterContext.parameterBuilder()
                                .parameterType("query").modelRef(new ModelRef("string")).hidden(true).name(paramName.get()).build();
                        parameters.add(parameter1);
                    }

                }
                ResolvedMethodParameter methodParameter = bodyParameter.get(0);
                ParameterContext parameterContext = new ParameterContext(methodParameter,
                        new ParameterBuilder(),
                        context.getDocumentationContext(),
                        context.getGenericsNamingStrategy(),
                        context);
                Parameter parameter = parameterContext.parameterBuilder()
                        .parameterType("body").modelRef(new ModelRef(clazzName)).name(clazzName).build();
                parameters.add(parameter);


            }
        }
        context.operationBuilder().parameters(parameters);
    }

    @Override
    public boolean supports(DocumentationType delimiter) {
        return delimiter == DocumentationType.SWAGGER_2;
    }
}
