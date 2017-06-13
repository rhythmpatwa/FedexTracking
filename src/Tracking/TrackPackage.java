package Tracking;

import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.NoSuchElementException;
import java.sql.*;
import java.text.SimpleDateFormat;

public class TrackPackage{

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		int ID = 0;
		packet_attr p;
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("'[(Date: 'MM/dd/yyy') Time:' HH:mm:ss')]'");
		Date date = new Date();
		
		ArrayList<packet_attr> packagelist = new ArrayList<packet_attr>();
		
		Database d = new Database();
		System.out.println("Initializing connection with server...");
		d.dbconn();
		
		Iterator<Double> i1 = d.t_ID.iterator();
		Iterator<Integer> i2 = d.t_source.iterator();
		Iterator<Integer> i3 = d.t_destination.iterator();
		System.out.println("Retrieving package status...");
		while(i1.hasNext()){
			p = new packet_attr(i1.next(),i2.next(),i3.next());
			p.PackagePath();
			packagelist.add(p);
		}
		System.out.println("Successful!!\n");
		
		for(int i = 0; i < packagelist.size() ; i++){
			packagelist.get(i).record.add(dateFormat.format(date));
		}
		
		Simulator simulator = new Simulator(packagelist);
		simulator.start();
		
		while(true){
			try{
				Thread.sleep(1000);
			}
			catch(InterruptedException ex){
				Thread.currentThread().interrupt();
			}
			System.out.println("FEDEX: Track Your Package Status");
			System.out.println("--------------------------------\n");
			Scanner input = new Scanner(System.in);
			System.out.print("Enter ID:");
			//ID = input.nextInt();
			try{
				ID = input.nextInt();
			}
			catch(InputMismatchException e){
				System.out.println("Tracking ID should be an integer\n\n");
			}
			if((ID > 0)&&(ID < 100000)){
				Query query = new Query(packagelist,ID);
				query.start();
			}
			else
				System.out.println("Enter a valid tracking ID\n\n");
		}
	}// end of main	
}
