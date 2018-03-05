package com.example.demo;

import com.example.demo.filter.AccessFilter;
import com.example.demo.filter.FailedFilterProcessor;
import com.netflix.zuul.FilterProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

/**
 *  http://blog.didispace.com/spring-cloud-starter-dalston-6-1/
 */
@SpringCloudApplication
@EnableZuulProxy
public class ApiGatewayApplication {

	public static void main(String[] args) {
        FilterProcessor.setProcessor(new FailedFilterProcessor());
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

    @Bean
    public AccessFilter accessFilter() {
        return new AccessFilter();
    }
}
