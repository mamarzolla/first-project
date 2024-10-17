package it.corso.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "cart")
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Integer id;
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "shoe", referencedColumnName = "id")
	private Shoe shoe;
	@Column
	private double price;
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "color", referencedColumnName = "id" )
	private Color color;
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "size", referencedColumnName = "id")
	private Size size;
	@Column
	private Integer qty;
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "user", referencedColumnName = "id")
	private User user;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public Shoe getShoe() {
		return shoe;
	}
	public void setShoe(Shoe shoe) {
		this.shoe = shoe;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public Size getSize() {
		return size;
	}
	public void setSize(Size size) {
		this.size = size;
	}
	public Integer getQty() {
		return qty;
	}
	public void setQty(Integer qty) {
		this.qty = qty;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	

}
