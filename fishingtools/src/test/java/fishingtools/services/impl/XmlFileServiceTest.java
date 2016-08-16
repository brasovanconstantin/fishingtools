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


public class XmlFileServiceTest {
	
	FileService fs = new XmlFileService();
	final String PATH = "rods.xml";
	final int TOTAL_DEMO_RODS = 10;
	
@Before
	
	public void prepare() throws Exception {
		List<FishingRods> rodsList = DemoData.getDemoRods(TOTAL_DEMO_RODS);
		fs.saveAll(rodsList, PATH);
	}

@Test
public void readAlltest() throws Exception {
	List<FishingRods> rodsList = fs.readAll(PATH);
	assertNotNull(rodsList);
	assertFalse(rodsList.isEmpty());
	assertEquals(TOTAL_DEMO_RODS, rodsList.size());
}

}
