package fishingtools.services.impl;

import java.io.File;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import fishingtools.domain.FishingRods;
import fishingtools.domain.FishingRodsXmlHelper;
import fishingtools.services.FileService;



public class XmlFileService implements FileService{
	
	private static final Logger log = Logger.getLogger(XmlFileService.class.getName());
	private File file;

	public void saveAll(List<FishingRods> rods, String path) throws Exception {
		
		file = new File(path);
		JAXBContext jaxbContext = JAXBContext.newInstance(FishingRodsXmlHelper.class);
		Marshaller marshaler = jaxbContext.createMarshaller();
		marshaler.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		FishingRodsXmlHelper helper = new FishingRodsXmlHelper();
		helper.setRodsList(rods);
		marshaler.marshal(helper, file);
		log.log(Level.INFO, String.format("saved objects in file: %s", file.getAbsolutePath()));
		
	}

	public List<FishingRods> readAll(String path) throws Exception {
		file = new File(path);
		JAXBContext jaxbContext = JAXBContext.newInstance(FishingRodsXmlHelper.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		FishingRodsXmlHelper helper = (FishingRodsXmlHelper) unmarshaller.unmarshal(file);
		log.log(Level.INFO, String.format("Returned total of %d objects from %s" ,helper.getRodsList().size(), file.getAbsolutePath()));
		return helper.getRodsList();
		
	}

}
