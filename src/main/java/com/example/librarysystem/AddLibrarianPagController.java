package com.example.librarysystem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class AddLibrarianPagController {

    @FXML
    private TextField email;

    @FXML
    private Label errorMassageButton;

    @FXML
    private TextField mobile;

    @FXML
    private Label emailValidation;

    @FXML
    private Label nationalIdValidation;

    @FXML
    private TextField nationalID;

    @FXML
    private TextField password;

    @FXML
    private TextField userName;

    Validation check = new Validation();
    @FXML
    void addButton(ActionEvent event) {
        String name = userName.getText();
        String em = email.getText();
        String mobileNum = mobile.getText();
        String pass = password.getText();
        String national = nationalID.getText();
        if (check.validEmail(em) == false){
            emailValidation.setText("please enter a correct email");
        }else {
            emailValidation.setText(null);
        }
        if(check.isNumber(national) == false || national.length() != 14) {
            nationalIdValidation.setText("please enter a correct national ID");
        }else if(name != null && check.validEmail(em) == true  && mobileNum != null && pass != null){
            Librarian l2 = new Librarian(name,em,mobileNum,national,pass);
            l2.add();
            errorMassageButton.setText(l2.massage);
            errorMassageButton.setTextFill(Color.GREEN);
            remove();
        }else {
            errorMassageButton.setTextFill(Color.RED);
            errorMassageButton.setText("Missing data!!!");
            remove();
        }
    }

    public void remove(){
        userName.setText(null);
        email.setText(null);
        mobile.setText(null);
        password.setText(null);
        nationalID.setText(null);
        nationalIdValidation.setText(null);
        emailValidation.setText(null);
    }

}
