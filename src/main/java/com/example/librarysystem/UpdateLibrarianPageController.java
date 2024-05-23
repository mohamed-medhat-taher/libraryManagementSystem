package com.example.librarysystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class UpdateLibrarianPageController {

    @FXML
    private Label deleteMassage;

    @FXML
    private TextField email;

    @FXML
    private Label errorMassage1;

    @FXML
    private Label errorMassageSearch;

    @FXML
    private TextField mobilenum;

    @FXML
    private TextField name;

    @FXML
    private TextField nationalId;

    @FXML
    private TextField password;

    @FXML
    private Label updateMassage;

    @FXML
    private TextField userInputCode;

    @FXML
    void deleteLib(ActionEvent event) {
        Librarian b1 = new Librarian();
        String num = userInputCode.getText();
        if(b1.CheckLibrarian(num) == true) {
            String code = userInputCode.getText();
            b1.delete(code);
            deleteMassage.setTextFill(Color.GREEN);
            deleteMassage.setText(b1.massage);
            remove();
        }else {
            deleteMassage.setTextFill(Color.RED);
            deleteMassage.setText("Please check the code of book");
        }
    }

    @FXML
    void searchButton(ActionEvent event) {
        Librarian s1 = new Librarian();
        String num = userInputCode.getText();
        updateMassage.setText(null);
        deleteMassage.setText(null);
        if(s1.CheckLibrarian(num) == true){
            s1.search(num);
            name.setText(s1.getName());
            mobilenum.setText(s1.getMobile());
            password.setText(s1.getPassword());
            nationalId.setText(s1.getNationalId());
            email.setText(s1.getEmail());
            errorMassageSearch.setText("");
            updateMassage.setText("");
        }else {
            errorMassageSearch.setText("Please write a correct code");
            name.setText(s1.getName());
            mobilenum.setText(s1.getMobile());
            password.setText(s1.getPassword());
            nationalId.setText(s1.getNationalId());
            email.setText(s1.getEmail());
        }

    }

    @FXML
    void updateLib(ActionEvent event) {
        Librarian b1 = new Librarian();
        String code = userInputCode.getText();
        if(b1.CheckLibrarian(code) == true) {
            String libName = name.getText();
            String mobileNum = mobilenum.getText();
            String pass = password.getText();
            String national_id = nationalId.getText();
            String em = email.getText();
            b1.update(libName, code, "name");
            b1.update(mobileNum, code, "mobile");
            b1.update(pass, code, "password");
            b1.update(national_id, code, "nationalId");
            b1.update(em,code,"email");
            updateMassage.setTextFill(Color.GREEN);
            updateMassage.setText(b1.massage);
            remove();
            errorMassageSearch.setText(null);
        }else {
            updateMassage.setTextFill(Color.RED);
            updateMassage.setText("Please check the code of book");
            remove();
        }
    }

    public void remove(){
        name.setText(null);
        email.setText(null);
        mobilenum.setText(null);
        password.setText(null);
        nationalId.setText(null);
    }

}
