package commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import expressions.ShuntingYard;
import interpreter.MyParser;
import server.ClientHandler;
import server.MySerialServer;
import server.Server;

public class DataReaderServer implements Command {
	public static volatile boolean stop;
	public static Object lock ;
	Server server;
	@Override
	public void execute(String[] array) {
		stop=false;

		server =new MySerialServer();
		server.open(Integer.parseInt(array[1]), new ClientHandler() {
			@Override
			public void handleClient(InputStream in, OutputStream out) {
				BufferedReader Bin=new BufferedReader(new InputStreamReader(in));
				synchronized (DataReaderServer.lock) {
					DataReaderServer.lock.notifyAll();
				}
				
				while (!stop) {
					try {
						String Line;
						Line = Bin.readLine();
						String[] vars = Line.split(",");
						for (int i=0;i<vars.length;i++)
						{
							if(Double.parseDouble(vars[i])!= MyParser.symbolTable.get(MyParser.vars.get(i)).getV())
								MyParser.symbolTable.get(MyParser.vars.get(i)).setV(Double.parseDouble(vars[i]));

						}



					} catch(IOException e1){
						e1.printStackTrace();
					}
					try {
						long num = (long) ShuntingYard.calc("1000/" + array[2]);
						Thread.sleep(num);
					} catch (InterruptedException e) {
					}
				}
				server.stop();
			}
		});


	}
	static {
		DataReaderServer.stop = false;
		DataReaderServer.lock = new Object();
	}

}
