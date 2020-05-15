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

    public SportKindService(SportKindRepository sportKindRepository) {
        this.sportKindRepository = sportKindRepository;
    }

    public boolean saveSportKind(SportKind sportKind) {
        errors = new HashMap<>();
        previousValues = new HashMap<>();

        validateData(sportKind.getName(), sportKind.getCode());

        if (errors.isEmpty()) {
            sportKindRepository.save(sportKind);
            return true;
        }
        previousValues.put("sportKind", sportKind);
        return false;
    }

    private void validateData(String name, String code) {
        validateField(name, SPORT_KIND_NAME_LENGTH, "nameError");
        if (code.length() != CODE_LENGTH || StringUtils.containsWhitespace(code)) {
            errors.put("codeError", "Код должен состоять из " + CODE_LENGTH + " символов(без пробелов)!");
        }
        if (sportKindIsAlreadyExist(name, code)) {
            errors.put("objectExist", "Данный вид спорта уже находится в базе данных!");
        }
    }

    private boolean sportKindIsAlreadyExist(String name, String code) {
        return sportKindRepository.findByNameAndCode(name, code) != null;
    }

    public SportKind findSportKindById(Integer id) {
        return sportKindRepository.findById(id).orElse(null);
    }

    public Iterable<SportKind> findAllSportKinds() {
        return sportKindRepository.findAll();
    }

    public void deleteSportKindById(Integer id) {
        sportKindRepository.deleteById(id);
    }
}
