package com.example.common;

public class Treatment {

    private String name;
    private String date;
    private String description;
    private String price;

    public Treatment() {
    }

    public String getName() {
        return name;
    }

    public Treatment setName(String name) {
        this.name = name;
        return this;
    }

    public String getDate() {
        return date;
    }

    public Treatment setDate(String date) {
        this.date = date;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Treatment setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getPrice() {
        return price;
    }

    public Treatment setPrice(String price) {
        this.price = price;
        return this;
    }
}
