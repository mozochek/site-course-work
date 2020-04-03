package com.mozochek.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

import static com.mozochek.utils.LengthConstants.*;

@Entity
@Table(name = "tournaments", schema = "webdb")
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = TOURNAMENT_NAME_LENGTH)
    private String name;

    @Column(length = TOURNAMENT_FORMAT_LENGTH)
    private String format;

    @Column(length = CITY_LENGTH)
    private String city;

    @Column(name = "date_from")
    private Date dateFrom;

    @Column(name = "date_till")
    private Date dateTill;

    @Column(name = "age_group", length = TOURNAMENT_AGE_GROUP_LENGTH)
    private String ageGroup;

    @Column(length = TOURNAMENT_GENDER_LENGTH)
    private String gender;

    @Column(length = TOURNAMENT_CATEGORY_LENGTH)
    private String category;

    @Column(length = TOURNAMENT_CLASS_LENGTH)
    private String tournamentClass;

    @ManyToOne(targetEntity = SportDiscipline.class)
    @JoinColumn(name = "sport_discipline_id", nullable = false)
    private SportDiscipline sportDiscipline;

    @OneToOne(targetEntity = Team.class)
    @JoinColumn(name = "winner_team_id")
    private Team winnerTeam;

    @OneToOne(targetEntity = Human.class)
    @JoinColumn(name = "main_judge_id")
    private Human mainJudge;

    public Tournament() {

    }

    public Tournament(String name, String format, String city, String ageGroup, String gender, String category, String tournamentClass) {
        this.name = name;
        this.format = format;
        this.city = city;
        this.ageGroup = ageGroup;
        this.gender = gender;
        this.category = category;
        this.tournamentClass = tournamentClass;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTill() {
        return dateTill;
    }

    public void setDateTill(Date dateTill) {
        this.dateTill = dateTill;
    }

    public String getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(String ageGroup) {
        this.ageGroup = ageGroup;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTournamentClass() {
        return tournamentClass;
    }

    public void setTournamentClass(String tournamentClass) {
        this.tournamentClass = tournamentClass;
    }

    public SportDiscipline getSportDiscipline() {
        return sportDiscipline;
    }

    public void setSportDiscipline(SportDiscipline sportDiscipline) {
        this.sportDiscipline = sportDiscipline;
    }

    public Team getWinnerTeam() {
        return winnerTeam;
    }

    public void setWinnerTeam(Team winnerTeam) {
        this.winnerTeam = winnerTeam;
    }

    public Human getMainJudge() {
        return mainJudge;
    }

    public void setMainJudge(Human mainJudge) {
        this.mainJudge = mainJudge;
    }

    @Override
    public String toString() {
        return "Tournament{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sportDiscipline=" + sportDiscipline +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tournament)) return false;
        Tournament that = (Tournament) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(sportDiscipline, that.sportDiscipline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, sportDiscipline);
    }
}
