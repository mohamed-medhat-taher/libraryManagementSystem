package com.example.librarysystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class LoginPageController {

    @FXML
    private Label errormessage;

    @FXML
    private PasswordField password;

    @FXML
    private TextField userName;

    private Stage stage;
    private Scene scene;

    @FXML
    void loginFun(ActionEvent event) throws IOException {
        String user = userName.getText();
        String pass = password.getText();

        if(checkLibCredentials(user, pass)){
            Parent root = FXMLLoader.load(getClass().getResource("homePage.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else if (checkAdminCredentials(user,pass)) {
            Parent root = FXMLLoader.load(getClass().getResource("homeAdminPage.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }else {
            errormessage.setText("User or password wrong");
        }
    }


    public Boolean checkAdminCredentials(String email, String password) {
        String query = "SELECT 1 FROM admin WHERE email = ? AND password = ?";
        try (Connection conn = connection.connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next(); // Returns true if there's a result, false otherwise
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public Boolean checkLibCredentials(String email, String password) {
        String query = "SELECT 1 FROM librarian WHERE email = ? AND password = ?";
        try (Connection conn = connection.connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next(); // Returns true if there's a result, false otherwise
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }



}
