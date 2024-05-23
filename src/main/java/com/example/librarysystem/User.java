package com.example.librarysystem;
import java.sql.*;

public class User extends Person{

    public String getJob() {
        return job;
    }

    private String job ;

    public String[] getTitle() {
        return title;
    }

    private String[] title = {"Doctor","Student"};


     User(){}
     User(String name, String email,String mobile,String job)  {
        super(name,email,mobile);
        this.job=job;
    }
    public void add()throws NullPointerException{

    }


    @Override
    void update(String name, String mobile, String column) {
        String sql = "UPDATE user SET '"+column+"'= '"+name+"' WHERE code = "+mobile+";";
        try {

            Connection conn =  connection.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
            massage = "Updated successfully";

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void delete(String email){
        String sql = "DELETE FROM user WHERE code = "+email+";";
        try {
            Connection conn =  connection.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
            massage = "Deleted successfully";
        }catch (SQLException e){
            System.out.println(e.getMessage());
    }
}


    public boolean checkUser(String mobile){
        boolean flag=false;
        Statement stmt = null;
        try {
            Connection conn =  connection.connect();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM user WHERE code like '"+mobile+"';");

            while ( rs.next() ) {
                String code_book = rs.getString("mobile");
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

    public void updateHistory(String name, String code, String column){
        String sql = "UPDATE history SET '"+column+"'= '"+name+"' WHERE bookCode = "+code+";";
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