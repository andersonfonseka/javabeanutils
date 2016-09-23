package com.github.andersonfonseka.domain;

public class City extends GenericDescriptionBean {
	
	private Province province;

	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}
	
}
