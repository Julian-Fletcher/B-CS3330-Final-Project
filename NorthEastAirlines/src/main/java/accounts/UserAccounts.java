package accounts;

import java.util.List;

import edu.mu.NorthEastAirlines.Flight;
import flights.UserFlightData;

public class UserAccounts 
{
	private String username;
	private String password;
	private int id;
	private AccountStatus membershipLevel;
	private String firstName;
	private String lastName;
	private List<UserFlightData> bookedFlights;
	private boolean isLoggedIn;
	private int points;
	
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
	public List<UserFlightData> getBookedFlights() {
		return bookedFlights;
	}
	public void setBookedFlights(List<UserFlightData> bookedFlights) {
		this.bookedFlights = bookedFlights;
	}
	
	public void setUserPoints(int points)
	{
		this.points = points;
	}
	
	public int getUserPoints() {
		return this.points;
	}
	
	public boolean updatePoints(int addition) {
		if(addition < 0) {
			return false;
		}
		this.points += addition;
		return true;
	}
	
	@Override
	public String toString() {
		return "UserAccounts [username=" + username + ", password=" + password + ", id=" + id + ", membershipLevel="
				+ membershipLevel + ", firstName=" + firstName + ", lastName=" + lastName + ", bookedFlights="
				+ bookedFlights + ", isLoggedIn=" + isLoggedIn + ", points=" + points + "]";
	}

	/**
	 * Gets the login status of an account.
	 * @return	The login status of the account (true or false)
	 */
	public boolean getLoginStatus() {
		if(this.isLoggedIn == true) {
			return true;
		}
		return false;
	}
	
	/**
	 * Update the login status of an account.
	 * @param value The new login status
	 * @return True
	 */
	public boolean setLoginStatus(boolean value) {
		this.isLoggedIn = value;
		return true;	// Returns true always cause it should never fail!!
	}
}
