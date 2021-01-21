package server;

import java.net.Socket;
import java.net.SocketTimeoutException;
import java.io.IOException;
import java.net.ServerSocket;

public class MySerialServer implements Server
{
	private int port;
	private ClientHandler c;
	private volatile boolean stop;

	public MySerialServer() {
		this.stop = false;
	}

	@Override
	public void open(final int port, final ClientHandler c) {
		this.port = port;
		this.c = c;
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
		server.setSoTimeout(300000000);
		while (!this.stop) {
			try {
				final Socket aClient = server.accept();
				System.out.println("Client connected to the server");
				try {
					this.c.handleClient(aClient.getInputStream(), aClient.getOutputStream());
					aClient.close();
				}
				catch (IOException e) {
					System.out.println("invalid input2-output");
					e.printStackTrace();
				}
			}
			catch (SocketTimeoutException e2) {
				System.out.println("Time Out");
				e2.printStackTrace();
			}
		}
		server.close();
	}
}
