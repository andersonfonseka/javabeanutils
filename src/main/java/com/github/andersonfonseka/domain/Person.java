package com.github.andersonfonseka.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Person extends GenericBean {
	
	private String name;
	private Date dateOfBirth;
	private Integer age;
	private Double height;
	private Boolean disabledPerson;
	private Character gender;
	private SkinColor skinColor;
	
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

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public Boolean getDisabledPerson() {
		return disabledPerson;
	}

	public void setDisabledPerson(Boolean disabledPerson) {
		this.disabledPerson = disabledPerson;
	}

	public Character getGender() {
		return gender;
	}

	public void setGender(Character gender) {
		this.gender = gender;
	}

	public SkinColor getSkinColor() {
		return skinColor;
	}

	public void setSkinColor(SkinColor skinColor) {
		this.skinColor = skinColor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((addressList == null) ? 0 : addressList.hashCode());
		result = prime * result + ((age == null) ? 0 : age.hashCode());
		result = prime * result + ((contactList == null) ? 0 : contactList.hashCode());
		result = prime * result + ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
		result = prime * result + ((disabledPerson == null) ? 0 : disabledPerson.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + ((height == null) ? 0 : height.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Person other = (Person) obj;
		if (addressList == null) {
			if (other.addressList != null) {
				return false;
			}
		} else if (!addressList.equals(other.addressList)) {
			return false;
		}
		if (age == null) {
			if (other.age != null) {
				return false;
			}
		} else if (!age.equals(other.age)) {
			return false;
		}
		if (contactList == null) {
			if (other.contactList != null) {
				return false;
			}
		} else if (!contactList.equals(other.contactList)) {
			return false;
		}
		if (dateOfBirth == null) {
			if (other.dateOfBirth != null) {
				return false;
			}
		} else if (!dateOfBirth.equals(other.dateOfBirth)) {
			return false;
		}
		if (disabledPerson == null) {
			if (other.disabledPerson != null) {
				return false;
			}
		} else if (!disabledPerson.equals(other.disabledPerson)) {
			return false;
		}
		if (gender == null) {
			if (other.gender != null) {
				return false;
			}
		} else if (!gender.equals(other.gender)) {
			return false;
		}
		if (height == null) {
			if (other.height != null) {
				return false;
			}
		} else if (!height.equals(other.height)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", dateOfBirth=" + dateOfBirth + ", age=" + age + ", height=" + height
				 + ", disabledPerson=" + disabledPerson + ", gender=" + gender
				+ ", addressList=" + addressList + ", contactList=" + contactList + "]";
	}
}
