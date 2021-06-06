package com.iti.jets.reportingsystem.exceptions;

public class EntityNotFoundException extends RuntimeException{
    public EntityNotFoundException(String message){
        super(message); //this should call the constructor and pass the message
    }
}
