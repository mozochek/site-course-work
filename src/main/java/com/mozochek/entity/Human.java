package com.mozochek.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;
import java.util.Set;

//TODO Класс почти готов, сделать заключительную проверку
@Entity
@Table(name = "humans", schema = "webdb")
public class Human {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, updatable = false)
    private Integer id;

    @Column(nullable = false, length = 35)
    private String name;

    @Column(nullable = false, length = 35)
    private String surname;

    @Column(nullable = false, length = 35)
    private String patronymic;

    @Column(nullable = false, length = 30)
    private String city;

    @Column(name = "birth_date")
    private Date birthDate;

    @OneToMany(targetEntity = TournamentJudge.class, mappedBy = "human")
    private Set<TournamentJudge> judges;

    @OneToMany(targetEntity = TournamentParticipant.class, mappedBy = "human")
    private Set<TournamentParticipant> participants;

    private Human() {

    }

    public Human(Integer id, String name, String surname, String patronymic, String city, Date birthDate) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.city = city;
        this.birthDate = birthDate;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String getCity() {
        return city;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public Set<TournamentJudge> getJudges() {
        return judges;
    }

    public void setJudges(Set<TournamentJudge> judges) {
        this.judges = judges;
    }

    public Set<TournamentParticipant> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<TournamentParticipant> participants) {
        this.participants = participants;
    }

    @Override
    public String toString() {
        return "Human{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", city='" + city + '\'' +
                ", birthDate=" + birthDate +
                ", judges=" + judges +
                ", participants=" + participants +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Human)) return false;
        Human human = (Human) o;
        return id.equals(human.id) &&
                name.equals(human.name) &&
                surname.equals(human.surname) &&
                patronymic.equals(human.patronymic) &&
                city.equals(human.city) &&
                birthDate.equals(human.birthDate) &&
                Objects.equals(judges, human.judges) &&
                Objects.equals(participants, human.participants);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, patronymic, city, birthDate, judges, participants);
    }
}
