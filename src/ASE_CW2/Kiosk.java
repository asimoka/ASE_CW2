package ASE_CW2;

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
	
	/* the method that allocates passenger groups
	 * with the taxi that is available and has
	 * enough seats for this group*/ 
	
	public void run(){
		String output="";
		try {
			while ((TaxiList.size()>0) && (PassengerGroupList.size()>0))
			{
				if (taxiIndexWithEnoughSeats()!=-1)
				{
				output="\nDestination:  "+PassengerGroupList.get(0).getDestination()+"\nPassengers:  "
						+PassengerGroupList.get(0).getPassengersNumber()+
						"\nTaxi No: "+TaxiList.get(taxiIndexWithEnoughSeats()).getPlateNumber();
				System.out.print(output);
				TaxiList.remove(taxiIndexWithEnoughSeats());
				PassengerGroupList.remove(0);
				}
			}
		} 
		catch (IndexOutOfBoundsException e) {
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
