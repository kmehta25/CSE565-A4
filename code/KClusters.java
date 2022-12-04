import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class KClusters extends Handler{

    @Override
    public void handleRequest(int type) {
        if(type!=3) {
            this.nextHandler.handleRequest(type);
            return;
        }
//        Scanner scanner = new Scanner(System.in);
        CitiesList.getInstance().resetClusters();
        LinkedHashMap<String, City> cities = CitiesList.getInstance().citiesList;
        ArrayList<City> centroids = new ArrayList<City>();
        
        
        String cityName = "_";
        while(cityName.trim().length()>0 
        		|| centroids.size()==0) {
        	cityName = JOptionPane.showInputDialog("Enter City name/Just press enter to stop");
        	
        	if(cityName == null && CitiesList.getInstance().clusterCentroids.size()==0){
        		JOptionPane.showMessageDialog(null, "Algorithms needs at least one centroid.");
        	}
	        if(cityName.trim().length()>0){
	        	if(cities.containsKey(cityName)) {
	        		centroids.add(cities.get(cityName));
	        	}
	        	else {
	        		JOptionPane.showMessageDialog(null, "City name not present in the list");
	        	}
	 
	        }
        }
        
//        String choice = "";
//        while(!choice.trim().equals("-1")) {
//            System.out.println("Enter city name for adding as a centroid, "
//                    + "else just press enter:-1");
//            choice = scanner.next();
//            if(cities.containsKey(choice)) {
//                centroids.add(cities.get(choice));
//                System.out.println("City added to the centroids: "
//                        + "now k = "+centroids.size());
//            }
//            else {
//                if(choice.trim().equals("-1"))
//                    break;
//                System.out.println("City not present in the list of cities");
//            }
//        }
//        scanner.close();
        CitiesList.getInstance().clusterCentroids = centroids;
        generateKClusters(centroids);

        setChanged();
        notifyObservers();
        for(String city: cities.keySet()) {
            System.out.println("City name: "+city+" cluster assigned: "+CitiesList.getInstance().citiesList.get(city).clusterIndex);
        }
    }

    private void generateKClusters(ArrayList<City> centroids) {
    	int beforeStarting = Thread.activeCount();
        for(int i=0; i<centroids.size(); i++) {
            Centroid centroid = new Centroid(centroids.get(i), i);
            Thread thread = new Thread(centroid);
            System.out.print("Starting new thread "+centroid.centroid.label);
            thread.start();
        }

        // To sync the threads
        while(Thread.activeCount()>beforeStarting)
            System.out.println("Executing threads: "+Thread.activeCount());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // add links to path repo
        CitiesList.getInstance().addClusterPaths();
    }

}
