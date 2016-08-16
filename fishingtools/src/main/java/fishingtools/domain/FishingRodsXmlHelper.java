package fishingtools.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class FishingRodsXmlHelper {

	private List<FishingRods> rodsList;

	public List<FishingRods> getRodsList() {
		return rodsList;
	}

	@XmlElement
	public void setRodsList(List<FishingRods> rodsList) {
		this.rodsList = rodsList;
	}

}
