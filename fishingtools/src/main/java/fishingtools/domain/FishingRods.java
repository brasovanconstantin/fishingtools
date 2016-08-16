package fishingtools.domain;

import java.util.Date;

/**
 * This class represents a Fishing rod object
 * @author Brasovan Constantin
 *
 */

public class FishingRods {
	
	private Long id;
	private String type;
	private double lenght;
	private Power power;
	private String material;
	private int numberOfPieces;
	private Date dateOfManufacture;
	private double price;
	private int availableInStock;
	
	public FishingRods() {
		super();
	}

	public FishingRods(Long id, String type, double lenght, Power power, String material, int numberOfPieces,
			Date dateOfManufacture, double price, int availableInStock) {
		super();
		this.id = id;
		this.type = type;
		this.lenght = lenght;
		this.power = power;
		this.material = material;
		this.numberOfPieces = numberOfPieces;
		this.dateOfManufacture = dateOfManufacture;
		this.price = price;
		this.availableInStock = availableInStock;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getLenght() {
		return lenght;
	}

	public void setLenght(double lenght) {
		this.lenght = lenght;
	}

	public Power getPower() {
		return power;
	}

	public void setPower(Power power) {
		this.power = power;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public int getNumberOfPieces() {
		return numberOfPieces;
	}

	public void setNumberOfPieces(int numberOfPieces) {
		this.numberOfPieces = numberOfPieces;
	}

	public Date getDateOfManufacture() {
		return dateOfManufacture;
	}

	public void setDateOfManufacture(Date dateOfManufacture) {
		this.dateOfManufacture = dateOfManufacture;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getAvailableInStock() {
		return availableInStock;
	}

	public void setAvailableInStock(int availableInStock) {
		this.availableInStock = availableInStock;
	}

	@Override
	public String toString() {
		return "FishingRods [id=" + id + ", type=" + type + ", lenght=" + lenght + ", power=" + power + ", material="
				+ material + ", numberOfPieces=" + numberOfPieces + ", dateOfManufacture=" + dateOfManufacture
				+ ", price=" + price + ", availableInStock=" + availableInStock + "]";
	}

	
	

}
