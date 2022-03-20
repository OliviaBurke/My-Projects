
class FinishedCheckoutEvent extends Event {

	private double endShoppingTime = 0.0;

	FinishedCheckoutEvent(Customer a, double b) {
		super(a, b);
		endShoppingTime = a.getEndShoppingTime();
	}
}
