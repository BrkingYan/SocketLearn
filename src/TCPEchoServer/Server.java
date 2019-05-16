package TCPEchoServer;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

public class Server {
    private static final int BUFSIZE = 32;
    public static void main(String[] args) throws IOException {

        if (args.length != 1)
            throw new IllegalArgumentException("Parameter(s):<Port>");
        int servPort = 7;

        ServerSocket serverSocket = new ServerSocket(servPort);

        int recvMsgSize;
        byte[] receiveBuf = new byte[BUFSIZE];
        while (true){
            Socket clientSocket = serverSocket.accept();
            SocketAddress clientAddress = clientSocket.getRemoteSocketAddress();
            System.out.println("handling client at " + clientAddress);

            InputStream in = clientSocket.getInputStream();
            OutputStream out = clientSocket.getOutputStream();

            while ((recvMsgSize = in.read(receiveBuf)) != -1){
                out.write(receiveBuf,0,recvMsgSize);
            }

            String msgFromClient = new String(receiveBuf) ;
            System.out.println("message from client: " + msgFromClient);

            /*byte[] echoMessage = "server echo".getBytes();
            out.write(echoMessage,0,echoMessage.length);
            System.out.println("******message echoed******");*/

            clientSocket.close();
        }
    }
}
