/**
 * 
 */
package com.user.user.models;

/**
 * @author memory
 *
 */
public class MY_ADDRESS {
	private Integer USER_id;
	public Integer getUSER_id() {
		return USER_id;
	}
	public void setUSER_id(Integer uSER_id) {
		USER_id = uSER_id;
	}
	private Integer my_address_id;
	private String province;
	private String district;
	private String sub_district;
	private String road;
	private String number;
	private String postal_code;
	/**
	 * 
	 */
	public MY_ADDRESS() {
		// TODO Auto-generated constructor stub
	}
	public Integer getMy_address_id() {
		return my_address_id;
	}
	public void setMy_address_id(Integer my_address_id) {
		this.my_address_id = my_address_id;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getSub_district() {
		return sub_district;
	}
	public void setSub_district(String sub_district) {
		this.sub_district = sub_district;
	}
	public String getRoad() {
		return road;
	}
	public void setRoad(String road) {
		this.road = road;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getPostal_code() {
		return postal_code;
	}
	public void setPostal_code(String postal_code) {
		this.postal_code = postal_code;
	}

}
