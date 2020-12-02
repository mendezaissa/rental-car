package com.talentpath.rentalcar.models;

import java.util.Date;

public class Transaction {

    private Integer transactionId;
    private Date from;
    private Date to;
    private Integer customerId;
    private Integer carId;


    public Transaction(){
    }

    public Transaction ( Transaction that ){
        this.transactionId = that.transactionId;
        this.from = that.from;
        this.to = that.to;
        this.customerId = that.customerId;
        this.carId = that.carId;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }
}
