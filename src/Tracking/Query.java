package Tracking;

import java.util.ArrayList;


public class Query extends Thread{
	Shortest_path l = new Shortest_path();
	
	ArrayList<packet_attr> packagelist = new ArrayList<packet_attr>();
	
	int IDnum = 0;
	
	public Query(ArrayList<packet_attr> pack, int ID) {
		IDnum = ID-1;
		packagelist = pack;
	}
	
	public void run(){
	    String city = new String();
	    String destination = new String();
	    String source = new String();
	    
	    source = l.cityName(packagelist.get(IDnum).source);
	    city = l.cityName(packagelist.get(IDnum).currentCity);
	    destination = l.cityName(packagelist.get(IDnum).destination);
	    
	    System.out.print("SOURCE CITY: " + source + " [Out For Delivery]\t\t");
	    System.out.println("DESTINATION CITY: " + destination + "\n");
	    System.out.println("------------------------------Package History------------------------------");
	    
	    for(int i = 0; i < packagelist.get(IDnum).history.size(); i ++){
	    	System.out.print(packagelist.get(IDnum).history.get(i) + "\t\t\t");
	    	if(i < packagelist.get(IDnum).record.size())
	    		System.out.println(packagelist.get(IDnum).record.get(i));
	    }
	    /*for(int i = 0; i < packagelist.get(IDnum).history.size(); i ++){
	    	System.out.println(packagelist.get(IDnum).record.get(i));
	    }*/
	    
	    System.out.println("---------------------------------------------------------------------------");
	    System.out.println("CURRENT CITY: " + city);
	    
	    if(city == destination){
	        System.out.println("STATUS: DELIVERED TO " + destination);
	    }
	    else{
	    	System.out.println("STATUS: IN TRANSIT [Last Update: Reached " + city + "]");
	    }
	    
	    System.out.println("---------------------------------------------------------------------------");
	    System.out.println("---------------------------------------------------------------------------\n\n");
	}

}
