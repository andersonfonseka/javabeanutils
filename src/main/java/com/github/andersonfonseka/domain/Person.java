package com.github.andersonfonseka.domain;

import java.util.ArrayList;
import java.util.List;

public class Person extends GenericBean {
	
	private String name;
	
	private List<Address> addressList = new ArrayList<Address>();
	
	private List<Contact> contactList = new ArrayList<Contact>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Address> getAddressList() {
		return addressList;
	}

	public void addAddressList(Address address) {
		this.addressList.add(address);
	}

	public List<Contact> getContactList() {
		return contactList;
	}

	public void addContactList(Contact contact) {
		this.contactList.add(contact);
	}
	
}
