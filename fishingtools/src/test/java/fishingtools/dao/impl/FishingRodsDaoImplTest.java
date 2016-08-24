package fishingtools.dao.impl;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fishingtools.dao.FishingRodsDao;
import fishingtools.domain.FishingRods;
import fishingtools.domain.Power;
import fishingtools.util.DemoData;
import mavendemo.domain.User;



public class FishingRodsDaoImplTest {
	
	private FishingRodsDao dao = new FishingRodsDaoImpl();
	
	@Before
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
	
	@Test
	public void testUpdate(){
		final Long ROD_ID = 1L;
		FishingRods newRod = new FishingRods();
		newRod.setType("new_type");
		newRod.setLenght(2.4);
		newRod.setPower(Power.HEAVY);
		newRod.setMaterial("new_material");
		newRod.setDateOfManufacture(Date.class.newInstance());
		newRod.setPrice(300.0);
		newRod.setAvailableInStock(10);
		
		boolean result = dao.update(newRod, ROD_ID);


		Assert.assertTrue(result);
	}
		
	@Test
	public void testDelete(){
		final Long ROD_ID = 4L;
		boolean result = dao.delete(ROD_ID);
		Assert.assertTrue(result);
	}
		
}
