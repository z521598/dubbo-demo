package com.baidu.producer;

import java.io.IOException;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;
import com.baidu.api.ISayService;

public class ProducerMain {
	public static void main(String[] args) throws IOException {
		ISayService sayService = new SayService();
		ApplicationConfig config = new ApplicationConfig();
		config.setName("dubbo-demo");

		RegistryConfig registry = new RegistryConfig();
		registry.setAddress("zookeeper://127.0.0.1:2181");

		ProtocolConfig protocol = new ProtocolConfig();
		protocol.setName("http");
		protocol.setPort(9998);
		protocol.setThreads(200);

		config.setRegistry(registry);

		ServiceConfig<ISayService> service = new ServiceConfig<>();
		service.setApplication(config);
		service.setProtocol(protocol);
		service.setInterface(ISayService.class);
		service.setRef(sayService);
		service.setVersion("1.0.0");

		service.export();
		System.in.read();
	}
}
