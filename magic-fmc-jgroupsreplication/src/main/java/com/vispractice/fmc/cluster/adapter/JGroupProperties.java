package com.vispractice.fmc.cluster.adapter;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
@ConfigurationProperties(prefix="cluster")
public class JGroupProperties {

	private String confXml;

	private String group;

	private String hostName;
}
