package com.github.andersonfonseka.dto;

import java.util.ArrayList;
import java.util.List;

public class PersonDTO extends GenericBeanDTO {
	
	private String name;
	
	private List<AddressDTO> addressList = new ArrayList<AddressDTO>();
	
	private List<ContactDTO> contactList = new ArrayList<ContactDTO>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<AddressDTO> getAddressList() {
		return addressList;
	}

	public void addAddressList(AddressDTO address) {
		this.addressList.add(address);
	}

	public List<ContactDTO> getContactList() {
		return contactList;
	}

	public void addContactList(ContactDTO contact) {
		this.contactList.add(contact);
	}

	public void setAddressList(List<AddressDTO> addressList) {
		this.addressList = addressList;
	}

	public void setContactList(List<ContactDTO> contactList) {
		this.contactList = contactList;
	}
	
}
