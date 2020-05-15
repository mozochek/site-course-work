package com.mozochek.entity;

import com.mozochek.utils.DateUtil;
import com.mozochek.utils.Gender;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.sql.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static com.mozochek.utils.LengthConstants.CITY_LENGTH;
import static com.mozochek.utils.LengthConstants.HUMAN_NAME_LENGTH;
import static com.mozochek.utils.LengthConstants.HUMAN_PATRONYMIC_LENGTH;
import static com.mozochek.utils.LengthConstants.HUMAN_SURNAME_LENGTH;

@Entity
@Table(name = "people", schema = "webdb")
public class Human implements IrremovableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = HUMAN_NAME_LENGTH)
    private String name;

    @Column(nullable = false, length = HUMAN_SURNAME_LENGTH)
    private String surname;

    @Column(length = HUMAN_PATRONYMIC_LENGTH)
    private String patronymic;

    @Column(length = CITY_LENGTH)
    private String city;

    @Column(nullable = false)
    private Date birthDate;

    @Enumerated
    @Column(columnDefinition = "int2")
    private Gender gender;

    // Многие-ко-многим с Tournament | Список турниров, на которых человек был судьей
    @ManyToMany(mappedBy = "judges")
    private Set<Tournament> tournamentsAsJudge;

    // Многие-ко-многим с Team | Список команд, в которых был этот человек
    @ManyToMany(mappedBy = "people")
    private Set<Team> teams;

    public Human() {

    }

    public Human(String name, String surname, String patronymic, String city, Date birthDate, Gender gender) {
        this.name = capitalizeFirstCharacter(name);
        this.surname = capitalizeFirstCharacter(surname);
        this.patronymic = capitalizeFirstCharacter(patronymic);
        this.city = capitalizeFirstCharacter(city);
        this.birthDate = birthDate;
        this.gender = gender;
    }

    /*
     * Getters and setters
     */
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
        this.name = capitalizeFirstCharacter(name);
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = capitalizeFirstCharacter(surname);
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = capitalizeFirstCharacter(patronymic);
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = capitalizeFirstCharacter(city);
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String printBirthDate() {
        return DateUtil.printFormattedDate(birthDate);
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Set<Tournament> getTournamentsAsJudge() {
        return tournamentsAsJudge;
    }

    public void setTournamentsAsJudge(Set<Tournament> tournamentsAsJudge) {
        this.tournamentsAsJudge = tournamentsAsJudge;
    }

    public Set<Team> getTeams() {
        return teams;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }

    /*
     * Helper methods
     */
    public void addTournamentAsJudge(Tournament tournament) {
        if (tournamentsAsJudge == null) {
            tournamentsAsJudge = new HashSet<>();
        }
        tournamentsAsJudge.add(tournament);
    }

    public void addTeam(Team team) {
        if (teams == null) {
            teams = new HashSet<>();
        }
        teams.add(team);
    }

    private String capitalizeFirstCharacter(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    /*
     * toString, equals, hashCode
     */
    @Override
    public String toString() {
        return "Human{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", city='" + city + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Human)) return false;
        Human human = (Human) o;
        return Objects.equals(id, human.id) &&
                Objects.equals(name, human.name) &&
                Objects.equals(surname, human.surname) &&
                Objects.equals(patronymic, human.patronymic) &&
                Objects.equals(city, human.city) &&
                Objects.equals(birthDate, human.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, patronymic, city, birthDate);
    }
}
