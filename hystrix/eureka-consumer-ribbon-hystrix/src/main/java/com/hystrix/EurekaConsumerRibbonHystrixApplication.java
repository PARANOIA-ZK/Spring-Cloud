package com.hystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * http://blog.didispace.com/spring-cloud-starter-dalston-4-1/
 */
@EnableCircuitBreaker
@EnableDiscoveryClient
@SpringBootApplication
public class EurekaConsumerRibbonHystrixApplication {

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

    //zipki跟踪策略，全部跟踪 -- 412
    @Bean
    public AlwaysSampler defaultSampler(){
        return new AlwaysSampler();
    }

	public static void main(String[] args) {
		SpringApplication.run(EurekaConsumerRibbonHystrixApplication.class, args);
	}
}
