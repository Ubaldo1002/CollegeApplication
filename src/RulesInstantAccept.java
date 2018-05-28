import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.util.Pair;
import user.StateAgeAccept;
import user.States;

public  class RulesInstantAccept {

        /* ALL must be met to Instant Accept
            In-state (California) age 17 or older, and younger than 26; or older than 80 from any
state.
            o High School GPA of 90% or higher of scale provided in their application. For example,
3.6 on a 4.0 scale; or 4.5 on a 5.0 scale.
            o SAT score greater than 1920 or ACT score greater than 27. Note: Both, or only one of
these, may be present in the applicant.
            o No “instant reject” criteria is hit (see below)
         */

        private static List<StateAgeAccept> stateAndAge =
                new ArrayList ( Arrays.asList( new StateAgeAccept(States.CA, 17,26),
                        new StateAgeAccept(States.ANY, 80,200)));

        private static double equalOrHigherHighSchoolGPAScore= 90;
        private static double greaterSATScore = 1920;
        private static double greaterACTScore = 27;
        private static boolean instantRejectAllowed = false;


    public static List<StateAgeAccept> getStateAndAge() {
        return stateAndAge;
    }

    public static double getEqualOrHigherHighSchoolGPAScore() {
        return equalOrHigherHighSchoolGPAScore;
    }

    public static double getGreaterSATScore() {
        return greaterSATScore;
    }

    public static double getGreaterACTScore() {
        return greaterACTScore;
    }

    public static boolean isInstantRejectAllowed() {
        return instantRejectAllowed;
    }
}
