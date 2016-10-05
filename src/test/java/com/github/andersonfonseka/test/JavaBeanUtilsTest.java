package com.github.andersonfonseka.test;

import java.util.Date;
import java.util.List;
import java.util.Vector;

import org.junit.Assert;
import org.junit.Test;

import com.github.andersonfonseka.core.JavaBeanUtils;
import com.github.andersonfonseka.domain.Address;
import com.github.andersonfonseka.domain.City;
import com.github.andersonfonseka.domain.Contact;
import com.github.andersonfonseka.domain.Country;
import com.github.andersonfonseka.domain.Person;
import com.github.andersonfonseka.domain.Province;
import com.github.andersonfonseka.domain.School;
import com.github.andersonfonseka.domain.SkinColor;
import com.github.andersonfonseka.dto.AddressDTO;
import com.github.andersonfonseka.dto.ContactDTO;
import com.github.andersonfonseka.dto.PersonDTO;
import com.github.andersonfonseka.dto.SchoolDTO;

public class JavaBeanUtilsTest {

	
	@Test
	public void javaBeanUtilsTest() throws Exception {
		
		School school = createSchool();
		SchoolDTO schoolDTO = new SchoolDTO();
		
		JavaBeanUtils javaBeanUtils = new JavaBeanUtils();
		javaBeanUtils.copyProperties(school, schoolDTO);
		
		
		assertSchoolFields(school, schoolDTO);
	}
	
	@Test
	public void addCopyReferenceClassTest() throws Exception {
		//TODO implement
	}

	private void assertSchoolFields(School school, SchoolDTO schoolDTO) {
		Vector<Person> persons =(Vector<Person>)school.getPersons();
		
		for(int i = 0; i < persons.size() ; i++){
			assertPersonFields(persons.get(i), ((Vector<PersonDTO>)schoolDTO.getPersons()).get(i));
		}
	}

	private void assertPersonFields(Person person, PersonDTO personDTO) {
		Assert.assertEquals(person.getId(), personDTO.getId());
		Assert.assertEquals(person.getName(), personDTO.getName());
		Assert.assertEquals(person.getAge(), personDTO.getAge());
		Assert.assertEquals(person.getDateOfBirth(), personDTO.getDateOfBirth());
		Assert.assertEquals(person.getDisabledPerson(), personDTO.getDisabledPerson());
		Assert.assertEquals(person.getGender(), personDTO.getGender());
		Assert.assertEquals(person.getHeight(), personDTO.getHeight());
		assertSkinColor(person, personDTO);
		
		assertAdressList(person.getAddressList(),personDTO.getAddressList());
		assertContactList(person.getContactList(),personDTO.getContactList());
	}

	private void assertSkinColor(Person person, PersonDTO personDTO) {
		if(person.getSkinColor()!= null){
			Assert.assertEquals(person.getSkinColor().name(), personDTO.getSkinColor().name());
		}
	}

	private void assertAdressList(List<Address> addressList, List<AddressDTO> addressListDTO) {
		Assert.assertTrue(addressList.size() == addressListDTO.size());
		
		for(int i = 0; i < addressList.size() ; i++){
			Address address = addressList.get(i);
			AddressDTO addressDTO = addressListDTO.get(i);
			
			Assert.assertEquals(address.getStreet(), addressDTO.getStreet());
			Assert.assertEquals(address.getZipCode(), addressDTO.getZipCode());
			Assert.assertEquals(address.getCity().getDescription(), addressDTO.getCity().getDescription());
			Assert.assertEquals(address.getCity().getId(), addressDTO.getCity().getId());
			Assert.assertEquals(address.getCity().getProvince().getDescription(), addressDTO.getCity().getProvince().getDescription());
			Assert.assertEquals(address.getCity().getProvince().getId(), addressDTO.getCity().getProvince().getId());
			Assert.assertEquals(address.getCity().getProvince().getCountry().getDescription(), addressDTO.getCity().getProvince().getCountry().getDescription());
			Assert.assertEquals(address.getCity().getProvince().getCountry().getId(), addressDTO.getCity().getProvince().getCountry().getId());
		}
	}
	
	private void assertContactList(List<Contact> contactList, List<ContactDTO> contactListDTO) {
		 Assert.assertTrue(contactList.size() == contactListDTO.size());
		
		 for(int i = 0; i < contactList.size() ; i++){
			 
			 Contact contact = contactList.get(i);
			 ContactDTO contactDTO = contactListDTO.get(i);
			 
			 Assert.assertEquals(contact.getDescription(), contactDTO.getDescription());
			 Assert.assertEquals(contact.getId(), contactDTO.getId());
			 Assert.assertEquals(contact.getType(), contactDTO.getType());
		 }
	}

	private School createSchool() {
		Person person = new Person();
		person.setAge(Integer.valueOf(40));
		person.setDateOfBirth(new Date());
		person.setDisabledPerson(Boolean.FALSE);
		person.setGender('M');
		person.setHeight(Double.valueOf(1.8));
		person.setId(1L);
		person.setName("Juan Wilson");
		person.setSkinColor(SkinColor.BLACK);
		
		
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

		return school;
	}
}
