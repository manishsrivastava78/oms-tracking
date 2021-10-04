package com.tcs.eas.rest.apis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/**
 * 
 * @author 44745
 *
 */
@SpringBootApplication
public class TrackingApplication {
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(TrackingApplication.class, args);
	}
	@Bean
	public WebMvcConfigurer configurer(){
		return new WebMvcConfigurer(){
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/*")
						.allowedOrigins("*");
			}
		};
	}
	
}
