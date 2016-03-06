package cn.evchar.hardware.handler;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class DeviceAcceptorHandler extends ChannelHandlerAdapter {
	
	// @Override
	// public void channelRead(ChannelHandlerContext ctx, Object msg) throws
	// Exception {
	// super.channelRead(ctx, msg);
	// }
	//
	// @Override
	// public void channelActive(ChannelHandlerContext ctx) throws Exception {
	// super.channelActive(ctx);
	// System.out.println("connected!");
	// }
	//
	// @Override
	// public void channelReadComplete(ChannelHandlerContext ctx) throws
	// Exception {
	// ctx.flush();
	// }

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		ctx.close();
	}
}
