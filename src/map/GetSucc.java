package map;
/*
 * UTA-ID:1001565680

 * NAME: RITHIN SURYA SAINADH GADAPA
 */
import java.util.ArrayList;
import java.util.HashMap;

public class GetSucc {
	public HashMap<Integer, String> getSuccessor(String state, ArrayList<String> source, ArrayList<String> dest ) {

		HashMap<Integer, String> result = new HashMap<Integer, String>();
		for (int i = 0; i < source.size(); i++) {
			if( source.get(i).equalsIgnoreCase(state)) {
				result.put(i,dest.get(i));
			}
		}
		return result;
	}
}




