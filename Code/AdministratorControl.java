

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Title 		: AdministratorControl.java
 * Description	: This class is the control class for administrator function part.
 * @author		: Yang Hu 
 * @date      	: 11/5/2020
 */
public class AdministratorControl {
	/**
	 * Determine whether the format of the input dish name is correct.
	 * @param option To receive a parameter of String type. 
	 * @return Return an integer value. Return 2 if the input is null and 0 if the input is correct.
	 **/
	public int judgeTextF1(String option) {
		if (option.length() <= 0) {
			System.out.println("Input should not be null.");
			return 2;
		}else {
			System.out.println("Option:"+option+" ||Correct input!");
			return 0;
		}
	}
	/**
	 * Determine whether the format of the input price is correct.
	 * @param price To receive a parameter of String type. 
	 * @return Return an integer value. Return 3 if the input is null. Return 2 if the input is not a number.
	 * 									Return 1 if the input is not a two decimal place. Return 0 if the input is correct.
	 **/
	public int judgeTextF2(String price) {
		if (price.length() <= 0) {
			System.out.println("Input should not be null.");
			return 3;
		}else if(Pattern.compile("(?i)[a-z]").matcher(price).find()) {
			System.out.println("Price:"+price+" ||All number!");
			return 2;
		}else if(!isNumric(price)){
			System.out.println("Price:"+price+" ||An integer or two decimal places!");
			return 1;
		}else {
			System.out.println("Price:"+price+" ||Correct input!");
			return 0;
		}
	}
	/**
	 * Determine whether the format of the input is a number.
	 * @param str To receive a parameter of String type. 
	 * @return Return an boolean typw. Return true if the input is a number. Return false if the input is not a number.
	 **/
	public boolean isNumric(String str) {
		Pattern pattern = Pattern.compile("0?[0-9]+(\\.?[0-9]{2}|)");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}
}
