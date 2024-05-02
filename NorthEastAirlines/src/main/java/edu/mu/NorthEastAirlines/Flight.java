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
		return planeObject.getAvailableSeats(type);
	}
	
	public ArrayList<Seat> getAllSeats(SeatType type) {
		System.out.println("CALLED");
		return planeObject.getAllSeats(type);
	}

	public void changeSeatAvailabilityToFalse(Seat seat)
	{
		seat.setAvailable(false);
	}
	
	public int getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(int flightNumber) {
		this.flightNumber = flightNumber;
	}
	public PlaneObject getPlaneType() {
		return planeObject;
	}
	public void setPlaneType(PlaneObject planeType) {
		this.planeObject = planeType;
	}
	@Override
	public String toString() {
		return "Flight [departureLocation=" + departureLocation + ", arrivalLocation=" + arrivalLocation
				+ ", departureTime=" + departureTime + ", arrivalTime=" + arrivalTime + ", flightNumber=" + flightNumber
				+ ", planeType=" + planeObject + "]";
	}
}
