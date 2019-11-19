package com.dialog.base.netty;

import com.dialog.base.Const;
import com.dialog.base.InputUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;

import java.net.InetSocketAddress;

/**
 * @author: ywy
 * @date: 2019-09-29
 * @desc:
 */
public class NettyDialogClient {
    public static void main(String[] args) throws Exception {
        new NettyDialogClient().run();
    }

    public void run() throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        ChannelFuture channelFuture = bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(new NettyDialogClientHandler());
                    }
                })
                .connect(new InetSocketAddress(Const.HOST_NAME, Const.PORT)).sync();
        channelFuture.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                System.out.println("Connect Success");
            }
        });
        channelFuture.channel().closeFuture().sync();

        group.shutdownGracefully();

    }

    private static class NettyDialogClientHandler extends ChannelInboundHandlerAdapter {
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            try {
                ByteBuf byteBuf = (ByteBuf) msg;
                String receiveMsg = byteBuf.toString(CharsetUtil.UTF_8);
                System.err.println("[wencai]:" + receiveMsg);
                if ("quit".equals(receiveMsg)) {
                    ctx.close();
                } else {
                    String inputData = InputUtil.getString("pls send msg");
                    ByteBuf sendBuf = Unpooled.buffer(inputData.getBytes().length);
                    sendBuf.writeBytes(inputData.getBytes());
                    ctx.writeAndFlush(sendBuf);
                }
            } finally {
                ReferenceCountUtil.release(msg);
            }
        }
    }
}
