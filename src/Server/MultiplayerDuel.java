package Server;

import java.net.Socket;

public class MultiplayerDuel implements Runnable {
    Socket clientSocket;

    public MultiplayerDuel(Socket clientSocket){
        this.clientSocket=clientSocket;
    }

    @Override
    public void run(){

    }

}
