package affix.java.effective.uslength;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	TestUSLengthCreation.class,
	TestUSLengthEqualsAndHashcode.class,
	TestUSLengthCompare.class,
	TestUSLengthString.class
})
public class AllTests {;}
