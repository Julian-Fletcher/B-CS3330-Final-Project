package flights;



public class UserFlightData {
	private int flightNumber;
	private int seatNumber;
	
	public UserFlightData(int flight, int seat) {
		this.flightNumber = flight;
		this.seatNumber = seat;
	}
	
	
	public int getFlight() {
		return flightNumber;
	}
	public int getSeat() {
		return seatNumber;
	}
}
