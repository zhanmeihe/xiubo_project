package com.epwk.software.boot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

/**
 * 
 * @author zhanmeihe
 *
 *
 */
@SpringBootApplication
@MapperScan("com.epwk.software.boot.mapper")
public class SpringbootEpwkApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {

		return builder.sources(SpringbootEpwkApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootEpwkApplication.class, args);
		 
	}
}
