package com.mozochek.service;

import com.mozochek.entity.Tournament;
import com.mozochek.repository.TournamentRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import static com.mozochek.utils.LengthConstants.*;

@Service
public class TournamentService {

    private TournamentRepository tournamentRepository;

    private Tournament tournament;
    private HashMap<String, String> errors;
    private HashMap<String, String> previousValues;

    public TournamentService(TournamentRepository tournamentRepository) {
        this.tournamentRepository = tournamentRepository;
    }

    public boolean addTournament(Tournament tournament, String dateFrom, String dateTill) {
        this.tournament = tournament;
        errors = new HashMap<>();
        previousValues = new HashMap<>();

        validateData(dateFrom, dateTill);

        if (errors.isEmpty()) {
            tournamentRepository.save(this.tournament);
            return true;
        }
        setPreviousValues();
        return false;
    }

    private void validateData(String dateFrom, String dateTill) {
        validateField(tournament.getName(), TOURNAMENT_NAME_LENGTH, "nameError");
        validateField(tournament.getFormat(), TOURNAMENT_FORMAT_LENGTH, "formatError");
        validateField(tournament.getCity(), CITY_LENGTH, "cityError");
        validateField(tournament.getAgeGroup(), TOURNAMENT_AGE_GROUP_LENGTH, "ageGroupError");
        validateField(tournament.getGender(), TOURNAMENT_GENDER_LENGTH, "genderError");
        validateField(tournament.getCategory(), TOURNAMENT_CATEGORY_LENGTH, "categoryError");
        validateField(tournament.getTournamentClass(), TOURNAMENT_CLASS_LENGTH, "tournamentClassError");
        tournament.setDateFrom(validateAndFormatDate(dateFrom, "dateFromError"));
        tournament.setDateTill(validateAndFormatDate(dateTill, "dateTillError"));
    }

    private void validateField(String str, int maxLength, String errorName) {
        if (isBlankOrEmpty(str)) {
            errors.put(errorName, "Заполните поле!");
        }
        if (isLengthIncorrect(str, maxLength)) {
            errors.put(errorName, "Длина поля не должна превышать " + maxLength + "символов!");
        }
    }

    private Date validateAndFormatDate(String date, String errorName) {
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

    private boolean isBlankOrEmpty(String str) {
        return StringUtils.isEmpty(str) || str.isBlank();
    }

    private boolean isLengthIncorrect(String str, int maxLength) {
        return str.length() > maxLength;
    }

    private void setPreviousValues() {
        if (errors.get("nameError") == null) {
            previousValues.put("prevName", tournament.getName());
        }
        if (errors.get("formatError") == null) {
            previousValues.put("prevFormat", tournament.getFormat());
        }
        if (errors.get("cityError") == null) {
            previousValues.put("prevCity", tournament.getCity());
        }
        if (errors.get("ageGroupError") == null) {
            previousValues.put("prevAgeGroup", tournament.getAgeGroup());
        }
        if (errors.get("genderError") == null) {
            previousValues.put("prevGender", tournament.getGender());
        }
        if (errors.get("categoryError") == null) {
            previousValues.put("prevCategory", tournament.getCategory());
        }
        if (errors.get("tournamentClassError") == null) {
            previousValues.put("prevTournamentClass", tournament.getTournamentClass());
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        if (errors.get("dateFromError") == null) {
            previousValues.put("prevDateFrom", simpleDateFormat.format(tournament.getDateFrom()).replace('-', '.'));
        }
        if (errors.get("dateTillError") == null) {
            previousValues.put("prevDateTill", simpleDateFormat.format(tournament.getDateTill()).replace('-', '.'));
        }
    }

    public HashMap<String, String> getErrors() {
        return errors;
    }

    public HashMap<String, String> getPreviousValues() {
        return previousValues;
    }
}
