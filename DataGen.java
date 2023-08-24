/*
 * KONSTANTINOS KIKIDIS (4387) 
 * CHRISTOS KROKIDAS (4399) 
 * KONSTANTINOS TSAMPIRAS (4508)
 */

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Random;


public class DataGen {

	private Random randomgen;
	private Data[] dataSet1;
	private Data[] dataSet2;
	private Data[] dataSet3;
	private Data[] dataSet4;
	private Data[] dataSet5;
	private Data[] dataSet6;
	private Data[] dataSet7;
	private Data[] dataSet8;
	private Data[] dataSet9;
	private Data[] dataSet10;
	
	public DataGen() {
		randomgen = new Random();
		dataSet1 = new Data[150];
		dataSet2 = new Data[150];
		dataSet3 = new Data[150];
		dataSet4 = new Data[150];
		dataSet5 = new Data[150];
		dataSet6 = new Data[75];
		dataSet7 = new Data[75];
		dataSet8 = new Data[75];
		dataSet9 = new Data[75];
		dataSet10 = new Data[150];
		createDataSets();
	}
	
	private void createDataSets() {
		double r1,r2;
		// dataset1 : [0.8, 1.2] x [0.8, 1.2] 
		for(int i = 0; i < 150; i++) {
			r1 = randomgen.nextDouble();
			double x1 = 0.8*r1 + (1-r1)*1.2;
			r2 = randomgen.nextDouble();
			double x2 = 0.8*r2 + (1-r2)*1.2;
			dataSet1[i] = new Data(x1,x2);
			//System.out.println(dataSet1[i]);
		}
		// dataset2 : [0, 0.5] x [0, 0.5]
		for(int i = 0; i < 150; i++) { 
			r1 = randomgen.nextDouble();
			double x1 = 0*r1 + (1-r1)*0.5;
			r2 = randomgen.nextDouble();
			double x2 = 0*r2 + (1-r2)*0.5;
			dataSet2[i] = new Data(x1,x2);
			//System.out.println(dataSet2[i]);
		}
		// dataset3 : [0,0.5] x [1.5,2]
		for(int i = 0; i < 150; i++) { 
			r1 = randomgen.nextDouble();
			double x1 = 0*r1 + (1-r1)*0.5;
			r2 = randomgen.nextDouble();
			double x2 = 1.5*r2 + (1-r2)*2;
			dataSet3[i] = new Data(x1,x2);
			//System.out.println(dataSet3[i]);
		}
		// dataset4 : [1.5,2] x [0,0.5]  
		for(int i = 0; i < 150; i++) {
			r1 = randomgen.nextDouble();
			double x1 = 1.5*r1 + (1-r1)*2;
			r2 = randomgen.nextDouble();
			double x2 = 0*r2 + (1-r2)*0.5;
			dataSet4[i] = new Data(x1,x2);
			//System.out.println(dataSet4[i]);
		}
		// dataset5 : [1.5,2] x [1.5,2]
		for(int i = 0; i < 150; i++) { 
			r1 = randomgen.nextDouble();
			double x1 = 1.5*r1 + (1-r1)*2;
			r2 = randomgen.nextDouble();
			double x2 = 1.5*r2 + (1-r2)*2;
			dataSet5[i] = new Data(x1,x2);
			//System.out.println(dataSet5[i]);
		}
		// dataset6 : [0.8, 1.2] x [0, 0.4] 
		for(int i = 0; i < 75; i++) {
			r1 = randomgen.nextDouble();
			double x1 = 0.8*r1 + (1-r1)*1.2;
			r2 = randomgen.nextDouble();
			double x2 = 0*r2 + (1-r2)*0.4;
			dataSet6[i] = new Data(x1,x2);
			//System.out.println(dataSet6[i]);
		}
        // dataset7 : [0.8, 1.2] x [1.6,2]
		for(int i = 0; i < 75; i++) { 
			r1 = randomgen.nextDouble();
			double x1 = 0.8*r1 + (1-r1)*1.2;
			r2 = randomgen.nextDouble();
			double x2 = 1.6*r2 + (1-r2)*2;
			dataSet7[i] = new Data(x1,x2);
			//System.out.println(dataSet7[i]);
		}
        // dataset8 : [0.3,0.7] x [0.8, 1.2]
		for(int i = 0; i < 75; i++) { 
			r1 = randomgen.nextDouble(); 
			double x1 = 0.3*r1 + (1-r1)*0.7;
			r2 = randomgen.nextDouble();
			double x2 = 0.8*r2 + (1-r2)*1.2;
			dataSet8[i] = new Data(x1,x2);
			//System.out.println(dataSet8[i]);
		}
        // dataset9 : [1.3,1.7] x [0.8, 1.2]
		for(int i = 0; i < 75; i++) { 
			r1 = randomgen.nextDouble();
			double x1 = 1.3*r1 + (1-r1)*1.7;
			r2 = randomgen.nextDouble();
			double x2 = 0.8*r2 + (1-r2)*1.2;
			dataSet9[i] = new Data(x1,x2);
			//System.out.println(dataSet9[i]);
		}
		// dataset10 : [0,2] x [0,2] 
		for(int i = 0; i < 150; i++) {               
			r1 = randomgen.nextDouble();
			double x1 = 0*r1 + (1-r1)*2;
			r2 = randomgen.nextDouble();
			double x2 = 0*r2 + (1-r2)*2;
			dataSet10[i] = new Data(x1,x2);
			//System.out.println(dataSet10[i]);
		}
		
		
	}
	
	
	public void writeToFile() {
		PrintWriter out = null;
		try
		{
		out = new PrintWriter(new FileOutputStream("Data.txt"));
		}
		catch(FileNotFoundException e)
		{
		System.out.println("Error opening the file Data.txt");
		System.exit(0);
		}
		for(int i = 0; i < 150; i++) {
			out.println(dataSet1[i]);
		}
		for(int i = 0; i < 150; i++) {
			out.println(dataSet2[i]);
		}
		for(int i = 0; i < 150; i++) {
			out.println(dataSet3[i]);
		}
		for(int i = 0; i < 150; i++) {
			out.println(dataSet4[i]);
		}
		for(int i = 0; i < 150; i++) {
			out.println(dataSet5[i]);
		}
		for(int i = 0; i < 75; i++) {
			out.println(dataSet6[i]);
		}
		for(int i = 0; i < 75; i++) {
			out.println(dataSet7[i]);
		}
		for(int i = 0; i < 75; i++) {
			out.println(dataSet8[i]);
		}
		for(int i = 0; i < 75; i++) {
			out.println(dataSet9[i]);
		}
		for(int i = 0; i < 150; i++) {
			out.println(dataSet10[i]);
		}
		out.close( );
	}	
}
