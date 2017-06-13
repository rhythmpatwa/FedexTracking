package Tracking;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class packet_attr {
	
	double packageID;
	int source,destination,currentCity = 0;
	int speed = 100;
	int[] time = new int[20];
	ArrayList<Integer> nodes = new ArrayList<Integer>();
	ArrayList<Integer> distances = new ArrayList<Integer>();
	ArrayList<String> history = new ArrayList<String>();
	ArrayList<String> record = new ArrayList<String>();
	ArrayList<Integer> shortestPath = new ArrayList<Integer>();
	ArrayList<Integer> timeRequired = new ArrayList<Integer>();
	
	
	public packet_attr(double pid, int source, int destination){
		packageID = pid;
		this.source = source;
		this.destination = destination;
		this.currentCity = source;
	}
	
	@SuppressWarnings("unchecked")
	public void PackagePath() {
		Shortest_path city = new Shortest_path();
		
		shortestPath = city.getShortestPath(source, destination);
		
		timeRequired.clear(); 
		
		int sum = 0;
		
		for(int i = 0,len = shortestPath.size(); i < len; i++)
			nodes.add(shortestPath.get(i));
		
		for(int i = 0,len = shortestPath.size(); i < len-1; i++)
			distances.add(city.getMat(nodes.get(i),nodes.get(i+1)));
		
		for(int i = 0,len = shortestPath.size(); i < len-1; i++){
			sum = sum + (distances.get(i)/100);
			time[i] = sum;
			timeRequired.add(time[i]);
		}
	}	// end of method PackagePath

	public void UpdatePackage(int globalTime){
		Shortest_path city = new Shortest_path ();
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("'[(Date: 'MM/dd/yyy') Time:' HH:mm:ss')]'");
		Date date = new Date();
		
		int count = 0;
		int temp = globalTime - 1;
		
		history.clear();
		
		for(int i = count; i <= timeRequired.size(); i++){
			time[i] = time[i] - globalTime;
			
			if((time[i] == 0) || ((time[i] >= (-temp)) && (time[i] < 0))){
				record.add(dateFormat.format(date));
			}
			
			if(time[count] <= 0){
				currentCity = shortestPath.get(i);
				history.add(city.cityName(currentCity));
				count = i;
			}
		}
	}// caller package has been updated
}
