package edu.mu.NorthEastAirlines;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import seatSelection.SeatType;
import seatSelection.Seat;
import flights.Airport;
import flights.PlaneObject;
import flights.PlaneType;

/**
 * Class to store data that relates to the function of flights as seen from a customer perspective
 */
public class Flight {
	Airport departureLocation;
	Airport arrivalLocation;
	LocalDateTime departureTime;
	LocalDateTime arrivalTime;
	int flightNumber;
	PlaneObject planeObject;
	
	public Flight(Airport departureLocation, Airport arrivalLocation, LocalDateTime departureTime,
			LocalDateTime arrivalTime, int flightNumber, PlaneObject planeObject) {
		super();
		this.departureLocation = departureLocation;
		this.arrivalLocation = arrivalLocation;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.flightNumber = flightNumber;
		this.planeObject = planeObject;
	}
	
	/**
	 * Gets flight instance's departure location.
	 * 
	 * @return			An Airport that flight is departing from
	 */
	public Airport getDepartureLocation() {
		return departureLocation;
	}
	
	/**
	 * Sets flight instance's departure location.
	 * 
	 * @param departureLocation		An Airport that represents the airport of departure
	 */
	public void setDepartureLocation(Airport departureLocation) {
		this.departureLocation = departureLocation;
	}
	
	/**
	 * Gets flight instance's arrival location.
	 * 
	 * @return 					Airport that represents the airport of arrival
	 */
	public Airport getArrivalLocation() {
		return arrivalLocation;
	}
	
	/**
	 * Sets flight instance's arrival location.
	 * 
	 * @param arrivalLocation	The Airport in which the flight is set to arrive
	 */
	public void setArrivalLocation(Airport arrivalLocation) {
		this.arrivalLocation = arrivalLocation;
	}
	
	/**
	 * Gets flight instance's departure time
	 * 
	 * @return 					A LocalDateTime object storing the departure time
	 */
	public LocalDateTime getDepartureTime() {
		return departureTime;
	}
	
	/**
	 * Sets flight instance's departure time.
	 * 
	 * @param departureTime		A LocalDateTime object representing the flight's departure time
	 */
	public void setDepartureTime(LocalDateTime departureTime) {
		this.departureTime = departureTime;
	}
	
	/**
	 * Gets flight instance's arrival time
	 * 
	 * @return 					A LocalDateTime object storing the arrival time
	 */
	public LocalDateTime getArrivalTime() {
		return arrivalTime;
	}
	
	/**
	 * Sets flight instance's arrival time.
	 * 
	 * @param arrivalTime		A LocalDateTime object representing the flight's arrival time
	 */
	public void setArrivalTime(LocalDateTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	
	/**
	 * Gets available seats that belong to the flight's PlaneObject
	 * 
	 * @see PlaneObject
	 * 
	 * @param type		A SeatType that corresponds to the desired available seat type
	 * @return			An ArrayList<Seat> that contains all available seats of specified seat type
	 */
	public ArrayList<Seat> getAvailableSeats(SeatType type) {
		System.out.println("CALLED");
		return planeObject.getAvailableSeats(type);
	}
	
	/**
	 * Gets all seats that belong to the flight's PlaneObject
	 * 
	 * @see PlaneObject
	 * 
	 * @param type		A SeatType that corresponds to the desired seat type
	 * @return			An ArrayList<Seat> that contains all seats of specified seat type
	 */
	public ArrayList<Seat> getAllSeats(SeatType type) {
		System.out.println("CALLED");
		return planeObject.getAllSeats(type);
	}

	/**
	 * Changes the availability of a seat to occupied (false)
	 * 
	 * 
	 * @param seat		A Seat that will have its availability changed
	 */
	public void changeSeatAvailabilityToFalse(Seat seat)
	{
		seat.setAvailable(false);
	}
	
	/**
	 * Gets the flight number of flight instance
	 * 
	 * 
	 * @return 			An integer id representing the flight
	 */
	public int getFlightNumber() {
		return flightNumber;
	}
	
	/**
	 * Sets the flight number of flight instance
	 * 
	 * 
	 * @param flightNumber 		An new integer id to represent the flight
	 */
	public void setFlightNumber(int flightNumber) {
		this.flightNumber = flightNumber;
	}
	
	/**
	 * Gets the PlaneObject of the flight instance
	 * 
	 * 
	 * @return 			A PlaneObject belonging to the Flight
	 */
	public PlaneObject getPlaneType() {
		return planeObject;
	}
	
	/**
	 * Sets the PlaneObject of flight instance
	 * 
	 * 
	 * @param planeType		A PlaneObject that will belong to the Flight
	 */
	public void setPlaneType(PlaneObject planeType) {
		this.planeObject = planeType;
	}
	
	/**
	 * Creates a toString for printing flight information
	 * 
	 * 
	 * @return 			String of information about the Flight instance
	 */
	@Override
	public String toString() {
		return "Flight [departureLocation=" + departureLocation.city + ", arrivalLocation=" + arrivalLocation.city
				+ ", departureTime=" + departureTime + ", arrivalTime=" + arrivalTime + ", flightNumber=" + flightNumber
				+ ", planeType=" + planeObject.getModel().name() + "]";
	}
}
