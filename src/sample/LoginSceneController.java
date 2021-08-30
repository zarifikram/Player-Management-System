package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Socket;

public class LoginSceneController {
    @FXML
    TextField userNameTextField;
    private Parent root;
    private Stage stage;
    private Scene scene;
    final private String MENU_SCENE_FILE = "menuScene.fxml";
    private String userName;
    private FXMLLoader menuSceneLoader;

    public void loginToMenuScene(ActionEvent event) throws Exception {
        setUserNameFromTextField();
        getMenuSceneLoader();
        getRoot();
        showUsernameToMenuScene();
        setMenuSceneData();
        switchToMenuScene(event);
    }

    private void switchToMenuScene(ActionEvent event){
        Scene currentScene = ((Node)event.getSource()).getScene();
        stage = (Stage)currentScene.getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void setUserNameFromTextField(){
        userName = userNameTextField.getText();
    }
    private void getMenuSceneLoader(){
        menuSceneLoader = new FXMLLoader(getClass().getResource(MENU_SCENE_FILE));
    }
    private void getRoot() throws IOException {
        root = menuSceneLoader.load();
    }

    private void showUsernameToMenuScene() {
        MenuSceneController menuSceneController = menuSceneLoader.getController();
        menuSceneController.setUserName(userName);
        menuSceneController.setWelcomeLabel();
    }

    private void setMenuSceneData() throws Exception {
        MenuSceneController menuSceneController = menuSceneLoader.getController();
        new Client();
        new MarketUpdateListenerThread(menuSceneController);
    }
}

class Client {
    static public NetworkUtil communicator;
    private Socket clientSocket;

    public Client() throws IOException {
        connectClientToServer();
        communicator = new NetworkUtil(clientSocket);

    }
    private void connectClientToServer() throws IOException {
        clientSocket = new Socket("127.0.0.1", 22222);
        System.out.println("Just got connected!");
    }
}