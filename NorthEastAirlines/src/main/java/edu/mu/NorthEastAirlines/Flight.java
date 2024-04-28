package edu.mu.NorthEastAirlines;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import seatSelection.SeatType;
import seatSelection.Seat;
import flights.Airport;
import flights.PlaneObject;
import flights.PlaneType;

public class Flight {
	Airport departureLocation;
	Airport arrivalLocation;
	LocalDateTime departureTime;
	LocalDateTime arrivalTime;
	int flightNumber;
	PlaneObject planeType;
	ArrayList<Seat> allSeats;	// Flights need a seat list
	
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
	public ArrayList<Seat> getAvailableSeats(SeatType type) {
		System.out.println("CALLED");
		return planeType.getAvailableSeats(type);
	}
	
	public ArrayList<Seat> getAllSeats(SeatType type) {
		System.out.println("CALLED");
		return planeType.getAllSeats(type);
	}

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
	@Override
	public String toString() {
		return "Flight [departureLocation=" + departureLocation + ", arrivalLocation=" + arrivalLocation
				+ ", departureTime=" + departureTime + ", arrivalTime=" + arrivalTime + ", flightNumber=" + flightNumber
				+ ", planeType=" + planeType + "]";
	}
	
	
	public boolean getSeatAvailability(int seatNumber) {
		boolean availability; 
		
		for(Seat seat : this.allSeats) {
			if(seat.getSeatNumber() == seatNumber) {
				availability = seat.isAvailable();
				return availability;
			}
		}
		// If seat doesn't exist
		return false;
	}
	
}
