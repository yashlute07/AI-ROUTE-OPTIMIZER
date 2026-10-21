package com.routeoptimizer.model;

public class Route {

    private int id;
    private String routeName;
    private double distance;
    private int travelTime;
    private double toll;

    public Route(int id, String routeName, double distance, int travelTime, double toll) {
        this.id = id;
        this.routeName = routeName;
        this.distance = distance;
        this.travelTime = travelTime;
        this.toll = toll;
    }

    public int getId() {
        return id;
    }

    public String getRouteName() {
        return routeName;
    }

    public double getDistance() {
        return distance;
    }

    public int getTravelTime() {
        return travelTime;
    }

    public double getToll() {
        return toll;
    }
}