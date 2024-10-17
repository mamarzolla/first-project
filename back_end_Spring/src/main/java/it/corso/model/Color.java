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
import jakarta.persistence.Table;

@Entity
@Table(name="colors")
public class Color {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	private String color;
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "shoescolor",
			joinColumns = @JoinColumn(name="color_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name="shoe_id", referencedColumnName = "id"))
	List<Shoe> availableColors= new ArrayList<>();
	
	  
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public List<Shoe> getAvailableColors() {
		return availableColors;
	}
	public void setAvailableColors(List<Shoe> availableColors) {
		this.availableColors = availableColors;
	}

	
}
