package seatSelection;

import edu.mu.NorthEastAirlines.Flight;

/**
 * Class for seats which are utilized in planes.
 */
public class Seat {
	private int seatNumber;
	private SeatType seatType;
	private boolean available;
	
	/**
	 * Seat constructor.
	 * @param number	Integer to be set as the seat number
	 * @param type		SeatType enum to set as the seat type
	 * @see SeatType
	 */
	public Seat(int number, SeatType type) {
		this.seatNumber = number;
		this.seatType = type;
		this.available = true;
	}
	/**
	 * Returns seat number.
	 * @return	Seat number
	 */
	public int getSeatNumber() {
		return seatNumber;
	}
	
	/**
	 * Returns seat type.
	 * @return	Seat type
	 */
	public SeatType getSeatType() {
		return seatType;
	}
	
	/**
	 * Returns whether a seat is available or not.
	 * @return	True or false
	 */
	public boolean isAvailable() {
		return available;
	}
	
	/**
	 * Update availability of a seat.
	 * @param available	True or false boolean
	 */
	public void setAvailable(boolean available) {
		this.available = available;
	}	

	/**
	 * ToString for seat information.
	 */
	@Override
	public String toString() {
		return "Seat [seatNumber=" + seatNumber + ", seatType=" + seatType + ", available=" + available + "]";
	}
}
