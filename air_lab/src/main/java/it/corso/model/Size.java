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
@Table(name="sizes")
public class Size {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Integer id;
	@Column
	private String size;
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "shoessize",
			joinColumns = @JoinColumn(name="size_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name="shoe_id", referencedColumnName = "id"))
	List<Shoe> availableSizes =  new ArrayList<>();
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public List<Shoe> getAvailableSizes() {
		return availableSizes;
	}
	public void setAvailableSizes(List<Shoe> availableSizes) {
		this.availableSizes = availableSizes;
	}

}
