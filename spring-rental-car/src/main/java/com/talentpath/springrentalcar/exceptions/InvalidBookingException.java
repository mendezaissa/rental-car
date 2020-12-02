package com.talentpath.springrentalcar.exceptions;

public class InvalidBookingException extends Exception{

    public InvalidBookingException(String s){
        super(s);
    }

    public InvalidBookingException(String s, Throwable innerException){
        super(s, innerException);
    }
}
