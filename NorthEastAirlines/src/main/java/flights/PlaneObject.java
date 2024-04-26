package flights;

import java.util.ArrayList;

import seatSelection.Seat;
import seatSelection.SeatType;

public class PlaneObject {

	private ArrayList<Seat> seats;
	private PlaneType model;
	
	
	public PlaneObject() {
		this.seats = new ArrayList<Seat>();

	}
	
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
	
	
	
	public ArrayList<Seat> getAvailableSeats(SeatType type) {
		ArrayList<Seat> narrowseats = new ArrayList<Seat>();
		System.out.println("CALLED2");
		for(Seat seat : this.seats) {
			if(seat.getSeatType() == type && seat.isAvailable()) {
				narrowseats.add(seat);
			}
		}
		return narrowseats;
	}
	public ArrayList<Seat> getAllSeats(SeatType type) {
		ArrayList<Seat> narrowseats = new ArrayList<Seat>();
		for(Seat seat : this.seats) {
			if(seat.getSeatType() == type) {
				narrowseats.add(seat);
			}
		}
		return narrowseats;
	}
}
