package map;

/*
 * NAME: RITHIN SURYA SAINADH GADAPA
UTA-ID:1001565680
 * 
 */

public class make {	//to make nodes eith parent , state and pathcost

	String state;

	make parent ;
	double pathcost;

	
	 public make(String source)
     {
         this.state = source;
    
     }
	 
	 public String tostring() {
		 return state;
	 }
	
	
}
	 