package tank.net;

import lombok.extern.slf4j.Slf4j;
import tank.GameModel;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author zcl
 */
@Slf4j
public class Client {

	public static final Client INSTANCE = new Client();
	private Channel channel = null;

	private Client() {}
	public void connect() {
		EventLoopGroup group = new NioEventLoopGroup(1);

		Bootstrap b = new Bootstrap();

		try {
			ChannelFuture f = b.group(group).channel(NioSocketChannel.class).handler(new ClientChannelInitializer())
					.connect("localhost", 8888);

			f.addListener(new ChannelFutureListener() {
				@Override
				public void operationComplete(ChannelFuture future) throws Exception {
					if (!future.isSuccess()) {
						log.info("not connected!");
						System.out.println("not connected!");
					} else {
						log.info("connected!");
						System.out.println("connected!");
						// initialize the channel
						channel = future.channel();
					}
				}
			});

			f.sync();
			// wait until close
			f.channel().closeFuture().sync();
			log.info("connection closed!");
			System.out.println("connection closed!");
		} catch (Exception e) {
			log.info("connection error! {}",e.getStackTrace().toString());
			e.printStackTrace();
		} finally {
			group.shutdownGracefully();
		}
	}

	public void send(Msg msg) {
		System.out.println("SEND:" + msg);
		channel.writeAndFlush(msg);
	}

	public void closeConnect() {
		/*this.send("_bye_");
		//channel.close();
*/	}
}

class ClientChannelInitializer extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ch.pipeline()
			.addLast(new MsgEncoder())
			.addLast(new MsgDecoder())
			.addLast(new ClientHandler());
	}

}

/**
 * @author zcl
 */
@Slf4j
class ClientHandler extends SimpleChannelInboundHandler<Msg> {

	@Override
	public void channelRead0(ChannelHandlerContext ctx, Msg msg) throws Exception {
		log.info("channelRead0 msg:{}",msg);
		System.out.println(msg);
		msg.handle();
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		ctx.writeAndFlush(new TankJoinMsg(GameModel.getInstance().getMainTank()));
	}

}
