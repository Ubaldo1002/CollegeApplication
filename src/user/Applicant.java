package user;

import java.util.Date;

public class Applicant {

    private String firstName;
    private String lastName;
    private Date birthDate;
    private Address currentAddress;
    private Score SATScore;
    private Score ACTScore;
    private Score GPAScore;


    private boolean hasFelonyinLastFiveYears;
    private int numberOfFelonies;

    public Applicant(String firstName, String lastName, Date birthDate, Address currentAddress, Score SATScore
            , Score ACTScore, Score GPAScore, boolean hasFelonyinLastFiveYears, int numberOfFelonies) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.currentAddress = currentAddress;
        this.SATScore = SATScore;
        this.ACTScore = ACTScore;
        this.GPAScore = GPAScore;
        this.hasFelonyinLastFiveYears = hasFelonyinLastFiveYears;
        this.numberOfFelonies = numberOfFelonies;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public Address getCurrentAddress() {
        return currentAddress;
    }

    public Score getSATScore() {
        return SATScore;
    }

    public Score getACTScore() {
        return ACTScore;
    }

    public Score getGPAScore() {
        return GPAScore;
    }

    public boolean isHasFelonyinLastFiveYears() {
        return hasFelonyinLastFiveYears;
    }

    public int getNumberOfFelonies() {
        return numberOfFelonies;
    }
}
