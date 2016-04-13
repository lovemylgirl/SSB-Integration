package org.dream.service.hardware;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import cn.evchar.hardware.server.DeviceAcceptor;

@Component
public class DeviceManager {

	private static final Logger logger = LoggerFactory.getLogger(DeviceManager.class);

	@PostConstruct
	public void init() {
		logger.info("Netty is start.....");
	}

	private static DeviceAcceptor acceptor = DeviceAcceptor.getInstance();

}
