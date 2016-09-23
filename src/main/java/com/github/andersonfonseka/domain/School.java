package com.github.andersonfonseka.domain;

import java.util.Collection;
import java.util.Vector;

public class School extends GenericBean {
	
	private String name;
	
	private Collection<Person> persons = new Vector<Person>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
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
