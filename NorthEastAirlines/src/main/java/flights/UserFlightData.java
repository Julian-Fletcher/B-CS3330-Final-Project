package flights;

import seatSelection.SeatType;

/**
 * Class for storing a user's selected flight and seat configuration.
 */
public class UserFlightData {
	private int flightNumber;
	private int seatNumber;
	private SeatType seatType;
	
	public UserFlightData(int flight, int seat, SeatType seatType) {
		this.flightNumber = flight;
		this.seatNumber = seat;
		this.seatType = seatType;
	}
	
	/**
	 * Gets flight number stored in this instance of UserFlightData.
	 * 
	 * @return			An integer corresponding to a flight number
	 */
	public int getFlight() {
		return flightNumber;
	}
	
	/**
	 * Gets seat number stored in this instance of UserFlightData.
	 * 
	 * @return			An integer corresponding to a seat number
	 */
	public int getSeat() {
		return seatNumber;
	}

	/**
	 * Gets SeatType stored in this instance of UserFlightData.
	 * 
	 * @return			A SeatType corresponding to the type of seat stored
	 */
	public SeatType getSeatType() {
		return seatType;
	}	
}
