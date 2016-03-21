package affix.java.effective.generics;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	CheckObjectStackString.class,
	CheckObjectStackInt.class,
	TestGenericStackString.class,
	TestGenericStackInt.class
})
public class AllTests {;}
