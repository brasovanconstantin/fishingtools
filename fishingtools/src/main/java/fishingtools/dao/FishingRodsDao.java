package fishingtools.dao;

import java.util.List;

import fishingtools.domain.FishingRods;

public interface FishingRodsDao {

	boolean save(FishingRods rod);

	List<FishingRods> findAll();

}
