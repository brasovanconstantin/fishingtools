package fishingtools.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fishingtools.domain.FishingRods;
import fishingtools.domain.Power;



public class DemoData {
	
	private static double rangeMin = 500;
    private static double rangeMax = 50;
    private static Random random = new Random();
	
	public static List<FishingRods> getDemoRods(int totalObjects) {
		
		List<FishingRods> list = new ArrayList<FishingRods>();
		
		
		
		for (int i = 0; i < totalObjects; i++) {
			FishingRods rod = new FishingRods();
			
			rod.setType("type: " + random.nextInt());
			rod.setLenght(random.nextDouble());
			rod.setPower(Power.values()[random.nextInt(Power.values().length)]);
			rod.setMaterial("material: " + random.nextInt());
			rod.setNumberOfPieces(random.nextInt(5)+1);
			rod.setDateOfManufacture(RandomDate.getRandomDate());
			rod.setPrice(getRandomPrice());
			rod.setAvailableInStock(random.nextInt());
			
			
			list.add(rod);
		}
		return list;
	}

	private static double getRandomPrice() {
		double value = rangeMin + (rangeMax - rangeMin) * random.nextDouble();
		return round(value);
	}

	private static double round(double value) {
		
		long factor = (long) Math.pow(10, 2);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
		
	}
	

}
