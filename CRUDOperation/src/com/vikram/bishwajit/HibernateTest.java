package com.vikram.bishwajit;

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
		SessionFactory sessionFactory = null;

		// Saving Data into the Database.
		sessionFactory = savingIntoDataBase();

		// Fetching Data from Database
		fetchingFromDatabase(sessionFactory);

		// Deleting Data from Database
		deletingFromDatabase(sessionFactory);

		// Fetching Data from Database
		updatingFromDatabase(sessionFactory);
	}

	/**
	 * Saving into the Database.
	 */
	private static SessionFactory savingIntoDataBase() {

		// Creating the Session factory from Configuration File.
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

		// Creating the Session out of sessionFactory.
		Session session = sessionFactory.openSession();

		// Begin the Transaction.
		session.beginTransaction();

		// Saving the Transaction of a UserDetails.
		for (int i = 1; i <= 10; i++) {
			UserDetails userDetails = new UserDetails();
			userDetails.setUserName("Bishwajit " + i);
			session.save(userDetails);
		}

		// Commit.
		session.getTransaction().commit();

		// Close Session.
		session.close();

		return sessionFactory;

	}

	/**
	 * Fetching Data from the DataBase.
	 */
	private static void fetchingFromDatabase(SessionFactory sessionFactory) {
		// Creating the Session out of sessionFactory.
		Session session = sessionFactory.openSession();

		// Begin the Transaction.
		session.beginTransaction();

		UserDetails userDetails = null;

		userDetails = (UserDetails) session.get(UserDetails.class, 6);
		System.out.println("No Data Found.");

		session.getTransaction().commit();
		session.close();

		System.out.print("\nFETCHING FROM THE DATABASE::");
		// EAGER FETCHING OF DATA
		System.out.println(userDetails.getUserId() + " ---> " + userDetails.getUserName());
	}

	/**
	 * Deleting Data from the DataBase.
	 */
	private static void deletingFromDatabase(SessionFactory sessionFactory) {
		// Creating the Session out of sessionFactory.
		Session session = sessionFactory.openSession();

		// Begin the Transaction.
		session.beginTransaction();

		UserDetails userDetails = (UserDetails) session.get(UserDetails.class, 6);

		// Deleting from DataBase
		System.out.print("\nDELETING FROM THE DATABASE::");
		session.delete(userDetails);

		session.getTransaction().commit();
		session.close();
	}

	/**
	 * Updating Data from the DataBase.
	 */
	private static void updatingFromDatabase(SessionFactory sessionFactory) {
		// Creating the Session out of sessionFactory.
		Session session = sessionFactory.openSession();

		// Begin the Transaction.
		session.beginTransaction();

		UserDetails userDetails = (UserDetails) session.get(UserDetails.class, 1);

		// Updating from DataBase
		userDetails.setUserName("Vikram");
		System.out.print("\nUpdating FROM THE DATABASE::");
		session.update(userDetails);

		session.getTransaction().commit();
		session.close();
	}
}
