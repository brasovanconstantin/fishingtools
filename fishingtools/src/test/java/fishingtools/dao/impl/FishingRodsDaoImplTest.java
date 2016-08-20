package fishingtools.dao.impl;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import fishingtools.dao.FishingRodsDao;
import fishingtools.domain.FishingRods;
import fishingtools.util.DemoData;



public class FishingRodsDaoImplTest {
	
	private FishingRodsDao dao = new FishingRodsDaoImpl();
	
	@Test
	public void testSave() {
		
		FishingRods rod = DemoData.getDemoRods(1).get(0);
		
		boolean result = dao.save(rod);
		Assert.assertTrue(result);
	}
	
	@Test
	public void testFindAll() {
		List<FishingRods> allrods = dao.findAll();
		Assert.assertNotNull(allrods);
		Assert.assertFalse(allrods.isEmpty());
		Assert.assertTrue(allrods.size()>0);
	}
		
		
}
