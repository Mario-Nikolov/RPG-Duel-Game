package Network.Server;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ExitGame {
    Socket clientSocket;
    public ExitGame(Socket socket){
        clientSocket = socket;
    }

    public void endClientGame(){
        try(PrintWriter out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true);){
            out.println("END");
            CloseSocket.close(clientSocket);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
