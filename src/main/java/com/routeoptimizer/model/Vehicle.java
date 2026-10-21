package com.routeoptimizer.model;

public class Vehicle {

    private int id;
    private String vehicleType;
    private double mileage;

    public Vehicle(int id, String vehicleType, double mileage) {
        this.id = id;
        this.vehicleType = vehicleType;
        this.mileage = mileage;
    }

    public int getId() {
        return id;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public double getMileage() {
        return mileage;
    }
}