package flights;

import seatSelection.SeatType;

public class UserFlightData {
	private int flightNumber;
	private int seatNumber;
	private SeatType seatType;
	
	public UserFlightData(int flight, int seat, SeatType seatType) {
		this.flightNumber = flight;
		this.seatNumber = seat;
		this.seatType = seatType;
	}
	
	
	public int getFlight() {
		return flightNumber;
	}
	public int getSeat() {
		return seatNumber;
	}

	public SeatType getSeatType() {
		return seatType;
	}	
}
