//Yeng, Ivan, and Stefin
//4.22.23
//This class is to construct a class which can hold and output data needed

package waterHack;
import java.io.File; 
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class precipitationSlopes {

	//set up ArrayLists
	private static ArrayList<waterHackClass> list = new ArrayList<waterHackClass>();
	private static ArrayList<point> coordinateList = new ArrayList<point>();

	public static void main(String[] args) {
		//read all data
		Scanner input = null;
		try{ 
			input = new Scanner(new File("BearCreek_precipitation.csv"));
		}catch(FileNotFoundException e){ 
			e.printStackTrace();
		}
		input.nextLine(); 

		//take all data, separate into categories: date and precipitation
		while (input.hasNext()){
			String[] fields = input.nextLine().split(",");
			waterHackClass nextData = new waterHackClass();
			nextData.pdata(fields[0], Double.parseDouble(fields[1]));
			//nextData.string();
			list.add(nextData);	
		}
		findSlope();
	}
	//function to find the slope of all data
	public static void findSlope() {
		//set up and local variables
		int coordinateTracker = 0;
		int i;
		point coordinate = new point();
		double lowPointX = list.get(0).getPrecipitation();
		double lowPointY = 0;
		double highPointX = list.get(0).getPrecipitation();
		double highPointY = 0;
		double slope;

		for (i = 1 ; i < list.size()-1; i++) {
			double precipitation = list.get(i).getPrecipitation();
			double prevPrecipitation = list.get(i-1).getPrecipitation();

			//finds a corner, then keeps track of it
			while(prevPrecipitation>=precipitation && i < list.size()-1){
				i++;
				precipitation = list.get(i).getPrecipitation();
				prevPrecipitation = list.get(i-1).getPrecipitation();
			}
			coordinate.addPoints(prevPrecipitation,i);
			coordinateList.add(coordinate);
			highPointX = coordinateList.get(coordinateTracker).getX();
			highPointY = coordinateList.get(coordinateTracker).getY();
			coordinateTracker++;
			//Slope formula using points
			slope = (lowPointX-highPointX)/(lowPointY-highPointY);
			System.out.println(slope);

			//finds a corner, then keeps track of it
			while(prevPrecipitation <= precipitation && i < list.size()-1){
				i++;
				precipitation = list.get(i).getPrecipitation();
				prevPrecipitation = list.get(i-1).getPrecipitation();
			}
			coordinate.addPoints(prevPrecipitation,i);
			coordinateList.add(coordinate);
			lowPointX = coordinateList.get(coordinateTracker).getX();
			lowPointY = coordinateList.get(coordinateTracker).getY();
			coordinateTracker++;
			//Slope formula using points
			slope = (highPointX-lowPointX)/(highPointY-lowPointY);
			System.out.println(slope);
		}
		//Tracks the last point down
		coordinate.addPoints(list.get(i-1).getPrecipitation(),i);
		coordinateList.add(coordinate);
		lowPointX = coordinateList.get(coordinateTracker).getX();
		lowPointY = coordinateList.get(coordinateTracker).getY();
		coordinateTracker++;
		//Slope formula using points
		slope = (highPointX-lowPointX)/(highPointY-lowPointY);
		System.out.println(slope);

	}
}

