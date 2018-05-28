package user;

import java.util.Date;

public class ApplicantData {

    private String firstName;
    private String lastName;
    private Date birthDate;
    private Address currentAddress;
    private int SATScore;
    private int ACTScore;
    private Score GPAScore;


    private boolean hasFelonyinLastFiveYears;

    public ApplicantData(String firstName, String lastName, Date birthDate, Address currentAddress, int SATScore
            , int ACTScore, Score GPAScore, boolean hasFelonyinLastFiveYears) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.currentAddress = currentAddress;
        this.SATScore = SATScore;
        this.ACTScore = ACTScore;
        this.GPAScore = GPAScore;
        this.hasFelonyinLastFiveYears = hasFelonyinLastFiveYears;
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

    public int getSATScore() {
        return SATScore;
    }

    public int getACTScore() {
        return ACTScore;
    }

    public Score getGPAScore() {
        return GPAScore;
    }

    public boolean isHasFelonyinLastFiveYears() {
        return hasFelonyinLastFiveYears;
    }

}
