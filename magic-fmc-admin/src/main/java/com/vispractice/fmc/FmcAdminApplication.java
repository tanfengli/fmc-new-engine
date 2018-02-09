package com.vispractice.fmc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
/**
 * 
 * 编  号：<br/>
 * 名  称：FmcAdminApplication<br/>
 * 描  述：启动主程序<br/>
 * 完成日期：2016年12月14日 上午11:35:34<br/>
 * 编码作者：sky<br/>
 */
@SpringBootApplication
@EnableCaching
@EnableAsync
public class FmcAdminApplication {

	private static final Logger logger = LoggerFactory.getLogger(FmcAdminApplication.class);
	
	/**
	 * 
	 * 实现流程:启动应用程序<br/>
	 * @Title: main 
	 * @param args
	 */
	public static void main(String[] args) {
		logger.info("magic-fmc starting...");
		SpringApplication.run(FmcAdminApplication.class, args);
	}
	
}
