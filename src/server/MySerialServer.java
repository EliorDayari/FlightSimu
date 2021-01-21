package server;

import java.net.Socket;
import java.net.SocketTimeoutException;
import java.io.IOException;
import java.net.ServerSocket;

public class MySerialServer implements Server
{
	private int port;
	private ClientHandler clientHandler;
	private volatile boolean stop;

	public MySerialServer() {
		this.stop = false;
	}

	@Override
	public void open(final int port, final ClientHandler clientHandler) {
		this.port = port;
		this.clientHandler = clientHandler;
		new Thread(() -> {
			try {
				this.runServer();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}).start();
	}

	@Override
	public void stop() {
		this.stop = true;
	}

	private void runServer() throws Exception {
		final ServerSocket server = new ServerSocket(this.port);
		System.out.println("Server is open. waiting for clients...");
		server.setSoTimeout(164000000);
		while (!this.stop) {
			try {
				final Socket aClient = server.accept();
				System.out.println("Client connected to the server");
				try {
					this.clientHandler.handleClient(aClient.getInputStream(), aClient.getOutputStream());
					aClient.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
			catch (SocketTimeoutException e2) {
				e2.printStackTrace();
			}
		}
		server.close();
	}

	public static void main(String[] args) {
		Server s=new MySerialServer(); // initialize
		CacheManager cm=new FileCacheManager();
		MyClientHandler ch=new MyClientHandler(cm);
		s.open(7777,new ClientHandlerPath(ch));
	}
}
