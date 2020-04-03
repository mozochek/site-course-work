package com.mozochek.service;

import com.mozochek.entity.SportKind;
import com.mozochek.repository.SportKindRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;

import static com.mozochek.utils.LengthConstants.CODE_LENGTH;
import static com.mozochek.utils.LengthConstants.SPORT_KIND_NAME_LENGTH;

@Service
public class SportKindService extends AbstractService {

    private SportKindRepository sportKindRepository;

    private SportKind sportKind;

    public SportKindService(SportKindRepository sportKindRepository) {
        this.sportKindRepository = sportKindRepository;
    }

    public boolean addSportKind(SportKind sportKind) {
        this.sportKind = sportKind;

        errors = new HashMap<>();
        previousValues = new HashMap<>();

        validateData(this.sportKind.getName(), this.sportKind.getCode());

        if (errors.isEmpty()) {
            sportKindRepository.save(this.sportKind);
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
        if (isExist(name, code)) {
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

    private boolean isExist(String name, String code) {
        return sportKindRepository.findByNameAndCode(name, code) != null;
    }
}
