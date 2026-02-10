package Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class ServerMain {
    public static final int PORT = 5111;
    public static void main(String[] args) {

        System.out.println("Server starting on port "+ PORT);
        try(ServerSocket serverSocket = new ServerSocket(PORT);)  {

            while (true) {
                System.out.println("Server listening on port " + PORT);

                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());

                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true);

                try {
                    int option;
                    out.println("_____MENU_____");
                    out.println("(1) Single player");
                    out.println("(2) Multiplayer");
                    out.println("(3) Exit game");
                    out.println("Enter option: ");

                    option = Integer.parseInt(in.readLine());
                    System.out.println("Server received: " + option);
                    switch (option) {
                        case 1 -> new Thread(new SinglePlayer(clientSocket)).start();   //Single player mode
                        case 2 -> new Thread(new MultiplayerDuel(clientSocket)).start();    //Multiplayer duel mode
                        case 3 -> new ExitGame(clientSocket).endClientGame();
                        default -> throw new InvalidOptionException("Invalid option! Choose again: ");
                    }
                }catch(NumberFormatException e){
                    out.println("Wrong number format!");
                }catch(InvalidOptionException e){
                    e.printStackTrace();
                }

            }

        } catch(SocketException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}


