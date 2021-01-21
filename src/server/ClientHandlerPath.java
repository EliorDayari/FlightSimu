package server;

import java.io.OutputStream;
import java.io.InputStream;

public class ClientHandlerPath implements ClientHandler
{
    MyClientHandler ch;
    public static volatile boolean stop;

    public ClientHandlerPath(final MyClientHandler ch) {
        this.ch = ch;
    }

    @Override
    public void handleClient(final InputStream in, final OutputStream out) {
        while (!ClientHandlerPath.stop) {
            this.ch.handleClient(in, out);
        }
    }

    static {
        ClientHandlerPath.stop = false;
    }
}
