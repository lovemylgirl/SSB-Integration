package org.dream.service.hardware;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import cn.evchar.hardware.server.DeviceAcceptor;

@Component
public class DeviceManager {
	private static int num = 0;

	@PostConstruct
	public void init() {
		num++;
		System.out.println("Netty is start....." + num);
	}

	// private static DeviceAcceptor acceptor = DeviceAcceptor.getInstance();
	
}
