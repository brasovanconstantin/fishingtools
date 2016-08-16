package fishingtools.services.impl;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import fishingtools.domain.FishingRods;
import fishingtools.services.FileService;



public class JsonFileService implements FileService{
	
	private static final Logger log = Logger.getLogger(JsonFileService.class.getName());
	
	private Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
	private File file;

	public void saveAll(List<FishingRods> rods, String path) throws Exception {

		file = new File(path);
		FileWriter fw = new FileWriter(file);
		gson.toJson(rods, fw);
		fw.close();
		log.log(Level.INFO, "objects saved to: " + file.getAbsolutePath());
		
	}

	public List<FishingRods> readAll(String path) throws Exception {
		file = new File(path);
		FileReader fr = new FileReader(file);
		List<FishingRods> rods = gson.fromJson(fr, List.class);
		log.log(Level.INFO, String.format("Loaded from file %s total %d objects ", file.getAbsolutePath(),rods.size()));
		return rods;
		
	}

}
