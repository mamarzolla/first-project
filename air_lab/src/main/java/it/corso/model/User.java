package it.corso.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "Users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	@Pattern(regexp="[a-zA-Z\\sòàèì']{1,50}", message="Error in Name")
	private String name;
	@Column
	@Pattern(regexp="[a-zA-Z\\sòàèì']{1,50}", message="Error in Last Name")
	private String lastName;
	@Column
	@Pattern(regexp = "^[A-Za-z0-9._%+-]{1,50}@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$", message = "Email address not correct")
	private String email;
	@Column
	@Pattern(regexp="[0-9]{1,10}", message="Error in phone One")
	private String phoneOne;
	@Column
	@Pattern(regexp="[0-9]{1,10}", message="Error in phone two")
	private String phoneTwo;
	@Column
	@JsonIgnore
	private String token;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "profile_id")
	private Profile profile;
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private List<Address> addressList;
	@OneToMany(mappedBy = "user",fetch = FetchType.EAGER, orphanRemoval = true)
	private List<Cart> cartList;
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true)
	private List<Order> orderList;
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
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneOne() {
		return phoneOne;
	}
	public void setPhoneOne(String phoneOne) {
		this.phoneOne = phoneOne;
	}
	public String getPhoneTwo() {
		return phoneTwo;
	}
	public void setPhoneTwo(String phoneTwo) {
		this.phoneTwo = phoneTwo;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Profile getProfile() {
		return profile;
	}
	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	public List<Address> getAddressList() {
		return addressList;
	}
	public void setAddressList(List<Address> addressList) {
		this.addressList = addressList;
	}
	public List<Cart> getCartList() {
		return cartList;
	}
	public void setCartList(List<Cart> cartList) {
		this.cartList = cartList;
	}
	public List<Order> getOrderList() {
		return orderList;
	}
	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}

}
