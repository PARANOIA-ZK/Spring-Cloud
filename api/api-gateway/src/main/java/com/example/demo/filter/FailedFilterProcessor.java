package com.example.demo.filter;

import com.netflix.zuul.FilterProcessor;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

/**
 * @author PARANOIA_ZK
 * @date 2018/3/2 10:33
 */
public class FailedFilterProcessor extends FilterProcessor {

    /**
     * 存储抛出异常的过滤器实例
     *
     * @param filter
     * @return the return value for that filter
     * @throws ZuulException
     */
    @Override
    public Object processZuulFilter(ZuulFilter filter) throws ZuulException {
        try {
            return super.processZuulFilter(filter);
        } catch (ZuulException e) {
            RequestContext ctx = RequestContext.getCurrentContext();
            ctx.set("failed.filter", filter);
            throw e;
        }
    }
}
