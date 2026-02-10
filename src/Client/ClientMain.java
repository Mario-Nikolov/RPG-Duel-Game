package Client;

import java.io.IOException;
import java.net.Socket;

public class ClientMain {
    public static void main (String[] args) throws IOException {
        Socket socket = new Socket("localhost", 5111);
        Client client = new Client(socket);

            client.listenForInput();

            client.outputOption();

            client.listenForInput();

    }
}
