/**
 * @author Jennifer Storozum
 * COSI 21a PA3
 * 8/8/17
 * This is the driver class for the PA. It creates the hash table, min priority queue and reads the file.
 * Processes are enqueued and dequeued from the MPQ according to priority number.
 */
import java.io.File;
import java.util.Scanner;

public class ProcessMain {

	public static void main(String[] args) throws Exception {
		HashTable<Integer, Process> ht = new HashTable<Integer, Process>(2029);
		MinPriorityQueue mpq = new MinPriorityQueue(10);

		int idNo;
		int pVal;
		String pName;

		//read the file and create process objects
		File f = new File("Process.txt");
		Scanner input = new Scanner(f);
		while(input.hasNextLine()){
			idNo = input.nextInt();
			pVal = input.nextInt();
			pName = input.next();
			Process p = new Process(idNo, pVal, pName);

			//if the ID is already in the map, skip it
			//add all unique ID processes to the map and enqueue them
			if(!ht.contains(idNo)){
				ht.add(idNo, p);
				mpq.enqueue(p);
			}
		}

		//dequeue all the processes in priority order, print the process names
		//then delete from hashtable
		while(!mpq.isEmpty()){
			Process min = mpq.dequeue();
			int minID = min.getID();
			System.out.println(ht.get(minID).getName());
			ht.delete(minID);
		}
	}


}
