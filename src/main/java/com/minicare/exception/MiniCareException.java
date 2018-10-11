package com.minicare.exception;

public class MiniCareException extends RuntimeException{
    public MiniCareException(String message){
        super(message);
    }

    public MiniCareException(Throwable t){
        super(t);
    }
}
