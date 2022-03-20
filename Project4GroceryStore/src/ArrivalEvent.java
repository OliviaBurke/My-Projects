
class ArrivalEvent extends Event {

	public ArrivalEvent(Customer a, double time) {
		super(a, time);

	}

	public double finishedShopTime() {
		// gets the time the customer takes to finish shopping and add current time
		return (super.customer.getNumItems() * super.customer.getTimePerItem() + super.time);

	}

}
