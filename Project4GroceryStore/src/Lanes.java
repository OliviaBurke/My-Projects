import java.util.ArrayList;

public class Lanes {

	ArrayList<CheckoutLane> lanes = new ArrayList<CheckoutLane>();
	private int numOfLanes = 12;
	private int NumOfExpressLanes = 8;

	// the constructor to create list of checkout lanes
	public Lanes() {
		for (int i = 0; i < numOfLanes; i++) {
			if (i < NumOfExpressLanes) {
				CheckoutLane CL = new ExpressCheckout();
				lanes.add(CL);
			} else {
				CheckoutLane CL = new RegularCheckout();
				lanes.add(CL);
			}
		}
	}

	public void check() {
		for (int i = 0; i < lanes.size(); i++) {
			System.out.println(lanes.getClass() + " size " + lanes.get(i).CL.size());
		}
		System.out.println("\n");

	}

}
