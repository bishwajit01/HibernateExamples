package com.vikram.bishwajit;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.vikram.bishwajit.dto.FourWheeler;
import com.vikram.bishwajit.dto.TwoWheeler;
import com.vikram.bishwajit.dto.Vehicle;

/**
 * @author Bishwajit.
 *
 */
public class HibernateTest {

	public static void main(String[] args) {

		savingIntoDataBase();
	}

	/**
	 * Saving into the Database.
	 */
	private static void savingIntoDataBase() {

		Vehicle vehicle1 = new Vehicle();
		vehicle1.setVehicleName("CAR");

		TwoWheeler twoWheeler = new TwoWheeler();
		twoWheeler.setVehicleName("Bike");
		twoWheeler.setStreeingWheel("Bike Steering Handle");

		FourWheeler fourWheeler = new FourWheeler();
		fourWheeler.setVehicleName("WagonR");
		fourWheeler.setStreeingHandle("Car Steering Wheel");

		// Creating the Session factory from Configuration File.
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

		// Creating the Session out of sessionFactory.
		Session session = sessionFactory.openSession();

		// Begin the Transaction.
		session.beginTransaction();

		// Saving the Transaction of a Vehicle.
		session.save(vehicle1);
		session.save(twoWheeler);
		session.save(fourWheeler);

		// Commit.
		session.getTransaction().commit();

		// Close Session.
		session.close();

	}

}
