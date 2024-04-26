package flights;

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
	public int getLocationVal()
	{
		return locationVal;
	}
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
