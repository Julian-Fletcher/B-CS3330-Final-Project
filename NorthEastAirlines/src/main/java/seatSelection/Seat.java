package seatSelection;

import edu.mu.NorthEastAirlines.Flight;

public class Seat {
	private int seatNumber;
	private SeatType seatType;
	private boolean available;
	private Flight flight;
	
	public Seat(int number, SeatType type) {
		this.seatNumber = number;
		this.seatType = type;
		this.available = true;
	}
	public int getSeatNumber() {
		return seatNumber;
	}
	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}
	public SeatType getSeatType() {
		return seatType;
	}
	public void setSeatType(SeatType seatType) {
		this.seatType = seatType;
	}
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}	
	public Flight getFlight() {
		return this.flight;
	}
	public void setFlight(Flight flight) {
		this.flight = flight;
	}	
}
