package com.talentpath.springrentalcar.exceptions;

public class NoCarFoundException extends Exception{

    public NoCarFoundException(String s){
        super(s);
    }

    public NoCarFoundException( String s, Throwable innerException){
        super(s, innerException);
    }
}
