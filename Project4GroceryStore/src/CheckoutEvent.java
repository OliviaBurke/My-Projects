
import java.util.Queue;

public class CheckoutEvent extends Event {

	private Queue<Customer> CL;

	public CheckoutEvent(Customer a, double b, Queue<Customer> CL) {
		super(a, b);
		this.CL = CL;
	}

	// checking customers in each lane and seeing if they are in the checkout queue
	public double checkout() {
		if (CL.getClass() == ExpressCheckout.class) {
			ExpressCheckout expressCheckout = new ExpressCheckout();
			return expressCheckout.exTime(customer, time);
		} else {
			RegularCheckout regCheckout = new RegularCheckout();
			return regCheckout.regCheck(customer, time);
		}
	}
}
