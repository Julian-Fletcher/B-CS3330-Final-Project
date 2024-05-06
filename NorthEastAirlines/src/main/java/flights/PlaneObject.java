package flights;

import java.util.ArrayList;

import seatSelection.Seat;
import seatSelection.SeatType;

/**
 * Class that represents planes and keeps track of PlaneType and its accompanying seats.
 */
public class PlaneObject {

	private ArrayList<Seat> seats;
	private PlaneType model;
	
	
	public PlaneObject() {
		this.seats = new ArrayList<Seat>();

	}
	
	/**
	 * Gets all economy seats that belong to PlaneObject.
	 * 
	 * @return			An ArrayList<Seat> of all economy seats in the PlaneObject
	 */
	public ArrayList<Seat> getEconomySeats() {
		ArrayList<Seat> narrowseats = new ArrayList<Seat>();
		for(Seat seat : this.seats) {
			if(seat.getSeatType() == SeatType.ECONOMY) {
				narrowseats.add(seat);
			}
		}
		return narrowseats;
	}
	
	/**
	 * Gets all comfort seats that belong to PlaneObject.
	 * 
	 * @return			An ArrayList<Seat> of all comfort seats in the PlaneObject
	 */
	public ArrayList<Seat> getComfortSeats() {
		ArrayList<Seat> narrowseats = new ArrayList<Seat>();
		for(Seat seat : this.seats) {
			if(seat.getSeatType() == SeatType.COMFORT) {
				narrowseats.add(seat);
			}
		}
		return narrowseats;
	}
	
	/**
	 * Gets all first class seats that belong to PlaneObject.
	 * 
	 * @return			An ArrayList<Seat> of all first class seats in the PlaneObject
	 */
	public ArrayList<Seat> getFirstClassSeats() {
		ArrayList<Seat> narrowseats = new ArrayList<Seat>();
		for(Seat seat : this.seats) {
			if(seat.getSeatType() == SeatType.FIRST_CLASS) {
				narrowseats.add(seat);
			}
		}
		return narrowseats;
	}
	
	/**
	 * Gets the PlaneType of the PlaneObject.
	 * 
	 * @see PlaneType
	 * @return			PlaneType of the instance
	 */
	public PlaneType getModel() {
		return model;
	}
	
	/**
	 * Adds seats to the PlaneObject.
	 * Used mostly in GeneratePlane()
	 * 
	 * @see PlanePsuedoFactory
	 * 
	 * @param seat		A seat object which is to be added to the PlaneObject
	 */
	public void addSeat(Seat seat) {
		this.seats.add(seat);
	}
	
	/**
	 * Sets model of PlaneObject.
	 * Used mostly in GeneratePlane()
	 * 
	 * @see PlanePsuedoFactory
	 * 
	 * @param model		A PlaneType which is to be assigned to the PlaneObject
	 */
	public void setModel(PlaneType model) {
		this.model = model;
	}
	
	
	/**
	 * Gets all available seats matching the provided SeatType parameter.
	 * 
	 * @param type		A SeatType enumerator value of which type to retrieve seats from.
	 * @return			An ArrayList<Seat> of all available seats in the PlaneObject of specified type
	 */
	public ArrayList<Seat> getAvailableSeats(SeatType type) {
		ArrayList<Seat> narrowseats = new ArrayList<Seat>();

		for(Seat seat : this.seats) {
			if(seat.getSeatType().equals(type) && seat.isAvailable()) {
				narrowseats.add(seat);
			}
		}
		return narrowseats;
	}
	
	/**
	 * Gets all seats available or not by matching the provided SeatType parameter.
	 * 
	 * @param type		A SeatType enumerator value of which type to retrieve seats from.
	 * @return			An ArrayList<Seat> of all seats in the PlaneObject of specified type
	 */
	public ArrayList<Seat> getAllSeats(SeatType type) {
		ArrayList<Seat> narrowseats = new ArrayList<Seat>();
		for(Seat seat : this.seats) {
			if(seat.getSeatType() == type) {
				narrowseats.add(seat);
			}
		}
		return narrowseats;
	}
	
//	public void setFlight(Flight flight) {
//		for(Seat seat : this.getAllSeats(SeatType.COMFORT)) {
//			seat.setFlight(flight);
//		}
//		for(Seat seat : this.getAllSeats(SeatType.COMFORT)) {
//			seat.setFlight(flight);
//		}
//		for(Seat seat : this.getAllSeats(SeatType.COMFORT)) {
//			seat.setFlight(flight);
//		}
//	}
}
