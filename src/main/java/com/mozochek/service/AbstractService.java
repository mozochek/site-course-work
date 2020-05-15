package com.mozochek.service;

import org.springframework.util.StringUtils;

import java.sql.Date;
import java.util.HashMap;

import static com.mozochek.utils.DateUtil.parseDate;
import static com.mozochek.utils.LengthConstants.DATE_LENGTH;

public abstract class AbstractService {

    protected HashMap<String, String> errors;
    protected HashMap<String, Object> previousValues;

    public AbstractService() {

    }

    public void validateField(String str, int maxLength, String errorName) {
        if (isBlankOrEmpty(str)) {
            errors.put(errorName, "Заполните поле!");
        }
        if (isLengthIncorrect(str, maxLength)) {
            errors.put(errorName, "Длина поля не должна превышать " + maxLength + "символов!");
        }
    }

    public void validateDate(String date, String errorName) {
        if (date.length() != DATE_LENGTH || date.isBlank()) {
            errors.put(errorName, "Введите дату в формате 'дд.мм.гггг'!");
        }
    }

    public Date validateAndParseDate(String date, String errorName) {
        validateDate(date, errorName);
        if (errors.get(errorName) == null) {
            return parseDate(date);
        }
        return null;
    }

    public boolean isBlankOrEmpty(String str) {
        return StringUtils.isEmpty(str) || str.isBlank();
    }

    public boolean isLengthIncorrect(String str, int length) {
        return str.length() > length;
    }

    public HashMap<String, String> getErrors() {
        return errors;
    }

    public HashMap<String, Object> getPreviousValues() {
        return previousValues;
    }
}
