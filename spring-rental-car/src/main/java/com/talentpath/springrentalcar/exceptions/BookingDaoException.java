package com.talentpath.springrentalcar.exceptions;

public class BookingDaoException extends Exception{

    public BookingDaoException(String s){
        super(s);
    }

    public BookingDaoException( String s, Throwable innerException){
        super(s, innerException);
    }
}
