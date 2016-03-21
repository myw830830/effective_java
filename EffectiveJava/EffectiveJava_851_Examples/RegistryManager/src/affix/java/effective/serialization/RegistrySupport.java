package affix.java.effective.serialization;

import java.io.*;

/**
 * This class holds public static methods for storing/retrieving objects from a
 * serialized file.
 */
public class RegistrySupport {

	private RegistrySupport(){;}
	
	public static void storeMgr(RegistryManager mgr){
		
		// Java 7 supports try-with-resources (requires implementation of AutoCloseable)
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("RegistryMgr.ser"))){
			oos.writeObject(mgr);
		}
		catch(IOException ioe){
			ioe.printStackTrace();
		}
	}
	
	
	public static RegistryManager readMgr(){
		
		RegistryManager manager = null;
		
		// Java 7 supports try-with-resources (requires implementation of AutoCloseable)
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("RegistryMgr.ser"))){
			manager = (RegistryManager)ois.readObject();
		}
		// Fall back on null value
		// Java 7 supports multi-catch
		catch(ClassNotFoundException | IOException ioe){;}

		return manager; 
	}
}