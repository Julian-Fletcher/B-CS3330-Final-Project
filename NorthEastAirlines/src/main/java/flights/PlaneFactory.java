package flights;

import java.util.Random;

import flights.PlaneObject;
import flights.Seat;

public class PlaneFactory {

	public PlaneObject generaterRandomPlane() {
		PlaneObject newplane = new PlaneObject();
		Random random = new Random();
		int randplanetype = random.nextInt(3);
		newplane.setModel(PlaneType.values()[randplanetype]);
	    
	    int nseats = random.nextInt(150 - 50) + (50-randplanetype*10);

	    
	    for(int i=0;i<nseats;i++) {
	    	
	    	Seat seat = new Seat(i, SeatType.values()[random.nextInt(3)]);
	    	newplane.addSeat(seat);
	    	
	    }
	    
		return new PlaneObject();
	}
}
