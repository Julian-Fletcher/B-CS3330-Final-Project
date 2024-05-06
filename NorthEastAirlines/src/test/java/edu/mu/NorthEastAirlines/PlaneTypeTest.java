package edu.mu.NorthEastAirlines;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import flights.PlaneType;

public class PlaneTypeTest {
	@Test
	void testGetPlaneType() {
		assertEquals(0, PlaneType.LARGE_PLANE.getPlaneVal());
		assertEquals(1, PlaneType.MEDIUM_PLANE.getPlaneVal());
		assertEquals(2, PlaneType.SMALL_PLANE.getPlaneVal());
	}
	
}
