package com.hystrix.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Future;

/**
 * @author PARANOIA_ZK
 * @date 2017/12/18 13:45
 */
@Service
public class ConsumerService {

    Logger logger = LoggerFactory.getLogger(ConsumerService.class);

    @Autowired
    RestTemplate restTemplate;

    /**
     * 我们使用了@HystrixCommand来将某个函数包装成了Hystrix命令，这里除了定义服务降级之外，Hystrix框架就会自动的为这个函数实现调用的隔离。
     * 所以，依赖隔离、服务降级在使用时候都是一体化实现的，这样利用Hystrix来实现服务容错保护在编程模型上就非常方便的，并且考虑更为全面。
     * todo  请求合并时，版本不同，注解方式使用时有差异 -- 190
     * @return
     */
    @HystrixCommand(fallbackMethod = "fallback")
    public String consumer() {
        long start = System.currentTimeMillis();
        String result = restTemplate.getForObject("http://eureka-client/dc", String.class);
        long end = System.currentTimeMillis();
        logger.info("spend time :" + (end - start));
        return result;
    }

    //todo 异步版本有差异 - 17x
    //@HystrixCommand
    //public Future<String> consumeraAsync() {
    //    long start = System.currentTimeMillis();
    //    String result = new AsyncResult<String>(){
    //        @Override
    //        public String invoke(){
    //
    //        }
    //    };
    //    long end = System.currentTimeMillis();
    //    logger.info("spend time :" + (end - start));
    //    return result;
    //}

    public String fallback(Throwable e) {
        //todo 服务降级之后异常传递如何实现 - 176
        logger.warn(e.getMessage());
        return "ERROR : 请求失败，这是备用方法中执行的内容。";
    }
}
