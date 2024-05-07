package accounts;

import java.util.ArrayList;
import java.util.List;

import edu.mu.NorthEastAirlines.Flight;
import flights.UserFlightData;

/**
 * Account Class for users.
 */
public class UserAccounts 
{
	private String username;
	private String password;
	private int id;
	private AccountStatus membershipLevel;
	private String firstName;
	private String lastName;
	private List<UserFlightData> bookedFlights = new ArrayList<UserFlightData>();
	private boolean isLoggedIn;
	private int points;
	
	/**
	 * Gets the username
	 * @return	Account username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * Sets account username
	 * @param username	Desired username
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * Returns account password.
	 * @return	The account password
	 */
	
	public String getPassword() {
		return password;
	}
	
	/**
	 * Sets account password to provided string.
	 * @param password	The desired password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Returns account id
	 * @return	Account ID	
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Sets the account id of the account
	 * 
	 * @param id The account ID
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Returns the account membership level
	 * @return	The AccountStatus Enum level
	 */
	public AccountStatus getMembershipLevel() {
		return membershipLevel;
	}
	
	/**
	 * Sets the account membership level
	 * @param membershipLevel	The new membership level
	 */
	public void setMembershipLevel(AccountStatus membershipLevel) {
		this.membershipLevel = membershipLevel;
	}
	
	/**
	 * Gets the firstname of the account
	 * @return	The String first name
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * Sets the account first name
	 * @param firstName		The desired first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * Gets the last name of the account
	 * @return	The String last name
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * Sets the last name of the account
	 * @param lastName	The desired last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * Lists the users boooked flights
	 * @return	An ArrayList of booked flights
	 */
	public List<UserFlightData> getBookedFlights() {
		return bookedFlights;
	}
	
	/**
	 * Sets the users booked flights ArrayList	
	 * @param bookedFlights	The new ArrayList
	 */
	public void setBookedFlights(List<UserFlightData> bookedFlights) {
		this.bookedFlights = bookedFlights;
	}
	
	/**
	 * Sets the users points
	 * @param points	New point count
	 */
	public void setUserPoints(int points)
	{
		this.points = points;
	}
	
	/**
	 * Gets the users point count
	 * @return	The user point count
	 */
	public int getUserPoints() {
		return this.points;
	}
	
	/**
	 * Updates the user point count
	 * @param addition	The amount to modify the point count by	
	 * @return	Whether the update was successful or not
	 */
	public boolean updatePoints(int addition) {
		if(addition < 0) {
			return false;
		}
		this.points += addition;
		return true;
	}
	
//	/**
//	 * To string. 
//	 */
//	@Override
//	public String toString() {
//		return "UserAccounts [username=" + username + ", password=" + password + ", id=" + id + ", membershipLevel="
//				+ membershipLevel + ", firstName=" + firstName + ", lastName=" + lastName + ", bookedFlights="
//				+ bookedFlights + ", isLoggedIn=" + isLoggedIn + ", points=" + points + "]";
//	}

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
	
	public boolean addBookedFlight(UserFlightData flight) {
		return this.bookedFlights.add(flight);
	}
}
