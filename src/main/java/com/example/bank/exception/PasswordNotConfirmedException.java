package com.example.bank.exception;

public class PasswordNotConfirmedException extends RuntimeException{
    public PasswordNotConfirmedException(String message) {
        super(message);
    }
}
