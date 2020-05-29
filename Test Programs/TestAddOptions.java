
/**
 * Title 		: TestRegister.java
 * Description	: This JUnit class is used to test the fault-tolerance of AddOptions.java.
 * @author		: Yang Hu 
 * @date      	: 12/5/2020
 */

import org.junit.jupiter.api.Test;

class TestAddOptions {
	
	@Test
	void test() {
		AdministratorControl a = new AdministratorControl();
		// Name of the options should not be zero
		a.judgeTextF1("");
		// Correct input
		a.judgeTextF1("Onion");
		// Price of the options should not be zero
		a.judgeTextF2("");
		// Price of the options should be all number
		a.judgeTextF2("abc");
		// Correct integer type input
		a.judgeTextF2("2");
		// Correct double type input
		a.judgeTextF2("2.544");
		a.judgeTextF2("2.5");
		a.judgeTextF2("2.54");
	}

}
