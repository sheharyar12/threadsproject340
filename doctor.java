import java.util.Random;
import java.util.Stack;


/**
 * Title: doctor Class
 * Description: The doctors main job is to see if the patients need a shot or not and 
 * after giving each patient a shot they will get a speach from the doctor.
 * @author Shehar yar
 *
 */
public class doctor extends Thread{

	//instant varibale for the doctor class as needed.
	private String name;
	private Thread t;
	public static int treatedp = 0;
	public volatile Stack<patients> myStack = new Stack<patients>();
	public static boolean speach = false;
	public long sleepValue = 500;
	
	
	/**
	 * Title : doctor
	 * Description : doctor constructor which gives the doctor its name and if he gave a speech or not.
	 * @param name
	 * @param num
	 */
	public doctor(String name , int num) {

		this.name = name + " " +num;
		treatedp =0;
		speach=false;
		
	}
	
	/**
	 * Title: rearangeStack
	 * Description: since we used a stack this re orders a stack to have a first come first served 
	 * based manner.
	 */
	public void rearangeStack()
	{
		patients[] tempPatients = new patients[treatedp];
		for(int i =0;i<treatedp;i++)
		{
			tempPatients[i] = myStack.pop();
		}
		for(int i=0;i<treatedp;i++)
		{
			
			myStack.push(tempPatients[i]);
		}
	}
	
	/**
	 * Title: needsAshot
	 * Description: This decides if the patient needs a shot or not in 50/50 chance.
	 * @return an boolean if num = 1 so its true he gets a shot.
	 */
	public boolean needsAshot()
	{
		Random randomGenerator = new Random();
	    int num = randomGenerator.nextInt(2);
	    if(num ==1)
	    {
	    	return true;
	    }
	    else
	    	return false;
	}
	
	/**
	 * Title: treatedp
	 * Description: increments treatedp for treated patients so no other threads can acces this 
	 * variable at the same time because its synchronized.
	 */
	public synchronized void treatedp()
	{
		treatedp++;
	}
	
	/**
	 * Title: interrupt
	 * Description: calling inttrupt on the main thread.
	 */
	public void interrupt()
	{
		t.interrupt();
	}
	
	/**
	 * Title : run
	 * Description: run is being over written, it sleeps over and over until its interrupted by the assistant, then the 
	 * doctor will know if he or she needs a shot 
	 */
	public void run()
	{
		//while loop 
		loop1:
		while(true)
		{
			try {
				t.sleep(500);
				break loop1;
				
			} catch (InterruptedException e) 
			{
				for(int i=1;i<=main.mypatients.length-1;i++)
				{
				
						if(needsAshot()==true)
		        		{
							if(main.mypatients[i].needShot==true)
							{
								myStack.push(main.mypatients[i]);
								System.out.println(main.mypatients[i].getPatientsName() + " needs a shot");
								System.out.println(main.mypatients[i].getPatientsName() + " gets a shot");
								main.mypatients[i].needShot=false;
								this.treatedp();
							}
		        		}
						else
						{
							main.mypatients[i].needShot=false;
						}
				}
						
			}
				
			}//endwhile
	
			
			System.out.println("Doctor gives speach to the patients that are in the waiting room  about how bad candies and sugar are to our health");
			try {
				sleep(300);
				//t.sleep(sleepValue);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			main.myDoctor.rearangeStack();
			
			for(int i = 0;i < treatedp;i++)
			{
			
				if(myStack.peek().alive())
				{	
					try {
						myStack.peek().setWaitsForDoctor(false);
						myStack.pop().joinThread();
						
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				
			}
			
			for(int i=1;i<main.myassistant.length;i++)
			{
				main.myassistant[i].loopbreak=true;
			}
			for(int i=1;i<main.mypatients.length;i++)
			{
				main.mypatients[i].setWaitsForDoctor(false);
			}
	}
	
	
	
	/**
	 * Title : start
	 * Description: Overwrites the start in thread to give each doctor a thread name and number if more then one doctor
	 * main can be modified.
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
