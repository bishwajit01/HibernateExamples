package com.vikram.bishwajit.dto;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * @author Bishwajit.
 *
 */
@Entity
@Table(name = "USER_DETAILS")
public class UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "USER_ID")
	private int userId;

	@Column(name = "USER_NAME")
	private String userName;

	@Column(name = "JOINING_DATE")
	@Temporal(TemporalType.DATE) // JUST THE DATE
	private Date date;

	@Column(name = "DESCRIPTION")
	@Lob
	private String description;

	@Column(name = "COUNTRY")
	@Transient // By making it transient, it will not add in the database.
	private String country;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "street", column = @Column(name = "HOME_STREET")),
			@AttributeOverride(name = "houseNumber", column = @Column(name = "HOME_HOUSE_NUMBER")),
			@AttributeOverride(name = "city", column = @Column(name = "HOME_CITY")),
			@AttributeOverride(name = "state", column = @Column(name = "HOME_STATE")),
			@AttributeOverride(name = "pincode", column = @Column(name = "HOME_PINCODE")) })
	private Address homeAddress;

	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "street", column = @Column(name = "OFFICE_STREET")),
		@AttributeOverride(name = "houseNumber", column = @Column(name = "OFFICE_HOUSE_NUMBER")),
		@AttributeOverride(name = "city", column = @Column(name = "OFFICE_CITY")),
		@AttributeOverride(name = "state", column = @Column(name = "OFFICE_STATE")),
		@AttributeOverride(name = "pincode", column = @Column(name = "OFFICE_PINCODE")) })
	private Address officeAddress;

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the homeAddress
	 */
	public Address getHomeAddress() {
		return homeAddress;
	}

	/**
	 * @param homeAddress the homeAddress to set
	 */
	public void setHomeAddress(Address homeAddress) {
		this.homeAddress = homeAddress;
	}

	/**
	 * @return the officeAddress
	 */
	public Address getOfficeAddress() {
		return officeAddress;
	}

	/**
	 * @param officeAddress the officeAddress to set
	 */
	public void setOfficeAddress(Address officeAddress) {
		this.officeAddress = officeAddress;
	}

}
