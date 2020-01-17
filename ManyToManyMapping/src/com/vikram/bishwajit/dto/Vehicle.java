/**
 * 
 */
package com.vikram.bishwajit.dto;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * @author Bishwajit.
 *
 */
@Entity
@Table(name = "VEHICLE")
public class Vehicle {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "VEHICLE_ID")
	private int vehicleId;

	@Column(name = "VEHICLE_NAME")
	private String vehicleName;

	@ManyToMany
	private Collection<UserDetails> userDetails = new ArrayList<UserDetails>();

	/**
	 * @return the vehicleId
	 */
	public int getVehicleId() {
		return vehicleId;
	}

	/**
	 * @param vehicleId the vehicleId to set
	 */
	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}

	/**
	 * @return the vehicleName
	 */
	public String getVehicleName() {
		return vehicleName;
	}

	/**
	 * @param vehicleName the vehicleName to set
	 */
	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}

	/**
	 * @return the userDetails
	 */
	public Collection<UserDetails> getUserDetails() {
		return userDetails;
	}

	/**
	 * @param userDetails the userDetails to set
	 */
	public void setUserDetails(Collection<UserDetails> userDetails) {
		this.userDetails = userDetails;
	}

}
