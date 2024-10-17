package it.corso.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
@Entity
@Table(name = "shoes")
public class Shoe {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	private String name;
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "category_id", referencedColumnName = "id")
	private Category category;
	@Column
	private Double price;
	@Column
	private String description;
	@Column
	private Boolean bestSeller;
	@Column
	private Double rating;
	@Column
	private String img;
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "shoescolor",
			joinColumns = @JoinColumn(name="shoe_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name="color_id", referencedColumnName = "id"))
	List<Color> availableColors= new ArrayList<>();
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "shoessize",
			joinColumns = @JoinColumn(name="shoe_id",referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name="size_id", referencedColumnName = "id"))
	List<Size> availableSizes= new ArrayList<>();

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
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
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
	public Boolean getBestSeller() {
		return bestSeller;
	}
	public void setBestSeller(Boolean bestSeller) {
		this.bestSeller = bestSeller;
	}
	public Double getRating() {
		return rating;
	}
	public void setRating(Double rating) {
		this.rating = rating;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public List<Color> getAvailableColors() {
		return availableColors;
	}
	public void setAvailableColors(List<Color> availableColors) {
		this.availableColors = availableColors;
	}
	public List<Size> getAvailableSizes() {
		return availableSizes;
	}
	public void setAvailableSizes(List<Size> availableSizes) {
		this.availableSizes = availableSizes;
	}


}
