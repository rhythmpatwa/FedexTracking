package Tracking;


import java.util.ArrayList;



public class Simulator extends Thread {
	ArrayList<packet_attr> packagelist = new ArrayList<packet_attr>();
	
	int i = 0;
	
	public Simulator(ArrayList<packet_attr> pack) {
		
		packagelist = pack;
	}
	
	public void run(){
		while(true){
			try{
				Thread.sleep(1000);
			}
			catch(InterruptedException ex){
				Thread.currentThread().interrupt();
			}
			
			for(int i =0; i < packagelist.size(); i++){
				packagelist.get(i).UpdatePackage(1);
			}
			
		}
		
	}
}
