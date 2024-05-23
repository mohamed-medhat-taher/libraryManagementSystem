package com.example.librarysystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class addBookPageController implements Initializable {

    @FXML
    private Button addbookButton;

    @FXML
    private TextField author;

    @FXML
    private TextField bookName;

    @FXML
    private Button browseImage;

    @FXML
    private TextField codeBook;

    @FXML
    private Label errorMassageButton;

    @FXML
    private Label errormasseage;

    @FXML
    private ImageView imageBook;

    @FXML
    private ComboBox<String> typeBook;

    private Stage stage;
    private Scene scene;

    private String src;
    String imageabsulute;

    Validation check = new Validation();
    Book b1 = new Book();
    @FXML
    void addBook(ActionEvent event) throws MalformedURLException {
        String title = bookName.getText();
        String code = codeBook.getText();
        String auth = author.getText();
        String image = src;
        String imageSrc = imageabsulute;

        if(check.isNumber(code) == true && code.length() == 9 && b1.CheckBookCode(code) == false) {
            if (typeBook.getSelectionModel().getSelectedItem() != null && title != null && code != null && author != null) {
                String type = typeBook.getSelectionModel().getSelectedItem().toString();
                Book b1 = new Book(title, code, auth, type, image, imageSrc);
                b1.add();
                errorMassageButton.setText(b1.massage);
                errorMassageButton.setTextFill(Color.GREEN);
                remove();

            } else {
                errorMassageButton.setTextFill(Color.RED);
                errorMassageButton.setText("Missing data!!!");
            }
        }else {
            errorMassageButton.setTextFill(Color.RED);
            errorMassageButton.setText("Invalid code!!");
        }
    }

    @FXML
    void uploadimage(ActionEvent event) throws MalformedURLException {
        Book u1 = new Book();
        imageBook.setImage(u1.uploadImage());
        src = u1.getImageSrc();
        imageabsulute = u1.getImageRootSrc();
        errormasseage.setText(u1.massage);
    }

    public void initialize(URL location, ResourceBundle resources) {
        Book l1 = new Book();
        typeBook.getItems().addAll(l1.getBookType());}

    public void remove() throws MalformedURLException {
        bookName.setText(null);
        codeBook.setText(null);
        author.setText(null);
        typeBook.getSelectionModel().select(null);
        Path imageFile = Paths.get("D:\\Intellij\\LibrarySystem\\src\\main\\resources\\com\\example\\assets\\upload.png");
        imageBook.setImage(new Image(imageFile.toUri().toURL().toExternalForm()));
    }

}
