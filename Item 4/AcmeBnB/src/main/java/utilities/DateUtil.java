package utilities;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	public static Date addDays(Date date, int days){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }
	
	public static Boolean isOneDayAfter(Date checkinDate, Date checkoutDate){
		if((checkoutDate.getTime() - checkinDate.getTime()) >= 86400000){
			return true;
		}else{
			return false;
		}
	}
}
