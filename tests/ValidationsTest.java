import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import user.Address;
import user.ApplicantData;
import user.Score;
import user.States;
import utilities.ParserHelper;

import static utilities.ParserHelper.isValidNameFormat;

public class ValidationsTest {


    @Test
    public void testApplicationForInstantReject(){
        Address myAddress = new Address("2410 E 3rd Street", "National City", States.CA, 91950);

        ApplicantData applicantData = new ApplicantData("Ubaldo", "Reyes"
                , java.sql.Timestamp.valueOf("1982-02-10 00:10:10.0"), myAddress
                , 1900, 20, new Score(10,15), false);

        ApplicationEvaluation applicationEvaluation = new ApplicationEvaluation(applicantData);
        String result = applicationEvaluation.qualifyForInstantReject();

        if(result.contains("Felonies"))
            Assertions.assertEquals("Has Felonies in the last 5 Years", result);
        else if(result.contains("GPA"))
            Assertions.assertEquals("Has High School GPA Score below: " + RulesInstantReject.getBelowHighSchoolGPAScore(), result);
        else if(result.contains("Age"))
            Assertions.assertEquals("Has Age Lower than: " + RulesInstantReject.getAgeLowerThan(), result);
        else if(result.contains("First Name"))
            Assertions.assertEquals("First Name Format is Incorrect", result);
        else if(result.contains("Last Name"))
            Assertions.assertEquals("Last Name Format is Incorrect", result);
    }


    @Test
    public void testApplicationForInstantAccept(){
        Address myAddress = new Address("2410 E 3rd Street", "National City", States.CA, 91950);

        ApplicantData applicantData = new ApplicantData("Ubaldo", "Reyes"
                , java.sql.Timestamp.valueOf("1998-02-10 00:10:10.0"), myAddress
                , 1950, 29, new Score(14,15), false);

        ApplicationEvaluation applicationEvaluation = new ApplicationEvaluation(applicantData);
        boolean result = applicationEvaluation.qualifyForInstantAccept(false);

        Assertions.assertEquals(true, result);
    }

    @Test
    public void validateFirstNameFormat(){
        String firstName = "UbaldoAA";

        boolean firstNameIsValid = isValidNameFormat(RulesInstantReject.isValidFirstNameFormat() , firstName);

        Assertions.assertEquals(false, firstNameIsValid);
    }


    @Test
    public void validateLastNameFormat(){
        String lastName = "ReyeS";

        boolean lastNameIsValid = isValidNameFormat(RulesInstantReject.isValidLastNameFormat() , lastName);
        Assertions.assertEquals(false, lastNameIsValid);

        //don't validate lastName
        boolean lastName2IsValid = isValidNameFormat(false , lastName);
        Assertions.assertEquals(true, lastName2IsValid);
    }


    @Test
    public void calculateAge() throws Exception {

        Timestamp timestamp =  java.sql.Timestamp.valueOf("2010-05-28 10:10:10.0");

        int age = ParserHelper.calculateAge(new Date(timestamp.getTime()));

        Assertions.assertEquals(8, age);
    }



}
