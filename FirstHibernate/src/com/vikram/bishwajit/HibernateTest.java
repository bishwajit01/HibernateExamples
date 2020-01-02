package com.vikram.bishwajit;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.vikram.bishwajit.dto.Address;
import com.vikram.bishwajit.dto.UserDetails;

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
	 * Fetching from the Database.
	 */
	private static void fetchingFromDatabase(SessionFactory sessionFactory) {
		UserDetails userDetails = null;
		int i = 1;

		// Creating the Session out of sessionFactory.
		Session session = sessionFactory.openSession();

		// Begin the Transaction.
		session.beginTransaction();

		// Getting the UserDetails::
		// Passing the user details class as object and passing the Primary Key
		// TypeCasting into the UserDetails.
		do {
			userDetails = (UserDetails) session.get(UserDetails.class, i);
			if (null != userDetails) {
				System.out.println("ID       :: " + userDetails.getUserId());
				System.out.println("UserName :: " + userDetails.getUserName());
				System.out.println("Address  :: " + userDetails.getDescription());
				System.out.println("Joining  :: " + userDetails.getDate());
				System.out.println("Country  :: " + userDetails.getCountry());
				System.out.println("House No :: " + userDetails.getAddress().getHouseNumber());
				System.out.println("Street   :: " + userDetails.getAddress().getStreet());
				System.out.println("City     :: " + userDetails.getAddress().getCity());
				System.out.println("Pincode  :: " + userDetails.getAddress().getPincode());
				System.out.println("State    :: " + userDetails.getAddress().getState());
				i++;
			} else {
				break;
			}
		} while (true);
	}

	/**
	 * Saving into the Database.
	 */
	private static SessionFactory savingIntoDataBase() {

		UserDetails userDetails1 = new UserDetails();
		Address address1 = new Address();

		UserDetails userDetails2 = new UserDetails();
		Address address2 = new Address();

		// Setting Values into First Address Objects.
		address1.setHouseNumber("A316");
		address1.setCity("Bengaluru");
		address1.setState("KARNATAKA");
		address1.setStreet("2nd Cross");
		address1.setPincode("560100");

		// Setting Values into First User Details Objects.
		// userDetails.setUserId(1);
		userDetails1.setUserName("Bishwajit");
		userDetails1.setDate(new Date());
		userDetails1.setDescription("DESC 1");
		userDetails1.setCountry("INDIA");
		userDetails1.setAddress(address1);

		// Setting Values into Second Address Objects.
		address2.setHouseNumber("A206");
		address2.setCity("Bengaluru");
		address2.setState("KARNATAKA");
		address2.setStreet("2nd Cross");
		address2.setPincode("560001");

		// Setting Values into Second User Details Objects.
		// userDetails.setUserId(1);
		userDetails2.setUserName("Vikram");
		userDetails2.setDate(new Date());
		userDetails2.setDescription("DESC 2");
		userDetails2.setCountry("INDIA");
		userDetails2.setAddress(address2);

		// Creating the Session factory from Configuration File.
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

		// Creating the Session out of sessionFactory.
		Session session = sessionFactory.openSession();

		// Begin the Transaction.
		session.beginTransaction();

		// Saving the Transaction.
		session.save(userDetails1);
		session.save(userDetails2);

		// Commit.
		session.getTransaction().commit();

		// Close Session.
		session.close();

		return sessionFactory;
	}

}
