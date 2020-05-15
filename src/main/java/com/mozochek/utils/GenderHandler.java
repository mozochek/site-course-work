package com.mozochek.utils;

public class GenderHandler {

    private static Gender[] genders = Gender.values();

    public static Gender getGenderById(int id) {
        if (genders == null) {
            genders = Gender.values();
        }
        if (genders.length < id) {
            return null;
        }
        return genders[id];
    }

    public static Gender getGenderByName(String name) {
        if (name.equals("мужской")) {
            return Gender.MALE;
        } else {
            return Gender.FEMALE;
        }
    }
}
