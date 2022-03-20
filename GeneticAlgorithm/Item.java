
public class Item {

	private final String name;
    
	private final double weight;
	
	private final int value;
	
	private boolean included;
	
	public Item(String name, double weight, int value) {
		this.name = name;
		this.weight = weight;
		this.value = value;	
	}
	
	public Item(Item other) {
		this.name = other.name;
		this.weight = other.weight;
		this.value = other.value;	
	}
	
	public String getName() {
		return this.name;
	}
	 
	public double getWeight() {
		return this.weight;
	}
	  
	public int getValue() {
		return this.value;
	}
	
	public boolean isIncluded() {
		return this.included;
	}
	
	public void setIncluded(boolean included) {
		this.included = included;
	}
	

	
}
	

