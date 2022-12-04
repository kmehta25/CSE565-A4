import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class TSPpro extends Handler{
	
	@Override
	public void handleRequest(int type) {
		if(type!=Handler.TSPPRO) {
			this.nextHandler.handleRequest(type);
			return;
		}
		LinkedHashMap<String, City> cities = CitiesList.getInstance().citiesList;
		System.out.println("Calculating TSPPro for new path of "+cities.size()+" cities....");
		
		Iterator<Entry<String, City>> iter = cities.entrySet().iterator();
		
		ArrayList<City> allCitiesList = new ArrayList<City>();
		while(iter.hasNext())	
			allCitiesList.add(iter.next().getValue());
		
		if(cities.size()>=1) {
			tspPro(allCitiesList);
			
			// add links to path repo
			CitiesList.getInstance().addTSPPath();
			
			// notifyObservers
			setChanged();
			notifyObservers();
			}		
						
	}
	
	
	@SuppressWarnings("unchecked")
	private void allPermutationsHelper(ArrayList<City> permutation, ArrayList<ArrayList<City>> permutations, int n) {
        // Base case - we found a new permutation, add it and return
        if (n <= 0) {
            permutations.add(permutation);
            return;
        }
        // Recursive case - find more permutations by doing swaps
        ArrayList<City> tempPermutation = (ArrayList<City>) permutation.clone();
        for (int i = 0; i < n; i++) {
            swap(tempPermutation, i, n - 1); // move element at i to end
            // move everything else around, holding the end constant
            allPermutationsHelper(tempPermutation, permutations, n - 1);
            swap(tempPermutation, i, n - 1); // backtrack
        }
    }
	
	
	private void swap(ArrayList<City> tempPermutation, int first, int second) {
        City temp = tempPermutation.get(first);
        tempPermutation.set(first, tempPermutation.get(second));
        tempPermutation.set(second, temp);
    }
	
	private void tspPro(ArrayList<City> original) {
		ArrayList<ArrayList<City>> permutations = new ArrayList<>();
        allPermutationsHelper(original, permutations, original.size());
        
        int shortestPath = 0, index = 0;
        double minDistance = Double.MAX_VALUE;
        
        for(ArrayList<City> path: permutations) {
        	double pathDist = 0;
        	System.out.println("Total paths: "+permutations.size()+" calculating for path: "+index);
        	for(int i=0; i<path.size()-1; i++) {
        		double dx = (path.get(i).bounds.x-path.get(i+1).bounds.x);
				double dy = (path.get(i).bounds.y-path.get(i+1).bounds.y);
				pathDist  += Math.sqrt(dx*dx+dy*dy);
				if(minDistance<pathDist) {
					break;
				}
        	}
        	if(pathDist<minDistance) {
        		minDistance = pathDist;
        		shortestPath = index;
        	}
        	index++;
        }
        LinkedHashMap<String, City> shortestPathMap = new LinkedHashMap<String, City>();
        for(City city: permutations.get(shortestPath))
        	shortestPathMap.put(city.label, city);
        
		CitiesList.getInstance().citiesList = shortestPathMap;
		CitiesList.getInstance().pathDist = minDistance;
		System.out.println("minDistance "+minDistance);
    }
}
