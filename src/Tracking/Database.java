package Tracking;

import java.sql.*;
import java.util.ArrayList;

public class Database{
	
	ArrayList<packet_attr> packagelist = new ArrayList<packet_attr>();
	
	ArrayList<Double>t_ID = new ArrayList<Double>();
	
	ArrayList<Integer>t_source = new ArrayList<Integer>();
	
	ArrayList<Integer>t_destination = new ArrayList<Integer>();
		
	public Database(){
		
	}
	
	public void dbconn(){
		
		try{
	      String myDriver = "com.mysql.jdbc.Driver";
	      String myUrl = "jdbc:mysql://localhost/FEDEX?useSSL=false";
	      Class.forName(myDriver);
	      Connection conn = DriverManager.getConnection(myUrl, "root", "Fall@2015");
	      Statement stat = conn.createStatement();
	      ResultSet oneRs = stat.executeQuery("select * from packages");
	      System.out.println("Connection Established!");
	      System.out.println("Fetching status for packages...");
	      	while (oneRs.next()){
	      		t_ID.add(oneRs.getDouble("id"));
	      		t_source.add(oneRs.getInt("source"));
	      		t_destination.add(oneRs.getInt("destination"));	      		
	        }
	      	System.out.println("Successful!!");
	      	stat.close();
	      	conn.close();
	      	System.out.println("Connection Closed!");
	    }
		
	    catch (Exception e){
	    	System.err.println("Got an exception! ");
	    	System.err.println(e.getMessage());
	    }		
	}

}