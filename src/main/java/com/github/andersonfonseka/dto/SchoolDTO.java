package com.github.andersonfonseka.dto;

import java.util.Collection;
import java.util.Vector;

public class SchoolDTO extends GenericDescriptionBeanDTO {
	
	private Collection<PersonDTO> persons = new Vector<PersonDTO>();

	
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
