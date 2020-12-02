package com.talentpath.rentalcar.models;

public class Car {

    private Integer carId;
    private String make;
    private String model;
    private boolean availability;
    private Integer rate;

    //default constructor
    public Car(){

    }

    public Car( Car that ) {
        this.carId = that.carId;
        this.make = that.make;
        this.model = that.model;
        this.availability = that.availability;
        this.rate = that.rate;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }
}
