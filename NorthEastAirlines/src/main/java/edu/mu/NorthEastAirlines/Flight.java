package edu.mu.NorthEastAirlines;

import java.time.LocalDateTime;
import java.util.List;

import flights.Airport;
import flights.PlaneObject;
import seatSelection.Seat;

public class Flight {
	Airport departureLocation;
	Airport arrivalLocation;
	LocalDateTime departureTime;
	LocalDateTime arrivalTime;
	List<Seat> availableSeats;
	int flightNumber;
	PlaneObject planeType;
	
	public Flight(Airport departureLocation, Airport arrivalLocation, LocalDateTime departureTime,
			LocalDateTime arrivalTime, List<Seat> availableSeats, int flightNumber, PlaneObject planeType) {
		super();
		this.departureLocation = departureLocation;
		this.arrivalLocation = arrivalLocation;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.availableSeats = availableSeats;
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
	public List<Seat> getAvailableSeats() {
		return availableSeats;
	}
	public void setAvailableSeats(List<Seat> availableSeats) {
		this.availableSeats = availableSeats;
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
}
