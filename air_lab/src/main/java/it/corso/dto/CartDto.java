package it.corso.dto;

public class CartDto {
	private Integer id;
	private CartShoeDto shoe;
	private ColorShoeDto color;
	private SizeShoeDto size;
	private int qty;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public CartShoeDto getShoe() {
		return shoe;
	}
	public void setShoe(CartShoeDto shoe) {
		this.shoe = shoe;
	}
	public ColorShoeDto getColor() {
		return color;
	}
	public void setColor(ColorShoeDto color) {
		this.color = color;
	}
	public SizeShoeDto getSize() {
		return size;
	}
	public void setSize(SizeShoeDto size) {
		this.size = size;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
		
	}
