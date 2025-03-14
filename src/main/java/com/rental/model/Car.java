package com.rental.model;

/**
 *
 * @author Java Programming with Aldrin
 */
public class Car {

    private int carid;
    private String name;
    private String category;
    private int noSeats;
    private String type;
    private double rentalAmountForDay;
    private String availability;



    public Car(int carid, String name, String category, int noSeats, String type, double rentalAmountForDay, String availability ) {
        this.carid = carid;
        this.name = name;
        this.category = category;
        this.noSeats = noSeats;
        this.type = type;
        this.rentalAmountForDay = rentalAmountForDay;
        this.availability = availability;
    }

    public Car( String name, String category, int noSeats, String type, double rentalAmountForDay, String availability ) {

        this.name = name;
        this.category = category;
        this.noSeats = noSeats;
        this.type = type;
        this.rentalAmountForDay = rentalAmountForDay;
        this.availability = availability;
    }

    public int getCarid() {
        return carid;
    }

    public void setCarid(int carid) {
        this.carid = carid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getNoSeats() {
        return noSeats;
    }

    public void setNoSeats(int noSeats) {
        this.noSeats = noSeats;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getRentalAmountForDay() {
        return rentalAmountForDay;
    }

    public void setRentalAmountForDay(double rentalAmountForDay) {
        this.rentalAmountForDay = rentalAmountForDay;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }
}
