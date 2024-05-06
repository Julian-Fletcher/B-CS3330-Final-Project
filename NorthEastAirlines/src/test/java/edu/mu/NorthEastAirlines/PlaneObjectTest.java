package edu.mu.NorthEastAirlines;

import accounts.AccountStatus;

import accounts.UserAccounts;
import flights.PlanePsuedoFactory;
import flights.PlaneType;
import seatSelection.Seat;
import seatSelection.SeatType;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Test;



import org.junit.jupiter.api.Test;

import accounts.AccountStatus;
import accounts.UserAccounts;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;




public class PlaneObjectTest {
	
	@Test
	public void testGetInstance() {
		// GEt instance 
		PlanePsuedoFactory instance1 = PlanePsuedoFactory.getInstance();
		
		// IInstance 2
		PlanePsuedoFactory instance2 = PlanePsuedoFactory.getInstance();
		
		assertSame(instance1, instance2,"Instances should be the same");
	}
	
	
	
	
	
	@Test
	public void testGeneratePlane() {
		
		PlanePsuedoFactory factory = new PlanePsuedoFactory();
		flights.PlaneObject plane = factory.generaterRandomPlane();
		assertNotNull(plane,"Plane should have been generated");
	}
	
	@Test
	public void testSeatManagement() {
		PlanePsuedoFactory factory = new PlanePsuedoFactory();
		flights.PlaneObject plane = factory.generaterRandomPlane();
		
		
		assertNotNull(plane.getAvailableSeats(SeatType.COMFORT), "Plane should have comfort seats");
		
		assertNotNull(plane.getAvailableSeats(SeatType.ECONOMY),"Plane should have economy seats");
		
		assertNotNull(plane.getAvailableSeats(SeatType.FIRST_CLASS),"Plane should have firstclass seats");
		
		assertNotSame(plane.getAllSeats(SeatType.ECONOMY), plane.getAvailableSeats(SeatType.ECONOMY),"Some seats should be automatically booked");


		
	}
	
	@Test
	public void testModel() {
		PlanePsuedoFactory factory = new PlanePsuedoFactory();
		flights.PlaneObject plane = factory.generaterRandomPlane();
		
		
		assertNotNull(plane.getModel(), "Plane should have model");
		
		plane.setModel(PlaneType.MEDIUM_PLANE);
		assertSame(plane.getModel(), PlaneType.MEDIUM_PLANE, "Plane should be medium");
		
		//Extra test just in case plane was already randomly generated to start as MEDIUM
		plane.setModel(PlaneType.SMALL_PLANE);
		assertSame(plane.getModel(), PlaneType.SMALL_PLANE, "Plane should be small");
	}
	
	@Test
	public void testAddSeat() {
		PlanePsuedoFactory factory = new PlanePsuedoFactory();
		flights.PlaneObject plane = factory.generaterRandomPlane();
		
		Seat seat = new Seat(9999, SeatType.COMFORT);
		plane.addSeat(seat);
		assertTrue(plane.getAvailableSeats(SeatType.COMFORT).contains(seat), "Seat should exist in plane");

		
	}
	
	
	
	
//	
//	public void testBookSeat() {
//		
//	}
}
