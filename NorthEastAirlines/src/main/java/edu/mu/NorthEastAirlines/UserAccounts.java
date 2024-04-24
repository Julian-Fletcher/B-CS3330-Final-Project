package edu.mu.NorthEastAirlines;

import java.util.List;

public class UserAccounts 
{
	private String username;
	private String password;
	private int id;
	private AccountStatus membershipLevel;
	private String firstName;
	private String lastName;
	private List<Flight> bookedFlights;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public AccountStatus getMembershipLevel() {
		return membershipLevel;
	}
	public void setMembershipLevel(AccountStatus membershipLevel) {
		this.membershipLevel = membershipLevel;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public List<Flight> getBookedFlights() {
		return bookedFlights;
	}
	public void setBookedFlights(List<Flight> bookedFlights) {
		this.bookedFlights = bookedFlights;
	}
}
