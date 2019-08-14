package com.baidu.producer;

import java.io.IOException;

import com.baidu.api.ISayService;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;

public class ProducerMain {
	public static void main(String[] args) throws IOException {
		ISayService sayService = new SayService();
		ApplicationConfig config = new ApplicationConfig();
		config.setName("dubbo-demo");

		RegistryConfig registry = new RegistryConfig();
		registry.setAddress("zookeeper://127.0.0.1:2181");

		ProtocolConfig protocol = new ProtocolConfig();
		protocol.setName("dubbo");
		protocol.setServer("netty4");
		protocol.setPort(9998);
		protocol.setThreads(10);

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
