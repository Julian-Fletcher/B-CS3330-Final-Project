package edu.mu.NorthEastAirlines;

public class Account {
	
	private String firstName;
	private String accountId;
	private String password;
	private String lastName;
	private Flight[] bookedFlights;
	private AccountStatus membershipLevel;
	private int points;
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Flight[] getBookedFlights() {
		return bookedFlights;
	}
	public void setBookedFlights(Flight[] bookedFlights) {
		this.bookedFlights = bookedFlights;
	}
	public AccountStatus getMembershipLevel() {
		return membershipLevel;
	}
	public void setMembershipLevel(AccountStatus membershipLevel) {
		this.membershipLevel = membershipLevel;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	
}
