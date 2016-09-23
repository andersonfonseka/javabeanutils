package com.github.andersonfonseka.dto;

import java.util.Collection;
import java.util.Vector;

public class SchoolDTO extends GenericBeanDTO {
	
	private String name;
	
	private Collection<PersonDTO> persons = new Vector<PersonDTO>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Collection<PersonDTO> getPersons() {
		return persons;
	}

	public void setPersons(Collection<PersonDTO> persons) {
		this.persons = persons;
	}

	public void addPerson(PersonDTO person){
		this.persons.add(person);
	}
}
