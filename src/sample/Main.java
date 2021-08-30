package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    private Scene scene;
    private final int HEIGHT = 1920;
    private final int WIDTH = 1080;
    private Stage stage;
    final private String LOGIN_SCENE_FILE = "loginScene.fxml";
    final private String ICON_FILE_PATH = "footballDatabaseLogo.png";

    private void setIconToStage(){
        Image icon = new Image(ICON_FILE_PATH);
        stage.getIcons().add(icon);
    }

    private void readyFinalStage(){
        stage.setTitle("Club Database");
        stage.setResizable(false);
        setIconToStage();
        stage.setScene(scene);
    }

    private Scene createAndGetSceneFromParent(Parent parent){
        scene = new Scene(parent);

        return scene;
    }
    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource(LOGIN_SCENE_FILE));
        this.stage = stage;
        Scene scene = createAndGetSceneFromParent(root);
        readyFinalStage();

        this.stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
