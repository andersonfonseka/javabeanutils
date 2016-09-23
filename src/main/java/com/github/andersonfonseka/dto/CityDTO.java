package com.github.andersonfonseka.dto;

public class CityDTO extends GenericDescriptionBeanDTO {
	
	private ProvinceDTO province;

	public ProvinceDTO getProvince() {
		return province;
	}

	public void setProvince(ProvinceDTO province) {
		this.province = province;
	}
	
}
