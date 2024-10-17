package it.corso.dto;

import java.util.List;

public class InsertShoe {
	private String name;
	private String category;
	private double price;
	private List<String>availableSizes;
	private List<String>availableColors;
	private String description;
	private boolean bestSeller;
	private double rating;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public List<String> getAvailableSizes() {
		return availableSizes;
	}
	public void setAvailableSizes(List<String> availableSizes) {
		this.availableSizes = availableSizes;
	}
	public List<String> getAvailableColors() {
		return availableColors;
	}
	public void setAvailableColors(List<String> availableColors) {
		this.availableColors = availableColors;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isBestSeller() {
		return bestSeller;
	}
	public void setBestSeller(boolean bestSeller) {
		this.bestSeller = bestSeller;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

}
