package grpc.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.util.concurrent.Executors;


import io.grpc.Server;
import io.grpc.netty.shaded.io.grpc.netty.NettyServerBuilder;



public class grpcServer 
{
	private String address = "127.0.0.5";
	private int port = 50051;
	private Server server;

	private SocketAddress socket;

	public grpcServer(NotificationService notificationService) {
		try { socket = new InetSocketAddress(InetAddress.getByName(address), port);	} catch(UnknownHostException e) {};

		server = NettyServerBuilder.forAddress(socket).executor(Executors.newFixedThreadPool(16))
				.addService(notificationService)
				.build();
//		logger.info("Server started, listening on " + port);
	}


	public void start() throws IOException
	{
		this.server.start();
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				// Use stderr here since the logger may have been reset by its JVM shutdown hook.
				System.err.println("*** shutting down gRPC server since JVM is shutting down");
				grpcServer.this.stop();
				System.err.println("*** server shut down");
			}
		});
	}

	public void stop() {
		if (server != null) {
			server.shutdown();
		}
	}

	/**
	 * Await termination on the main thread since the grpc library uses daemon threads.
	 */
	public void blockUntilShutdown() throws InterruptedException {
		if (server != null) {
			server.awaitTermination();
		}
	}



}
