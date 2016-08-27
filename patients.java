import java.util.Random;
import java.util.Stack;


/**
 * Title: patients class
 * Description : Each patient comes to a party and eat candy. There is a thres hold which is 35.
 * If the patient eats more then the threshold then they will go to doctors office and wait for 
 * assistant and doctor.
 * @author Shehar Yar
 *
 */
public class patients extends Thread{
	
	// instance variables.
	private int num_candy;
	private int candy_threshold = 35;
	private String name;
	private Thread t;
	public static int p = 0;
	public static int sickP = 0;
	private boolean sick;
	public static int counter = 0;
	private boolean waitsForDoctor = false;
	public int patientsNumber;
	public boolean needShot = false;
	
	/**
	 * Title : patients
	 * Description : each patient gets a name and a number
	 * @param name
	 * @param num
	 */
	public patients(String name, int num)
	{
		this.name = name + "-" + num;
		sick = false;
		patientsNumber = num;
		waitsForDoctor = false;
		needShot = false;
	}
	
	// This functions below are mutator functions and accesor functions that are being over written so they can be accessed from different classes when patient is called.
	public boolean isWaitsForDoctor() {return waitsForDoctor;}
	public void setWaitsForDoctor(boolean waitsForDoctor) {this.waitsForDoctor = waitsForDoctor;}
	public String getPatientsName(){return this.name;}	
	public boolean returnValues(){return sick;}	
	public boolean getSick(){return this.sick;}	
	public void setPatientOkay(){this.sick = false;}
	public boolean alive(){return t.isAlive();}
	public void joinThread() throws InterruptedException{t.join();}

	
	
	

	/**
	 * Title : run
	 * Description: overwritting the run function in thread class.
	 * each thread will run and eat a candy. If more then 35 candy eaten then the patient will get tummy ache
	 * and they will wait for assistant to take care of them and then wait for doctor. If they didnt eat more
	 * then the threshold candy , the thread will simply exit.
	 */
	public void run()
	{
		System.out.println("Running " +  Thread.currentThread().getName() + " ");
		
		
	    int num = ateCandy();
	    System.out.println(Thread.currentThread().getName() + " ate  " + num + " candies");
	    
	    if(num >= candy_threshold)
	    {
	    	
	    	this.sick = true;
	    	this.setWaitsForDoctor(true);
	    	System.out.println(Thread.currentThread().getName() + " has a tummy ache and arrives at doctor's office");
	    	System.out.println(Thread.currentThread().getName() + " waits for assistance");
	    	synchronized(this)
	    	{
	    		counter++;
	    		//sickP++;
	    		try {
					sleep(200);
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	
	    	}	
	    	while(this.sick==true){}
	    	while(this.isWaitsForDoctor()==true){}
	    	System.out.println(Thread.currentThread().getName() + " leaves office");
	    }
	    else
	    {
	    	synchronized(this)
	    	{
	    		counter++;
	    		System.out.println(Thread.currentThread().getName() + " doesnt eat more then 35 candies");
	    	}
	    	
	    }
	    System.out.println(Thread.currentThread().getName() + " leaves the doctors office after speach");
	}
	
	
	


	/**
	 * Title : ateCandy
	 * Description: Generates a random number from 0-100
	 * @return s the number that is generated as an integer
	 */
	public int ateCandy()
	{
		Random randomGenerator = new Random();
	    num_candy = randomGenerator.nextInt(100);
	    return num_candy;
	}
	
	/**
	 * Title : Start
	 * Description: starts the thread and overwrites the original in the thread class and each
	 * thread gets a name.
	 */
	public void start ()
	  {
		
	      
	      p++;
	      System.out.println("Starting " +  name );
	      if (t == null)
	      {
	         t = new Thread (this, name);
	         t.start ();
	      }
	  }
	

	

	
	

	
	
}
