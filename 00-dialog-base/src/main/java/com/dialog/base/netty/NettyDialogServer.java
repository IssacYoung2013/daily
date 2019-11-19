package com.dialog.base.netty;

import com.dialog.base.Const;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;

import java.util.concurrent.Executors;

import static com.dialog.base.Const.NEW_LINE;

/**
 * @author: ywy
 * @date: 2019-09-28
 * @desc:
 */
public class NettyDialogServer {
    public static void main(String[] args) throws Exception {
        new NettyDialogServer().run();
    }

    public void run() throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup(4);
        EventLoopGroup workGroup = new NioEventLoopGroup(8);

        ServerBootstrap serverBootstrap = new ServerBootstrap();

        ChannelFuture channelFuture = serverBootstrap.group(bossGroup, workGroup)
                .channel(NioServerSocketChannel.class)
                .childOption(ChannelOption.SO_BACKLOG,128)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(new DialogServerHandler());
                    }
                })
                .bind(Const.PORT).sync();

        System.out.println("start server");

        channelFuture.channel().closeFuture().sync();

    }

    private static class DialogServerHandler extends ChannelInboundHandlerAdapter {
        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            String hi = "hello,this is wencai";
            byte[] data = hi.getBytes();
            ByteBuf buf = Unpooled.buffer(data.length);
            buf.writeBytes(data);
            ctx.writeAndFlush(buf);
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            try {

                ByteBuf buf = (ByteBuf) msg;
                String receiveMsg = buf.toString(CharsetUtil.UTF_8);
                String sendMsg = receiveMsg.toUpperCase();
                if ("quit".equals(receiveMsg)) {
                    sendMsg = "quit";
                }
                byte[] data = (sendMsg).getBytes();
                ByteBuf sendBuf = Unpooled.buffer(data.length);
                sendBuf.writeBytes(data);

                ctx.writeAndFlush(sendBuf);
            } finally {
                ReferenceCountUtil.release(msg);
            }
        }
    }
}
