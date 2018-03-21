package map;

/*
 * NAME: RITHIN SURYA SAINADH GADAPA
UTA-ID:1001565680
 * 
 */
import java.util.Comparator;

public class Milescomparator implements Comparator<make>
	{
	    @Override
	    public int compare(make x, make y)
	    {
	      
	        if (x.pathcost < y.pathcost)
	        {
	            return -1;
	        }
	        if (x.pathcost > y.pathcost)
	        {
	            return 1;
	        }
	        return 0;
	    }
	}
	
