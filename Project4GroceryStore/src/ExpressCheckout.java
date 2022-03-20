

public class ExpressCheckout extends CheckoutLane {
   
	public double exTime(Customer a, double time) {
		return((0.10 * a.getNumItems()) + 1.0 + time );
	}

	@Override
	public double getCheckoutTime(Customer c) {
		// TODO Auto-generated method stub
		return 0;
	}
}
