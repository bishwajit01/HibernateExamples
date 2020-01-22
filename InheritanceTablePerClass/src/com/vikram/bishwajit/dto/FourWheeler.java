package com.vikram.bishwajit.dto;

import javax.persistence.Entity;
/**
 * 
 * @author Bishwajit
 *
 */
@Entity
public class FourWheeler extends Vehicle {
	private String streeingHandle;

	/**
	 * @return the streeingHandle
	 */
	public String getStreeingHandle() {
		return streeingHandle;
	}

	/**
	 * @param streeingHandle the streeingHandle to set
	 */
	public void setStreeingHandle(String streeingHandle) {
		this.streeingHandle = streeingHandle;
	}

}
