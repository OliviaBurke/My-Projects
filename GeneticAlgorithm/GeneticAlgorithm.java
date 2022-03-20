import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import java.util.List;

public class GeneticAlgorithm {
	
	public static ArrayList<Item> readData(String filename) throws FileNotFoundException{
		// Reads in a data file with the format shown below and creates and 
		// returns an ArrayList of Item objects.
		// item1_label, item1_weight, item1_value
		// item2_label, item2_weight, item2_value 
		
		//Create a file to find the current path
		File testFile = new File("");
		String currentPath = testFile.getAbsolutePath();
		String fileData = null;
		ArrayList<Item> items = new ArrayList<>();
	
		try {
			//Read in the file and store the data
			Scanner in = new Scanner(new File(currentPath + "/src/" + filename));
			while (in.hasNextLine()) {
			 //Parse each line and add to the items list
			 String[] item = in.nextLine().split(", ");
			 Item newItem = new Item(item[0], Double.parseDouble(item[1]), Integer.parseInt(item[2]));
			 items.add(newItem);
			}	
		//Catch the file data
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return items;
	}

	public static ArrayList<Chromosome> intializePopulation(ArrayList<Item> items, int populationSize) {
		//Creates and returns an ArrayList of populationSize Chromosome objects that each
		//contain the items, with their included field randomly set to true or false. 
		ArrayList<Chromosome> initialPop = new ArrayList<Chromosome>();
		for(int i = 0; i < populationSize; i++) {
			Chromosome newChrome = new Chromosome(items);
			initialPop.add(newChrome);
		}
		return initialPop;	
	}

	public static void main(String[] args) throws FileNotFoundException {
		//Reads the data about the items in from a file called items.txt 
		ArrayList<Item> items = readData("ItemsList.txt");
		// set the initial population to 10
	    ArrayList<Chromosome> initialPop = intializePopulation(items, 1000);
	    ArrayList<Chromosome> population = new ArrayList<Chromosome>();
	    ArrayList<Chromosome> nextGen = new ArrayList<Chromosome>();
	    for(int i = 0; i < initialPop.size(); i++) {
	    	// Part 1 of step 2: adding current population to the next generation
	    	population.add(initialPop.get(i));
	     }
	    for(int k = 0; k < 20; k++) {
	    
	     nextGen.addAll(population);
	    	for(int z = 0; z < 10; z++) {
			      Random rng1 = new Random();
			      Random rng2 = new Random();
			      // the index of the two parents to crossover
			      int j = rng1.nextInt(population.size());
			      int x = rng2.nextInt(population.size());
			      Chromosome child = population.get(j).crossover(population.get(x));
			      // Completeing step 2: adding child to the next generatio
			      nextGen.add(child);
	    	}
	        // Step 4 : randomly choosing 10% of the population to mutate.
	    	int numToMutate = (int) ((int) nextGen.size() * 0.1);
	        for(int y = 0; y < numToMutate; y++) {
	          Random rng3 = new Random();
	          // index of the object to mutate
	          int m = rng3.nextInt(nextGen.size());
	          nextGen.get(m).mutate();
	        }
	       // Step 5: Sort according to fitness 
	       Collections.sort(nextGen); 
	       
	       // Step 6: Clear out the current population and add the top 10 of nextGen back into population
	       
	       population.clear();
	       for (int i = 0; i < 10; i ++) {
	    	   population.add(nextGen.get(i));
	       }
	       nextGen.clear();
	      Collections.sort(population);  
	     
	    }
	    System.out.println("Most fit: " + population.get(0));
	    
	    //perform the steps described in the Running the Genetic Algorithm section above. 
	    //1. Create a set of 10 random individuals to serve as initial population. 
	    //2. Add each of the individuals in the current population to the next generation. 
	    //3. Randomly pair off the individuals and perform crossover to create a child and add it the next generation as well.
	    //4. Randomly choose 10% of the individuals in the next generation and expose them to mutation. 
	    //5. Sort the individuals in the next generation according to their fitness.
	    //6. Clear out the current population and add the top 10 of the next generation back into the population.
	    //7. Repeat steps 2-6 20 times.
	    //8. Sort the population and display the fittest individual to the console. 
	
	}
 }
