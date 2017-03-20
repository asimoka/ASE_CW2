package ASE_CW2;

import java.lang.management.ThreadInfo;
import java.util.ArrayList;

public class Kiosk extends Thread {
	
	private ArrayList<Taxi> TaxiList;
	private ArrayList<PassengerGroup> PassengerGroupList;
	
	public Kiosk(ArrayList<Taxi> TaxiList, ArrayList<PassengerGroup> PassengerGroupList){
		this.TaxiList=TaxiList;
		this.PassengerGroupList=PassengerGroupList;
	}
	
		
	/*the method that returns the index of the taxi 
	 * that has enough seats for the 
	 * first passenger group in the queue*/
	
	public int taxiIndexWithEnoughSeats()
	{
		for (int i=0;i<=TaxiList.size();i++)
		{
			if (PassengerGroupList.get(0).getPassengersNumber()<=TaxiList.get(i).getMaxPassengersNumber()){return i;}
		}
		return -1;
	}
	
	
	/*the method that allocates passenger groups
	 * with the taxi that is available and has
	 * enough seats for this group*/ 
	
	public synchronized void  run(){
		String output="";
		try {
			
			
			
			while ((TaxiList.size()>0) && (PassengerGroupList.size()>0))
			{
				double rand=Math.random()*500;
				int randInt = (int)rand;
				Thread.sleep(randInt);
				if (taxiIndexWithEnoughSeats()!=-1)
				{
				output="\nGroup:  "+PassengerGroupList.get(0).getGroupName()+ "\nDestination " + PassengerGroupList.get(0).getDestination()+"\nPassengers:  "
						+PassengerGroupList.get(0).getPassengersNumber()+
						"\nTaxi No: "+TaxiList.get(taxiIndexWithEnoughSeats()).getPlateNumber();
				System.out.print(output);
				System.out.print(Thread.currentThread()+"\n\n");
				TaxiList.remove(taxiIndexWithEnoughSeats());
				PassengerGroupList.remove(0);
				}
				
			}
		} 
		catch (InterruptedException e) {
			System.out.println(e.getMessage());
			}
		catch (IndexOutOfBoundsException e) {
			output="\n\n"+PassengerGroupList.size()+" passenger groups left in the queue"+TaxiList.size()+" taxis are still available";
			System.out.print("No enough parameters");
		} 
		if (TaxiList.isEmpty()) {
			output="\n\nNo available taxis at the moment.  \n"+PassengerGroupList.size()+" passenger groups left in the queue";
			
		} 
		else if (PassengerGroupList.isEmpty()) {
			output="\n\nNo Passengers in the queue.  \n"+TaxiList.size()+" taxis are still available";
		}
		System.out.print(output);
	}
	
	

	
}
