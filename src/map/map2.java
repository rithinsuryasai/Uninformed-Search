package map;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import javax.swing.text.html.HTMLDocument.Iterator;


/*
 * NAME: RITHIN SURYA SAINADH GADAPA
UTA-ID:1001565680
 * 
 */

public class map2 {


	public static void main(String[] args) {

		ArrayList<String> source = new ArrayList<String>();  //the source arraylist stores all the sources from the input.txt
		ArrayList<String> dest = new ArrayList<String>();    // the destination stores all destinations fro input file
		ArrayList<Double> miles = new ArrayList<Double>();	// distance between them

		Comparator<make> comparator = new Milescomparator();	//comparator for priority queue

		GetSucc succ = new GetSucc();


		HashMap<Integer, String> children = new HashMap<Integer, String>();    //hash map to get succesors

		ArrayList<String> closedset = new ArrayList<String>();   // closed set as in concept


		String startpoint =new String();	//source as in the input
		String endpoint =new String();		//destinantion as provided in output
		int pathcost=0;
		startpoint=args[1];		//source taken from input
		endpoint=args[2];
		try {
			File file = new File(args[0]);				//reading the file
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line = new String();

			while (!(line = bufferedReader.readLine()).equals("END OF INPUT")) {		//reading file line by line

				String extract[] = new String[100];
				extract = line.split(" "); 		//splitting the text input into three words

				source.add(extract[0]);		//adding the source from file to arraylist
				dest.add(extract[1]);		//adding the destinatio from file to arraylist
				miles.add(Double.parseDouble(extract[2]));

			}
			fileReader.close();
		}catch (IOException e) {
			e.printStackTrace();
		} catch(NullPointerException e) {
			e.printStackTrace();			
		}

/*Again repeaating the same file function to append the souce and destination arrays with each others
 * 
 * 
 */
		try {
			File file = new File(args[0]);
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line1 = new String();

			while (!(line1 = bufferedReader.readLine()).equals("END OF INPUT")) {

				String extract1[] = new String[100];
				extract1 = line1.split(" "); 

				source.add(extract1[1]);
				dest.add(extract1[0]);
				miles.add(Double.parseDouble(extract1[2]));

			}
			fileReader.close();
		}catch (IOException e) {
			e.printStackTrace();
		} catch(NullPointerException e) {
			e.printStackTrace();			
		}
		
		/*
		 * All the input from the file is in the three array lists.
		 */
		
		
		PriorityQueue<make> mainQueue = new PriorityQueue<make>(100,comparator);// priority queue with the distance comparator in other class file


		mainQueue.add(new make(startpoint));			//adding the source node to fringe

		do       {
			
			make present = (make)mainQueue.poll();		//node Remove-Front(fringe)
			closedset.add(present.state);				//adding the source node to closedset

			if (present.state.equalsIgnoreCase(endpoint))	//goal test  if Goal-Test(problem,State(node)) then return node and print path
			{			
				System.out.println("distance:"+ present.pathcost+" km");
				List<make> l=printPath(present);
				if(1==l.size()) { 
					System.out.println("distance: infinity\r\n" + 
							"route: \r\n" + 
							"none");
				}else {
					for (int p=0; p<l.size()-1; p++) {
						double d=0;
						for(int j=0; j<source.size();j++) {
							if ( (l.get(p).state.equalsIgnoreCase(source.get(j)))  && (l.get(p+1).state.equalsIgnoreCase(dest.get(j)))) {
								d= miles.get(j);
							}
						}
						System.out.println(l.get(p).state+" to "+l.get(p+1).state+","+d+" km");
					}

				}

				break;	
			}

			children = succ.getSuccessor(present.state, source, dest);		//to getsuccesors of the node removed from fringe


			Set set =children.entrySet();			//hashmap from the get succ method

			java.util.Iterator itr = set.iterator();	//retreiving the hash map 
			while(itr.hasNext()) {

				Map.Entry mentry=(Map.Entry)itr.next();
				int index= Integer.parseInt(mentry.getKey().toString());
				String child=(String)mentry.getValue();

				double path1= present.pathcost + miles.get(index);

				make c1= new make(child);
				c1.parent = present;
				c1.pathcost = path1;

				if (!closedset.contains(c1.state) && (!mainQueue.contains(child))) {
					mainQueue.add(c1);
				} else if((mainQueue.contains(c1.state)) && (c1.pathcost > ( present.pathcost + miles.get(index)))){

					c1.parent=present;
					c1.pathcost = present.pathcost + miles.get(index);
					mainQueue.remove(c1);
					mainQueue.add(c1);

				}

			} 

		}while(!mainQueue.isEmpty());

		if(mainQueue.isEmpty()) {			//if the goal state doesnot exist then it is this condition
			System.out.println("distance: infinity\r\n" + 
					"route: \r\n" + 
					"none");
		}

	}

	public static List<make>  printPath(make goal){			//this function prints the path after reaching the goal state
		List<make> path = new ArrayList<make>();
		for(make node = goal; node!=null; node = node.parent){
			path.add(node);
		}

		Collections.reverse(path);


		return path;

	}
}







