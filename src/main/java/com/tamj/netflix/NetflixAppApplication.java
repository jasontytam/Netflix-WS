package com.tamj.netflix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NetflixAppApplication {
	
	public NetflixAppApplication() {

//		ClassPathXmlApplicationContext context
//			= new ClassPathXmlApplicationContext("beans.xml");
		
//		AnnotationConfigApplicationContext context
//			= new AnnotationConfigApplicationContext(ApplicationConfig.class);
//		
//		NetflixSearchService searchSvc = context.getBean(NetflixSearchService.class);
//		searchSvc.getTitleDetailById("80161352");

	}

	public static void main(String[] args) {
		SpringApplication.run(NetflixAppApplication.class, args);

	}

}
