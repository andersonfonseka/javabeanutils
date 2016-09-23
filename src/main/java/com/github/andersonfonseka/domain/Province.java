package com.github.andersonfonseka.domain;

public class Province extends GenericDescriptionBean {
	
	private Country country;

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}
	
}
