package affix.java.effective.annotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, 
	ElementType.CONSTRUCTOR,
	ElementType.METHOD, 
	ElementType.FIELD})
/**
 * This annotation is used in example code for course EffectiveJava.
 */
public @interface EffectiveJava {
	/**
	 * This method is used to map example code to item definition 
	 * @return int holding item no in reference book "Effective Java" 2nd ed 
	 */
	int item();
	/**
	 * This method is used to map example code to item description in reference book 
	 * "Effective Java" 2nd ed 
	 * @return String int presents item title in full text 
	 */
	String description();
}
