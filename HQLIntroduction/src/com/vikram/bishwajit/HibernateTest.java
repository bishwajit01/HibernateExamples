package com.vikram.bishwajit;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.vikram.bishwajit.dto.UserDetails;

/**
 * @author Bishwajit.
 *
 */
public class HibernateTest {

	public static void main(String[] args) {
		SessionFactory sessionFactory = savingIntoDataBase();

		queryFormation(sessionFactory);
	}

	/**
	 * @param sessionFactory
	 */
	private static void queryFormation(SessionFactory sessionFactory) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Query query1 = session.createQuery("from UserDetails");
		List listUserDetails1 = query1.list();

		Query query2 = session.createQuery("from UserDetails where user_id = 5");
		List<UserDetails> listUserDetails2 = (List<UserDetails>) query2.list();

		Query query3 = session.createQuery("from UserDetails where user_id < 5");
		List<UserDetails> listUserDetails3 = (List<UserDetails>) query3.list();

		// Provides Pagination.
		Query query4 = session.createQuery("from UserDetails where user_id > 5");
		query4.setFirstResult(2);
		query4.setMaxResults(5);
		List<UserDetails> listUserDetails4 = (List<UserDetails>) query4.list();

		Query query5 = session.createQuery("select userName from UserDetails");
		List<String> listUserDetails5 = (List<String>) query5.list();

		Query query6 = session.createQuery("select new map{user_id, userName} from UserDetails");
		List<String> listUserDetails6 = (List<String>) query6.list();

		Query query7 = session.createQuery("select max(user_id) from UserDetails");
		List<String> listUserDetails7 = (List<String>) query7.list();

		/*
		 * This may lead to SQL INJECTION.
		 * Because it will return all the user, even though the first condition is true or false.
		 * As second condition will be always true.
		 * 
		 */
		String userId1 = "6 or 1 = 1";
		Query query8 = session.createQuery("from UserDetails where user_id < " + userId1);
		List<UserDetails> listUserDetails8 = (List<UserDetails>) query8.list();

		// Parameter Bindings.
		String userId2 = "6";
		String userName1 = "Vikram";
		Query query9 = session.createQuery("from UserDetails where user_id < ? and userName = ?");
		query9.setInteger(0, Integer.parseInt(userId2));
		query9.setString(1, userName1);
		List<UserDetails> listUserDetails9 = (List<UserDetails>) query9.list();

		String userId3 = "6";
		String userName2 = "Vikram";
		Query query10 = session.createQuery("from UserDetails where user_id < :user_id and userName = :userName");
		query9.setInteger("user_id", Integer.parseInt(userId2));
		query9.setString("userName", userName1);
		List<UserDetails> listUserDetails10 = (List<UserDetails>) query10.list();

		/**
		 * Named Query
		 */
		Query query11 = session.getNamedQuery("UserDetailsById");
		query11.setInteger(0, 2);
		List<UserDetails> listUserDetails11 = (List<UserDetails>) query11.list();
		
		/**
		 * Named NativeQuery
		 */
		Query query12 = session.getNamedQuery("UserDetailsByName");
		query11.setString(0, "Bishwajit 1");
		List<UserDetails> listUserDetails12 = (List<UserDetails>) query12.list();
		
		// Session Commit and Close
		session.getTransaction().commit();
		session.close();

		System.out.println("\nTotal Table Size ::" + listUserDetails1.size());
		System.out.println("\nID = 5 :: " + listUserDetails2.get(0).getUserName());
		System.out.print("\nUsenames whose id is less than 5 :: ");
		for (UserDetails user : listUserDetails3) {
			System.out.print(user.getUserName() + " ");
		}
		System.out.println();
		System.out.print("\nUsenames whose id is greater than 5 using Pagination :: ");
		for (UserDetails user : listUserDetails4) {
			System.out.print(user.getUserName() + " ");
		}
		System.out.print("\nOnly Username ::");
		listUserDetails5.forEach(v -> System.out.print(v + " "));

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
}