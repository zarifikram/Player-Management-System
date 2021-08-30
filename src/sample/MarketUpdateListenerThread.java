package sample;

import javafx.application.Platform;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MarketUpdateListenerThread implements Runnable{
    MenuSceneController menuSceneController;
    public MarketUpdateListenerThread(MenuSceneController menuSceneController) {
        this.menuSceneController = menuSceneController;

        new Thread(this, "listener").start();

    }

    @Override
    public void run() {
        while(true){
            System.out.println("2");
            try {
                Object msg = Client.communicator.read();
                if(msg.equals("update")) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                menuSceneController.callAppropriateFunctionBasedOnCurrentLabel();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
                else if(msg instanceof Club){
                    System.out.println("msg instanceof club");
                    Club updatedClub = (Club) msg;
                    menuSceneController.setDatabase(updatedClub);
                }
                else{
                    List<Player> searchResult = (List<Player>) msg;
                    List<Player> transferList = getTransferList(searchResult);
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            menuSceneController.showInGrid(transferList);
                        }
                    });

                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

    private List<Player> getTransferList(List<Player> playerList){
        List<Player> transferList = new ArrayList();
        for(Player player : playerList){
            if(player.getClub().equals(menuSceneController.getUserName())) continue;
            transferList.add(player);
        }
        return transferList;
    }
}
