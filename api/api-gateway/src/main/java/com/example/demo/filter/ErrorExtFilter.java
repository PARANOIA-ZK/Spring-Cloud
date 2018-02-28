package com.example.demo.filter;

import org.springframework.cloud.netflix.zuul.filters.post.SendErrorFilter;

/**
 * @author PARANOIA_ZK
 * @date 2018/2/28 16:43
 */
public class ErrorExtFilter extends SendErrorFilter{
    @Override
    public String filterType() {
        return "error";
    }

    @Override
    public int filterOrder() {
        return 30;
    }

    @Override
    public Object run() {
        return super.run();
    }

    @Override
    public boolean shouldFilter() {
        return super.shouldFilter();
    }
}
