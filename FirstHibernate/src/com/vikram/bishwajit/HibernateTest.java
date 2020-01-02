package com.vikram.bishwajit;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.vikram.bishwajit.dto.UserDetails;

/**
 * @author Bishwajit.
 *
 */
public class HibernateTest {

	public static void main(String[] args) {
		UserDetails userDetails = new UserDetails();
		userDetails.setUserId(1);
		userDetails.setUserName("Bishwajit");
		userDetails.setDate(new Date());
		userDetails.setAddress("Bangalore");
		userDetails.setCountry("INDIA");

		/**
		 * Saving into the Database.
		 */
		// Creating the Session factory from Configuration File.
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

		// Creating the Session out of sessionFactory.
		Session session = sessionFactory.openSession();

		// Begin the Transaction.
		session.beginTransaction();

		// Saving the Transaction.
		session.save(userDetails);

		// Commit.
		session.getTransaction().commit();

		// Close Session.
		session.close();

		userDetails = null;

		/**
		 * Fetching from the Database.
		 */
		// Creating the Session out of sessionFactory.
		session = sessionFactory.openSession();

		// Begin the Transaction.
		session.beginTransaction();

		// Getting the UserDetails::
		// Passing the user details class as object and passing the Primary Key
		// TypeCasting into the UserDetails.
		userDetails = (UserDetails) session.get(UserDetails.class, 1);
		System.out.println("ID       :: " + userDetails.getUserId());
		System.out.println("UserName :: " + userDetails.getUserName());
		System.out.println("Address  :: " + userDetails.getAddress());
		System.out.println("Joining  :: " + userDetails.getDate());
		System.out.println("Country  :: " + userDetails.getCountry());
	}

}
