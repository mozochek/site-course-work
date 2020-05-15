package com.mozochek.service;

import com.mozochek.entity.Human;
import com.mozochek.repository.HumanRepository;
import com.mozochek.utils.Gender;
import org.springframework.stereotype.Service;

import java.util.HashMap;

import static com.mozochek.utils.LengthConstants.*;

@Service
public class HumanService extends AbstractService {

    private HumanRepository humanRepository;

    public HumanService(HumanRepository humanRepository) {
        this.humanRepository = humanRepository;
    }

    public boolean saveHuman(Human human) {
        errors = new HashMap<>();
        previousValues = new HashMap<>();

        validateData(human);

        if (errors.isEmpty()) {
            humanRepository.save(human);
            return true;
        }
        previousValues.put("human", human);
        return false;
    }

    private void validateData(Human human) {
        validateField(human.getName(), HUMAN_NAME_LENGTH, "nameError");
        validateField(human.getSurname(), HUMAN_SURNAME_LENGTH, "surnameError");
        /*if (!(human.getGender().toLowerCase().equals("мужской") || human.getGender().toLowerCase().equals("женский"))) {
            errors.put("genderError", "Ошибка выбора пола!");
        }*/
        if (!isBlankOrEmpty(human.getPatronymic()) && isLengthIncorrect(human.getPatronymic(), HUMAN_PATRONYMIC_LENGTH)) {
            errors.put("patronymicError", "Длина поля не должна превышать " + HUMAN_PATRONYMIC_LENGTH + "символов!");
        }
        if (isBlankOrEmpty(human.getPatronymic())) {
            human.setPatronymic(null);
        }
        if (!isBlankOrEmpty(human.getCity()) && isLengthIncorrect(human.getCity(), CITY_LENGTH)) {
            errors.put("cityError", "Длина поля не должна превышать " + CITY_LENGTH + "символов!");
        }
        if (isBlankOrEmpty(human.getCity())) {
            human.setCity(null);
        }
        if (human.getBirthDate() == null) {
            errors.put("birthDateError", "Введите дату в формате 'дд.мм.гггг'!");
        }
    }

    public Iterable<Human> findPeople() {
        return humanRepository.findAll();
    }

    public Human findHumanById(Integer id) {
        return humanRepository.findById(id).orElse(null);
    }

    public Iterable<Human> findPeopleByGender(String gender) {
        return humanRepository.findAllByGender(gender);
    }

    public Iterable<Human> findPeopleByGender(Gender gender) {
        return humanRepository.findAllByGender(gender);
    }

    public void deleteHumanById(Integer id) {
        humanRepository.deleteById(id);
    }
}
