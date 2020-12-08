package com.talentpath.springrentalcar.exceptions;

public class NoTransactionFoundException extends Exception{

    public NoTransactionFoundException(String s){
        super(s);
    }

    public NoTransactionFoundException(String s, Throwable innerException){
        super(s, innerException);
    }
}
