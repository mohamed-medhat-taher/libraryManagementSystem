package com.example.librarysystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeAdminPageController implements Initializable {

    @FXML
    private BorderPane pb;

    private Stage stage;
    private Scene scene;

    @FXML
    void AddLibrarianPage(MouseEvent event) {
        loadPage("AddLibrarianPage.fxml");
    }

    @FXML
    void addBookPage(MouseEvent event) {
        loadPage("addPage.fxml");
    }

    @FXML
    void addUserPage(MouseEvent event) {
        loadPage("addUserPage.fxml");
    }

    @FXML
    void historyPage(MouseEvent event) throws IOException {
       loadPage("historyPage.fxml");
    }

    @FXML
    void logout(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("loginPage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void searchPage(MouseEvent event) {
        loadPage("SearchPage.fxml");
    }

    @FXML
    void updateBookPage(MouseEvent event) {
        loadPage("updateBookPage.fxml");
    }

    @FXML
    void updateLibrarianPage(MouseEvent event) {
        loadPage("updateLibrarianPage.fxml");
    }

    @FXML
    void updateUserPage(MouseEvent event) {
        loadPage("UpdateUserPage.fxml");
    }

    public void loadPage(String page) {
        Parent root = null;

        try {
            root = FXMLLoader.load(getClass().getResource(page));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        pb.setCenter(root);

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //TODO
    }
}