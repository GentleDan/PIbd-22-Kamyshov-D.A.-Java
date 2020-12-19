package com.company;

public class CampAlreadyHaveException extends Exception {
    public CampAlreadyHaveException() {
        super("На стоянке уже есть такой транспорт!");
    }
}
