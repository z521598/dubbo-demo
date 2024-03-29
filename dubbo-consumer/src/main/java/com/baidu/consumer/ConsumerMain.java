package com.baidu.consumer;

import com.baidu.api.ISayService;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;

public class ConsumerMain {
	public static void main(String[] args) {
		ApplicationConfig config = new ApplicationConfig();
		config.setName("dubbo-demo");

		RegistryConfig registry = new RegistryConfig();
		registry.setAddress("zookeeper://127.0.0.1:2181");

		ProtocolConfig protocol = new ProtocolConfig();
		protocol.setPort(9998);
		protocol.setThreads(10);
		protocol.setServer("netty4");

		config.setRegistry(registry);

		ReferenceConfig<ISayService> service = new ReferenceConfig<>();
		service.setApplication(config);
		service.setInterface(ISayService.class);
		service.setVersion("1.0.0");

		String message = service.get().say("world");
		System.out.println(message);
	}
}
