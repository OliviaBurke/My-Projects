import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Simulator {

	// ArrayList or PriorityQueue of CheckoutLanes, create the lane objects (desired
	// number of each) and add them
	// to the data structure
	private static double simClock = 0.0;
	private static double custSumWaitTime = 0.0;
	private static double numOfCustomers = 0.0;

	static ArrayList<CheckoutLane> checkOutLanes = new ArrayList<>();
	static ArrayList<Customer> customers = new ArrayList<>();

	public static void main(String[] args) throws IOException {

		// Read in the file w/ all of the customers
		String file = "src/arrival medium.txt";
		Path path = Paths.get(file);
		BufferedReader br = Files.newBufferedReader(path);
		String currentLine;
		while ((currentLine = br.readLine()) != null) {
			
			String[] customerLine = currentLine.split("\\s+");
			Customer NewCustomer = new Customer((Double.parseDouble(customerLine[0])),
					Integer.parseInt(customerLine[1]), Double.parseDouble(customerLine[2]));
			customers.add(NewCustomer);
		}
	
		br.close();
		PriorityQueue<Event> events = new PriorityQueue<>();

		// for each line, create a customer object for that person and add it to the
		// customers list
		// also create an ArrivalEvent object and add it to the p q
		for (Customer a : customers) {
			ArrivalEvent ae = new ArrivalEvent(a, a.getArrivalTime());
			events.add(ae);
		}

		Lanes allLanes = new Lanes();
		// while events p q is not empty
		// poallLanes the next event (Event should implement Comparable and sort by
		// time)
		// see what kind of event it is
		// (if you used subclasses, say if (thisEvent instanceof ArrivalEvent) to do
		// that
		// if this is an ArrivalEvent, create a FinishedShoppingEvent with the right
		// time and add it to the events p q
		// if it's a FinishedShoppingEvent, pick an appropriate checkout lane, add the
		// customer associated with this event
		// to that lane, and figure out their finished checkout time and create a new
		// event based on that and add it
		// to the p q
		// if this is a finished checkout event, update the average wait time

		while (!events.isEmpty()) {
			// events.poallLanes();
			// simClock is the current time and "time" is the event time
			if (simClock < events.peek().time) {
				simClock = events.peek().time;
			}
			// checking top event, if its an object of the arrival event class
			if (events.peek().getClass() == ArrivalEvent.class) {
				// new finished shopping event for the customer that has already arrived,
				// looking (peeking) at that customer, getting their finished shopping time
				FinishedShoppingEvent newFSE = new FinishedShoppingEvent(events.peek().customer,
						((ArrivalEvent) events.peek()).finishedShopTime());
				// get the customers finished shopping time
				events.peek().customer.setFinishedShoppingTime(((ArrivalEvent) events.peek()).finishedShopTime());
				// add to events p q
				events.add(newFSE);
				// if the event is a finished shopping event, pick appropriate checkout lane,
				// add customer, find finsished checkout time, create new event for that, add to
				// p q
			} else if (events.peek().customer == null) {
				;
			} else if (events.peek().getClass() == FinishedShoppingEvent.class) {

				// sorting by lanes with least amount customer
				allLanes.lanes.sort(null);
				// if customer has less than or equal to 12 items and lanes are the same size
				// CL = Checkout Lane
				if (events.peek().customer.getNumItems() <= 12
						&& allLanes.lanes.get(0).CL.size() == allLanes.lanes.get(1).CL.size()) {
					if (allLanes.lanes.get(0).getClass() == ExpressCheckout.class) {
						allLanes.lanes.get(0).CL.add(events.peek().customer);
						// if there is already a customer in the lane, create checkout event for first
						// customer
						if (allLanes.lanes.get(0).CL.size() == 1) {
							CheckoutEvent CL = new CheckoutEvent(allLanes.lanes.get(1).CL.peek(), simClock,
									allLanes.lanes.get(1).CL);
							events.add(CL);
						}

					} else if (allLanes.lanes.get(1).getClass() == ExpressCheckout.class) {
						allLanes.lanes.get(1).CL.add(events.peek().customer);
						if (allLanes.lanes.get(1).CL.size() == 1) {
							CheckoutEvent CL = new CheckoutEvent(allLanes.lanes.get(1).CL.peek(), simClock,
									allLanes.lanes.get(1).CL);
							events.add(CL);
						}
					}
					// if customer has more than 12 items, add to regular checkout lane
				} else if (events.peek().customer.getNumItems() > 12) {
					// for loop is checking checking type of lane
					A: for (int i = 0; i < allLanes.lanes.size(); i++) {
						// checking checkout lanes from assorted lanes, and adding customer to
						// appropriate lane
						if (allLanes.lanes.get(i).getClass() == RegularCheckout.class) {
							allLanes.lanes.get(i).CL.add(events.peek().customer);
							if (allLanes.lanes.get(i).CL.size() == 1) {
								CheckoutEvent CL = new CheckoutEvent(allLanes.lanes.get(i).CL.peek(), simClock,
										allLanes.lanes.get(i).CL);
								events.add(CL);
							}
							break A;
						}
					}

				} else {
					// get the first available lane with least amount of customers
					allLanes.lanes.get(0).CL.add(events.peek().customer);
					if (allLanes.lanes.get(0).CL.size() == 1) {
						CheckoutEvent CL = new CheckoutEvent(allLanes.lanes.get(0).CL.peek(), simClock,
								allLanes.lanes.get(0).CL);
						events.add(CL);
					}
				}
			} else if (events.peek().getClass() == CheckoutEvent.class) {
				FinishedCheckoutEvent checkoutEnd = new FinishedCheckoutEvent(events.peek().customer,
						((CheckoutEvent) events.peek()).checkout());
				events.add(checkoutEnd);
				events.peek().customer.setStartCheckoutTime(simClock);
			} else if (events.peek().getClass() == FinishedCheckoutEvent.class) {
				// Time spent waiting in line
				custSumWaitTime += (events.peek().customer.getStartCheckoutTime()
						- events.peek().customer.getFinishedShoppingTime());
				// looping through each lane from arraylist and checking if the customer is
				// first customer in checkout queue
				for (int i = 0; i < allLanes.lanes.size(); i++) {
					// if first customer is in the checkout queue
					if (allLanes.lanes.get(i).CL.contains(events.peek().customer)) {
						// poallLanes that customer if in checkout queue
						allLanes.lanes.get(i).CL.poll();
						if (!allLanes.lanes.get(i).CL.isEmpty()) {
							CheckoutEvent CL = new CheckoutEvent(allLanes.lanes.get(i).CL.peek(), simClock,
									allLanes.lanes.get(i).CL);
							events.add(CL);
						}
					}
				}
			}
			
			events.poll();
			// allLanes.check();
		}

		System.out.println("There are " + customers.size() + " customers");
		System.out.println("Average Wait Time: " + custSumWaitTime / customers.size());

	}
}
