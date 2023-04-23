//Yeng, Ivan, and Stefin
//4.22.23
//This class is to construct a class which can hold and output data needed

package waterHack;

public class waterHackClass {
	//set up and global variables
	public String date;
	public double stageHeight;
	public double flowRate;
	public String flowRates;
	public double precipitation;
	
	//constructors
	public void data(String date, double stageHeight, double flowRate) {
		this.date = date;
		this.stageHeight = stageHeight;
		this.flowRate = flowRate;
	}
	
	public void pdata(String date, double precipitation) {
		this.date = date;
		this.precipitation = precipitation;
	}
	
	//constructor for "NaN" values
	public void badData(String date, double stageHeight, String flowRates) {
		this.date = date;
		this.stageHeight = stageHeight;
		this.flowRates = flowRates;
	}
	
	//setters and getters
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public double getStageHeight() {
		return stageHeight;
	}
	public void setStageHeight(int stageHeight) {
		this.stageHeight = stageHeight;
	}
	public double getFlowRate() {
		return flowRate;
	}
	public void setFlowRate(String flowRates) {
		this.flowRates = flowRates;
	}
	public String getFlowRates() {
		return flowRates;
	}
	public void setFlowRates(String flowRates) {
		this.flowRates = flowRates;
	}
	public double getPrecipitation() {
		return precipitation;
	}
	public void setPrecipitation(double precipitation) {
		this.precipitation = precipitation;
	}
	//One string for "NaN" and one for complete data
	public void string() {
		System.out.println("date: " + date + ", stageHeight: " + stageHeight + ", flowRate: " + flowRate);
	}
	public void strings() {
		System.out.println("date: " + date + ", stageHeight: " + stageHeight + ", flowRate: " + flowRates);
	}
	
	
}
