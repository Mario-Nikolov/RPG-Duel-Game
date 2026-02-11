package Client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private final Socket socket;
    private final PrintWriter out;
    private final BufferedReader in;

    private volatile boolean running = true;

    public Client(Socket socket){
        this.socket = socket;
        try{
            out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void stopClient() {
        running = false;
        try { socket.close(); } catch (IOException ignored) {}
    }

    public void listenForInput(){
        new Thread(() -> {
            try {
                while(running){
                    String textInput = in.readLine();

                    if (textInput == null) {
                        System.out.println("Disconnected from server.");
                        stopClient();
                        break;
                    }

                    if ("END".equals(textInput)) {
                        System.out.println("Game ended!");
                        stopClient();
                        break;
                    }

                    System.out.println(textInput);
                }
            } catch (IOException e) {
                if (running) e.printStackTrace();
            }
        }).start();
    }

    public void outputOption(){
        Thread t = new Thread(() -> {
            Scanner scanner = new Scanner(System.in);

            while(running){

                if (!running) break;

                String line;
                try {
                    line = scanner.nextLine();
                } catch (Exception e) {
                    break;
                }

                if (!running) break;

                try{
                    int output = Integer.parseInt(line);
                    out.println(output);
                } catch (NumberFormatException e) {
                    System.out.println("Enter a number (1-3).");
                }
            }
        });

        t.setDaemon(true);
        t.start();
    }
}
