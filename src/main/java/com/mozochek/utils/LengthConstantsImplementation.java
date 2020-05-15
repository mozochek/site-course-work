package com.mozochek.utils;

/**
 * Реализация интерфейса (был создан из-за невозможности добавить в модель данных интерфейс)
 */
public class LengthConstantsImplementation implements LengthConstants {

    private static LengthConstantsImplementation instance;

    private LengthConstantsImplementation() {

    }

    public static LengthConstantsImplementation getInstance() {
        if (instance == null) {
            instance = new LengthConstantsImplementation();
        }
        return instance;
    }

    /**
     * Эти геттеры нужны для движка Freemarker
     */
    public int getSPORT_KIND_NAME_LENGTH() {
        return LengthConstants.SPORT_KIND_NAME_LENGTH;
    }

    public int getSPORT_DISCIPLINE_NAME_LENGTH() {
        return LengthConstants.SPORT_DISCIPLINE_NAME_LENGTH;
    }

    public int getCODE_LENGTH() {
        return LengthConstants.CODE_LENGTH;
    }

    public int getCITY_LENGTH() {
        return LengthConstants.CITY_LENGTH;
    }

    public int getDATE_LENGTH() {
        return LengthConstants.DATE_LENGTH;
    }

    public int getTOURNAMENT_NAME_LENGTH() {
        return LengthConstants.TOURNAMENT_NAME_LENGTH;
    }

    public int getTOURNAMENT_FORMAT_LENGTH() {
        return LengthConstants.TOURNAMENT_FORMAT_LENGTH;
    }

    public int getTOURNAMENT_AGE_GROUP_LENGTH() {
        return LengthConstants.TOURNAMENT_AGE_GROUP_LENGTH;
    }

    public int getTOURNAMENT_TEAM_AMOUNT_LENGTH() {
        return LengthConstants.TOURNAMENT_TEAM_AMOUNT_LENGTH;
    }

    public int getTOURNAMENT_TEAM_CAPACITY_LENGTH() {
        return LengthConstants.TOURNAMENT_TEAM_CAPACITY_LENGTH;
    }

    public int getTOURNAMENT_CATEGORY_LENGTH() {
        return LengthConstants.TOURNAMENT_CATEGORY_LENGTH;
    }

    public int getTOURNAMENT_CLASS_LENGTH() {
        return LengthConstants.TOURNAMENT_CLASS_LENGTH;
    }

    public int getHUMAN_NAME_LENGTH() {
        return LengthConstants.HUMAN_NAME_LENGTH;
    }

    public int getTEAM_NAME_LENGTH() {
        return LengthConstants.TEAM_NAME_LENGTH;
    }

    public int getHUMAN_SURNAME_LENGTH() {
        return LengthConstants.HUMAN_SURNAME_LENGTH;
    }

    public int getHUMAN_PATRONYMIC_LENGTH() {
        return LengthConstants.HUMAN_PATRONYMIC_LENGTH;
    }

    public int getUSERNAME_LENGTH() {
        return LengthConstants.USERNAME_LENGTH;
    }

    public int getPASSWORD_LENGTH() {
        return LengthConstants.PASSWORD_LENGTH;
    }
}
