package sample;

import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;

public class ServerThread implements Runnable{
    private Socket clientSocket;
    private NetworkUtil communicator;
    private List<NetworkUtil> communicatorList;
    private String clubName;
    private Thread thread;
    private Club club;
    public ServerThread(Socket clientSocket, List<NetworkUtil> communicatorList) throws IOException {
        this.clientSocket = clientSocket;
        this.communicatorList = communicatorList;
        communicator = new NetworkUtil(clientSocket);
        communicatorList.add(communicator);
        this.thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        try{
            while(true){
                Object msg = receive();

                if(msg.equals("transfer market")) {
                    System.out.println("trying");
                    send(Server.database.getTransferPlayers());
                }
                else if(msg.equals("list")) {
                    {
                        Player player = (Player) receive();
                        Server.database.listPlayer(player);
                        for(NetworkUtil communicator : communicatorList) communicator.write("update");
                    }
                }
                else if(msg.equals("unlist")) {
                    {
                        Player player = (Player) receive();
                        Server.database.unListPlayer(player);
                        for(NetworkUtil communicator : communicatorList) communicator.write("update");
                    }
                }
                else if(msg.equals("buy")) {
                    {
                        Player player = (Player) receive();
                        String userName = (String) receive();
                        Server.database.transferPlayer(player, userName);
                        for(NetworkUtil communicator : communicatorList) communicator.write("update");
                        Server.database.writeToSource();
                    }
                }

                else if(msg.equals("getDatabase")) {
                    String clubName = (String) receive();
                    Club updatedClub = Server.database.getClub(clubName);
                    System.out.println(updatedClub);
                    send(updatedClub);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void send(Object object) throws IOException {
        communicator.write(object);
    }

    private Object receive() throws IOException, ClassNotFoundException {
        return communicator.read();
    }

}
