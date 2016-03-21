package affix.java.effective.moneyservice;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses(
		{
			TestCurrencies.class,
			TestBuyMoney.class,
			TestSellMoney.class,
			TestMoney.class,
			TestOrder.class
		}

)
public class AllTests {
}


