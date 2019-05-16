package TCPEchoClient;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;

public class Client {
    public static void main(String[] args) throws IOException {
        if ((args.length < 2) || (args.length > 3))
            throw  new IllegalArgumentException("Parameter(s):<Server> <word> [<Port>]");
        //127.0.0.1  "msg"  7
        String server = args[0];//127.0.0.1
        byte[] data = args[1].getBytes();

        int servPort = (args.length==3) ? Integer.parseInt(args[2]) : 7;
        Socket socket = new Socket(server,servPort);
        System.out.println("Connected to server ... sending echo string");

        InputStream in = socket.getInputStream();
        OutputStream out = socket.getOutputStream();
        out.write(data);

        int totalBytesRcvd = 0;
        int bytesRcvd;
        while (totalBytesRcvd < data.length){
            if ((bytesRcvd = in.read(data,totalBytesRcvd,data.length-totalBytesRcvd)) == -1){
                throw new SocketException("Connection closed premeaturely");
            }
            totalBytesRcvd += bytesRcvd;
        }
        System.out.println("Received: " + new String(data));

        socket.close();
    }
}
