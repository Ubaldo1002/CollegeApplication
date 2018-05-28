import user.ApplicantData;
import user.Score;
import user.StateAgeAccept;
import user.States;
import utilities.ParserHelper;

public class ApplicationEvaluation {
    private ApplicantData applicantDataInformation;

    int applicantAge = 0;

    float GPAScoreResult = 0;

    public ApplicationEvaluation(ApplicantData applicantDataInformation) {
        this.applicantDataInformation = applicantDataInformation;

        try {
            this.applicantAge = ParserHelper.calculateAge(applicantDataInformation.getBirthDate());

            Score GPAScore = applicantDataInformation.getGPAScore();
            this.GPAScoreResult = (GPAScore.getScore() * 100) / GPAScore.getTotalScore();
        }catch(Exception err){
            err.printStackTrace();
        }
    }

    public boolean qualifyForInstantAccept(boolean wasInstantReject){
        /* ALL must be met
        In-state (California) age 17 or older, and younger than 26; or older than 80 from any
state.
o High School GPA of 90% or higher of scale provided in their application. For example,
3.6 on a 4.0 scale; or 4.5 on a 5.0 scale.
o SAT score greater than 1920 or ACT score greater than 27. Note: Both, or only one of
these, may be present in the applicant.
o No “instant reject” criteria is hit (see below)
         */

        if(!RulesInstantAccept.isInstantRejectAllowed()){
            if(wasInstantReject)
                return false;
        }

        boolean meetAgeAndState = false;
        for(StateAgeAccept ageRules : RulesInstantAccept.getStateAndAge()){
            if(ageRules.getState().equals(States.ANY)){
                if (applicantAge >= ageRules.getStartRangeYearOld() && applicantAge < ageRules.getEndRangeYearOld()){
                    meetAgeAndState = true;
                    break;
                }
            }else if(ageRules.getState() == applicantDataInformation.getCurrentAddress().getState()){
                if (applicantAge >= ageRules.getStartRangeYearOld() && applicantAge< ageRules.getEndRangeYearOld()){
                    meetAgeAndState = true;
                    break;
                }
            }

        }
        if(!meetAgeAndState){
            return false;
        }


        if(RulesInstantAccept.getEqualOrHigherHighSchoolGPAScore() < GPAScoreResult){
            return false;
        }

        if(applicantDataInformation.getACTScore() > 0){
            if (RulesInstantAccept.getGreaterACTScore() > applicantDataInformation.getACTScore()){
                return false;
            }
        }

        if(applicantDataInformation.getSATScore() > 0){
            if (RulesInstantAccept.getGreaterSATScore() > applicantDataInformation.getSATScore()){
                return false;
            }
        }


        return true;
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
       if ( RulesInstantReject.isAcceptFelonies() == !applicantDataInformation.isHasFelonyinLastFiveYears()
           || RulesInstantReject.getBelowHighSchoolGPAScore() > GPAScoreResult
           || RulesInstantReject.getAgeLowerThan() > applicantAge
           || RulesInstantReject.isValidateFirstNameFormat() == isValidNameFormat(RulesInstantReject.isValidateFirstNameFormat(), applicantDataInformation.getFirstName())
           || RulesInstantReject.isValidateLastNameFormat() ==  isValidNameFormat(RulesInstantReject.isValidateLastNameFormat() , applicantDataInformation.getLastName())
       ){
          return true;
        }

        return false;
    }


    private boolean isValidNameFormat(boolean runValidation, String name){

        if (!runValidation){
            return true;
        }

        String initialName = name.substring(0,1);
        String restName = name.substring(1);

        if(!initialName.equals(name.substring(0,1).toUpperCase())  ||
                !restName.equals(name.substring(1).toLowerCase()) ){
            return true;
        }

        return false;
    }
}
