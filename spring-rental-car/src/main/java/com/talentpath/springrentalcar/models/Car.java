package com.talentpath.springrentalcar.models;

public class Car {

    private Integer carId;
    private String make;
    private String model;
    private Integer rate;

    public Car(){}

    public Car( Car that ){
        this.carId = that.carId;
        this.make = that.make;
        this.model = that.model;
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

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }
}
