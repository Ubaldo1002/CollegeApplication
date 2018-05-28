public class RulesInstantReject {
    /* Instant reject if ANY of these
            1 or more felonies over the past 5 years.
            o High School GPA below 70% of scale provided on application. For example, 2.8 on a 4.0
    scale.
            o The applicant claimed to be a negative age (it happens!) e.g. “-20” years old.
            o The applicant’s first and/or last name are not in the form of first letter capitalized, the
    rest lower case.

     */
    private static boolean acceptFelonies = false;
    private static float belowHighSchoolGPAScore= 70;
    private static int ageLowerThan = 0;
    private static boolean validateFirstNameFormat = true;
    private static boolean validateLastNameFormat = true;

    public static boolean isAcceptFelonies() {
        return acceptFelonies;
    }

    public static float getBelowHighSchoolGPAScore() {
        return belowHighSchoolGPAScore;
    }

    public static int getAgeLowerThan() {
        return ageLowerThan;
    }

    public static boolean isValidFirstNameFormat() {
        return validateFirstNameFormat;
    }

    public static boolean isValidLastNameFormat() {
        return validateLastNameFormat;
    }
}