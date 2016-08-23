package fishingtools.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

	public static Date getDateFromString(String value) throws ParseException {

		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date date = format.parse(value);
		return date;
	}
	
	

}
