package utilities;

import java.sql.Timestamp;
import java.util.Date;

public class ParserHelper {

    public static int calculateAge(Date birthDate)  throws Exception{

        Date now = new Date();
        long timeBetween = now.getTime() - birthDate.getTime();
        double yearsBetween = timeBetween / 3.15576e+10;
        int  age= (int) Math.floor(yearsBetween);

        return age;
    }
}
