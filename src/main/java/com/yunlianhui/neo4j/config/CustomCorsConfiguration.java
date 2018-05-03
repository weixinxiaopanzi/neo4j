package com.yunlianhui.neo4j.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * <p>Copyright (c) 广东云联国骥投资管理有限公司</p>
 *
 * <p>Title: 跨域访问设置</p>
 *
 * @author Maopz
 * @date 2018-04-23 08:59:36
 * @version V1.0
 */
@Configuration
public class CustomCorsConfiguration {

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {

			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/employee/**").allowedOrigins("http://10.18.3.18:8080");
			}
		};
	}
}
