import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;
/**
* This class is for maintaining the cities path list made of object of City class
*  
* @author  Suraj Suryawanshi
* @version 2.0
* @since   2021-10-08
*/

public class PathsList {
	
	public ArrayList<City[]> path;
	public double pathDist;
	private static PathsList object;
	
	
	private PathsList() {
		path =  new ArrayList<City[]>();
		pathDist = 0;
	}
	
	/**
	 * This method is for getting the instance of this class object
	 * 
	 * @return PathsList 
	 */
	public static PathsList getInstance() {
		if(PathsList.object == null) 
			PathsList.object = new PathsList();			
		return PathsList.object;
	}
	
	
	/**
	 * This method is clearing the cities path list
	 */
	public void clearPath() {
		PathsList.getInstance().path = new ArrayList<City[]>();
//		Flag.getInstance().val = true;
		System.out.println("clearing path list "+PathsList.getInstance().path.size());
	}

	
//	/**
//	 * This method is for saving the file to the CWD
//	 * 
//	 */ 
//	public void saveFile() {
//		String path = System.getProperty("user.dir");
//		StringBuilder sb = new StringBuilder();
//		for(int i=0; i<PathsList.object.path.size(); i++) {
//			sb.append(PathsList.object.path.get(i)[0].toString()+
//					" and "+
//					PathsList.object.path.get(i)[1].toString()+"\n");
//		}
//		try {
//		    File file = new File(path+"\\testPath_"+Math.round(Math.random()*1000)+".txt");
//		    FileWriter writer = new FileWriter(file);
//		    writer.append(sb.toString());
//		    writer.close();
//		    System.out.println("Save as file: " + file.getAbsolutePath());
//		} catch (Exception e) {
//			System.out.println("Error while writing the file");
//			e.printStackTrace();
//		}
//	}
//
//	
//	/**
//	 * This method is for importing an existing file 
//	 * 
//	 * @param file  file to import
//	 */
//	public void readInputCoords(File file) {
//		String label;
//		CitiesList.getInstance().citiesList = new LinkedHashMap<String, City>();
//		try {
//			Scanner sc = new Scanner(file);
//			while(sc.hasNextLine()) {
//				String arr[] = sc.nextLine().trim().split(" ");
//				
//				if(arr.length<3) {
//					System.out.println("Incorrect input format");
//					break;
//				}
//				label = arr[0];
//				CitiesList.getInstance().citiesList.put(label, 
//						new City(label, Integer.parseInt(arr[1]), Integer.parseInt(arr[2])));
//			}
////			Flag.getInstance().val = true;
//			sc.close();
//			
//		} catch (FileNotFoundException e) {
//			System.out.println("Error while reading the file");
//			e.printStackTrace();
//		}
//	}
	

	/**
	 * This method is for returning the length 
	 */
	public int getLength(){
        return path.size();
    }
}
