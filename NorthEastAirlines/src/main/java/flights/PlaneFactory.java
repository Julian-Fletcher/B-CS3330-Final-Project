package flights;

import java.util.Random;

import edu.mu.NorthEastAirlines.Flight;
import flights.PlaneObject;
import seatSelection.Seat;
import seatSelection.SeatType;
public class PlaneFactory {

	public PlaneObject generaterRandomPlane() {
		PlaneObject newplane = new PlaneObject();
		Random random = new Random();
		int randplanetype = random.nextInt(3);
		newplane.setModel(PlaneType.values()[randplanetype]);
	    
	    int nseats = random.nextInt(150 - 50) + (50-randplanetype*10);

	    
	    for(int i=0;i<nseats;i++) {
	    	
	    	Seat seat = new Seat(i, SeatType.values()[random.nextInt(3)]);
	    	if(random.nextInt(3) == 1) {
	    		seat.setAvailable(false);
	    	}
	    
	    	newplane.addSeat(seat);
	    	
	    }
	    
		return newplane;
	}
}
