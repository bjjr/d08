package utilities;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	
	private static Integer ONE_DAY = 86400000;
	
	public static Date addDays(Date date, int days){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }
	
	public static Boolean isOneDayAfter(Date checkinDate, Date checkoutDate){
		if((checkoutDate.getTime() - checkinDate.getTime()) >= ONE_DAY){
			return true;
		}else{
			return false;
		}
	}
	
	public static Integer getQuantityOfDays(Date begin, Date end){
		
		double endMinusBegin = (end.getTime() - begin.getTime())/ONE_DAY;
		
		Integer days = (int)endMinusBegin;
		
		return days;
	}
	
}
