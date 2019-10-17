package com.ws.gateway.fallback;

import org.springframework.cloud.netflix.zuul.filters.route.ZuulFallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * @Description:
 * @Author: QiuJunWu
 * @Date: 2019/10/17 0017 12:54
 * <p>
 * zuul 调用微服务 启动 回退方法
 */
@Component
public class ZuulFallback implements ZuulFallbackProvider {

    /**
     * 指定需要回退的微服务(serviceId)
     *
     * @return
     */
    @Override
    public String getRoute() {
        return "microservice-consumer-movie";
    }

    /**
     * 指定回退实现类
     *
     * @return
     */
    @Override
    public ClientHttpResponse fallbackResponse() {
        return new ClientHttpResponseImpl();
    }
}

class ClientHttpResponseImpl implements ClientHttpResponse {

    @Override
    public HttpStatus getStatusCode() throws IOException {
        return HttpStatus.OK;
    }

    @Override
    public int getRawStatusCode() throws IOException {
        return this.getStatusCode().value();
    }

    @Override
    public String getStatusText() throws IOException {
        return this.getStatusCode().getReasonPhrase();
    }

    @Override
    public void close() {

    }

    @Override
    public InputStream getBody() throws IOException {
        return new ByteArrayInputStream("微服务不可用,请稍后尝试!".getBytes());
    }

    /**
     * 设置响应头部
     */
    @Override
    public HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        MediaType mediaType = new MediaType("application", "json", Charset.defaultCharset());
        headers.setContentType(mediaType);
        return headers;
    }
}
