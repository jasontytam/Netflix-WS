package com.tamj.netflix.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.tamj.netflix.helper.AwsSecretMgrHelper;

@Configuration
public class ApplicationConfig {
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	public AwsSecretMgrHelper awsSecretHelper() {
		return new AwsSecretMgrHelper();
	}
}
