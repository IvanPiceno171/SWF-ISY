//Yeng, Ivan, and Stefin
//4.22.23
//This class is to construct a class which can hold and output data needed

package waterHack;
import java.io.File; 
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class stageHeightSlopes {
	
	//set up ArrayLists
		//List is used to store data straight from the Microsoft Excel
	private static ArrayList<waterHackClass> list = new ArrayList<waterHackClass>();
		//coordinateList is used to store coordinate points for slopes
	private static ArrayList<point> coordinateList = new ArrayList<point>();
		//slope list is used to store slopes
	private static ArrayList<integer> slopeList = new ArrayList<integer>();

	public static void main(String[] args) {
		//read all data
		Scanner input = null;
		try{ 
			input = new Scanner(new File("BearCreek_McKee_flow_eclipse.csv"));
		}catch(FileNotFoundException e){ 
			e.printStackTrace();
		}
		input.nextLine(); 

		//take all data, separate into categories: date, stageheight, flowrate
		while (input.hasNext()){
			String[] fields = input.nextLine().split(",");
			waterHackClass nextData = new waterHackClass();
			//seperates complete data and those without a flowrate
			if (fields[2].charAt(0) == ' ') {
				fields[2] = "null";
				nextData.badData(fields[0], Double.parseDouble(fields[1]), fields[2]);
				//nextData.strings();
				list.add(nextData);
			}else{
				nextData.data(fields[0], Double.parseDouble(fields[1]), Integer.parseInt(fields[2]));
				//nextData.string();
				list.add(nextData);
			}		
		}
		findSlope();
		int floodCounter = 0;
		for (int i = 1; i < slopeList.size()-1; i++) {
			if (slopeList.get(i-1).getSlope()-slopeList.get(i).getSlope() > 4) {
				floodCounter ++;
			}
			if (floodCounter > 1) {
				System.out.println("Get ready for a flood! Peak spike at: " + list.get(slopeList.get(i).getDay()).getDate());
				floodCounter = 0;
			}
		}
	}

	//function to find the slope of all data
	public static void findSlope() {
		//set up and local variables
		int coordinateTracker = 0;
		int i;
		point coordinate = new point();
		double lowPointX = list.get(0).getStageHeight();
		double lowPointY = 0;
		double highPointX = list.get(0).getStageHeight();
		double highPointY = 0;
		double slope;
		for (i = 1 ; i < list.size()-1; i++) {
			integer storage = new integer();
			double stageHeight = list.get(i).getStageHeight();
			double prevStageHeight = list.get(i-1).getStageHeight();

			//finds a corner, then keeps track of it
			while(prevStageHeight>=stageHeight && i < list.size()-1){
				i++;
				stageHeight = list.get(i).getStageHeight();
				prevStageHeight = list.get(i-1).getStageHeight();
			}
			coordinate.addPoints(prevStageHeight,i);
			coordinateList.add(coordinate);
			highPointX = coordinateList.get(coordinateTracker).getX();
			highPointY = coordinateList.get(coordinateTracker).getY();
			coordinateTracker++;
			//Slope formula using points
			slope = (lowPointX-highPointX)/(lowPointY-highPointY);
			//Storing slopes into ArrayList
			storage.putSlope(slope,i);
			slopeList.add(storage);
			//System.out.println(slope);

			//finds a corner, then keeps track of it
			while(prevStageHeight<=stageHeight && i < list.size()-1){
				i++;
				stageHeight = list.get(i).getStageHeight();
				prevStageHeight = list.get(i-1).getStageHeight();
			}
			storage = new integer();
			coordinate.addPoints(prevStageHeight,i);
			coordinateList.add(coordinate);
			lowPointX = coordinateList.get(coordinateTracker).getX();
			lowPointY = coordinateList.get(coordinateTracker).getY();
			coordinateTracker++;
			//Slope formula using points
			slope = (highPointX-lowPointX)/(highPointY-lowPointY);
			//Storing slopes into ArrayList
			storage.putSlope(slope,i);
			slopeList.add(storage);
			//System.out.println(slope);
		}
		//Tracks the last point down
		coordinate.addPoints(list.get(i-1).getStageHeight(),i);
		coordinateList.add(coordinate);
		lowPointX = coordinateList.get(coordinateTracker).getX();
		lowPointY = coordinateList.get(coordinateTracker).getY();
		coordinateTracker++;
		//Slope formula using points
		slope = (highPointX-lowPointX)/(highPointY-lowPointY);
		//Storing slopes into ArrayList
		integer storage = new integer();
		storage.putSlope(slope,i);
		slopeList.add(storage);
		//System.out.println(slope);
	}
}

