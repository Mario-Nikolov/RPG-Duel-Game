package Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(5001);) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true);

                try {
                    int option;
                    option = Integer.parseInt(in.readLine());
                    switch (option) {
                        case 1 -> new Thread(new SinglePlayer(clientSocket)).start();   //Single player mode
                        case 2 -> new Thread(new MultiplayerDuel(clientSocket)).start();    //Multiplayer duel mode
                        case 3 -> CloseSocket.close(clientSocket);
                        default -> throw new InvalidOptionException("Invalid option! Choose again: ");
                    }
                }catch(NumberFormatException e){
                    out.println("Wrong number format!");
                }catch(InvalidOptionException e){
                    e.getMessage();
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


