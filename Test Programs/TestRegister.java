
/**
 * Title 		: TestRegister.java
 * Description	: This JUnit class is used to test the fault-tolerance of Register.java.
 * @author		: Yang Hu 
 * @date      	: 12/5/2020
 */

import org.junit.jupiter.api.Test;

class TestRegister {


	@Test
	void test() {
		LoginControl r = new LoginControl();
		//The length of the name should be no longer than 20
		r.judgeName("aaaaabbbbbcccccdddddeeeeee");
		//Name should be all characters
		r.judgeName("12asd");
		//Name input should not be null
		r.judgeName("");
		//Correct input name
		r.judgeName("Lisa");
		
		//Email should contain '@' and '.'
		System.out.println("\n");
		r.judgeEmail("123456.123");
		r.judgeEmail("1234@123");
		//'@' should be before '.'
		r.judgeEmail("1234.@123");
		//Characters should be preceded by '@'
		r.judgeEmail("@123");
		//Characters should exist between '@' and '.'
		r.judgeEmail("123@.com");
		//Characters should exist after '.'
		r.judgeEmail("123@qq.");
		//Email input should not be null
		r.judgeEmail("");
		//Correct email input
		r.judgeEmail("123456@qq.com");
		
		System.out.println("\n");
		//Phone number should be all number
		r.judgePhone("1234ads");
		//The length of phone number should be smaller than 20
		r.judgePhone("111112222233333444445");
		//Phone number should not be null
		r.judgePhone("");
		//Correct phone number
		r.judgePhone("13588880000");
		
		System.out.println("\n");
		//Age should be smaller than 120 and greater than 1.
		r.judgeAge("0");
		r.judgeAge("130");
		//Age should be all numbers.
		r.judgeAge("abc");
		r.judgeAge("1a2d3");
		//Input should not be empty
		r.judgeAge("");
		//Correct input
		r.judgeAge("20");
		
		System.out.println("\n");
		//Input should not be empty
		r.judgePassword("","");
		r.judgePassword("1234","");
		r.judgePassword("","1234");
		//Inputs are not the same.
		r.judgePassword("abc1234", "1234abc");
		//Password should be no longer than 20.
		r.judgePassword("111112222233333444445", "111112222233333444445");
		//Correct inputs.
		r.judgePassword("1234abcd", "1234abcd");
		
	}

}
