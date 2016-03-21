package affix.java.effective.annotations;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,
		 ElementType.METHOD,
		 ElementType.CONSTRUCTOR})
public @interface Fix {
	String value();
}
