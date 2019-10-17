package com.ws.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description:
 * @Author: QiuJunWu
 * @Date: 2019/10/17 0017 9:30
 * 自定义 zuul过滤器
 */
@Slf4j
public class CustomZuulFilter extends ZuulFilter {

    /**
     *  指定过滤器的执行时期, 分别有pre / route / post / error 可选
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 过滤器执行的顺序 , 数字越小, 执行越早
     * @return
     */
    @Override
    public int filterOrder() {
        return 1;
    }


    /**
     * 是否需要执行该过滤器
     */
    @Override
    public boolean shouldFilter() {
        log.info("是否需要执行过滤器!");
        return true;
    }

    /**
     * 执行过滤器的逻辑
     * @return
     */
    @Override
    public Object run() {
        log.info(this.getClass().getSimpleName() + "执行了");
        return null;
    }
}
