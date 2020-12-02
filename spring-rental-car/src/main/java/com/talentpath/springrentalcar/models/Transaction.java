package com.talentpath.springrentalcar.models;

import java.util.Date;

public class Transaction {

    private Integer transactionId;
    private Integer customerId;
    private Integer carId;
    private Date from;
    private Date to;

    public Transaction(){}

    public Transaction(Transaction that) {
        this.transactionId = that.transactionId;
        this.customerId = that.customerId;
        this.carId = that.carId;
        this.from = that.from;
        this.to = that.to;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
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
}
