package edu.mu.NorthEastAirlines;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import flights.Airport;
import flights.PlaneObject;
<<<<<<< Updated upstream
import seatSelection.Seat;
=======
import flights.Seat;
import flights.SeatType;
>>>>>>> Stashed changes

public class Flight {
	Airport departureLocation;
	Airport arrivalLocation;
	LocalDateTime departureTime;
	LocalDateTime arrivalTime;
	int flightNumber;
	PlaneObject planeType;
	
	public Flight(Airport departureLocation, Airport arrivalLocation, LocalDateTime departureTime,
			LocalDateTime arrivalTime, int flightNumber, PlaneObject planeType) {
		super();
		this.departureLocation = departureLocation;
		this.arrivalLocation = arrivalLocation;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.flightNumber = flightNumber;
		this.planeType = planeType;
	}
	public Airport getDepartureLocation() {
		return departureLocation;
	}
	public void setDepartureLocation(Airport departureLocation) {
		this.departureLocation = departureLocation;
	}
	public Airport getArrivalLocation() {
		return arrivalLocation;
	}
	public void setArrivalLocation(Airport arrivalLocation) {
		this.arrivalLocation = arrivalLocation;
	}
	public LocalDateTime getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(LocalDateTime departureTime) {
		this.departureTime = departureTime;
	}
	public LocalDateTime getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(LocalDateTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
//	public ArrayList<Seat> getAvailableSeats(SeatType type) {
//		return planeType.getAvailableSeats(type);
//	}

	public int getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(int flightNumber) {
		this.flightNumber = flightNumber;
	}
	public PlaneObject getPlaneType() {
		return planeType;
	}
	public void setPlaneType(PlaneObject planeType) {
		this.planeType = planeType;
	}
}
