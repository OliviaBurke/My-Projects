class Event implements Comparable<Event> {

	// these values are set in Arrival Event class
	protected Customer customer;
	protected double time;

	public Event(Customer customer, double time) {
		this.customer = customer;
		this.time = time;
	}

	// comparing events
	@Override
	public int compareTo(Event o) {
		if (o.time > this.time) {
			return -1;
		} else if (o.time < this.time) {
			return 1;
		} else {
			return 0;
		}
	}

}
