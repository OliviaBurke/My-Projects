
class FinishedShoppingEvent extends Event {

	
	public FinishedShoppingEvent(Customer a, double time) {
		super(a, time);
		
	}
	// don't repeat the time and customer fields here -- it 
	// inherits those from the superclass Event
}
