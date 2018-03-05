package com.example.demo.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.post.SendErrorFilter;

/**
 * @author PARANOIA_ZK
 * @date 2018/2/28 16:43
 */
public class ErrorExtFilter extends SendErrorFilter {

    Logger logger = LoggerFactory.getLogger(SendErrorFilter.class);

    @Override
    public String filterType() {
        return "error";
    }

    @Override
    public int filterOrder() {
        return 30;
    }

    @Override
    public boolean shouldFilter() {
        //只处理post阶段的异常
        RequestContext requestContext = RequestContext.getCurrentContext();
        ZuulFilter failedFilter = (ZuulFilter) requestContext.get("failed.filter");
        if (failedFilter != null && "post".equals(failedFilter.filterType())) {
            logger.info("POST阶段异常");
            return true;
        }
        return false;
    }

    @Override
    public Object run() {
        return super.run();
    }
}
