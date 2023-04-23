const stageHeightSlopes = `public class stageHeightSlopes {
	
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
		findSlope();
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
			System.out.println(slope);

			//finds a corner, then keeps track of it
			while(prevStageHeight<=stageHeight && i < list.size()-1){
				i++;
				stageHeight = list.get(i).getStageHeight();
				prevStageHeight = list.get(i-1).getStageHeight();
			}
			coordinate.addPoints(prevStageHeight,i);
			coordinateList.add(coordinate);
			lowPointX = coordinateList.get(coordinateTracker).getX();
			lowPointY = coordinateList.get(coordinateTracker).getY();
			coordinateTracker++;
			//Slope formula using points
			slope = (highPointX-lowPointX)/(highPointY-lowPointY);
			System.out.println(slope);
		}
		//Tracks the last point down
		coordinate.addPoints(list.get(i-1).getStageHeight(),i);
		coordinateList.add(coordinate);
		lowPointX = coordinateList.get(coordinateTracker).getX();
		lowPointY = coordinateList.get(coordinateTracker).getY();
		coordinateTracker++;
		//Slope formula using points
		slope = (highPointX-lowPointX)/(highPointY-lowPointY);
		System.out.println(slope);

	}
}`;

export default stageHeightSlopes;
