package com.mozochek.utils;

/**
 * Интерфейс, содержащий длину полей (нужно для базы данных и Freemarker'а)
 */
public interface LengthConstants {
    int SPORT_KIND_NAME_LENGTH = 50;
    int SPORT_DISCIPLINE_NAME_LENGTH = 50;

    int CODE_LENGTH = 11;
    int CITY_LENGTH = 35;
    int DATE_LENGTH = 10;

    int TOURNAMENT_NAME_LENGTH = 30;
    int TOURNAMENT_FORMAT_LENGTH = 20;
    int TOURNAMENT_AGE_GROUP_LENGTH = 20;
    int TOURNAMENT_TEAM_AMOUNT_LENGTH = 128;
    int TOURNAMENT_TEAM_CAPACITY_LENGTH = 64;
    int TOURNAMENT_CATEGORY_LENGTH = 10;
    int TOURNAMENT_CLASS_LENGTH = 10;

    int TEAM_NAME_LENGTH = 30;

    int HUMAN_NAME_LENGTH = 30;
    int HUMAN_SURNAME_LENGTH = 30;
    int HUMAN_PATRONYMIC_LENGTH = 30;

    int USERNAME_LENGTH = 30;
    int PASSWORD_LENGTH = 100;
}
