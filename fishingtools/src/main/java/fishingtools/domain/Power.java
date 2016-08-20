package fishingtools.domain;

public enum Power {
	
	LIGHT, MEDIUM, HEAVY, ULTRA_HEAVY;	
	
	public static Power getPower(Object cellValue) {

		Power[] array = Power.values();
		for (int i = 0; i < array.length; i++) {
			if (cellValue.toString().equals(array[i].toString())) {
				return array[i];
			}
		}
		
		return null;
	}

}


