package edu.mu.NorthEastAirlines;

import accounts.AccountStatus;
import accounts.UserAccounts;
import junit.framework.TestCase;

public class AirlineManagerSingletonTest extends TestCase {
	
	public void testGetInstance() {
		// GEt instance 
		AirlineManagerSingleton instance1 = AirlineManagerSingleton.getInstance();
		
		// IInstance 2
		AirlineManagerSingleton instance2 = AirlineManagerSingleton.getInstance();
		
		assertSame("Instances should be the same", instance1, instance2);
	}
	
	public void testCreateAccount() {
		// Testing here
		
		AirlineManagerSingleton testManager = AirlineManagerSingleton.getInstance();
		
		UserAccounts account1 = testManager.createAccount("test", "test1", "john", "dough");
		
		assertNotNull("Account should be created:", account1);
		
		// Check if account was created properly
		assertEquals("john", account1.getFirstName());
		assertEquals("dough", account1.getLastName());
		assertEquals(AccountStatus.IRON, account1.getMembershipLevel());
		assertEquals("test", account1.getUsername());	
		
	}
	
	public void testCreateAccountWithExistingUser() {
		// Get singleton
		AirlineManagerSingleton testManager = AirlineManagerSingleton.getInstance();
		
		// Create unique account
		UserAccounts uniqueAccount = testManager.createAccount("test", "test1", "john", "dough");
		
		UserAccounts duplicateAccount = testManager.createAccount("test", "test1", "john", "dough");
		
		assertNull("Duplicate account should be NULL", duplicateAccount);
	}
	
//	public void testLogin() {
//		
//	}
//	
//	public void testDeleteAccount() {
//		
//	}
//	
//	public void testBookSeat() {
//		
//	}
}
