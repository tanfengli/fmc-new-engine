package com.vispractice.fmc.base;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "spring.task.pool")
@Data
public class TaskThreadPoolConfig {
	private int corePoolSize;

	private int maxPoolSize;

	private int keepAliveSeconds;

	private int queueCapacity;
	
}
