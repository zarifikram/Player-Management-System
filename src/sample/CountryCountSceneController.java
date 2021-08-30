package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CountryCountSceneController {

    @FXML
    private Label countryNameLabel;

    @FXML
    private Label playerCountLabel;

    public void showCountryCount(String countryName, int playerCount){
        countryNameLabel.setText(countryName);
        playerCountLabel.setText("Player Count :    " + playerCount);
    }
}
