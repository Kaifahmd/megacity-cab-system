package com.rental.model;

public class CarBooking {
    private int id;
    private int carId;
    private int userId;
    private int rentalDays;
    private double totalRental;
    private String permission;

    public CarBooking(int id, int carId, int userId, int rentalDays, double totalRental, String permission) {
        this.id = id;
        this.carId = carId;
        this.userId = userId;
        this.rentalDays = rentalDays;
        this.totalRental = totalRental;
        this.permission = permission;
    }

    // Getters and Setters
    public int getId() { return id; }
    public int getCarId() { return carId; }
    public int getUserId() { return userId; }
    public int getRentalDays() { return rentalDays; }
    public double getTotalRental() { return totalRental; }
    public String getPermission() { return permission; }

    public void setId(int id) { this.id = id; }
    public void setCarId(int carId) { this.carId = carId; }
    public void setUserId(int userId) { this.userId = userId; }
    public void setRentalDays(int rentalDays) { this.rentalDays = rentalDays; }
    public void setTotalRental(double totalRental) { this.totalRental = totalRental; }
    public void setPermission(String permission) { this.permission = permission; }
}
