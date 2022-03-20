import java.util.*;

abstract class CheckoutLane implements Comparable<CheckoutLane> {

	// the constructor
	public CheckoutLane() {

	}

	protected Queue<Customer> CL = new LinkedList<Customer>();

	// store the customers in arraylist
	public CheckoutLane(ArrayList<Customer> customers) {
		super();
		for (int i = 0; i < customers.size(); i++) {
			Customer newCustomer = new Customer((int) customers.get(i).getArrivalTime(), customers.get(i).getNumItems(),
					customers.get(i).getTimePerItem());
			this.CL.add(newCustomer);
		}
	}

	public int compareTo(CheckoutLane o) {
		if (this.CL.size() == o.CL.size()) {
			return 0;
		} else if (this.CL.size() > o.CL.size()) {
			return 1;
		} else {
			return -1;
		}
	}

	// optional, but it might make other things easier:
	public abstract double getCheckoutTime(Customer c);
}
