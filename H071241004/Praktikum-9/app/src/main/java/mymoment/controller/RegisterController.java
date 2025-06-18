package mymoment.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import mymoment.model.User;

import java.io.File;
import java.io.IOException;

public class RegisterController {

    @FXML
    private TextField nickNameField;

    @FXML
    private TextField fullNameField;

    @FXML
    private ImageView profileImageView;

    @FXML
    private Button submitButton;

    private File selectedProfileImage;

    @FXML
    void handleUploadButton(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Pilih Foto Profil");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        selectedProfileImage = fileChooser.showOpenDialog(submitButton.getScene().getWindow());

        if (selectedProfileImage != null) {
            Image image = new Image(selectedProfileImage.toURI().toString());
            profileImageView.setImage(image);
        }
    }

    @FXML
    void handleSubmitButton(ActionEvent event) throws IOException {
        String nickName = nickNameField.getText();
        String fullName = fullNameField.getText();

        if (nickName.isEmpty() || fullName.isEmpty() || selectedProfileImage == null) {
            // Optional: Show an alert for incomplete data
            System.out.println("Harap isi semua data dan pilih gambar profil.");
            return;
        }

        User user = User.getInstance();
        user.setNickName(nickName);
        user.setFullName(fullName);
        user.setProfileImage(new Image(selectedProfileImage.toURI().toString()));

        // Pindah ke scene Home
        Parent homeRoot = FXMLLoader.load(getClass().getResource("/mymoment/view/Home.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(homeRoot);
        stage.setScene(scene);
        stage.setTitle("MyMoment - Home");
        stage.show();
    }
}