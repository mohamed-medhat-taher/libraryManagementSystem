package com.example.librarysystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class History implements Initializable {

    @FXML
    private TableColumn<controller, String> codeBook;

    @FXML
    private TableColumn<controller, String> dateFrom;

    @FXML
    private TableColumn<controller, String> dateTo;

    @FXML
    private TableColumn<controller, String> mobileNum;

    @FXML
    private TableColumn<controller, String> name;

    @FXML
    private TableView<controller> table;
    @FXML
    private Label errorMassageSearch;


    @FXML
    private TextField userInputCode;




    public static ObservableList<controller> getData() {
        ObservableList<controller> list = FXCollections.observableArrayList();

        Statement stmt = null;
        try {
            Connection conn =  connection.connect();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM history" );
            while ( rs.next() ) {
                String username = rs.getString("name");
                String email = rs.getString("mobileNum");
                String code  = rs.getString("bookCode");
                String datefrom = rs.getString("dateFrom");
                String dateTo = rs.getString("dateTo");
                list.add(new controller(username,code,datefrom,dateTo,email));
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch ( SQLException e ) {
            System.err.println( e.getMessage() );
        }
        return list;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        name.setCellValueFactory(new PropertyValueFactory<controller,String>("username"));
        codeBook.setCellValueFactory(new PropertyValueFactory<controller,String>("code"));
        dateFrom.setCellValueFactory(new PropertyValueFactory<controller,String>("datefrom"));
        dateTo.setCellValueFactory(new PropertyValueFactory<controller,String>("dateTo"));
        mobileNum.setCellValueFactory(new PropertyValueFactory<controller,String>("email"));

        table.setItems(getData());

        FilteredList<controller> filteredData = new FilteredList<>(getData(), b -> true);
        userInputCode.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(controller -> {
                if (newValue == null || newValue.isEmpty()){
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (controller.getUsername().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                }else if (controller.getCode().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                }else if (controller.getEmail().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                }else {
                    return false;
                }
            });
        });

        SortedList<controller> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(table.comparatorProperty());

        table.setItems(sortedData);
    }
}