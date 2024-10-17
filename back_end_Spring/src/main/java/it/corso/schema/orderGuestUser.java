package it.corso.schema;

import java.util.List;

public class orderGuestUser {
	private String name;
	private String lastName;
	private String email;
	private String phoneOne;
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
	public List<AddressesSchema> getAddressList() {
		return addressList;
	}
	public void setAddressList(List<AddressesSchema> addressList) {
		this.addressList = addressList;
	}
	private String phoneTwo;
	private List<AddressesSchema> addressList;
}
