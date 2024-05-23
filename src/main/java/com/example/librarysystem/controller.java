package com.example.librarysystem;

public class controller {

    private String username;
    private String code;
    private String datefrom;
    private String dateTo;

    private String email;

    public controller(String username, String code, String datefrom, String dateTo, String email) {
        this.username = username;
        this.code = code;
        this.datefrom = datefrom;
        this.dateTo = dateTo;
        this.email = email;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDatefrom() {
        return datefrom;
    }

    public void setDatefrom(String datefrom) {
        this.datefrom = datefrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
