package com.example.librarysystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class AddUserPageController implements Initializable {

    @FXML
    private TextField codeBook;

    @FXML
    private Label codeNotFound;

    @FXML
    private DatePicker dateFrom;

    @FXML
    private DatePicker dateTo;

    @FXML
    private TextField email;

    @FXML
    private Label errorMassageButton;

    @FXML
    private TextField mobile;

    @FXML
    private ComboBox<String> title;

    @FXML
    private TextField userName;


    @FXML
    private Label emailValidation;

    Validation check = new Validation();

    @FXML
    void addUserButton(ActionEvent event) {
        String name = userName.getText();
        String em = email.getText();
        String mobileNum = mobile.getText();
        String code = codeBook.getText();
        LocalDate To = dateTo.getValue();
        LocalDate from = dateFrom.getValue();


        if (To != null && from != null && title.getSelectionModel().getSelectedItem() != null && name != null && em != null && mobileNum != null) {
            String start = To.toString();
            String end = from.toString();
            String jop = title.getSelectionModel().getSelectedItem().toString();
            Book b1 = new Book();
            if(b1.CheckBookCode(code) == true) {
                codeNotFound.setText(null);
                if(check.validEmail(em) == true) {
                    emailValidation.setText(null);
                    if (b1.checkBook(code) != true) {
                        codeNotFound.setText(null);
                        if (from.isBefore(To)) {
                            String sql = "INSERT INTO user (name,email,mobile,dateFrom,dateTo,code,job) VALUES (?,?,?,?,?,?,?);";
                            try {
                                Connection conn = connection.connect();
                                PreparedStatement pstmt = conn.prepareStatement(sql);
                                pstmt.setString(1, name);
                                pstmt.setString(2, em);
                                pstmt.setString(3, mobileNum);
                                pstmt.setString(4, end);
                                pstmt.setString(5, start);
                                pstmt.setString(6, code);
                                pstmt.setString(7, jop);
                                pstmt.executeUpdate();
                                b1.update("unavailable", code, "availability");
                                b1.Borrow(code, mobileNum, name, end, start);
                                history(name, mobileNum, code, start, end);
                                errorMassageButton.setText("Add successfully");
                                userName.setText("");
                                email.setText("");
                                mobile.setText("");
                                codeBook.setText("");
                                title.getSelectionModel().select("");
                                dateTo.setValue(null);
                                dateFrom.setValue(null);
                            } catch (SQLException e) {
                                System.out.println(e.getMessage());
                                errorMassageButton.setText("Missing data!!!");
                            }
                            errorMassageButton.setTextFill(Color.GREEN);
                        } else {
                            errorMassageButton.setTextFill(Color.RED);
                            errorMassageButton.setText("Please enter valid date!!!");
                        }
                    } else {
                        codeNotFound.setText("Invalid Code!!!");
                    }
                }else {
                    emailValidation.setText("please enter a correct email");
                }
            }else {
                codeNotFound.setText("Please enter a correct Code!!!");
            }
        }else {
            errorMassageButton.setTextFill(Color.RED);
            errorMassageButton.setText("Missing data!!!");
        }
    }

    public void initialize(URL location, ResourceBundle resources) {
        User u1 = new User();
        title.getItems().addAll(u1.getTitle());
    }

    public void history(String name,String mob,String code,String to,String from){
        String sql = "INSERT INTO history (name,mobileNum,bookCode,dateTo,dateFrom) VALUES (?,?,?,?,?);";

        try{
            Connection conn =  connection.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, mob);
            pstmt.setString(3, code);
            pstmt.setString(4, to);
            pstmt.setString(5, from);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
