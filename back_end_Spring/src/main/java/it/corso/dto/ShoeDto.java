package it.corso.dto;

import java.util.List;

public class ShoeDto {

	private Integer id;

	private String name;

	private CategoryDto category;

	private Double price;

	private String description;

	private String image;

	private Boolean bestSeller;

	private double rating;
	
	private List<ColorShoeDto> availableColors;
	
	private List<SizeShoeDto> availableSizes;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CategoryDto getCategory() {
		return category;
	}

	public void setCategory(CategoryDto category) {
		this.category = category;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public List<SizeShoeDto> getAvailableSizes() {
		return availableSizes;
	}

	public void setAvailableSizes(List<SizeShoeDto> availableSizes) {
		this.availableSizes = availableSizes;
	}

	public List<ColorShoeDto> getAvailableColors() {
		return availableColors;
	}

	public void setAvailableColors(List<ColorShoeDto> availableColors) {
		this.availableColors = availableColors;
	}

	public Boolean getBestSeller() {
		return bestSeller;
	}

	public void setBestSeller(Boolean bestSeller) {
		this.bestSeller = bestSeller;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

}
