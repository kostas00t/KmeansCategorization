/*
 * KONSTANTINOS KIKIDIS (4387) 
 * CHRISTOS KROKIDAS (4399) 
 * KONSTANTINOS TSAMPIRAS (4508)
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class KMeansAlgorithm{
	
	private int M;
	static private Data[] educationSet;  
	static private Data[] centers;
	private ArrayList[] groups;
	private boolean term = false; 
	
	public KMeansAlgorithm(int M) {
		this.M = M;
		educationSet = new Data[1200];
	}
	
	/*
	 * Reads the file with the 1200 random points and 
	 * loads them onto the education set as Data object
	 */
	public void loadDataFromFile(String filename) {  
		Scanner inputReader = null; 
		try
		{
			inputReader = new Scanner(new FileInputStream(filename));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("File "+ filename + "was not found");
			System.out.println("or could not be opened.");
			System.exit(0);
		}
		
		int i = 0;
		while (inputReader.hasNextLine( )) {
			String line = inputReader.nextLine();
			String[] lineData = line.split(",");	
			educationSet[i] = new Data(Double.parseDouble(lineData[0]), Double.parseDouble(lineData[1]));
			i++;
		}
		inputReader.close();
	}
	
	/*
	 * Initializes the weights (group centers)
	 * Selects a random int in [0-1200) range as center
	 */
	public void initializeWeights() { 
		groups = new ArrayList[M];
		for(int i = 0; i < M; i++) {   
			groups[i] = new ArrayList<Data>();
		}
		centers = new Data[M]; 
		Random randgen = new Random();
		
		for(int i = 0; i < M; i++) {  
			int r = randgen.nextInt(1200);  
			centers[i] = new Data (educationSet[r].getX1(), educationSet[r].getX2());
		}
	}
	
	/*
	 * Calculate distance of points with M centers
	 * Hold distance and position of team
	 */
	public void minEuclidDistance(Data data) {
		double keep_dist = Double.MAX_VALUE;
		int index_team = 0;
		double dist;
		for(int i = 0; i < M; i++) {
			dist = Math.sqrt(Math.pow(Math.abs((data.getX1() - centers[i].getX1())), 2) + Math.pow(Math.abs((data.getX2() - centers[i].getX2())), 2));
			if(dist < keep_dist) {
				keep_dist = dist;    
				index_team = i;   
			}  
		}
		if(!groups[index_team].contains(data)) {
			for(int i = 0; i < M; i++) {
				if(groups[i].contains(data)) {
					groups[i].remove(data);
				}
			}
			groups[index_team].add(data);     
		}
	}
	
	/*
	 * Adjust the centers for each group
	 */
	public void adjustCenters() {
		double x1 = 0;
		double x2 = 0;
		term = true;
		for(int i = 0; i < M; i++) { 
			for(int j = 0; j < groups[i].size(); j++) {
				x1 += ((Data)groups[i].get(j)).getX1(); 
				x2 += ((Data)groups[i].get(j)).getX2();
			}
			if(groups[i].size()!=0) {
				x1 = x1 / groups[i].size();  
				x2 = x2 / groups[i].size();
				if(centers[i].getX1() != x1 || centers[i].getX2() != x2) {   
					centers[i].setX1(x1); 
					centers[i].setX2(x2);
					term = false;
				}
			}
		}
	}
	
	/*
	 * Calculate dispersion, we want it as small as possible
	 */
	public double calculateDispersion() {  
		double d = 0.0;
		for(int i = 0; i < M; i++) {
			for(int j = 0; j < groups[i].size(); j++) {
				d += Math.pow(Math.abs(((Data)groups[i].get(j)).getX1() - centers[i].getX1()), 2) + Math.pow(Math.abs(((Data) groups[i].get(j)).getX2() - centers[i].getX2()), 2);
			}
		}
		System.out.println("Total dispersion: "+String.format("%.6g",d));
		return d;
	}
	
	/*
	 * Returns our centers
	 */
	public Data[] getCenters() {
		return centers;
	}
	
	/*
	 * Takes each point of the education set and calculates 
	 * the Euclid distance of each point with the M centers
	 */
	public void runKMeans() {
		int t = 0;
		initializeWeights();                             
		boolean exit = true;
		while(exit) {
			for(int i = 0; i < educationSet.length; i++) {              
				minEuclidDistance(educationSet[i]);
			}                                                            
			adjustCenters();
			if(term) {
				exit = false;
			}
			else {
				t = t + 1;
			}
			if (t==500) {
				exit = false;
			}
		}
	}

	public static void main(String[] args) {

		DataGen gen2 = new DataGen();                               
		gen2.writeToFile();
		
		
		KMeansAlgorithm km3 = new KMeansAlgorithm(3);
		km3.loadDataFromFile("Data.txt");
		double localBest3 = Double.MAX_VALUE;
		Data[] centersTemp3 = new Data[3];
		for(int i = 0; i < 15; i++) {
			km3.runKMeans();
			double tempd = km3.calculateDispersion();
			if(localBest3 > tempd) {
				localBest3 = tempd;
				centersTemp3 = km3.getCenters();
				
			}
		}
		System.out.println("Best Dispersion with M = 3: "+localBest3+"\n");
		Plot plot3 = new Plot(centersTemp3,educationSet, "KMeans with M = 3");
		plot3.setVisible(true);


		KMeansAlgorithm km6 = new KMeansAlgorithm(6);
		km6.loadDataFromFile("Data.txt");
		double localBest6 = Double.MAX_VALUE;
		Data[] centersTemp6 = new Data[6];
		for(int i = 0; i < 15; i++) {
			km6.runKMeans();
			double tempd = km6.calculateDispersion();
			if(localBest6 > tempd) {
				localBest6 = tempd;
				centersTemp6 = km6.getCenters();
			}
		}
		System.out.println("Best Dispersion with M = 6: "+localBest6+"\n");
		Plot plot6 = new Plot(centersTemp6,educationSet, "KMeans with M = 6");
		plot6.setVisible(true);


		KMeansAlgorithm km9 = new KMeansAlgorithm(9);
		km9.loadDataFromFile("Data.txt");
		double localBest9 = Double.MAX_VALUE;
		Data[] centersTemp9 = new Data[9];
		for(int i = 0; i < 15; i++) {
			km9.runKMeans();
			double tempd = km9.calculateDispersion();
			if(localBest9 > tempd) {
				localBest9 = tempd;
				centersTemp9 = km9.getCenters();
				
			}
			
		}
		
		System.out.println("Best Dispersion with M = 9: "+localBest9+"\n");
		Plot plot9 = new Plot(centersTemp9,educationSet, "KMeans with M = 9");
		plot9.setVisible(true);


		KMeansAlgorithm km12 = new KMeansAlgorithm(12);
		km12.loadDataFromFile("Data.txt");
		double localBest12 = Double.MAX_VALUE;
		Data[] centersTemp12 = new Data[12];
		for(int i = 0; i < 15; i++) {
			km12.runKMeans();
			double tempd = km12.calculateDispersion();
			if(localBest12 > tempd) {
				localBest12 = tempd;
				centersTemp12 = km12.getCenters();
			}
		}
		System.out.println("Best Dispersion with M = 12: "+localBest12+"\n");
		Plot plot12 = new Plot(centersTemp12,educationSet, "KMeans with M = 12");
		plot12.setVisible(true);
	}

}
