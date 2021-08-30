package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;


import java.io.IOException;
import java.util.List;
import java.util.Map;


public class MenuSceneController {
    private final String DATABASE_DIR = "players.txt";
    public Club database;

    public String getUserName() {
        return userName;
    }

    private String userName;

    @FXML
    private ImageView clubImageView;

    @FXML
    private Label welcomeLabel;
    private final String DEFAULT_BUTTON_STYLE = "-fx-background-color : #000000; -fx-text-fill : #B3B3B3";
    private final String ACTIVE_BUTTON_STYLE = "-fx-background-color : #434343; -fx-text-fill : #FFFFFF";
    @FXML
    TextField primaryTextField;

    @FXML
    GridPane grid;

    @FXML
    TextField secondaryTextField;

    @FXML
    Button byPlayerName;

    @FXML
    Button byCountry;

    @FXML
    Button byPosition;

    @FXML
    Button bySalaryRange;

    @FXML
    Button countryWisePlayerCount;

    @FXML
    Button maximumSalaryPlayer;

    @FXML
    Button maximumAgePlayer;

    @FXML
    Button maximumHeightPlayer;

    @FXML
    Button transferMarketButton;

    @FXML
    Label salaryLabel;

    private Button currentButton = byPlayerName;


    private void clearButtonEffect(){
        byPlayerName.setStyle(DEFAULT_BUTTON_STYLE);
        byCountry.setStyle(DEFAULT_BUTTON_STYLE);
        byPosition.setStyle(DEFAULT_BUTTON_STYLE);
        bySalaryRange.setStyle(DEFAULT_BUTTON_STYLE);
        countryWisePlayerCount.setStyle(DEFAULT_BUTTON_STYLE);
        maximumAgePlayer.setStyle(DEFAULT_BUTTON_STYLE);
        maximumSalaryPlayer.setStyle(DEFAULT_BUTTON_STYLE);
        maximumHeightPlayer.setStyle(DEFAULT_BUTTON_STYLE);
    }

    private void giveButtonEffect(Button button){
        button.setStyle(ACTIVE_BUTTON_STYLE);
    }

    private void giveButtonEffect(){
        currentButton.setStyle(ACTIVE_BUTTON_STYLE);
    }

    public void setDatabase(Club club){
        database = club;
    }

    public void setUserName(String userName){
        this.userName = userName;

    }

    public void setWelcomeLabel(){
        currentButton = byPlayerName;
        giveButtonEffect();
        welcomeLabel.setText("Hello, " + userName + "!");
        System.out.println("hello");
        String clubImageURL = "img/" + userName.replace(" ", "") + ".png";
        System.out.println(clubImageURL);
        Image clubImage = new Image("img/" + userName.replace(" ", "") + ".png");
        clubImageView.setImage(clubImage);
    }

    private void hideSecondaryTextField(){
        secondaryTextField.setVisible(false);
    }

    private void showSecondaryTextField(){
        secondaryTextField.setVisible(true);
    }

    private void hidePrimaryTextField(){
        primaryTextField.setVisible(false);
    }
    private void showPrimaryTextField(){
        primaryTextField.setVisible(true);
    }
    private void clearGrid(){
        grid.getChildren().clear();
    }

    private void clear(){
        grid.getChildren().clear();
        primaryTextField.setText("");
        secondaryTextField.setText("");
    }

    public void onClickByPlayerName(ActionEvent event){
        clear();
        currentButton = byPlayerName;
        hideSecondaryTextField();
        showPrimaryTextField();
        primaryTextField.setPromptText("Player Name");
        clearButtonEffect();
        giveButtonEffect();
    }

    public void onClickByCountry(ActionEvent event){
        clear();
        currentButton = byCountry;
        hideSecondaryTextField();
        primaryTextField.setPromptText("Player Country Of Origin");
        showPrimaryTextField();
        clearButtonEffect();
        giveButtonEffect();
    }

    public void onClickByPosition(ActionEvent event){
        clear();
        currentButton = byPosition;
        hideSecondaryTextField();
        primaryTextField.setPromptText("Player Position");
        showPrimaryTextField();
        clearButtonEffect();
        giveButtonEffect();
    }

    public void onClickBySalaryRange(ActionEvent event){
        clear();
        currentButton = bySalaryRange;
        primaryTextField.setPromptText("Min Limit");
        secondaryTextField.setPromptText("Max Limit");
        showSecondaryTextField();
        showPrimaryTextField();
        clearButtonEffect();
        giveButtonEffect();
    }

    public void onClickCountryWisePlayerCount(ActionEvent event) throws InterruptedException {
        clear();
        currentButton = countryWisePlayerCount;
        hideSecondaryTextField();
        hidePrimaryTextField();
        clearButtonEffect();
        giveButtonEffect();
        callAppropriateFunctionBasedOnCurrentLabel();
    }

    public void onClickMaximumSalaryPlayer(ActionEvent event) throws InterruptedException {
        clear();
        currentButton = maximumSalaryPlayer;
        hideSecondaryTextField();
        hidePrimaryTextField();
        clearButtonEffect();
        giveButtonEffect();
        callAppropriateFunctionBasedOnCurrentLabel();
    }

    public void onClickMaximumAgePlayer(ActionEvent event) throws InterruptedException {
        clear();
        currentButton = maximumAgePlayer;
        hideSecondaryTextField();
        hidePrimaryTextField();
        clearButtonEffect();
        giveButtonEffect();
        callAppropriateFunctionBasedOnCurrentLabel();
    }

    public void onClickMaximumHeightPlayer(ActionEvent event) throws InterruptedException {
        clear();
        currentButton = maximumHeightPlayer;
        hideSecondaryTextField();
        hidePrimaryTextField();
        clearButtonEffect();
        giveButtonEffect();
        callAppropriateFunctionBasedOnCurrentLabel();
    }

    public void onClickBuyButton(ActionEvent event) throws InterruptedException {
        clear();
        currentButton = transferMarketButton;
        hidePrimaryTextField();
        hideSecondaryTextField();
        clearButtonEffect();
        callAppropriateFunctionBasedOnCurrentLabel();
    }

    synchronized public void callAppropriateFunctionBasedOnCurrentLabel() throws InterruptedException {
        clearGrid();
        sendUpdateDatabaseRequest();
        wait(200);
        updateSalaryLabel();

        if(currentButton == byPlayerName){
            String playerName = primaryTextField.getText();
            List<Player> searchResult = database.searchName(playerName);
            showInGrid(searchResult);
        }
        else if(currentButton == byCountry){
            String countryName = primaryTextField.getText();
            List<Player> searchResult = database.searchCountry(countryName);
            showInGrid(searchResult);
        }else if(currentButton == byPosition){
            String positionName = primaryTextField.getText();
            List<Player> searchResult = database.searchPosition(positionName);
            showInGrid(searchResult);
        }else if(currentButton == bySalaryRange){
            int lowestRange = Integer.parseInt(primaryTextField.getText());
            int highestRange = Integer.parseInt(secondaryTextField.getText());
            List<Player> searchResult = database.searchSalary(lowestRange, highestRange);
            showInGrid(searchResult);
        }else if(currentButton == countryWisePlayerCount){
            Map countryPlayerCount = database.countryWiseCount();
            showInGrid(countryPlayerCount);
        }else if(currentButton == maximumAgePlayer){
            List<Player> searchResult = database.maxAge();
            showInGrid(searchResult);
        }else if(currentButton == maximumHeightPlayer){
            List<Player> searchResult = database.maxHeight();
            showInGrid(searchResult);
        }else if(currentButton == maximumSalaryPlayer){
            List<Player> searchResult = database.maxSalary();
            showInGrid(searchResult);
        }else if(currentButton == transferMarketButton){
            try {
                Client.communicator.write("transfer market");
                wait(200);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void showInGrid(Map countryPlayerCount) {
        int row = 0;
        try {
            for(Object countryName : countryPlayerCount.keySet()){
                int playerCount = (int) countryPlayerCount.get(countryName);
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("countryCountScene.fxml"));
                Parent root = fxmlLoader.load();
                CountryCountSceneController countryCountSceneController = fxmlLoader.getController();
                countryCountSceneController.showCountryCount((String) countryName, playerCount);

                grid.add(root, 0, row++);
                GridPane.setMargin(root, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    synchronized public void sendUpdateDatabaseRequest() {
        try {
            Client.communicator.write("getDatabase");
            Client.communicator.write(userName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void showInGrid(List<Player> searchResult){
        int row = 0;
        try {
            for(Player player : searchResult){
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("playerScene.fxml"));
                Parent root = fxmlLoader.load();
                PlayerSceneController playerSceneController = fxmlLoader.getController();
                playerSceneController.showPlayer(player);
                playerSceneController.setUserName(userName);
//                player.print();
                if(player.isListed() && isOfSameClub(player)){
                    playerSceneController.changeButtonTypeToRemoveListing();
                }
                else if(player.isListed() && !isOfSameClub(player)){
                    playerSceneController.changeButtonTypeToBuy();
                }
                else {
                    playerSceneController.changeButtonTypeToSell();
                }

                grid.add(root, 0, row++);
                GridPane.setMargin(root, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void search(KeyEvent event){
        if(event.getCode().equals(KeyCode.ENTER)) {
            try {
                callAppropriateFunctionBasedOnCurrentLabel();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void onMouseHover(MouseEvent event){
        Button button = (Button) event.getSource();
        if(button == currentButton) return;
        button.setStyle("-fx-background-color : #000000; -fx-text-fill : #ffffff");
    }

    @FXML
    public void offMouseHover(MouseEvent event){
        Button button = (Button) event.getSource();
        if(button == currentButton) return;
        button.setStyle("-fx-background-color : #000000; -fx-text-fill : #b3b3b3");
    }

    private boolean isOfSameClub(Player player){
        return player.getClub().equals(userName);
    }

    public void updateSalaryLabel(){
        salaryLabel.setText("Total Salary :  $" + Integer.toString((int)database.totalSalary()));
    }


}
