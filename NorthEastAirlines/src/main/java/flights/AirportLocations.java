package flights;

/**
 * Class that contains information about available airport locations to choose from.
 */
public enum AirportLocations 
{
	NewYorkCity(0),
	LosAngeles(1),
	Chicago(2), 
	Houston(3),
	Phoenix(4),
	Philadelphia(5),
	SanAntonio(6),
	SanDiego(7),
	Dallas(8),
	SanJose(9);
	
	final int locationVal;
	
	
	AirportLocations(int locationVal) 
	{
		this.locationVal = locationVal;
	}
	
	/**
	 * Gets integer id of location.
	 * 
	 * @return			Integer representation of location id
	 */
	public int getLocationVal()
	{
		return locationVal;
	}
	
	/**
	 * Converts provided integer location id to actual location enumerator value.
	 * 
	 * @param value		An integer value to match against location id values.
	 * @return			An AirportLocation value that corresponds to provided integer id
	 */
	public static AirportLocations getAirportLocations(int value)
	{
		for(AirportLocations locations : AirportLocations.values())
		{
			if(locations.getLocationVal() == value)
			{
				return locations;
			}
		}
		return null;
	}
}
