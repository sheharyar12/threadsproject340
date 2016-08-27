import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Title : Assistant class
 * Description: Assistant class will determine if the patients need a shot and if they do 
 * they will take care of each patient when they come to doctors office in orderly manner way.
 * @author Shehar Yar
 *
 */

public class assistant extends Thread{
	
	//instance variable 
	private String name;
	public boolean aIsFree = true;
	private Thread t;
	public int sP;
	public Queue<patients> patientQ = new LinkedList<patients>();
	public static boolean loopbreak=false;
	

	
	
	
	/**
	 * Title : assistant
	 * Description: This constructor creates the assistant and give each 
	 * assistant its own name and makes sure if the thread is free or not.
	 * @param name : assistants name 
	 * @param num : assistants number if more the one thread created. 
	 */
	public assistant(String name , int num) {
		this.name = name + " " +num;
		aIsFree = true;	
	}
	
	/**
	 * Title :removePinQueue
	 * Description : Removes patients from the patient queue.
	 * @return s the top of the patient that was removed.
	 */
	public synchronized patients removePinQueue()
	{
		return patientQ.remove();
	}
	
	/**
	 * Title : getAssistantName
	 * Description: returns the name of the assistant.
	 * @return the name of the assistant.
	 */
	public String getAssistantName()
	{
		return this.name;
	}

	/**
	 * Title: InttrruptAssistant
	 * Description: interrupts the tread of the assistant. called from the thread function.
	 */
	public void IntrruptAssistant()
	{
		t.interrupt();
	}
	

	/**
	 * Title: run
	 * Description: Overriding the run method in the thread class so the assistant
	 * can take the patients information down and then the doctor will be interrupted to 
	 * decide if they need a shot or not. 
	 */
	public void run()
	{
		while(patients.counter!=main.mypatients.length-1){}
		
		loopbreak:
		while(true)
		{
			
			for(int i =1;i<main.mypatients.length;i++)
			{
	
				this.aIsFree=false;
				if(main.mypatients[i].getSick()==true)
				{
					try {
						main.mypatients[i].setPatientOkay();
						sleep(200);
						System.out.println(Thread.currentThread().getName() + ": " + "Takes " + main.mypatients[i].getPatientsName() +" Information Down");
						System.out.println(main.mypatients[i].getPatientsName() + " is ready for consultation");
						main.mypatients[i].needShot=true;
						main.myDoctor.interrupt();
						if(main.mypatients[i].needShot==false)
						{
							System.out.println(main.mypatients[i].getPatientsName() + " sits back in waiting room for Doctor");
						}
						patients.p++;
						
						
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					//working on
					
				}
				this.aIsFree=true;
			}
			if(loopbreak==true)
			{
				break loopbreak;
			}
			
		}
		
		
		
			
	}
	
	/**
	 * Title: start
	 * Description : this overwrites the start in the thread class so it can give each thread its own name by number.
	 */
	public void start ()
	{
		
	      System.out.println("Starting " +  name);
	      if (t == null)
	      {
	         t = new Thread (this, name);
	         t.start ();
	      }
	}

}
