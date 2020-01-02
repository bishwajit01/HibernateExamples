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
		UserDetails userDetails1 = new UserDetails();
		UserDetails userDetails2 = new UserDetails();

		// Setting Values into First User Details Objects.
		// userDetails.setUserId(1);
		userDetails1.setUserName("Bishwajit");
		userDetails1.setDate(new Date());
		userDetails1.setDescription("DESC 1");
		userDetails1.setCountry("INDIA");

		// Setting Values into Second User Details Objects.
		// userDetails.setUserId(1);
		userDetails2.setUserName("Vikram");
		userDetails2.setDate(new Date());
		userDetails2.setDescription("DESC 2");
		userDetails2.setCountry("INDIA");
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
		session.save(userDetails1);
		session.save(userDetails2);

		// Commit.
		session.getTransaction().commit();

		// Close Session.
		session.close();

		UserDetails userDetails = null;
		int i = 1;

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
		do {
			userDetails = (UserDetails) session.get(UserDetails.class, i);
			if (null != userDetails) {
				System.out.println("ID       :: " + userDetails.getUserId());
				System.out.println("UserName :: " + userDetails.getUserName());
				System.out.println("Address  :: " + userDetails.getDescription());
				System.out.println("Joining  :: " + userDetails.getDate());
				System.out.println("Country  :: " + userDetails.getCountry());
				i++;
			} else {
				break;
			}
		} while (true);
	}

}
