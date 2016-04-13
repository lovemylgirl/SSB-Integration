package cn.evchar.hardware.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.evchar.hardware.handler.DeviceAcceptorHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class DeviceAcceptor {

	private static final Logger logger = LoggerFactory.getLogger(DeviceAcceptor.class);
	private static final String SERVER_IP = "0.0.0.0";
	private static final Integer PORT = 44444;
	private static final int BACKLOG_SIZE = 1000;

	private static DeviceAcceptor acceptor = null;

	public static DeviceAcceptor getInstance() {
		logger.info("DeviceAcceptor excute getInstance!");
		if (acceptor == null) {
			acceptor = new DeviceAcceptor();
			acceptor.start();
			return acceptor;
		} else {
			return acceptor;
		}
	}

	public void start() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				EventLoopGroup boosGroup = new NioEventLoopGroup(1);
				EventLoopGroup workerGroup = new NioEventLoopGroup();

				try {
					ServerBootstrap b = new ServerBootstrap();
					b.group(boosGroup, workerGroup).channel(NioServerSocketChannel.class)
							.option(ChannelOption.SO_BACKLOG, BACKLOG_SIZE).childHandler(new ChildChannelHandler());

					ChannelFuture f = b.bind(PORT);

					f.channel().closeFuture().sync();

				} catch (Exception e) {
					boosGroup.shutdownGracefully();
					workerGroup.shutdownGracefully();
				}
			}
		}).start();
	}

	private class ChildChannelHandler extends ChannelInitializer<SocketChannel> {
		@Override
		protected void initChannel(SocketChannel ch) throws Exception {
			ch.pipeline().addLast(new DeviceAcceptorHandler());
		}
	}
}
