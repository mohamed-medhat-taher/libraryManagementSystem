package com.example.librarysystem;

import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FilenameFilter;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;

public class Book {
    Image imageBook;
    private String title ;
    private String code;
    private String author;
    private String imageSrc;
    private String type;
    private String imageRootSrc;
    private String availability;
    private Stage stage;

    public String[] getBookType() {
        return bookType;
    }

    public String[] getAvail() {
        return avail;
    }

    private String[] avail = {"available","unavailable"};
    public String[] bookType = {"Fiction",
            "Non-fiction",
            "Autobiographies",
            "Self-help",
            "Reference books",
            "Textbooks",
            "Children's books",
            "Poetry",
            "Cookbooks",
            "Travel guides"};

    String massage ;

    public  Book(){

    }
    public Book(String title,String code,String author,String type,String imageSrc,String imageRootSrc){
        this.title = title;
        this.author = author;
        this.type = type;
        this.imageSrc = imageSrc;
        this.imageRootSrc = imageRootSrc;
        setCode(code);
        this.availability = "available";
    }

    public Book(String title,String code,String author,String type,String imageSrc,String imageRootSrc,String availability){
        this.title = title;
        this.author = author;
        this.type = type;
        this.imageSrc = imageSrc;
        this.imageRootSrc = imageRootSrc;
        setCode(code);
        this.availability = availability;
    }


    public void add()throws NullPointerException{
        String sql = "INSERT INTO books (title,code,author,imagesrc,type,availability,rootImageSrc) VALUES (?,?,?,?,?,?,?);";
        try{
            Connection conn =  connection.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, title);
            pstmt.setString(2, code);
            pstmt.setString(3, author);
            pstmt.setString(4,imageSrc);
            pstmt.setString(5, type);
            pstmt.setString(6,availability);
            pstmt.setString(7,imageRootSrc);
            pstmt.executeUpdate();
            massage = "Add successfully";
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            massage = "Missing data";
        }
    }
    public void update(String name,String code,String column){
        String sql = "UPDATE books SET '"+column+"'= '"+name+"' WHERE code = "+code+";";
        try {

            Connection conn =  connection.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
            massage = "Updated successfully";

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    public void delete(String code){
        String sql = "DELETE FROM books WHERE code = "+code+";";
        try {
            Connection conn =  connection.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
            massage = "Deleted successfully";
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public void search(String num){
        Statement stmt = null;
        try {
            Connection conn =  connection.connect();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM books WHERE code = "+num+";" );
            while ( rs.next() ) {
                title = rs.getString("title");
                author = rs.getString("author");
                code  = rs.getString("code");
                imageSrc = rs.getString("imagesrc");
                type = rs.getString("type");
                availability = rs.getString("availability");
                imageRootSrc = rs.getString("rootImageSrc");
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch ( SQLException e ) {
            System.err.println( e.getMessage() );
        }
    }


    public Image uploadImage() throws MalformedURLException {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image files","*.png","*.jpg"));
        File file = fc.showOpenDialog(stage);
        if(file == null){
            massage= "please upload an image!!!!";
            Path imageFile = Paths.get("D:\\Intellij\\LibrarySystem\\src\\main\\resources\\com\\example\\assets\\upload.png");
            imageBook = (new Image(imageFile.toUri().toURL().toExternalForm()));
            return imageBook;
        }else{
            imageSrc = file.getName();
            imageRootSrc = file.getAbsolutePath();
            Path imageFile = Paths.get(imageRootSrc);
            imageBook = (new Image(imageFile.toUri().toURL().toExternalForm()));
            massage="";
            return imageBook;
        }
    }

    public Boolean CheckBookCode (String num){
        boolean flag=false;
        Statement stmt = null;
        try {
            Connection conn =  connection.connect();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM books WHERE code like '"+num+"';");

            while ( rs.next() ) {
                String code_book = rs.getString("code");
                if(code_book==null)
                {
                    flag= false;
                } else
                {
                    flag= true;
                }
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch ( SQLException e ) {
            System.out.println( e.getMessage() );

        }
        return flag;
    }

    public String getTitle() {
        return title;
    }

    public String getCode() {
        return code;
    }

    public String getAuthor() {
        return author;
    }

    public String getImageSrc() {
        return imageSrc;
    }

    public String getType() {
        return type;
    }

    public String getAvailability() {
        return availability;
    }

    public String getImageRootSrc() {
        return imageRootSrc;
    }


    public void setCode(String code) {
        this.code = code;
    }

    public void Borrow(String code,String mobile,String name, String from,String to){
        String sql = "INSERT INTO borrow (bookCode,mobileNum,name,dateFrom,dateTo) VALUES (?,?,?,?,?);";
        try{
            Connection conn =  connection.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, code);
            pstmt.setString(2, mobile);
            pstmt.setString(3, name);
            pstmt.setString(4,from);
            pstmt.setString(5, to);
            pstmt.executeUpdate();
            massage = "Add successfully";
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            massage = "Missing data";
        }
    }

    public void deleteBorrow(String code){
        String sql = "DELETE FROM borrow WHERE bookCode = "+code+";";
        try {
            Connection conn =  connection.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
            massage = "Deleted successfully";
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public boolean checkBook(String code){
        boolean flag=false;
        Statement stmt = null;
        try {
            Connection conn =  connection.connect();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM borrow WHERE bookCode like '"+code+"';");

            while ( rs.next() ) {
                String code_book = rs.getString("bookCode");
                if(code_book==null)
                {
                    flag= false;
                } else
                {
                    flag= true;
                }
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch ( SQLException e ) {
            System.out.println( e.getMessage() );

        }
        return flag;
    }


    public void updateBorrow(String name, String mobile, String column){
        String sql = "UPDATE borrow SET '"+column+"'= '"+name+"' WHERE bookCode = "+mobile+";";
        try {

            Connection conn =  connection.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
            massage = "Updated successfully";

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}