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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class UpdateUserPageController implements Initializable {

    @FXML
    private Label codeNotFound;

    @FXML
    private TextField codeUpdate;

    @FXML
    private DatePicker dateFromUpdate;

    @FXML
    private DatePicker dateToUpdate;

    @FXML
    private Label deleteMassage;

    @FXML
    private TextField email;

    @FXML
    private Label errorMassageSearch;

    @FXML
    private TextField mobileUpdate;

    @FXML
    private ComboBox<String> typeUserUpdate;

    @FXML
    private Label updateMassage;

    @FXML
    private TextField userInputCode;

    @FXML
    private TextField userNameUpdate;

    String cod;


    @FXML
    void deleteUser(ActionEvent event) {
        User u1 = new User();
        Book b1 = new Book();
        String num = userInputCode.getText();
        if(u1.checkUser(num) == true) {
            String code = userInputCode.getText();
            u1.delete(code);
            b1.deleteBorrow(code);
            b1.update("available", code, "availability");
            deleteMassage.setText(u1.massage);
            remove();
            updateMassage.setText("");
        }else {
            deleteMassage.setText("Please check the code of book");
        }
    }

    @FXML
    void searchButton(ActionEvent event) {
        User s1 = new User();
        Book b1 = new Book();
        String code = userInputCode.getText();
        cod = code;
        if(b1.checkBook(code) == true){
            Statement stmt = null;
            try {
                Connection conn =  connection.connect();
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery( "SELECT * FROM user WHERE code = "+code+";" );

                while ( rs.next() ) {
                    String  name = rs.getString("name");
                    String em  = rs.getString("email");
                    String  m_Num = rs.getString("mobile");
                    String from = rs.getString("dateFrom");
                    String to = rs.getString("dateTo");
                    String cd = rs.getString("code");
                    String jop = rs.getString("job");
                    userNameUpdate.setText(name);
                    email.setText(em);
                    mobileUpdate.setText(m_Num);
                    codeUpdate.setText(cd);
                    typeUserUpdate.getSelectionModel().select(jop);
                    dateToUpdate.setValue(LocalDate.parse(to));
                    dateFromUpdate.setValue(LocalDate.parse(from));
                    errorMassageSearch.setText("");

                }
                rs.close();
                stmt.close();
                conn.close();
            } catch ( SQLException e ) {
                System.err.println( e.getMessage() );
            }
        }else {
            errorMassageSearch.setText("Please write a correct code");

        }
    }

    Validation ch = new Validation();

    @FXML
    void updateUser(ActionEvent event) {
        String check = getCod();
        User s1 = new User();
        Book b1 = new Book();
        String name = userNameUpdate.getText();
        String em = email.getText();
        String code = codeUpdate.getText();
        LocalDate To = dateToUpdate.getValue();
        LocalDate from = dateFromUpdate.getValue();
        String mobileNum = mobileUpdate.getText();
        String codeBook = userInputCode.getText();
        if(To != null && from != null && typeUserUpdate.getSelectionModel().getSelectedItem() != null && name != null && em != null  && mobileNum != null ){
            String start = To.toString();
            String end = from.toString();
            String jop =typeUserUpdate.getSelectionModel().getSelectedItem().toString();
            updateMassage.setText(null);
            if(b1.CheckBookCode(code) == true) {
                if (b1.checkBook(code) != true || check.equals(code)) {
                    if(ch.validEmail(em) == true) {
                        if (from.isBefore(To)) {
                            if (check.equals(code)) {
                                b1.update("unavailable", code, "availability");
                            } else {
                                b1.update("unavailable", code, "availability");
                                b1.update("available", check, "availability");
                            }
                            // update to user table
                            s1.update(name, codeBook, "name");
                            s1.update(em, codeBook, "email");
                            s1.update(mobileNum, codeBook, "mobile");
                            s1.update(start, codeBook, "dateTo");
                            s1.update(end, codeBook, "dateFrom");
                            s1.update(jop, codeBook, "job");
                            s1.update(code, codeBook, "code");

                            if (code.equals(cod) == true) {
                                // update to History table
                                s1.updateHistory(name, codeBook, "name");
                                s1.updateHistory(mobileNum, codeBook, "mobileNum");
                                s1.updateHistory(code, codeBook, "bookCode");
                                s1.updateHistory(start, codeBook, "dateTo");
                                s1.updateHistory(end, codeBook, "dateFrom");
                            } else {
                                AddUserPageController add = new AddUserPageController();
                                add.history(name, mobileNum, code, start, end);
                            }
                            // update to Borrow table
                            b1.updateBorrow(code, codeBook, "bookCode");
                            b1.updateBorrow(mobileNum, codeBook, "mobileNum");
                            b1.updateBorrow(name, codeBook, "name");
                            b1.updateBorrow(start, codeBook, "dateFrom");
                            b1.updateBorrow(end, codeBook, "dateTo");
                            updateMassage.setTextFill(Color.GREEN);
                            updateMassage.setText(s1.massage);
                            codeNotFound.setText("");
                            remove();
                        } else {
                            updateMassage.setTextFill(Color.RED);
                            updateMassage.setText("Please enter valid date!!!");
                        }
                    }else {
                        updateMassage.setTextFill(Color.RED);
                        updateMassage.setText("Please enter valid email!!!");
                    }
                } else {
                    updateMassage.setTextFill(Color.RED);
                    codeNotFound.setText("Invalid Code!!!");
                }
            }else {
                updateMassage.setTextFill(Color.RED);
                updateMassage.setText("Please enter a correct Code!!!");
            }
        }else {
            updateMassage.setTextFill(Color.RED);
            updateMassage.setText("Missing data!!!");
        }
    }

    public void initialize(URL location, ResourceBundle resources) {
        User u1 = new User();
        typeUserUpdate.getItems().addAll(u1.getTitle());}


    public String getCod() {
        return cod;
    }

    public void remove(){
        userNameUpdate.setText(null);
        mobileUpdate.setText(null);
        typeUserUpdate.getSelectionModel().select(null);
        codeUpdate.setText(null);
        email.setText(null);
        dateFromUpdate.setValue(null);
        dateToUpdate.setValue(null);
        codeUpdate.setText(null);
    }

}
