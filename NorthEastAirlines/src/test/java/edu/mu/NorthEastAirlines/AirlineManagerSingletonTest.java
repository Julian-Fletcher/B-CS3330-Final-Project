package edu.mu.NorthEastAirlines;

import org.junit.jupiter.api.Test;

import accounts.AccountStatus;
import accounts.UserAccounts;

import static org.junit.jupiter.api.Assertions.*;


class AirlineManagerSingletonTest {
	
	@Test
	void testGetInstance() {
		// GEt instance 
		AirlineManagerSingleton instance1 = AirlineManagerSingleton.getInstance();
		
		// IInstance 2
		AirlineManagerSingleton instance2 = AirlineManagerSingleton.getInstance();
		
		assertEquals(instance2, instance1, "Instances should be the same");
	}
	
	@Test
	public void testCreateAccount() {
		// Testing here
		
		AirlineManagerSingleton testManager = AirlineManagerSingleton.getInstance();
		
		UserAccounts account1 = testManager.createAccount("test", "test1", "john", "dough");
		
		//assertNotNull("Account should be created:", account1);
		
		// Check if account was created properly
		assertEquals("john", account1.getFirstName());
		assertEquals("dough", account1.getLastName());
		assertEquals(AccountStatus.IRON, account1.getMembershipLevel());
		assertEquals("test", account1.getUsername());	
		
	}
	
	@Test
	public void testCreateAccountWithExistingUser() {
		// Get singleton
		AirlineManagerSingleton testManager = AirlineManagerSingleton.getInstance();
		
		// Create unique account
		UserAccounts uniqueAccount = testManager.createAccount("test2", "test1", "john", "dough");
		
		UserAccounts duplicateAccount = testManager.createAccount("test2", "test1", "john", "dough");
		
		assertNull( duplicateAccount, "Duplicate account should be NULL");
	}
	
	@Test
	public void testLogin() {
		// Get singleton
		AirlineManagerSingleton testManager = AirlineManagerSingleton.getInstance();
		
		UserAccounts testaccount1 = testManager.createAccount("test3", "test3" , "john", "dame");
		UserAccounts testaccount2 = testManager.createAccount("test4", "test4", "john", "lame");
		
		// Test if logged in is set to false 
		boolean acct1LoggedIn = testaccount1.getLoginStatus();
		boolean acct2LoggedIn = testaccount2.getLoginStatus();
		assertFalse(acct1LoggedIn, "Account should not be logged in");
		assertFalse(acct2LoggedIn, "Account should not be logged in");
		
		
		// Log into account 1, test if login successful
		boolean acct1LogIn = testManager.login("test3", "test3");
		assertTrue(acct1LogIn, "Account should be logged in");
		
		acct1LoggedIn = testaccount1.getLoginStatus();
		assertTrue(acct1LoggedIn,"Login status should be true");
		
		// Log in again -- test already logged in
		boolean reLogIn = testManager.login("test3", "test3");
		assertFalse(reLogIn, "Account should already be logged in");
		
		boolean login2 = testManager.login("test4", "abcd");
		assertFalse(login2, "Account should not be logged in");
		
		// Attempt to log into a non-existent account
		boolean noAccountLogin = testManager.login("nouser", "nouser");
		assertFalse(noAccountLogin,"Account should not exist");
	}
	
	@Test
	public void testLogout() {
		// Create singleton
		AirlineManagerSingleton testManager = AirlineManagerSingleton.getInstance();
		
		// Create accounts
		UserAccounts account1 = testManager.createAccount("test5", "test5" , "john", "shame");
		UserAccounts account2 = testManager.createAccount("test6", "test6", "john", "fame");
		
		// Login and logout of account1
		boolean login3 = testManager.login("test5", "test5");
		// Test logout with incorrect password
		boolean wrongPassLogout = testManager.logout("test5", "wrongPassword");
		
		boolean logout = testManager.logout("test5", "test5");
		assertTrue(logout,"Account should be logged out");
		
		// Confirm account 1 is logged out
		
		
		// Log out of account 2
		boolean logout2 = testManager.logout("test6", "test6");
		assertFalse(logout2,"Account should not be logged out");
		
		// Test with account that does not exist
		boolean falseLogOut = testManager.logout("nouser", "nouser");
		assertFalse(falseLogOut, "Account should not report existing");		
		
	}
	
	@Test
	public void testDeleteAccount() {
		// Create singleton
		AirlineManagerSingleton testManager = AirlineManagerSingleton.getInstance();
		
		// Create accounts
		UserAccounts account1 = testManager.createAccount("deleteTest", "test5" , "john", "shame");
		
		// Delete account correctly
		boolean deleteAccount= testManager.deleteAccount("deleteTest", "test5");
		assertTrue(deleteAccount, "Accounts shouldn't be deleted");
		
		// Delete account, wrong password
		boolean deleteWrong = testManager.deleteAccount("deleteTest", "test6");
		assertFalse(deleteWrong, "Account should not be deleted");
		
		// Delete account that doesn't exist
		boolean deleteDoesntExist = testManager.deleteAccount("noAccountDelete", "abcd");
		assertFalse(deleteDoesntExist, "Accounts should not report deleted");
		
		
		// Delete already deleted account
		boolean deleteDeleted = testManager.deleteAccount("deleteTest", "test5");
		assertFalse(deleteDeleted,"Accounts shouldn't be deleted");
		
	}
//	
//	public void testBookSeat() {
//		
//	}
}
