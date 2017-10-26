package com.company.lab2;

public class DivisionByZeroException extends Exception {
    public DivisionByZeroException(String reason, String statement){
        super(reason + ": " + statement);
    }

}
