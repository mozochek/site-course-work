package com.mozochek.service;

import com.mozochek.entity.Tournament;
import com.mozochek.repository.TournamentRepository;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.HashMap;

import static com.mozochek.utils.LengthConstants.*;

@Service
public class TournamentService extends AbstractService {

    private TournamentRepository tournamentRepository;

    private Tournament tournament;

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
}
