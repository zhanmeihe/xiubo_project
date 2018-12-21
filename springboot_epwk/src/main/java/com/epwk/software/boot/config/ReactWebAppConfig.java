package com.epwk.software.boot.config;

import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * springboot静态资源配合
 * @author zhanmeihe
 *
 */
@Configuration
public class ReactWebAppConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		System.err.println("*********&&&&&&&&&&&&&&&&&&&&&&&");
		registry.addResourceHandler("/**").addResourceLocations("classpath:/META-INF/resources/")
				.addResourceLocations("classpath:/resources/").addResourceLocations("classpath:/static/")
				.addResourceLocations("classpath:/public/");
		
		//registry.addResourceHandler("/we/**").addResourceLocations("file:F:/preview/");
		super.addResourceHandlers(registry);

	}

}
