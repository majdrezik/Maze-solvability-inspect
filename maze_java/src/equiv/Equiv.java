package equiv;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Equiv<E> {
	
	private Map<E,Set<E>> map = new HashMap<>();

	public void add(E e1, E e2) {
		//if the map is empty, insert both values to the same set.
		if(map.isEmpty()) {
			map.put(e1, new HashSet<>());
			map.get(e1).add(e2); 
		}
		
		//if not empty.
		else {
			for(E key: map.keySet())
				if(map.containsKey(e1))
					  = map.get(e1);
		}
	
	}
	
}