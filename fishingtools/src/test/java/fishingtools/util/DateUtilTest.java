package fishingtools.util;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

public class DateUtilTest {
	
	@Test
	public void dateConversionTest() throws ParseException {
		
		String value = "03/05/2016";
		Date date = DateUtil.getDateFromString(value);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Assert.assertEquals(03, cal.get(Calendar.DAY_OF_MONTH));
		// months in Java Calendar a 0-indexed
		Assert.assertEquals(04, cal.get(Calendar.MONTH));
		Assert.assertEquals(2016, cal.get(Calendar.YEAR));

	}
	

}
