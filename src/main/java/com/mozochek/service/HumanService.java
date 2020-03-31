package com.mozochek.service;

import com.mozochek.entity.Human;
import com.mozochek.repository.HumanRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import static com.mozochek.utils.LengthConstants.*;

@Service
public class HumanService {

    private HumanRepository humanRepository;

    private HashMap<String, String> errors;
    private HashMap<String, String> previousValues;

    private Human human;

    public HumanService(HumanRepository humanRepository) {
        this.humanRepository = humanRepository;
    }

    public boolean addHuman(Human human, String humanBirthDate) {
        this.human = human;
        errors = new HashMap<>();
        previousValues = new HashMap<>();

        validateData(humanBirthDate);

        if (errors.isEmpty()) {
            humanRepository.save(human);
            return true;
        }
        setPreviousValues();
        return false;
    }

    private void validateData(String humanBirthDate) {
        validateField(human.getName(), HUMAN_NAME_LENGTH, "nameError");
        validateField(human.getSurname(), HUMAN_SURNAME_LENGTH, "surnameError");
        if (!isBlankOrEmpty(human.getPatronymic()) && isLengthIncorrect(human.getPatronymic(), HUMAN_PATRONYMIC_LENGTH)) {
            errors.put("patronymicError", "Длина поля не должна превышать " + HUMAN_PATRONYMIC_LENGTH + "символов!");
        }
        if (!isBlankOrEmpty(human.getCity()) && isLengthIncorrect(human.getCity(), CITY_LENGTH)) {
            errors.put("cityError", "Длина поля не должна превышать " + CITY_LENGTH + "символов!");
        }
        human.setBirthDate(validateAndFormatDate(humanBirthDate, "birthDateError"));
    }

    private void validateField(String str, int maxLength, String errorName) {
        if (isBlankOrEmpty(str)) {
            errors.put(errorName, "Заполните поле!");
        }
        if (isLengthIncorrect(str, maxLength)) {
            errors.put(errorName, "Длина поля не должна превышать " + maxLength + "символов!");
        }
    }

    private boolean isBlankOrEmpty(String str) {
        return StringUtils.isEmpty(str) || str.isBlank();
    }

    private boolean isLengthIncorrect(String str, int maxLength) {
        return str.length() > maxLength;
    }


    private Date validateAndFormatDate(String date, String errorName) {
        if (date.length() != DATE_LENGTH) {
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

    private void setPreviousValues() {
        if (errors.get("nameError") == null) {
            previousValues.put("prevName", human.getName());
        }
        if (errors.get("surnameError") == null) {
            previousValues.put("prevSurname", human.getSurname());
        }
        if (!isBlankOrEmpty(human.getPatronymic()) && errors.get("patronymicError") == null) {
            previousValues.put("prevPatronymic", human.getPatronymic());
        }
        if (!isBlankOrEmpty(human.getCity()) && errors.get("cityError") == null) {
            previousValues.put("prevCity", human.getCity());
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        if (errors.get("birthDateError") == null) {
            previousValues.put("prevBirthDate", simpleDateFormat.format(human.getBirthDate()).replace('-', '.'));
        }
    }

    public HashMap<String, String> getErrors() {
        return errors;
    }

    public HashMap<String, String> getPreviousValues() {
        return previousValues;
    }
}
