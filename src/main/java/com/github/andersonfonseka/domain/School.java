package com.github.andersonfonseka.domain;

import java.util.Collection;
import java.util.Vector;

public class School extends GenericDescriptionBean {
	
	private Collection<Person> persons = new Vector<Person>();

	
	public Collection<Person> getPersons() {
		return persons;
	}

	public void setPersons(Collection<Person> persons) {
		this.persons = persons;
	}

	public void addPerson(Person person){
		this.persons.add(person);
	}
}
