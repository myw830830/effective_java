package affix.java.effective.time;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses(
		{
			TestTimeCreation.class,
			TestTimePresentation.class,	
			TestTimeComparison.class,
			TestTimeEqualsAndHashcode.class
		}
)

public class AllTests {;}