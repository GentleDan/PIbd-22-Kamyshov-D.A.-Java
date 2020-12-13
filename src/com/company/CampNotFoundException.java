package com.company;

public class CampNotFoundException extends Exception {

    public CampNotFoundException(int index) {
        super("Не найден транспорт по указанному месту");
    }
}
