package com.vikram.bishwajit;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.vikram.bishwajit.dto.UserDetails;
import com.vikram.bishwajit.dto.Vehicle;

/**
 * @author Bishwajit.
 *
 */
public class HibernateTest {

	public static void main(String[] args) {

		SessionFactory sessionFactory = null;

		// Saving Data into the Database.
		sessionFactory = savingIntoDataBase();

		// Fetching Data from Database
		fetchingFromDatabase(sessionFactory);
	}

	/**
	 * Saving into the Database.
	 */
	private static SessionFactory savingIntoDataBase() {

		UserDetails userDetails1 = new UserDetails();
		UserDetails userDetails2 = new UserDetails();

		Vehicle vehicle1 = new Vehicle();
		Vehicle vehicle2 = new Vehicle();

		// Setting Values into First Vehicle Objects.
		vehicle1.setVehicleName("Dio");

		// Setting Values into Second Vehicle Objects.
		vehicle2.setVehicleName("CBZ");

		// Setting Values into First User Details Objects.
		userDetails1.setUserName("Bishwajit");
		// One to Many Mapping
		userDetails1.getListOfVehicle().add(vehicle1);
		userDetails1.getListOfVehicle().add(vehicle2);

		// Setting Values into First User Details Objects.
		userDetails2.setUserName("Bishwajit");
		// One to Many Mapping
		userDetails2.getListOfVehicle().add(vehicle2);
		userDetails2.getListOfVehicle().add(vehicle1);

		// Adding the Bi-Directional.
		vehicle1.setUserDetails(userDetails1);
		vehicle2.setUserDetails(userDetails1);

		// Creating the Session factory from Configuration File.
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

		// Creating the Session out of sessionFactory.
		Session session = sessionFactory.openSession();

		// Begin the Transaction.
		session.beginTransaction();

		// Saving the Transaction of a UserDetails.
		session.save(userDetails1);

		// Saving the Transaction of a Vehicle.
		session.save(vehicle1);
		session.save(vehicle2);

		// Commit.
		session.getTransaction().commit();

		// Close Session.
		session.close();

		return sessionFactory;
	}

	/**
	 * Fetching from the Database.
	 */
	private static void fetchingFromDatabase(SessionFactory sessionFactory) {

		// Creating the Session out of sessionFactory.
		Session session = sessionFactory.openSession();

		// Begin the Transaction.
		session.beginTransaction();

		getUserDetails(session);
		getVehicleDetails(session);
	}

	private static void getUserDetails(Session session) {
		// Getting the UserDetails::
		// Passing the user details class as object and passing the Primary Key
		// TypeCasting into the UserDetails.
		UserDetails userDetails = null;
		int i = 1;
		do {
			userDetails = (UserDetails) session.get(UserDetails.class, i);
			if (null != userDetails) {
				System.out.println("ID          :: " + userDetails.getUserId());
				System.out.println("UserName    :: " + userDetails.getUserName());
//				System.out.println("Vehicle_ID  :: " + userDetails.getVehicle().getVehicleId());
//				System.out.println("Vehicle_NAME:: " + userDetails.getVehicle().getVehicleName());
				i++;
			} else {
				break;
			}
		} while (true);
	}

	private static void getVehicleDetails(Session session) {
		// Getting the VehicleDetails::
		// Passing the Vehicle Details class as object and passing the Primary Key
		// TypeCasting into the Vehicle.
		Vehicle vehicle = null;
		int i = 3;
		do {
			vehicle = (Vehicle) session.get(Vehicle.class, i);
			if (null != vehicle) {
				System.out.println("ID         :: " + vehicle.getVehicleId());
				System.out.println("UserName   :: " + vehicle.getVehicleName());
				i++;
			} else {
				break;
			}
		} while (true);
	}
}
