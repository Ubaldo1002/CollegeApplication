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

    public static boolean isValidNameFormat(boolean runValidation, String name){

        if (!runValidation){
            return true;
        }

        String initialName = name.substring(0,1);
        String restName = name.substring(1);

        if(!initialName.equals(name.substring(0,1).toUpperCase())  ||
                !restName.equals(name.substring(1).toLowerCase()) ){
            return false;
        }

        return true;
    }
}
