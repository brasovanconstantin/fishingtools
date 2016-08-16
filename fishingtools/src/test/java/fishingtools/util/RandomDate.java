package fishingtools.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class RandomDate {

	public static Date getRandomDate() {

		GregorianCalendar gc = new GregorianCalendar();

		int year = 2016;

		gc.set(Calendar.YEAR, year);

		int dayOfYear = randBetween(1, gc.getActualMaximum(Calendar.DAY_OF_YEAR));

		gc.set(Calendar.DAY_OF_YEAR, dayOfYear);

		return gc.getTime();
	}

	public static int randBetween(int start, int end) {
		return start + (int) Math.round(Math.random() * (end - start));
	}
}
