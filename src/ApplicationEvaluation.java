import user.ApplicantData;
import user.Score;
import user.States;
import utilities.ParserHelper;

public class ApplicationEvaluation {
    private ApplicantData applicantDataInformation;

    int applicantAge = 0;

    float GPAScoreResult = 0;

    public ApplicationEvaluation(ApplicantData applicantDataInformation) {
        this.applicantDataInformation = applicantDataInformation;

        this.applicantAge = ParserHelper.calculateAge(applicantDataInformation.getBirthDate());

        Score GPAScore = applicantDataInformation.getGPAScore();
        this.GPAScoreResult = ( GPAScore.getScore() * 100)/ GPAScore.getTotalScore();
    }

    public boolean qualifyForInstantAccept(){
        /*
        In-state (California) age 17 or older, and younger than 26; or older than 80 from any
state.
o High School GPA of 90% or higher of scale provided in their application. For example,
3.6 on a 4.0 scale; or 4.5 on a 5.0 scale.
o SAT score greater than 1920 or ACT score greater than 27. Note: Both, or only one of
these, may be present in the applicant.
o No “instant reject” criteria is hit (see below)
         */

        if ((applicantDataInformation.getCurrentAddress().getState() == States.CA
                && (applicantAge >= 17 && applicantAge<26) ) || applicantAge > 80 ){
            return true;
        }

        if(GPAScoreResult > 90 ||
                (applicantDataInformation.getSATScore().getScore()>1920 || applicantDataInformation.getACTScore().getScore()>27)){
            return true;
        }



        return false;
    }


    public boolean qualifyForInstantReject(){
/*
1 or more felonies over the past 5 years.
o High School GPA below 70% of scale provided on application. For example, 2.8 on a 4.0
scale.
o The applicant claimed to be a negative age (it happens!) e.g. “-20” years old.
o The applicant’s first and/or last name are not in the form of first letter capitalized, the
rest lower case.

 */
        if ( applicantDataInformation.isHasFelonyinLastFiveYears() || GPAScoreResult < 70 || applicantAge < 0
                || validateNameFormat(applicantDataInformation.getFirstName())
                || validateNameFormat(applicantDataInformation.getLastName())){
            return true;
        }


        return false;
    }


    private boolean validateNameFormat(String name){
        String initialName = name.substring(0,1);
        String restName = name.substring(1);

        if(!initialName.equals(name.substring(0,1).toUpperCase())  ||
                !restName.equals(name.substring(1).toLowerCase()) ){
            return true;
        }

        return false;
    }
}
