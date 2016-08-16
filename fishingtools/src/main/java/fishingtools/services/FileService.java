package fishingtools.services;

import java.util.List;

import fishingtools.domain.FishingRods;

public interface FileService {
	
	void saveAll(List<FishingRods> rods, String path) throws Exception;
	
	List<FishingRods> readAll(String path)  throws Exception;

}
