package com.abc;

import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;

public class DateProvider {
    private static DateProvider instance = null;

    public static DateProvider getInstance() {
        if (instance == null)
            instance = new DateProvider();
        return instance;
    }

    public Date now() {
        return Calendar.getInstance().getTime();
    }
    public String nowInString() {
    	Calendar cal = Calendar.getInstance();
    	int month = cal.get(Calendar.MONTH);
    	int year = cal.get(Calendar.YEAR);
    	int day = cal.get(Calendar.DAY_OF_MONTH);
        return ""+year+""+month+""+day;
    }
    public String dateToString(Date date){
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    	return format.format(date);
    }
}
