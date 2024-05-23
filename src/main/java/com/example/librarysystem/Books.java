package com.example.librarysystem;

public class Books {

    private String type;
    private String name;
    private String author;
    private String imageSrc;

    private String availability;


    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }


    public void setName(String name) {
        this.name = name;
    }


    public void setAuthor(String author) {
        this.author = author;
    }

    public void setType(String type) {
        this.type = type;
    }
    public String getType() {
        return type;
    }
    public String getAuthor() {
        return author;
    }

    public String getName() {
        return name;
    }
    public String getImageSrc() {
        return imageSrc;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }
}
