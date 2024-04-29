package flights;

public enum PlaneType {
	LARGE_PLANE(0),
	MEDIUM_PLANE(1),
	SMALL_PLANE(2);
	
	final int planeVal;
	PlaneType(int planeVal) 
	{
		this.planeVal = planeVal;
	}
	public int getPlaneVal()
	{
		return planeVal;
	}
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
