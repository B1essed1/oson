package uz.blessed.oson.base;

import io.micrometer.common.lang.NonNull;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.ArrayList;
import java.util.Arrays;

@RestControllerAdvice(basePackages = "uz.blessed.oson")
public class RestFilterAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(
            @NonNull MethodParameter methodParameter,
            @NonNull Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(
            Object o,
            @NonNull MethodParameter methodParameter,
            @NonNull MediaType mediaType,
            @NonNull Class<? extends HttpMessageConverter<?>> aClass,
            @NonNull ServerHttpRequest serverHttpRequest,
            @NonNull ServerHttpResponse serverHttpResponse) {

        if (serverHttpResponse instanceof ServletServerHttpResponse) {

            ServletServerHttpResponse res = (ServletServerHttpResponse) serverHttpResponse;

            if (res.getServletResponse().getStatus() == 200) {

                return new ResponseWrapper<>(o);
            }
        }

        return o;
    }

}