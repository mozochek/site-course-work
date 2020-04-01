package com.mozochek.service;

import com.mozochek.entity.SportDiscipline;
import com.mozochek.entity.SportKind;
import com.mozochek.repository.SportDisciplineRepository;
import com.mozochek.repository.SportKindRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;

import static com.mozochek.utils.LengthConstants.CODE_LENGTH;
import static com.mozochek.utils.LengthConstants.SPORT_DISCIPLINE_NAME_LENGTH;


@Service
public class SportDisciplineService extends AbstractService {

    private SportKindRepository sportKindRepository;
    private SportDisciplineRepository sportDisciplineRepository;

    private SportKind sportKind;
    private SportDiscipline sportDiscipline;

    public SportDisciplineService(SportKindRepository sportKindRepository, SportDisciplineRepository sportDisciplineRepository) {
        this.sportKindRepository = sportKindRepository;
        this.sportDisciplineRepository = sportDisciplineRepository;
    }

    public boolean addSportDiscipline(Integer sportKindId, String name, String code) {
        sportKind = new SportKind();
        sportDiscipline = new SportDiscipline();
        sportDiscipline.setName(name);
        sportDiscipline.setCode(code);

        errors = new HashMap<>();
        previousValues = new HashMap<>();

        validateData(sportKindId, name, code);

        if (errors.isEmpty()) {
            sportDiscipline.setSportKind(sportKindRepository.findById(sportKindId).get());
            sportDisciplineRepository.save(sportDiscipline);
            return true;
        }
        setPreviousValues();
        return false;
    }

    private void validateData(Integer sportKindId, String name, String code) {
        validateField(name, SPORT_DISCIPLINE_NAME_LENGTH, "nameError");
        validateField(code, CODE_LENGTH, "codeError");
        if (code.length() != CODE_LENGTH || StringUtils.containsWhitespace(code)) {
            errors.put("codeError", "Код должен состоять из " + CODE_LENGTH + " символов(без пробелов)!");
        }
        if (sportKindRepository.findById(sportKindId).isEmpty()) {
            errors.put("sportKindError", "Выберите вид спорта!");
        }
        if (isExist(sportKindId, name, code)) {
            errors.put("objectExist", "Данная спортивная дисциплина уже находится в базе данных!");
        }
        if (sportKindRepository.findById(sportKindId).isEmpty()) {
            errors.put("sportKindError", "Был выбран несуществующий вид спорта!");
        }
    }

    private void setPreviousValues() {
        if (errors.get("nameError") == null) {
            previousValues.put("prevName", sportDiscipline.getName());
        }
        if (errors.get("codeError") == null) {
            previousValues.put("prevCode", sportDiscipline.getCode());
        }
    }

    private boolean isExist(Integer sportKindId, String sportDisciplineName, String sportDisciplineCode) {
        return sportDisciplineRepository
                .findBySportKindIdAndNameAndCode(sportKindId, sportDisciplineName, sportDisciplineCode) != null;
    }
}
