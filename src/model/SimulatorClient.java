package model;

import java.io.IOException;
import java.net.Socket;
import java.io.PrintWriter;

public class SimulatorClient
{
    public static volatile boolean stop;
    private static PrintWriter out;
    private static Socket socket;

    public void Connect(final String ip, final int port) {
        try {
            SimulatorClient.socket = new Socket(ip, port);
            SimulatorClient.out = new PrintWriter(SimulatorClient.socket.getOutputStream());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Connected to the Server");
    }

    public void Send(final String[] data) {
        for (final String s : data) {
            SimulatorClient.out.println(s);
            SimulatorClient.out.flush();
            System.out.println(s);
        }
    }

    public void stop() {
        if (SimulatorClient.out != null) {
            SimulatorClient.out.close();
            try {
                SimulatorClient.socket.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static {
        SimulatorClient.stop = false;
    }
}
