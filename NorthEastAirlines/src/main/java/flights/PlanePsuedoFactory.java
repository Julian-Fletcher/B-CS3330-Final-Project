package flights;

import java.util.Random;



import edu.mu.NorthEastAirlines.AirlineManagerSingleton;
import edu.mu.NorthEastAirlines.Flight;
import flights.PlaneObject;
import seatSelection.Seat;
import seatSelection.SeatSelectionStrategy;
import seatSelection.SeatType;


/**
 * Class used for creating random PlaneObject instances.
 * Named *PsuedoFactory because it is not a true Factory.
 */
public class PlanePsuedoFactory {


	private static PlanePsuedoFactory instance = null;		// Only one!

	
	/**
	 * Constructor
	 */
	public PlanePsuedoFactory() {
	
	}
	
	// Singleton initalizer. Only allows one!!
	// Does not yet make admin account!
	
	/**
	 * Gets/Initializes instance of singleton PlanePsuedoFactory.
	 * 
	 * @return			Instance of PlanePsuedoFactory
	 */
	public static PlanePsuedoFactory getInstance() {
		if(instance == null) {
			instance = new PlanePsuedoFactory();
			
		}
		return instance;
	}
	
	/**
	 * Generates a random plane for Flight.
	 * 
	 * @see Flight
	 * @return			PlaneObject with a randomly assigned number/availability of seats
	 */
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
