package com.example.librarysystem;

import java.sql.*;

public class Librarian extends Person{
    private String nationalId;
    private String password;


    Librarian() {

    }

    Librarian(String name , String email , String mobile,String nationalId, String password)
    {
        super(name,email,mobile);
        this.password = password;
        this.nationalId = nationalId;
    }

    public void add() throws NullPointerException {
        String sql = "INSERT INTO librarian (name,nationalId,email,mobile,password) VALUES (?,?,?,?,?);";
        try {
            Connection conn = connection.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, nationalId);
            pstmt.setString(3, email);
            pstmt.setString(4, mobile);
            pstmt.setString(5, password);
            pstmt.executeUpdate();
            massage = "Add successfully";
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            massage = "Missing data";
        }
    }

    @Override
    void update(String name, String national_id, String column) {
        String sql = "UPDATE librarian SET '" + column + "'= '" + name + "' WHERE nationalId = " + national_id + ";";
        try {

            Connection conn = connection.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
            massage = "Updated successfully";

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    void delete(String national_id) {
        String sql = "DELETE FROM librarian WHERE nationalId = " + national_id + ";";
        try {
            Connection conn = connection.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
            massage = "Deleted successfully";
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Boolean CheckLibrarian (String email){
        boolean flag=false;
        Statement stmt = null;
        try {
            Connection conn =  connection.connect();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM librarian WHERE nationalId like '"+email+"';");

            while ( rs.next() ) {
                String code_book = rs.getString("nationalId");
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

    public void search(String num){
        Statement stmt = null;
        try {
            Connection conn =  connection.connect();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM librarian WHERE nationalId = "+num+";" );
            while ( rs.next() ) {
                name = rs.getString("name");
                nationalId = rs.getString("nationalId");
                email  = rs.getString("email");
                password = rs.getString("password");
                mobile = rs.getString("mobile");
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch ( SQLException e ) {
            System.err.println( e.getMessage() );
        }
    }

    public String getNationalId() {
        return nationalId;
    }

    public String getPassword() {
        return password;
    }
}

