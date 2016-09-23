package com.github.andersonfonseka.dto;

public class ProvinceDTO extends GenericDescriptionBeanDTO {
	
	private CountryDTO country;

	public CountryDTO getCountry() {
		return country;
	}

	public void setCountry(CountryDTO country) {
		this.country = country;
	}
	
}
