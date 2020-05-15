package com.mozochek.entity;

import com.mozochek.utils.DateUtil;
import com.mozochek.utils.Gender;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.sql.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static com.mozochek.utils.LengthConstants.CITY_LENGTH;
import static com.mozochek.utils.LengthConstants.TOURNAMENT_AGE_GROUP_LENGTH;
import static com.mozochek.utils.LengthConstants.TOURNAMENT_CATEGORY_LENGTH;
import static com.mozochek.utils.LengthConstants.TOURNAMENT_CLASS_LENGTH;
import static com.mozochek.utils.LengthConstants.TOURNAMENT_FORMAT_LENGTH;
import static com.mozochek.utils.LengthConstants.TOURNAMENT_NAME_LENGTH;

@Entity
@Table(name = "tournaments", schema = "webdb")
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = TOURNAMENT_NAME_LENGTH)
    private String name;

    @Column(nullable = false, length = TOURNAMENT_FORMAT_LENGTH)
    private String format;

    @Column(nullable = false, length = CITY_LENGTH)
    private String city;

    @Column(nullable = false)
    private Date dateFrom;

    @Column(nullable = false)
    private Date dateTill;

    @Column(nullable = false, length = TOURNAMENT_AGE_GROUP_LENGTH)
    private String ageGroup;

    @Column(columnDefinition = "int2")
    private Gender gender;

    @Column(nullable = false, length = TOURNAMENT_CATEGORY_LENGTH)
    private String category;

    @Column(nullable = false, length = TOURNAMENT_CLASS_LENGTH)
    private String tournamentClass;

    @Column(nullable = false)
    private Short teamCapacity;

    @Column(nullable = false)
    private Short teamAmount;

    // Многие-к-одному с SportDiscipline | Спортивная дисциплина, по которой проводится турнир
    @ManyToOne(targetEntity = SportDiscipline.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "sport_discipline_id", nullable = false)
    private SportDiscipline sportDiscipline;

    // Один-к-одному с Team | Победитель турнира
    @OneToOne(targetEntity = Team.class)
    //@JoinTable(name = "tournament_winners", joinColumns = @JoinColumn(name = "tournament_id", nullable = false), inverseJoinColumns = @JoinColumn(name = "team_id", nullable = false))
    @JoinColumn(name = "winner_team_id", referencedColumnName = "id")
    private Team winnerTeam;

    // Многие-ко-многим с Human | Судьи турнира
    @ManyToMany(targetEntity = Human.class)
    @JoinTable(name = "tournament_judges", joinColumns = @JoinColumn(name = "tournament_id", nullable = false), inverseJoinColumns = @JoinColumn(name = "human_id", nullable = false))
    private Set<Human> judges;

    // Один-ко-многи с Team | Команды-участницы турнира
    @OneToMany(targetEntity = Team.class, mappedBy = "tournament")
    private Set<Team> teams;

    // Один-ко-многим с Match | Матчи турнира
    @OneToMany(targetEntity = Match.class, mappedBy = "tournament")
    private Set<Match> matches;

    public Tournament() {

    }

    public Tournament(String name, String format, String city, Date dateFrom, Date dateTill, String ageGroup, Gender gender, String category, String tournamentClass, Short teamCapacity, Short teamAmount, SportDiscipline sportDiscipline) {
        this.name = name;
        this.format = format;
        this.city = city.substring(0, 1).toUpperCase() + city.substring(1);
        this.dateFrom = dateFrom;
        this.dateTill = dateTill;
        this.ageGroup = ageGroup;
        this.gender = gender;
        this.category = category;
        this.tournamentClass = tournamentClass;
        this.teamCapacity = teamCapacity;
        this.teamAmount = teamAmount;
        this.sportDiscipline = sportDiscipline;
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
        this.city = city.substring(0, 1).toUpperCase() + city.substring(1);
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
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

    public Short getTeamCapacity() {
        return teamCapacity;
    }

    public void setTeamCapacity(Short teamCapacity) {
        this.teamCapacity = teamCapacity;
    }

    public Short getTeamAmount() {
        return teamAmount;
    }

    public void setTeamAmount(Short teamAmount) {
        this.teamAmount = teamAmount;
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

    public Set<Human> getJudges() {
        return judges;
    }

    public void setJudges(Set<Human> judges) {
        this.judges = judges;
    }

    public Set<Team> getTeams() {
        return teams;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }

    public Set<Match> getMatches() {
        return matches;
    }

    public void setMatches(Set<Match> matches) {
        this.matches = matches;
    }

    /*
     * Helper methods
     */
    public String printDateFrom() {
        return DateUtil.printFormattedDate(dateFrom);
    }

    public String printDateTill() {
        return DateUtil.printFormattedDate(dateTill);
    }

    public void addJudge(Human judge) {
        if (judges == null) {
            judges = new HashSet<>();
        }
        judges.add(judge);
    }

    public void addTeam(Team team) {
        if (teams == null) {
            teams = new HashSet<>();
        }
        teams.add(team);
    }

    public void addMatch(Match match) {
        if (matches == null) {
            matches = new HashSet<>();
        }
        matches.add(match);
    }

    public void removeMatches(Iterable<Match> matches) {
        for (Match match : matches) {
            removeMatch(match);
        }
    }

    public void removeMatch(Match match) {
        if(matches != null) {
            matches.remove(match);
        }
    }

    public void removeTeam(Team team) {
        if (teams != null) {
            teams.remove(team);
        }
    }

    public void removeJudge(Human judge) {
        if (judges != null) {
            judges.remove(judge);
        }
    }

    /*
     * toString, equals, hashCode
     */
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
