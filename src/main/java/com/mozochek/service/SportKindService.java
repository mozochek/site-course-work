package com.mozochek.service;

import com.mozochek.entity.SportKind;
import com.mozochek.repository.SportKindRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;

import static com.mozochek.utils.LengthConstants.CODE_LENGTH;
import static com.mozochek.utils.LengthConstants.SPORT_KIND_NAME_LENGTH;

@Service
public class SportKindService {

    private SportKindRepository sportKindRepository;

    private SportKind sportKind;
    private HashMap<String, String> errors;
    private HashMap<String, String> previousValues;

    public SportKindService(SportKindRepository sportKindRepository) {
        this.sportKindRepository = sportKindRepository;
    }

    public boolean addSportKind(String name, String code) {
        sportKind = new SportKind();
        sportKind.setName(name);
        sportKind.setCode(code);

        errors = new HashMap<>();
        previousValues = new HashMap<>();

        validateData(sportKind.getName(), sportKind.getCode());

        if (errors.isEmpty()) {
            sportKindRepository.save(sportKind);
            return true;
        }
        setPreviousValues();
        return false;
    }

    private void validateData(String name, String code) {
        validateField(name, SPORT_KIND_NAME_LENGTH, "nameError");
        validateField(code, CODE_LENGTH, "codeError");
        if (code.length() != CODE_LENGTH || StringUtils.containsWhitespace(code)) {
            errors.put("codeError", "Код должен состоять из " + CODE_LENGTH + " символов(без пробелов)!");
        }
        if (existenceCheck(name, code)) {
            errors.put("objectExist", "Данный вид спорта уже находится в базе данных!");
        }
    }

    private void setPreviousValues() {
        if (errors.get("nameError") == null) {
            previousValues.put("prevName", sportKind.getName());
        }
        if (errors.get("codeError") == null) {
            previousValues.put("prevCode", sportKind.getCode());
        }
    }

    private boolean existenceCheck(String name, String code) {
        return sportKindRepository.findByNameAndCode(name, code) != null;
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

    private boolean isLengthIncorrect(String str, int length) {
        return str.length() > length;
    }

    public HashMap<String, String> getErrors() {
        return errors;
    }

    public HashMap<String, String> getPreviousValues() {
        return previousValues;
    }
}
