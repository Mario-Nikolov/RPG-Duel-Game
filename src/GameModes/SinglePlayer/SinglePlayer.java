package GameModes.SinglePlayer;

import Characters.Character;

import java.io.*;
import java.net.Socket;

public class SinglePlayer implements Runnable{
    public static  Socket clientSocket;

    public  SinglePlayer(Socket clientSocket){
        this.clientSocket = clientSocket;
    }

    @Override
    public void run(){
        try(BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true);){
        Character bot = Plays.botCharacter();
        Character myCharacter = Plays.chooseCharacter(in,out);

        out.println("\nYour character: ");
        myCharacter.showInfo(out);

        System.out.println("\nOpponent character: ");
        bot.showInfo(out);
        System.out.println(" ");

        //Start game
        Plays.startGame(myCharacter,bot);

    }catch (IOException e){
            e.printStackTrace();
        }
    }
}
