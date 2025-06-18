package mymoment;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("view/Register.fxml"));
        Scene scene = new Scene(root, 350, 500);

        String cssPath = getClass().getResource("/mymoment/css/styles.css").toExternalForm();
        scene.getStylesheets().add(cssPath);

        primaryStage.setTitle("MyMoment - Register");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}