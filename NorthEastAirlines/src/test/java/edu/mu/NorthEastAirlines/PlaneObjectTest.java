package edu.mu.NorthEastAirlines;

import accounts.AccountStatus;
import accounts.UserAccounts;
import flights.PlanePsuedoFactory;
import flights.PlaneType;
import junit.framework.TestCase;
import seatSelection.Seat;
import seatSelection.SeatType;

public class PlaneObjectTest extends TestCase {
	
	public void testGetInstance() {
		// GEt instance 
		PlanePsuedoFactory instance1 = PlanePsuedoFactory.getInstance();
		
		// IInstance 2
		PlanePsuedoFactory instance2 = PlanePsuedoFactory.getInstance();
		
		assertSame("Instances should be the same", instance1, instance2);
	}
	
	
	
	
	
	public void testGeneratePlane() {
		
		PlanePsuedoFactory factory = new PlanePsuedoFactory();
		flights.PlaneObject plane = factory.generaterRandomPlane();
		assertNotNull("Plane should have been generated", plane);
	}
	
	
	public void testSeatManagement() {
		PlanePsuedoFactory factory = new PlanePsuedoFactory();
		flights.PlaneObject plane = factory.generaterRandomPlane();
		
		
		assertNotNull("Plane should have comfort seats", plane.getAvailableSeats(SeatType.COMFORT));
		
		assertNotNull("Plane should have economy seats", plane.getAvailableSeats(SeatType.ECONOMY));
		
		assertNotNull("Plane should have firstclass seats", plane.getAvailableSeats(SeatType.FIRST_CLASS));
		
		assertNotSame("Some seats should be automatically booked", plane.getAllSeats(SeatType.ECONOMY), plane.getAvailableSeats(SeatType.ECONOMY));


		
	}
	
	public void testModel() {
		PlanePsuedoFactory factory = new PlanePsuedoFactory();
		flights.PlaneObject plane = factory.generaterRandomPlane();
		
		
		assertNotNull("Plane should have model", plane.getModel());
		
		plane.setModel(PlaneType.MEDIUM_PLANE);
		assertSame("Plane should be medium",plane.getModel(), PlaneType.MEDIUM_PLANE);
		
		//Extra test just in case plane was already randomly generated to start as MEDIUM
		plane.setModel(PlaneType.SMALL_PLANE);
		assertSame("Plane should be small",plane.getModel(), PlaneType.SMALL_PLANE);
	}
	
	public void testAddSeat() {
		PlanePsuedoFactory factory = new PlanePsuedoFactory();
		flights.PlaneObject plane = factory.generaterRandomPlane();
		
		Seat seat = new Seat(9999, SeatType.COMFORT);
		plane.addSeat(seat);
		assertTrue("Seat should exist in plane", plane.getAvailableSeats(SeatType.COMFORT).contains(seat));

		
	}
	
	
	
	
//	
//	public void testBookSeat() {
//		
//	}
}
