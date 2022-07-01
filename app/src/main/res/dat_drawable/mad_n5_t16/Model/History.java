package com.example.mad_n5_t16.Model;

public class History {
    private int id;
    private int number;
    private int amount;
    private String location;
    private String donationDate;

    public History(int number, int amount, String location, String donationDate) {
        this.number = number;
        this.amount = amount;
        this.location = location;
        this.donationDate = donationDate;
    }

    public int getId() {
        return id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDonationDate() {
        return donationDate;
    }

    public void setDonationDate(String donationDate) {
        this.donationDate = donationDate;
    }
}
