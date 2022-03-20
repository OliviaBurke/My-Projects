import java.util.ArrayList;
import java.util.Random;

public class Chromosome extends ArrayList<Item> implements Comparable<Chromosome> {

	private static Random rbg = new Random();

	public Chromosome() {	
	}
	
	// storring all genes (items) in an ArrayList
	public Chromosome(ArrayList<Item> items) {
		//Adds a copy of each of the items passed in to this Chromosome. Uses a random number to
		//decide whether each item’s included field is set to true or false.
		super();
		for (int i = 0; i< items.size(); i++) {
		 Item newItem = new Item(items.get(i).getName(), items.get(i).getWeight(), items.get(i).getValue());
		 // randomly setting "included" to true or false for each gene to create a chromosome
		 newItem.setIncluded(rbg.nextBoolean() && rbg.nextBoolean()); //25% change of including
		 // adding the genes that are true
		 this.add(newItem);
		}
	}
	public Chromosome crossover(Chromosome other) {
		//Creates and returns a new child chromosome by performing the crossover operation on
		//this chromosome and the other one that is passed in (i.e. for each item, use a random
		//number to decide which parent’s item should be copied and added to the child). 
		
		// new array list of genes to create a chromosome (empty)
		ArrayList<Item> newGenes = new ArrayList<Item>();
		
		for (int i = 0; i < this.size(); i++) {
			Random rng = new Random();
			int value = rng.nextInt(10 + 1) + 1;
			if(value <= 5) {
		    Item newItem = new Item(this.get(i).getName(), this.get(i).getWeight(), this.get(i).getValue());
		    newGenes.add(newItem);
			} else { // if the rng in 6-10
			Item newItem = new Item(other.get(i).getName(), other.get(i).getWeight(), other.get(i).getValue());
			newGenes.add(newItem);
			}
		}
		// make a new chromosome using newGenes
		Chromosome newChrome = new Chromosome(newGenes);
		return newChrome;
	}
	
	public void mutate() {
		//Performs the mutation operation on this chromosome (i.e. for each item in this
        //chromosome, use a random number to decide whether or not to flip it’s included field from
		//true to false or vice versa). 
		// For each gene create a small probability for it to flip from included to not included
		// Using an RNG 1-10. Only if number is 1, flip the gene individual. Else leave it the same
	      for(int i = 0; i < this.size(); i++) {
	    	Random rng = new Random();
			 int value = rng.nextInt(10 + 1) + 1;
			 if(value == 1) {
			  // change the the gene from true to false
			  this.get(i).setIncluded(!this.get(i).isIncluded());
			 } 
		  }	
	 }
	
	public int getFitness() {
	//Returns the fitness of this chromosome. If the sum of all of the included items’ weights
    //are greater than 10, the fitness is zero. Otherwise, the fitness is equal to the sum of all of the
	//included items’ values. 
		int totalWeight = 0;
		int totalValue = 0;
		for(int i = 0; i < this.size(); i++) {
			if(this.get(i).isIncluded()) {
		        totalWeight += this.get(i).getWeight();
		        totalValue += this.get(i).getValue();
			}
		}

		  if(totalWeight >= 10) {
			return 0; 
		  }		
		return totalValue; 
	}
	
	public int compareTo(Chromosome other) {
	//Returns -1 if this chromosome’s fitness is greater than the other’s fitness, +1 if this
	//chromosome’s fitness is less than the other one’s, and 0 if their fitness is the same. 
		if (this.getFitness() > other.getFitness()) {
			return -1;
		} else if (this.getFitness() == other.getFitness()) {
			return 0;
		} else {
			return 1;
		}
	}
	
	public String toString() {
		//Display the name, weight and value of all items in this chromosome whose included
		//value is true, followed by the fitness of this chromosome.
		int totalWeight = 0;
		for(int i = 0; i<this.size(); i++) {
			if(this.get(i).isIncluded()) {
				totalWeight += this.get(i).getWeight();
			}
		}
		if(this.getFitness() == 0) {
			return "Unfit";
		}
		
		String string = "\n====================";
		for (int i = 0; i < this.size(); i ++) {
		 if(this.get(i).isIncluded()) {
		  string += "\n(" + this.get(i).getName() + " " + this.get(i).getWeight() + " lbs. $" + this.get(i).getValue() + ") ";
		 
		 }
		}
		string += "\nTotal Weight: " + totalWeight;
	    string += "\nFitness: $" + this.getFitness() +"\n";    
	    string += "====================\n";
	
		return string;	 
	}	
}
