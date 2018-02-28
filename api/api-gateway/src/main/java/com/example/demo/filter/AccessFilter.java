package com.example.demo.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author PARANOIA_ZK
 * @date 2017/12/19 15:20
 */
public class AccessFilter  extends ZuulFilter{

    private static Logger log = LoggerFactory.getLogger(AccessFilter.class);

    /**
     * 过滤器类型 -- 259
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 执行顺序
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 是否执行，指定有效范围
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        log.info("send {} request to {}", request.getMethod(), request.getRequestURL().toString());

        //Object accessToken = request.getParameter("accessToken");
        //if(accessToken == null) {
        //    log.warn("access token is empty");
        //    ctx.setSendZuulResponse(false);
        //    ctx.setResponseStatusCode(401);
        //    return null;
        //}
        //log.info("access token ok");

        //测试异常
        try {
            doSomething();
        } catch (Exception e) {
           ctx.set("error.status_code", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
           ctx.set("error.exception",e);
        }
        return null;
    }

    private void doSomething() {
        System.out.println("=---------------------------------------------------------------");
        throw new RuntimeException("hi  a error !!");
    }
}
