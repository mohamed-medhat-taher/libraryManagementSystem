package com.example.librarysystem;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class BookCard {

    @FXML
    private ImageView imagesrc;
    @FXML
    private Label availability;
    @FXML
    private Label author;

    @FXML
    private Label bookTitle;


    @FXML
    private Label type;

    public void setData(Books book){
        Image image = new Image(getClass().getResourceAsStream(book.getImageSrc()));
        imagesrc.setImage(image);
        bookTitle.setText(book.getName());
        author.setText(book.getAuthor());
        type.setText(book.getType());
        if(book.getAvailability().equals("available")){
            availability.setTextFill(Color.GREEN);
        }else {
            availability.setTextFill(Color.RED);
        }
        availability.setText(book.getAvailability());
    }

}