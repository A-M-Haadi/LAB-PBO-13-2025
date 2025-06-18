package mymoment.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mymoment.model.Post;
import mymoment.model.User;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private ImageView profileImageView;

    @FXML
    private Label nickNameLabel;

    @FXML
    private Label fullNameLabel;

    @FXML
    private Button addPostButton;

    @FXML
    private TilePane postContainer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        User user = User.getInstance();
        nickNameLabel.setText(user.getNickName());
        fullNameLabel.setText(user.getFullName());
        if (user.getProfileImage() != null) {
            profileImageView.setImage(user.getProfileImage());
        }
    }

    @FXML
    void handleAddPost() {
        // Membuat window baru untuk upload post
        Stage postStage = new Stage();
        postStage.initModality(Modality.APPLICATION_MODAL);
        postStage.setTitle("Upload Post");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);

        ImageView postImageView = new ImageView();
        postImageView.setFitHeight(100);
        postImageView.setFitWidth(100);
        postImageView.setPreserveRatio(true);

        Button uploadImageButton = new Button("Upload Image");
        TextArea captionArea = new TextArea();
        captionArea.setPromptText("Caption");
        Button submitPostButton = new Button("Submit");

        final File[] selectedPostImage = new File[1];

        uploadImageButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Pilih Gambar Post");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
            selectedPostImage[0] = fileChooser.showOpenDialog(postStage);
            if (selectedPostImage[0] != null) {
                postImageView.setImage(new Image(selectedPostImage[0].toURI().toString()));
            }
        });

        submitPostButton.setOnAction(e -> {
            if (selectedPostImage[0] != null && !captionArea.getText().isEmpty()) {
                Image image = new Image(selectedPostImage[0].toURI().toString());
                String caption = captionArea.getText();
                addPostToHome(new Post(caption, image));
                postStage.close();
            } else {
                 System.out.println("Harap pilih gambar dan isi caption.");
            }
        });

        grid.add(uploadImageButton, 0, 0);
        grid.add(postImageView, 1, 0);
        grid.add(new Label("Caption:"), 0, 1);
        grid.add(captionArea, 1, 1);
        grid.add(submitPostButton, 1, 2);

        Scene postScene = new Scene(grid, 400, 250);
        postStage.setScene(postScene);
        postStage.showAndWait();
    }

    private void addPostToHome(Post post) {
        ImageView imageView = new ImageView(post.getPostImage());
        imageView.setFitHeight(120);
        imageView.setFitWidth(120);
        imageView.setPreserveRatio(true);

        // Menambahkan caption saat di-hover
        Tooltip tooltip = new Tooltip(post.getCaption());
        Tooltip.install(imageView, tooltip);

        postContainer.getChildren().add(imageView);
    }
}