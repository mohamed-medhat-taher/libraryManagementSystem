package com.example.librarysystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SearchPageController implements Initializable {

    @FXML
    private Label author;

    @FXML
    private Label availability;

    @FXML
    private GridPane cardLayout;

    @FXML
    private Label code;

    @FXML
    private Label errorMassage;

    @FXML
    private ImageView image;

    @FXML
    private Label title;

    @FXML
    private Label type;

    @FXML
    private TextField userInputCode;

    private List<Books> listView;
    private Stage stage;
    private Scene scene;
    int column = 0;
    int row = 1;

    @FXML
    void searchButton(ActionEvent event) throws MalformedURLException {
        Book s1 = new Book();
        String num = userInputCode.getText();
        if(s1.CheckBookCode(num) == true){
            s1.search(num);
            title.setText(s1.getTitle());
            author.setText(s1.getAuthor());
            code.setText(s1.getCode());
            type.setText(s1.getType());
            if(s1.getAvailability().equals("available")){
                availability.setTextFill(Color.GREEN);
            }else {
                availability.setTextFill(Color.RED);
            }
            availability.setText(s1.getAvailability());
            Path imageFile = Paths.get(s1.getImageRootSrc());
            image.setImage(new Image(imageFile.toUri().toURL().toExternalForm()));
            errorMassage.setText("");
        }else {
            errorMassage.setText("Please write a correct code");
            title.setText("");
            author.setText("");
            code.setText("");
            type.setText("");
            availability.setText("");
            Path imageFile = Paths.get("");
            image.setImage(new Image(imageFile.toUri().toURL().toExternalForm()));
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listView = new ArrayList<>(recentlyadd());
        try{
            for(Books book : listView){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("book-card.fxml"));
                VBox cardBox = fxmlLoader.load();
                BookCard card = fxmlLoader.getController();
                card.setData(book);

                if(column == 4){
                    column = 0;
                    ++row;
                }
                cardLayout.add(cardBox,column++,row);
                GridPane.setMargin(cardBox,new Insets(10));

            }


        }catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    private List<Books> recentlyadd(){
        List<Books> ls = new ArrayList<>();
        Books books = new Books();

        Statement stmt = null;
        try {
            Connection conn = connection.connect();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM books ;" );
            while ( rs.next() ) {

                String title = rs.getString("title");
                String imagesrc = rs.getString("imagesrc");
                String author  = rs.getString("author");
                String  type = rs.getString("type");
                String avai = rs.getString("availability");
                books = new Books();
                books.setName(title);
                books.setImageSrc(imagesrc);
                books.setAuthor(author);
                books.setType(type);
                if(avai == "available"){

                }
                books.setAvailability(avai);
                ls.add(books);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch ( SQLException e ) {
            System.err.println( e.getMessage() );
        }
//

        return ls;
    }

}