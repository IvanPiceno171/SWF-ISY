//Yeng, Ivan, and Stefin
//4.22.23
//This class is to construct a class which can hold and output data needed

package waterHack;
import java.io.File; 
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class flowRateSlopes {
	
	//set up ArrayLists
	private static ArrayList<waterHackClass> list = new ArrayList<waterHackClass>();
	private static ArrayList<point> coordinateList = new ArrayList<point>();

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
		clean(list);
		findSlope();
	}

	public static void clean(ArrayList<waterHackClass> lists ) {
		for (int i = 0 ; i < lists.size(); i++) {
			double newValue = 0;
			int j = 0;
			waterHackClass data = lists.get(i);
			waterHackClass nextData = new waterHackClass();
			if (data.getFlowRate() == 10000) {
				//small section makes sure that other values of 1000 aren't counted
				while(lists.get(j).getFlowRate() == 10000) {
					j++;
				}
				newValue = (lists.get(i-1).getFlowRate() + lists.get(j).getFlowRate())/2;
				nextData.data(data.getDate(), data.getFlowRate(), newValue);
				lists.set(i, nextData);
			}
		}
	}	
	//function to find the slope of all data
	public static void findSlope() {
		//set up and local variables
		int coordinateTracker = 0;
		int i;
		point coordinate = new point();
		double lowPointX = list.get(0).getFlowRate();
		double lowPointY = 0;
		double highPointX = list.get(0).getFlowRate();
		double highPointY = 0;
		double slope;

		for (i = 1 ; i < list.size()-1; i++) {
			double flowRate = list.get(i).getFlowRate();
			double prevFlowRate = list.get(i-1).getFlowRate();

			//finds a corner, then keeps track of it
			while(prevFlowRate>=flowRate && i < list.size()-1){
				i++;
				flowRate = list.get(i).getFlowRate();
				prevFlowRate = list.get(i-1).getFlowRate();
			}
			coordinate.addPoints(prevFlowRate,i);
			coordinateList.add(coordinate);
			highPointX = coordinateList.get(coordinateTracker).getX();
			highPointY = coordinateList.get(coordinateTracker).getY();
			coordinateTracker++;
			//Slope formula using points
			slope = (lowPointX-highPointX)/(lowPointY-highPointY);
			System.out.println(slope);

			//finds a corner, then keeps track of it
			while(prevFlowRate<=flowRate && i < list.size()-1){
				i++;
				flowRate = list.get(i).getFlowRate();
				prevFlowRate = list.get(i-1).getFlowRate();
			}
			coordinate.addPoints(prevFlowRate,i);
			coordinateList.add(coordinate);
			lowPointX = coordinateList.get(coordinateTracker).getX();
			lowPointY = coordinateList.get(coordinateTracker).getY();
			coordinateTracker++;
			//Slope formula using points
			slope = (highPointX-lowPointX)/(highPointY-lowPointY);
			System.out.println(slope);
		}
		//Tracks the last point down
		coordinate.addPoints(list.get(i-1).getFlowRate(),i);
		coordinateList.add(coordinate);
		lowPointX = coordinateList.get(coordinateTracker).getX();
		lowPointY = coordinateList.get(coordinateTracker).getY();
		coordinateTracker++;
		//Slope formula using points
		slope = (highPointX-lowPointX)/(highPointY-lowPointY);
		System.out.println(slope);

	}
}

