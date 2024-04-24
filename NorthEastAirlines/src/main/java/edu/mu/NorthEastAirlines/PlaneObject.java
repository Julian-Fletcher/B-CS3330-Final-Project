package edu.mu.NorthEastAirlines;

import java.util.ArrayList;

public class PlaneObject {

	private ArrayList<Seat> seats;
	private PlaneType model;
	
	public ArrayList<Seat> getEconomySeats() {
		ArrayList<Seat> narrowseats = new ArrayList<Seat>();
		for(Seat seat : this.seats) {
			if(seat.getSeatType() == SeatType.ECONOMY) {
				narrowseats.add(seat);
			}
		}
		return narrowseats;
	}
	public ArrayList<Seat> getComfortSeats() {
		ArrayList<Seat> narrowseats = new ArrayList<Seat>();
		for(Seat seat : this.seats) {
			if(seat.getSeatType() == SeatType.COMFORT) {
				narrowseats.add(seat);
			}
		}
		return narrowseats;
	}
	public ArrayList<Seat> getFirstClassSeats() {
		ArrayList<Seat> narrowseats = new ArrayList<Seat>();
		for(Seat seat : this.seats) {
			if(seat.getSeatType() == SeatType.FIRST_CLASS) {
				narrowseats.add(seat);
			}
		}
		return narrowseats;
	}
	public PlaneType getModel() {
		return model;
	}
	public void addSeat(Seat seat) {
		this.seats.add(seat);
	}
	public void setModel(PlaneType model) {
		this.model = model;
	}
}
