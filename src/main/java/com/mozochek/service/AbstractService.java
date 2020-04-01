package com.mozochek.service;

import org.springframework.util.StringUtils;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import static com.mozochek.utils.LengthConstants.DATE_LENGTH;

public abstract class AbstractService {

    HashMap<String, String> errors;
    HashMap<String, String> previousValues;

    public AbstractService() {
        /*errors = new HashMap<>();
        previousValues = new HashMap<>();*/
    }

    void validateField(String str, int maxLength, String errorName) {
        if (isBlankOrEmpty(str)) {
            errors.put(errorName, "Заполните поле!");
        }
        if (isLengthIncorrect(str, maxLength)) {
            errors.put(errorName, "Длина поля не должна превышать " + maxLength + "символов!");
        }
    }

    Date validateAndFormatDate(String date, String errorName) {
        if (date.length() != DATE_LENGTH || date.isBlank()) {
            errors.put(errorName, "Введите дату в формате 'дд.мм.гггг'!");
            return null;
        } else {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
            date = date.replace('.', '-');
            Date formattedDate = null;
            try {
                formattedDate = new Date(simpleDateFormat.parse(date).getTime());
            } catch (ParseException e) {
                errors.put(errorName, "Введите дату в формате 'дд.мм.гггг'!");
            }
            return formattedDate;
        }
    }

    boolean isBlankOrEmpty(String str) {
        return StringUtils.isEmpty(str) || str.isBlank();
    }

    boolean isLengthIncorrect(String str, int length) {
        return str.length() > length;
    }

    public HashMap<String, String> getErrors() {
        return errors;
    }

    public HashMap<String, String> getPreviousValues() {
        return previousValues;
    }
}
