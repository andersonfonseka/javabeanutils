package com.github.andersonfonseka.test;

import java.util.Date;

import org.junit.Test;

import com.github.andersonfonseka.core.JavaBeanUtils;
import com.github.andersonfonseka.domain.Address;
import com.github.andersonfonseka.domain.City;
import com.github.andersonfonseka.domain.Contact;
import com.github.andersonfonseka.domain.Country;
import com.github.andersonfonseka.domain.Person;
import com.github.andersonfonseka.domain.Province;
import com.github.andersonfonseka.domain.School;
import com.github.andersonfonseka.dto.PersonDTO;

public class JavaBeanUtilsWrapperTest {

	
	@Test
	public void wrapperTest() throws Exception {
		
		Person person = createPerson();
		PersonDTO personDTO = new PersonDTO();
		
		JavaBeanUtils javaBeanUtils = new JavaBeanUtils();
		javaBeanUtils.copyProperties(person, personDTO);
		System.err.println(personDTO);
	}

	private Person createPerson() {
		Person person = new Person();
		person.setAge(Integer.valueOf(40));
		person.setDateOfBirth(new Date());
		person.setDisabledPerson(Boolean.FALSE);
		person.setGender('M');
		person.setHeight(Double.valueOf(1.8));
		person.setId(1L);
		person.setName("Juan Wilson");
		
		
		Person person2 = new Person();
		
		person2.setAge(Integer.valueOf(20));
		person2.setDateOfBirth(new Date());
		person2.setDisabledPerson(Boolean.FALSE);
		person2.setGender('F');
		person2.setHeight(Double.valueOf(1.6));
		person2.setId(2L);
		person2.setName("Mary Wilson");
		
		
		Contact contact = new Contact();
		contact.setId(1L);
		contact.setDescription("5581992440951");
		contact.setType("mobile");
		
		Contact contact2 = new Contact();
		contact2.setId(2L);
		contact2.setDescription("mary.wilson@gmail.com");
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
		school.setDescription("UFPE-CIn");

		school.addPerson(person);
		school.addPerson(person2);

		return person;
	}
}
