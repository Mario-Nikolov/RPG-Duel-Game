package Server;

import java.net.Socket;

public class SinglePlayer implements Runnable{
    Socket clientSocket;

    public  SinglePlayer(Socket clientSocket){
        this.clientSocket = clientSocket;
    }

    @Override
    public void run(){

    }
}
