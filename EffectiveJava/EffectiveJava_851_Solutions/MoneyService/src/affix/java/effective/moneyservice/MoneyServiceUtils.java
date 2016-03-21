package affix.java.effective.moneyservice;

import java.io.*;

/**
 * This class is used as a utility class for MoneyService implementations. 
 */
public class MoneyServiceUtils {
	

	/**
	 * Prevent client from creating objects of class holding no state
	 */
	private MoneyServiceUtils() {;}
	
	
	/**
	 * This method performs serialization of a Collection of Transactions.
	 * @param filename String holding destination file
	 * @param data holding a complete collection of data
	 */
	public static void storeTransactions(String filename, Object data){
		
		// Java7 try-with-resources
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename));){
			oos.writeObject(data);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * This method deserializes Transaction data stored in a file.
	 * @param filename String holding file to look for
	 * @return Object  holding a collection of Transactions or null if deserialization fails.
	 */
	public static Object readTransactions(String filename){
		
		Object theObject = null;
		
		// Java7 try-with-resources
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename));){
			theObject = ois.readObject();
		} 
		catch (FileNotFoundException e) {System.out.println("Debug: " + e.toString());}
		catch (IOException e) {System.out.println("Debug: " + e.toString());}
		catch (ClassNotFoundException e) {System.out.println("Debug: " + e.toString());}

		return theObject;	
	}
}
