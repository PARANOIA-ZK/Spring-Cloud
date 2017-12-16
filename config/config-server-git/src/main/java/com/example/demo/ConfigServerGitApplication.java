package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * http://blog.didispace.com/spring-cloud-starter-dalston-3/
 */
@SpringBootApplication
@EnableConfigServer //开启spring cloud config 的服务端功能
public class ConfigServerGitApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigServerGitApplication.class, args);
	}
}
