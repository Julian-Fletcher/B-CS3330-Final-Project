package accounts;

import java.util.List;

import edu.mu.NorthEastAirlines.Flight;
import seatSelection.Seat;

public class UserAccounts 
{
	private String username;
	private String password;
	private int id;
	private AccountStatus membershipLevel;
	private String firstName;
	private String lastName;
	private List<Seat> bookedFlights;
	private boolean isLoggedIn;
	
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
	public List<Seat> getBookedFlights() {
		return bookedFlights;
	}
	public void setBookedFlights(List<Seat> bookedFlights) {
		this.bookedFlights = bookedFlights;
	}
	
	
	// Access login status
	public boolean getLoginStatus() {
		if(this.isLoggedIn == true) {
			return true;
		}
		return false;
	}
	
	// Set the login status
	public boolean setLoginStatus(boolean value) {
		this.isLoggedIn = value;
		return true;	// Returns true always cause it should never fail!!
	}
}
