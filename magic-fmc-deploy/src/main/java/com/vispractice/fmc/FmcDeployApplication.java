package com.vispractice.fmc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class FmcDeployApplication {

	/**
	 * 
	 * 实现流程:启动应用程序<br/>
	 * 
	 * @Title: main
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("----start----");
		SpringApplication.run(FmcDeployApplication.class, args);
		 
	}
}
