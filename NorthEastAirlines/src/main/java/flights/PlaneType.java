package flights;

/**
 * Class for storing type of plane used in Flight.
 * @see Flight
 */
public enum PlaneType {
	
	LARGE_PLANE(0),
	MEDIUM_PLANE(1),
	SMALL_PLANE(2);
	
	
	final int planeVal;
	
	
	PlaneType(int planeVal) 
	{
		this.planeVal = planeVal;
	}
	
	/**
	 * Gets value of PlaneType index.
	 * 
	 * @return			An integer corresponding to a PlaneType value
	 */
	public int getPlaneVal()
	{
		return planeVal;
	}
	
	/**
	 * Gets PlaneType from integer value.
	 * 
	 * @param planeVal	An integer value
	 * @return			A PlaneType corresponding to inputed integer value
	 */
	public static PlaneType getPlaneType(int planeVal)
	{
		for(PlaneType planes : PlaneType.values())
		{
			if(planes.getPlaneVal() == planeVal)
			{
				return planes;
			}
		}
		return null;
	}
}
