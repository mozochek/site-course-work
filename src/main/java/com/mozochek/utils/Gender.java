package com.mozochek.utils;

public enum Gender {
    MALE("Мужской"),
    FEMALE("Женский"),
    INVALID_GENDER("Неизвестный");

    private String name;

    Gender(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
