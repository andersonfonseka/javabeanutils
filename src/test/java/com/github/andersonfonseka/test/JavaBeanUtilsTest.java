package com.github.andersonfonseka.test;

import org.junit.Test;

import com.github.andersonfonseka.core.JavaBeanUtils;
import com.github.andersonfonseka.domain.Address;
import com.github.andersonfonseka.domain.City;
import com.github.andersonfonseka.domain.Contact;
import com.github.andersonfonseka.domain.Person;
import com.github.andersonfonseka.domain.School;
import com.github.andersonfonseka.dto.SchoolDTO;

public class JavaBeanUtilsTest {

	@Test
	public void test() {

		Person person = new Person();
		person.setId(1L);
		person.setName("Anderson Fonseca");

		Person person2 = new Person();
		person2.setId(2L);
		person2.setName("Sophia Fonseca");

		Contact contact = new Contact();
		contact.setNumber("5581992440951");
		contact.setType("mobile");

		Address address = new Address();
		address.setStreet("Manoel Graciliano de Souza");
		address.setZipCode("53140160");

		City city = new City();
		city.setName("Recife");
		city.setCountry("BR");
		city.setProvince("PE");

		address.setCity(city);

		person.addAddressList(address);
		person.addContactList(contact);

		person2.addAddressList(address);
		person2.addContactList(contact);

		School school = new School();
		school.setId(1L);
		school.setName("UPFE-CIn");

		school.addPerson(person);
		school.addPerson(person2);

		System.out.println(school);

		SchoolDTO schoolDTO = new SchoolDTO();
		
		try {
			new JavaBeanUtils().copyProperties(school, schoolDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(schoolDTO);

	}

}
