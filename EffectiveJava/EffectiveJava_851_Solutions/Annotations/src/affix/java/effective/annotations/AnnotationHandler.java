package affix.java.effective.annotations;

import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class AnnotationHandler {

	private enum Type {CONSTRUCTOR, METHOD, FIELD}
	
	
	/**
	 * This method prints out all annotations for a specific element
	 * @param ae the element to investigate
	 * NB! Not used within this local application
	 */
	public void printAnnotations(AnnotatedElement ae){
	
		Annotation[] annotations = ae.getAnnotations();
		if(annotations.length > 0){
			System.out.println();
			System.out.println("Annotations for type: "+ae.toString());
			for(Annotation a : annotations){
				System.out.println("Annotation: " + a.annotationType().getName());	
				System.out.println("### Members of Annotation ");
				printMembers(a.annotationType().getDeclaredMethods(), a.annotationType().getName());
			}
			System.out.println("==================================================================");
		}
	}
	
	/**
	 * This method prints all members belonging to a specific type
	 * @param members array of Member type
	 * @param s String holding name of owner
	 * NB! Not used within this local application
	 */
    @SuppressWarnings("rawtypes")
	private static void printMembers(Member[] members, String s) {
    	System.out.println(s);
    	String memberString = "";
    	for (Member mbr : members) {
    		if (mbr instanceof Field)
    			memberString = ((Field)mbr).toGenericString();
    		else if (mbr instanceof Constructor)
    			memberString = ((Constructor)mbr).toGenericString();
    		else if (mbr instanceof Method)
    			memberString = ((Method)mbr).toGenericString();
    		if(memberString.indexOf(s) != -1){
    			System.out.println("\t"+ memberString );
    		}
    	}
    }
    
    @SuppressWarnings("rawtypes")
	private Constructor[] filterConstructors(Member[] members, String s){
    	
    	ArrayList<Constructor> remainingConstructors = new ArrayList<Constructor>();
    	
    	String memberString = "";
    	for (Member mbr : members) {
    		if (mbr instanceof Constructor){
    			memberString = ((Constructor)mbr).toGenericString();
    			if(memberString.indexOf(s) != -1){
    				remainingConstructors.add((Constructor)mbr);
    			}
    		}
    	}
    	return remainingConstructors.toArray(new Constructor[remainingConstructors.size()]);
    }
    
    private Method[] filterMethods(Member[] members, String s){
    	
    	ArrayList<Method> remainingMethods = new ArrayList<Method>();
    	
    	String memberString = "";
    	for (Member mbr : members) {
    		if (mbr instanceof Method){
    			memberString = ((Method)mbr).toGenericString();
    			if(memberString.indexOf(s) != -1){
    				remainingMethods.add((Method)mbr);
    			}
    		}
    	}
    	return remainingMethods.toArray(new Method[remainingMethods.size()]);
    }
    
	/**
	 * This method will print out current value of annotation
	 * @param annotationClass the annotation type to look for
	 * @param element the annotated element in the target class 
	 * @param annotationMethod the annotation method to look for
	 * @return a String holding annotation value or "" if missing
	 */
	private String checkAnnotationValue(Class<Annotation> annotationClass, AccessibleObject element, String annotationMethod){
	
		String valueString = "";
		try {
			Annotation anno = element.getAnnotation(annotationClass);
			if(anno != null){
				String tempAnno = anno.toString();

				int start = tempAnno.indexOf(annotationMethod);
				if(start != -1){
					int nextComma = tempAnno.indexOf(",", start);
					int startBracket = tempAnno.indexOf("[");
					int endBracket =  tempAnno.indexOf("]");
					// no brackets
					if(startBracket == -1){
						// check if there are more info to come
						if(nextComma != -1){
							valueString = tempAnno.substring(start, nextComma);
						}
						// must be last part of String
						else{
							valueString = tempAnno.substring(start, tempAnno.length()-1);
						}
					}
					// brackets in string
					else{
						// check if there are more info to come
						if(nextComma != -1 && nextComma < startBracket){
							valueString = tempAnno.substring(start, nextComma);
						}
						else{
							valueString = tempAnno.substring(start, endBracket+1);
						}	
					}
				}							
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		return valueString;
	}
	
	private void checkIfAnnotationIsPresent(Class<?> tempTargetClass, Class<Annotation> tempAnnotationClass, String[] annotationNames){
		// check annotation for type if present	
		if(tempTargetClass.isAnnotationPresent(tempAnnotationClass)){
			printAnnotations(tempTargetClass);
			Annotation anno = tempTargetClass.getAnnotation(tempAnnotationClass);	
			String tempAnno = anno.toString();

			// Annotations for Type check up
			int start = 0;
			for(int i=0; i<annotationNames.length; i++){
				String typeAnnoValue = "";
				start = tempAnno.indexOf(annotationNames[i]);
				if(start != -1){
					if(tempAnno.indexOf(",", start) != -1){
						typeAnnoValue = tempAnno.substring(start, tempAnno.indexOf(",",start));
						start = tempAnno.indexOf(",", start);
					}
					else{
						typeAnnoValue = tempAnno.substring(start, tempAnno.length()-1);
					}								
				}
				if(!typeAnnoValue.isEmpty()){
					System.out.println("+++ TYPE "+tempTargetClass.getSimpleName()+ " has annotation "
							+ tempAnnotationClass.getSimpleName() + ", "+typeAnnoValue);
				}
			}
		}
		this.lookForMember(tempAnnotationClass, annotationNames, tempTargetClass, Type.CONSTRUCTOR);
		this.lookForMember(tempAnnotationClass, annotationNames, tempTargetClass, Type.METHOD);
		this.lookForMember(tempAnnotationClass, annotationNames, tempTargetClass, Type.FIELD);		

	}
	
	
	@SuppressWarnings("rawtypes")
	private void lookForMember(Class<Annotation> tempAnnotationClass,  String[] annotationNames, Class<?> tempTargetClass, Type memberType ){
		
		AccessibleObject tempElement = null;		
		Member[] classMembers = null;
		
		switch(memberType){
		case CONSTRUCTOR:
			// get hold of all available Constructors for target class
			Constructor cs[] = tempTargetClass.getConstructors(); 
			cs = this.filterConstructors(cs, tempTargetClass.getCanonicalName());
			classMembers = cs;
			break;
			
		case METHOD:
			// get hold of all available methods for target class
			Method ms[] = tempTargetClass.getMethods(); 
			ms = this.filterMethods(ms, tempTargetClass.getCanonicalName());
			classMembers = ms;
			break;
			
		case FIELD:
			// get hold of all available fields for target class
			Field fs[] = tempTargetClass.getDeclaredFields(); 
			classMembers = fs;
			break;
		}

				
		Class[] types = null;
		String elementName = "";
		// Now get down to details for every element found 
		for(int m=0; m<classMembers.length; m++){
			for(int i=0; i<annotationNames.length; i++){
				elementName = classMembers[m].getName();
				try {
					switch(memberType){
					case CONSTRUCTOR:
						types = (Class[]) ((Constructor) classMembers[m]).getGenericParameterTypes();
						elementName = tempTargetClass.getSimpleName();
						tempElement = tempTargetClass.getConstructor(types);
						break;
					case METHOD:
						types = (Class[]) ((Method) classMembers[m]).getGenericParameterTypes();
						tempElement = tempTargetClass.getMethod(elementName, types);
						break;				
					case FIELD:
						tempElement = tempTargetClass.getDeclaredField(elementName);
						break;
					}
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				} catch (NoSuchFieldException e) {
					e.printStackTrace();
				} 
				String valueString = this.checkAnnotationValue(tempAnnotationClass, tempElement, annotationNames[i]);	

				if(!valueString.isEmpty()){											
					System.out.println("+++ " + memberType.toString()+ ": " + elementName + " has annotation: "
							+tempAnnotationClass.getSimpleName() + ", " + valueString);
				}
			}
		}
	}
	
	/**
	 * 
	 * @param args holding input as follows:
	 * args[0] Annotated class
	 * args[1] Annotation type
	 * args[2, ...] Elements in Annotation type to look for
	 */
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		// Define test resources
		AnnotationHandler ar = new AnnotationHandler();		
		
		String targetClassName = null;
		String annotationClassName = null;
		String[] annotationNames = null;		
		Class<Annotation> tempAnnotationClass = null;
		Class<?> tempTargetClass = null;
		
		// default values for parameters if not at least 3 program arguments are present
		if(args.length < 3){
			// The target class should also be passed in to main
			targetClassName = "affix.java.effective.annotations.AnnotationFix";
			// The annotation type should be passed in to main
			annotationClassName = "affix.java.effective.annotations.Fix";
			// The same goes for all annotation methods
			String[] methodNames = {"value", "version"};
			annotationNames = methodNames;			
		}
		// read program arguments
		else{
			targetClassName = args[0];
			annotationClassName = args[1];
			annotationNames = new String[args.length-2];
			for(int i=0; i<args.length-2; i++){
				annotationNames[i] = args[i+2];
			}
		}
		
		// Conversion to usable types are needed
		try {
			tempTargetClass = Class.forName(targetClassName);
			tempAnnotationClass = (Class<Annotation>) Class.forName(annotationClassName);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
			
		ar.checkIfAnnotationIsPresent(tempTargetClass, tempAnnotationClass, annotationNames);		

	}
	
}
