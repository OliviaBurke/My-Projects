import java.util.Random;

class Customer {

	private int customerID;
	private int numItems;
	private double timePerItem;
	private double finishedCheckoutTime;
	private double finishedShoppingTime;
	private double arrivalTime;
	private double startCheckoutTime;
	private double endShoppingTime;

	// the constructor
	public Customer(double arrivalTime, int numItems, double timePerItem) {
		this.arrivalTime = arrivalTime;
		this.numItems = numItems;
		this.timePerItem = timePerItem;

		Random rng = new Random();
		int min = 1000;
		int max = 9999;
		this.customerID = rng.nextInt(max - min) + min;

	}

	public double getArrivalTime() {
		return this.arrivalTime;
	}

	public void setArriveTime(double arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public double getFinishedCheckoutTime() {
		return this.finishedCheckoutTime;
	}

	public void setFinishedCheckoutTime(double finishedCheckoutTime) {
		this.finishedCheckoutTime = arrivalTime + numItems * timePerItem;
	}

	public int getCustomerID() {
		return this.customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public int getNumItems() {
		return this.numItems;
	}

	public void setNumItems(int numItems) {
		this.numItems = numItems;
	}

	public double getTimePerItem() {
		return this.timePerItem;
	}

	public void setTimePerItem(double timePerItem) {
		this.timePerItem = timePerItem;
	}

	public double getFinishedShoppingTime() {
		return this.finishedShoppingTime;
	}

	public void setFinishedShoppingTime(double finishedShoppingTime) {
		this.finishedShoppingTime = finishedShoppingTime;
	}

	public double getStartCheckoutTime() {
		return this.startCheckoutTime;
	}

	public void setStartCheckoutTime(double startCheckoutTime) {
		this.startCheckoutTime = startCheckoutTime;
	}

	public double getEndShoppingTime() {
		return this.endShoppingTime;
	}
}
