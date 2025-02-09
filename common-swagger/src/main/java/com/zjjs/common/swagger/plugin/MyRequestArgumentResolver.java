package com.zjjs.common.swagger.plugin;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zjjs.common.swagger.plugin.annotation.MyRequestBody;
import org.apache.commons.io.IOUtils;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * MyRequestBody解析器
 * 解决的问题：
 * 1、单个字符串等包装类型都要写一个对象才可以用@RequestBody接收；
 * 2、多个对象需要封装到一个对象里才可以用@RequestBody接收。
 *
 * @author Jerry
 * @date 2023-09-01
 */
public class MyRequestArgumentResolver implements HandlerMethodArgumentResolver {

    private static final String JSONBODY_ATTRIBUTE = "MY_REQUEST_BODY_ATTRIBUTE_XX";

    private static final Set<Class<?>> CLASS_SET = new HashSet<>();

    static {
        CLASS_SET.add(Integer.class);
        CLASS_SET.add(Long.class);
        CLASS_SET.add(Short.class);
        CLASS_SET.add(Float.class);
        CLASS_SET.add(Double.class);
        CLASS_SET.add(Boolean.class);
        CLASS_SET.add(Byte.class);
        CLASS_SET.add(BigDecimal.class);
        CLASS_SET.add(Character.class);
        CLASS_SET.add(Date.class);
    }

    /**
     * 设置支持的方法参数类型。
     *
     * @param parameter 方法参数。
     * @return 支持的类型。
     */
    @Override
    public boolean supportsParameter(@NonNull MethodParameter parameter) {
        return parameter.hasParameterAnnotation(MyRequestBody.class);
    }

    /**
     * 参数解析，利用fastjson。
     * 注意：非基本类型返回null会报空指针异常，要通过反射或者JSON工具类创建一个空对象。
     */
    @Override
    public Object resolveArgument(
            @NonNull MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            @NonNull NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest servletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
        Assert.notNull(servletRequest, "HttpServletRequest can't be NULL.");
        String contentType = servletRequest.getContentType();
        if (!HttpMethod.POST.name().equals(servletRequest.getMethod())) {
            throw new IllegalArgumentException("Only POST method can be applied @MyRequestBody annotation!");
        }
        if (!StrUtil.containsIgnoreCase(contentType, MediaType.APPLICATION_JSON_VALUE)) {
            throw new IllegalArgumentException(
                    "Only application/json Content-Type can be applied @MyRequestBody annotation!");
        }
        // 根据@MyRequestBody注解value作为json解析的key
        MyRequestBody parameterAnnotation = parameter.getParameterAnnotation(MyRequestBody.class);
        Assert.notNull(parameterAnnotation, "parameterAnnotation can't be NULL");
        JSONObject jsonObject = getRequestBody(webRequest);
        if (jsonObject == null) {
            if (parameterAnnotation.required()) {
                throw new IllegalArgumentException("Request Body is EMPTY!");
            }
            return null;
        }
        String key = parameterAnnotation.value();
        if (StrUtil.isBlank(key)) {
            key = parameter.getParameterName();
        }
        Object value = jsonObject.get(key);
        if (value == null) {
            if (parameterAnnotation.required()) {
                throw new IllegalArgumentException(String.format("Required parameter %s is not present!", key));
            }
            return null;
        }
        // 获取参数类型。
        Class<?> parameterType = parameter.getParameterType();
        // 基本类型
        if (parameterType.isPrimitive()) {
            return parsePrimitive(parameterType.getName(), value);
        }
        // 基本类型包装类
        if (isBasicDataTypes(parameterType)) {
            return parseBasicTypeWrapper(parameterType, value);
        } else if (parameterType == String.class) {
            // 字符串类型
            return value.toString();
        }
        // 对象类型
        if (!(value instanceof JSONArray)) {
            // 其他复杂对象
            return JSON.toJavaObject((JSONObject) value, parameterType);
        }
        if (parameter.getGenericParameterType() instanceof ParameterizedType) {
            return ((JSONArray) value).toJavaObject(parameter.getGenericParameterType());
        }
        // 非参数化的集合类型
        return JSON.parseObject(value.toString(), parameterType);
    }

    private Object parsePrimitive(String parameterTypeName, Object value) {
        final String booleanTypeName = "boolean";
        if (booleanTypeName.equals(parameterTypeName)) {
            return Boolean.valueOf(value.toString());
        }
        final String intTypeName = "int";
        if (intTypeName.equals(parameterTypeName)) {
            return Integer.valueOf(value.toString());
        }
        final String charTypeName = "char";
        if (charTypeName.equals(parameterTypeName)) {
            return value.toString().charAt(0);
        }
        final String shortTypeName = "short";
        if (shortTypeName.equals(parameterTypeName)) {
            return Short.valueOf(value.toString());
        }
        final String longTypeName = "long";
        if (longTypeName.equals(parameterTypeName)) {
            return Long.valueOf(value.toString());
        }
        final String floatTypeName = "float";
        if (floatTypeName.equals(parameterTypeName)) {
            return Float.valueOf(value.toString());
        }
        final String doubleTypeName = "double";
        if (doubleTypeName.equals(parameterTypeName)) {
            return Double.valueOf(value.toString());
        }
        final String byteTypeName = "byte";
        if (byteTypeName.equals(parameterTypeName)) {
            return Byte.valueOf(value.toString());
        }
        return null;
    }

    private Object parseBasicTypeWrapper(Class<?> parameterType, Object value) {
        if (Number.class.isAssignableFrom(parameterType)) {
            return this.parseNumberType(parameterType, value);
        } else if (parameterType == Boolean.class) {
            return value;
        } else if (parameterType == Character.class) {
            return value.toString().charAt(0);
        } else if (parameterType == Date.class) {
            return Convert.toDate(value);
        }
        return null;
    }

    private Object parseNumberType(Class<?> parameterType, Object value) {
        if (value instanceof String) {
            return Convert.convert(parameterType, value);
        }
        Number number = (Number) value;
        if (parameterType == Integer.class) {
            return number.intValue();
        } else if (parameterType == Short.class) {
            return number.shortValue();
        } else if (parameterType == Long.class) {
            return number.longValue();
        } else if (parameterType == Float.class) {
            return number.floatValue();
        } else if (parameterType == Double.class) {
            return number.doubleValue();
        } else if (parameterType == Byte.class) {
            return number.byteValue();
        } else if (parameterType == BigDecimal.class) {
            if (value instanceof Double || value instanceof Float) {
                return BigDecimal.valueOf(number.doubleValue());
            } else {
                return BigDecimal.valueOf(number.longValue());
            }
        }
        return null;
    }

    private boolean isBasicDataTypes(Class<?> clazz) {
        return CLASS_SET.contains(clazz);
    }

    private JSONObject getRequestBody(NativeWebRequest webRequest) throws IOException {
        HttpServletRequest servletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
        Assert.notNull(servletRequest, "servletRequest can't be NULL");
        // 有就直接获取
        JSONObject jsonObject = (JSONObject) webRequest.getAttribute(JSONBODY_ATTRIBUTE, RequestAttributes.SCOPE_REQUEST);
        // 没有就从请求中读取
        if (jsonObject == null) {
            String jsonBody = IOUtils.toString(servletRequest.getReader());
            jsonObject = JSON.parseObject(jsonBody);
            if (jsonObject != null) {
                webRequest.setAttribute(JSONBODY_ATTRIBUTE, jsonObject, RequestAttributes.SCOPE_REQUEST);
            }
        }
        return jsonObject;
    }
}
