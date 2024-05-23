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
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class UpdateBookPageController implements Initializable {

    @FXML
    private TextField authorUpdate;

    @FXML
    private ComboBox<String> availability;

    @FXML
    private TextField bookNameUpdate;

    @FXML
    private Button browseImage;

    @FXML
    private Label deleteMassage;

    @FXML
    private Label errorMassage;

    @FXML
    private Label errorMassageSearch;

    @FXML
    private ImageView imageUpload;

    @FXML
    private ComboBox<String> typeBookUpdate;

    @FXML
    private Label updateMassage;

    @FXML
    private TextField userInputCode;

    private String src;
    String imageAbsolute;

    @FXML
    void deleteBook(ActionEvent event) throws MalformedURLException {
        Book b1 = new Book();
        String num = userInputCode.getText();
        if(b1.CheckBookCode(num) == true) {
            String code = userInputCode.getText();
            b1.delete(code);
            deleteMassage.setText(b1.massage);
            updateMassage.setText("");
            remove();
        }else {
            deleteMassage.setText("Please check the code of book");
            updateMassage.setText("");
            remove();
        }
    }

    @FXML
    void searchButton(ActionEvent event) throws MalformedURLException {
        Book s1 = new Book();
        String num = userInputCode.getText();
        if(s1.CheckBookCode(num) == true){
            s1.search(num);
            bookNameUpdate.setText(s1.getTitle());
            authorUpdate.setText(s1.getAuthor());
            typeBookUpdate.getSelectionModel().select(s1.getType());
            availability.getSelectionModel().select(s1.getAvailability());
            Path imageFile = Paths.get(s1.getImageRootSrc());
            imageUpload.setImage(new Image(imageFile.toUri().toURL().toExternalForm()));
            errorMassageSearch.setText("");
            updateMassage.setText("");

        }else {
            errorMassageSearch.setText("Please write a correct code");
            bookNameUpdate.setText("");
            authorUpdate.setText("");
            typeBookUpdate.getSelectionModel().select("");
            availability.getSelectionModel().select("");
            Path imageFile = Paths.get("D:\\Intellij\\LibrarySystem\\src\\main\\resources\\com\\example\\assets\\upload.png");
            imageUpload.setImage(new Image(imageFile.toUri().toURL().toExternalForm()));
        }
    }

    @FXML
    void updateBook(ActionEvent event) throws MalformedURLException {
        Book b1 = new Book();
        String code = userInputCode.getText();
        if(b1.CheckBookCode(code) == true) {
            String title = bookNameUpdate.getText();
            String image = src;
            String imageRoot = imageAbsolute;
            if(image != null|| imageRoot != null){
                b1.update(image,code,"imagesrc");
                b1.update(imageRoot,code,"rootImageSrc");
            }
            String author = authorUpdate.getText();
            String type = typeBookUpdate.getSelectionModel().getSelectedItem().toString();
            String avail = availability.getSelectionModel().getSelectedItem().toString();
            b1.update(title, code, "title");
            b1.update(author, code, "author");
            b1.update(type, code, "type");
            b1.update(avail, code, "availability");
            updateMassage.setTextFill(Color.GREEN);
            updateMassage.setText(b1.massage);
            remove();
        }else {
            updateMassage.setTextFill(Color.RED);
            updateMassage.setText("Please check the code of book");
            remove();
        }
    }

    @FXML
    void uploadImage(ActionEvent event) throws MalformedURLException {
        Book u1 = new Book();
        imageUpload.setImage(u1.uploadImage());
        src = u1.getImageSrc();
        imageAbsolute = u1.getImageRootSrc();
        errorMassage.setText(u1.massage);
    }

    public void initialize(URL location, ResourceBundle resources) {
        Book l1 = new Book();
        typeBookUpdate.getItems().addAll(l1.getBookType());
        availability.getItems().addAll(l1.getAvail());
    }

    public void remove() throws MalformedURLException {
        bookNameUpdate.setText(null);
        userInputCode.setText(null);
        authorUpdate.setText(null);
        typeBookUpdate.getSelectionModel().select(null);
        Path imageFile = Paths.get("D:\\Intellij\\LibrarySystem\\src\\main\\resources\\com\\example\\assets\\upload.png");
        imageUpload.setImage(new Image(imageFile.toUri().toURL().toExternalForm()));
    }

}
