package sample;



import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Server {
    private ServerSocket serverSocket;
    private static final String DATABASE_DIR = "players.txt";
    public static Database database = new Database(DATABASE_DIR);
    private List<NetworkUtil> communicatorList = new ArrayList();
    public Server() throws IOException {
        serverSocket = new ServerSocket(22222);
        while(true){
            Socket clientSocket = serverSocket.accept();
            startThreadFor(clientSocket, communicatorList);
        }
    }

    private void startThreadFor(Socket clientSocket, List<NetworkUtil> communicatorList) throws IOException {
        new ServerThread(clientSocket, communicatorList);
    }

    public static void main(String[] args) throws Exception {
        database.readFromSource();
        System.out.println(database);

        new Server();

    }
}
