package fishingtools.dao;

import java.util.List;

import fishingtools.domain.FishingRods;

public interface FishingRodsDao {

	boolean save(FishingRods rod);

	List<FishingRods> findAll();

	boolean update(FishingRods newRod, Long id);

	boolean delete(Long id);

	long count();

}
