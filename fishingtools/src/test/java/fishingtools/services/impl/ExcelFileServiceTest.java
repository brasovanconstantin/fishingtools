package fishingtools.services.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import fishingtools.domain.FishingRods;
import fishingtools.services.FileService;
import fishingtools.util.DemoData;

public class ExcelFileServiceTest {
	
	FileService fs = new ExcelFileService();
	private final String PATH = "rods.xls";
	final int TOTAL_DEMO_RODS = 10;
	
@Test
	
	public void prepare() throws Exception {
		List<FishingRods> rodsList = DemoData.getDemoRods(TOTAL_DEMO_RODS);
		fs.saveAll(rodsList, PATH);
	}



}
