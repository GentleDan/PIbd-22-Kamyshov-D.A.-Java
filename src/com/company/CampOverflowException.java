package com.company;

public class CampOverflowException extends Exception {

    public CampOverflowException() {
        super("На стоянке нет свободных мест");
    }

}
