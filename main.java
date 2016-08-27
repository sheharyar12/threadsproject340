import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
/**
 * Title : Project 1 
 * Description: This project takes value of patients, assistants and if the patient ate too much candy
 * they will go to doctors office and there are two assistants handling each patient and doctor decides
 * randomly for each patient that came in if they get a shot or not. if the patient gets a shot then 
 * he/she will get a shot and wait in the waiting room. after the patients have received a shot one by one 
 * then doctor will come and give his speech . After the speech the patients will leave in order.
 * @author Shehar yar CS340 PROJECT 1
 * 
 *
 */

public class main {

	
	// instance variable for patient, doctor, and assistant
	private static int num_p;
	private static int num_a;
	public static patients[] mypatients;
	public static assistant[] myassistant;
	public static doctor myDoctor;
	
	

	
	public static void main(String[] args) throws InterruptedException {
		
		// accepts args values for patients and assistants
		num_p = Integer.valueOf(args[0]);
		num_a = Integer.valueOf(args[1]);
		mypatients = new patients[num_p+1];
		myassistant = new assistant[num_a+1];
		
		
		
		// starting patients threads, assistants and doctor.
		for(int i =1; i<=num_p;i++)
		{
			mypatients[i] = new patients("Patient",i);
			mypatients[i].start();

		}	
		for(int i=1;i<=num_a;i++)
		{
			myassistant[i] = new assistant("Assistant",i);
			myassistant[i].start();
			
		}
		
		myDoctor = new doctor("Doctor",1);
		myDoctor.start();
		
		
		
		
		
		

	}


}
