
class RegularCheckout extends CheckoutLane {

	public double regCheck(Customer customer, double t) {
		return ((0.05 * customer.getNumItems()) + 2.0 + t);
	}

	@Override
	public double getCheckoutTime(Customer c) {

		return 0;
	}
}
