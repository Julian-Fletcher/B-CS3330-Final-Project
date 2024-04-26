package flights;

public class Seat {
	private int seatNumber;
	private SeatType seatType;
	private boolean available;
	
	public Seat(int number, SeatType type) {
		this.seatNumber = number;
		this.seatType = type;
		this.available = true;
	}
	public int getSeatNumber() {
		return seatNumber;
	}
	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}
	public SeatType getSeatType() {
		return seatType;
	}
	public void setSeatType(SeatType seatType) {
		this.seatType = seatType;
	}
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}	
}
