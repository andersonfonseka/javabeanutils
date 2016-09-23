package com.github.andersonfonseka.test;

import org.junit.Test;

import com.github.andersonfonseka.core.JavaBeanUtils;
import com.github.andersonfonseka.domain.Address;
import com.github.andersonfonseka.domain.City;
import com.github.andersonfonseka.domain.Contact;
import com.github.andersonfonseka.domain.Country;
import com.github.andersonfonseka.domain.Person;
import com.github.andersonfonseka.domain.Province;
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
		contact.setId(1L);
		contact.setDescription("5581992440951");
		contact.setType("mobile");
		
		Contact contact2 = new Contact();
		contact2.setId(2L);
		contact2.setDescription("sophia.fonseka@gmail.com");
		contact2.setType("email");

		Address address = new Address();
		address.setStreet("Manoel Graciliano de Souza");
		address.setZipCode("53140160");

		Country country = new Country();
		country.setId(1L);
		country.setDescription("BR");
		
		Province province = new Province();
		province.setId(1L);
		province.setDescription("PE");
		province.setCountry(country);
		
		City city = new City();
		city.setId(1L);
		city.setDescription("Olinda");
		city.setProvince(province);

		address.setCity(city);

		person.addAddressList(address);
		person.addContactList(contact);

		person2.addAddressList(address);
		person2.addContactList(contact2);

		School school = new School();
		school.setId(1L);
		school.setDescription("UPFE-CIn");

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
