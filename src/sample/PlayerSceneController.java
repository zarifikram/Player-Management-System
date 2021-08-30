package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class PlayerSceneController {
    private final String SELL_BUTTON_STYLE = "-fx-background-color : #FF6565; -fx-text-fill : #434343; -fx-background-radius : 20";
    private final String BUY_BUTTON_STYLE = "-fx-background-color : #1ED760; -fx-text-fill : #434343; -fx-background-radius : 20";
    private final String REMOVELISTING_BUTTON_STYLE = "-fx-background-color : #B3B3B3; -fx-text-fill : #434343; -fx-background-radius : 20";

    @FXML
    private Label numberLabel;

    @FXML
    private ImageView faceImageView;

    @FXML
    private Label firstNameLabel;

    @FXML
    private Label lastNameLabel;

    @FXML
    private Label positionLabel;

    @FXML
    private ImageView countryImageView;

    @FXML
    private ImageView clubImageView;

    @FXML
    private Label ageLabel;

    @FXML
    private Label heightLabel;

    @FXML
    private Label salaryLabel;

    private Image faceImage, clubImage, countryImage;

    private Player player;

    @FXML
    private Button actionButton;
    private String userName;

    public void showPlayer(Player player){
        this.player = player;
        numberLabel.setText(Integer.toString(player.getNumber()));
        faceImage = new Image(player.getFaceImageSource());
        clubImage = new Image(player.getClubImageSource());
        countryImage = new Image(player.getCountryImageSource());
        firstNameLabel.setText(player.getFirstName());
        lastNameLabel.setText(player.getLastName());
        positionLabel.setText(player.getPosition());
        ageLabel.setText("Age : " + player.getAge());
        heightLabel.setText("Height : " + player.getHeight() + "m");
        faceImageView.setImage(faceImage);
        countryImageView.setImage(countryImage);
        clubImageView.setImage(clubImage);
        salaryLabel.setText("$" + player.getWeeklySalary());
    }

    @FXML
    public void onMouseHover(MouseEvent event){
        Button button = (Button) event.getSource();
        double width = button.getWidth();

        button.setPrefWidth(width*1.5);
    }

    @FXML
    public void offMouseHover(MouseEvent event){
        Button button = (Button) event.getSource();
        double width = button.getWidth();

        button.setPrefWidth(300);
    }

    @FXML
    public void onClickSellButton(ActionEvent event) throws IOException {
        if(actionButton.getText().equals("SELL")) {
            Client.communicator.write("list");
            Client.communicator.write(player);
        }
        else if(actionButton.getText().equals("BUY")){
            Client.communicator.write("buy");
            Client.communicator.write(player);
            Client.communicator.write(userName);
        }
        else{
            Client.communicator.write("unlist");
            Client.communicator.write(player);
        }
    }

    public void changeButtonTypeToSell(){
        actionButton.setStyle(SELL_BUTTON_STYLE);
        actionButton.setText("SELL");
    }

    public void changeButtonTypeToRemoveListing(){
        actionButton.setStyle(REMOVELISTING_BUTTON_STYLE);
        actionButton.setText("REMOVE LISTING");
    }

    public void changeButtonTypeToBuy(){
        actionButton.setStyle(BUY_BUTTON_STYLE);
        actionButton.setText("BUY");
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
