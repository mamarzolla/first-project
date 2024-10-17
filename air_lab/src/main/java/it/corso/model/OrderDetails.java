package it.corso.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ordersdetails")
public class OrderDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name ="order_id", referencedColumnName = "id")
	private Order order;
	@OneToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "shoe_id")
	private Shoe shoe;
	@Column
	private Integer qty;
	@OneToOne(cascade =  CascadeType.REFRESH)
	@JoinColumn(name = "color")
	private Color color;
	@OneToOne(cascade =  CascadeType.REFRESH)
	@JoinColumn(name = "size")
	private Size size;
	@Column
	private double partial;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Shoe getShoe() {
		return shoe;
	}
	public void setShoe(Shoe shoe) {
		this.shoe = shoe;
	}
	public Integer getQty() {
		return qty;
	}
	public void setQty(Integer qty) {
		this.qty = qty;
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
	public double getPartial() {
		return partial;
	}
	public void setPartial(double partial) {
		this.partial = partial;
	}

}
