package Server;

import java.io.IOException;
import java.net.Socket;

public class CloseSocket {
    public static void close(Socket socket){
        if(!socket.isClosed()){
            try{
                socket.close();
            }catch (IOException e){
                e.printStackTrace();
            }

        }
    }
}
