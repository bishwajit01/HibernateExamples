package com.vikram.bishwajit.dto;

import javax.persistence.Entity;
/**
 * 
 * @author Bishwajit
 *
 */
@Entity
public class TwoWheeler extends Vehicle {

	private String streeingWheel;

	/**
	 * @return the streeingWheel
	 */
	public String getStreeingWheel() {
		return streeingWheel;
	}

	/**
	 * @param streeingWheel the streeingWheel to set
	 */
	public void setStreeingWheel(String streeingWheel) {
		this.streeingWheel = streeingWheel;
	}
}
