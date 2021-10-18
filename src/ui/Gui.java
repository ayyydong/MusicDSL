package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.controllers.MainController;

import java.io.IOException;

public class Gui extends Application{
    private MainController mainController;

    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("GUI.fxml"));
        primaryStage.setTitle("myMusicGenerator");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) throws IOException {
        launch(args);
    }
}
