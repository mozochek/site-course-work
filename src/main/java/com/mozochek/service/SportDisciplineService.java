package com.mozochek.service;

import com.mozochek.entity.SportDiscipline;
import com.mozochek.entity.SportKind;
import com.mozochek.repository.SportDisciplineRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;

import static com.mozochek.utils.LengthConstants.CODE_LENGTH;
import static com.mozochek.utils.LengthConstants.SPORT_DISCIPLINE_NAME_LENGTH;

@Service
public class SportDisciplineService extends AbstractService {

    private SportDisciplineRepository sportDisciplineRepository;
    private SportKindService sportKindService;

    public SportDisciplineService(SportDisciplineRepository sportDisciplineRepository, SportKindService sportKindService) {
        this.sportDisciplineRepository = sportDisciplineRepository;
        this.sportKindService = sportKindService;
    }

    public boolean saveSportDiscipline(SportDiscipline sportDiscipline) {
        errors = new HashMap<>();
        previousValues = new HashMap<>();

        validateData(sportDiscipline.getName(), sportDiscipline.getCode(), sportDiscipline.getSportKind());

        if (errors.isEmpty()) {
            sportDisciplineRepository.save(sportDiscipline);
            return true;
        }
        previousValues.put("sportDiscipline", sportDiscipline);
        return false;
    }

    private void validateData(String name, String code, SportKind sportKind) {
        validateField(name, SPORT_DISCIPLINE_NAME_LENGTH, "nameError");
        validateField(code, CODE_LENGTH, "codeError");
        if (code.length() != CODE_LENGTH || StringUtils.containsWhitespace(code)) {
            errors.put("codeError", "Код должен состоять из " + CODE_LENGTH + " символов(без пробелов)!");
        }
        if (sportKind == null) {
            errors.put("sportKindError", "Ошибка выбора вида спорта!");
        } else {
            if (sportDisciplineIsAlreadyExist(name, code, sportKind)) {
                errors.put("objectExist", "Данная спортивная дисциплина уже находится в базе данных!");
            }
        }
    }

    private boolean sportDisciplineIsAlreadyExist(String name, String code, SportKind sk) {
        return sportDisciplineRepository.findByNameAndCodeAndSportKind(name, code, sk) != null;
    }

    public Iterable<SportDiscipline> findAllSportDisciplines() {
        return sportDisciplineRepository.findAll();
    }

    public Iterable<SportDiscipline> findSportDisciplinesBySportKindId(Integer id) {
        return sportDisciplineRepository.findBySportKindId(id);
    }

    public SportDiscipline findSportDisciplineById(Integer id) {
        return sportDisciplineRepository.findById(id).orElse(null);
    }

    public void deleteSportDisciplineById(Integer id) {
        sportDisciplineRepository.deleteById(id);
    }
}
