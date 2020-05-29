

import java.util.ArrayList;

/**
 * Title : MenuManagement.java 
 * Description: This class is the control class for user login and register part.
 * 
 * @author : Jiaqi Luo
 * @date : 15/5/2020
 */

public class LoginControl {
	private ArrayList<Boolean> idcheck = new ArrayList<Boolean>(); // check whether login phone or E-mail can match to userInfo.txt
	private static String rightpassword, rightid, right; // right password, right ID number and right phone number or E-mail
	private ArrayList<User> users = new ArrayList<User>(); // information of all users
	private static User user = new User();
	private Order o = new Order();
	private UserInfoManagement uim = new UserInfoManagement();

	/**
	 * Control method of boundary class Login
	 * @param ID User ID number
	 */
	public void loginControl(String ID) {
		users = uim.getUsers();
		for (int i = 0; i < users.size(); i++) {
			if (!ID.equals(users.get(i).getPhone()) && !ID.equals(users.get(i).getEmail())) {
				idcheck.add(false);
			} else if(ID.equals(users.get(i).getPhone())){
				idcheck.add(true);
				user = users.get(i);
				rightid = users.get(i).getID();
				right = users.get(i).getPhone();
				System.out.println(right);
				rightpassword = users.get(i).getPassword();
			} else if(ID.equals(users.get(i).getEmail())) {
				idcheck.add(true);
				user = users.get(i);
				rightid = users.get(i).getID();
				right = users.get(i).getEmail();
				System.out.println(right);
				rightpassword = users.get(i).getPassword();
			}
		}
	}

	/**
	 * Operations need to be done when finish login step
	 */
	public void finishLogin() {
		o.setUserID(rightid);
	}

	/**
	 * Operations need to be done when finish register step
	 * @param id User ID number
	 */
	public void finishRegister(String id) {
		users = uim.getUsers();
		o.setUserID(Integer.toString(getNewId()));
		user.setID(Integer.toString(getNewId()));
		users.add(user);
		uim.setUsers(users);
		uim.writeUserInfo();
	}

	/**
	 * Operations need to be done when begin modify information step
	 */
	public void beginModifyInfo() {
		users = uim.getUsers();
		users.remove(user);
	}

	/**
	 * Operations need to be done when finish modify information step
	 */
	public void finishModifyInfo() {
		user.setID(rightid);
		users.add(user);
		uim.setUsers(users);
		uim.writeUserInfo();

	}

	/**
	 * Set user information to a new user and add to all information of users
	 * @param name User name
	 * @param email User E-mail
	 * @param phone User phone number
	 * @param age User age
	 * @param pwd Password
	 * @param cpwd Confirm password
	 */
	public void checkInfo(String name, String email, String phone, String age, String pwd, String cpwd) {
		User u = new User();
		u.setName(name);
		u.setEmail(email);
		u.setPhone(phone);
		u.setAge(age);
		u.setPassword(pwd);
		if(u.getID()==null)
			u.setStamp("0");
		else
			u.setStamp(user.getStamp());
		setUser(u);
	}

	/**
	 * Method to check name format
	 * @param Name Input Name
	 * @return String of worn
	 */
	public String judgeName(String Name) {
		if (Name.equals("")) {
			System.out.println("Name:" + Name + "||Input should not be empty.");
			return "Input should not be empty.";
		} else if (!Name.matches("([a-zA-Z ]){1,20}")) {
			System.out.println("Name:" + Name + " ||Name should be all character and length is smaller than 20.");
			return "Name should be all character and length is smaller than 20.";
		} else {
			System.out.println("Name:" + Name + " ||Correct name input");
			return "";
		}
	}

	/**
	 * Method to check E-mail format
	 * @param E_Mail Input E-mail
	 * @return String of worn
	 */
	public String judgeEmail(String E_Mail) {
		if (E_Mail.equals("")) {
			System.out.println("Email:" + E_Mail + " ||Input should not be empty.");
			return "Input should not be empty.";
		} else if (!E_Mail.matches("[\\da-zA-Z]+([\\_]?[\\da-zA-Z])*@[\\da-zA-Z]+(\\.[a-z]{1,3})")) {
			System.out
					.println("Email:" + E_Mail + " ||Email should contain '@' and '.' ,and '@' should be before '.'.");
			return "Email should contain '@' and '.' and '@' should be before '.'\nThere are characters before'@', between '@' and '.' and after '.'";
		} else {
			System.out.println("Email:" + E_Mail + " ||Correct email Format.");
			return "";
		}
	}

	/**
	 * Method to check phone format
	 * @param Phone Input phone number
	 * @return String of worn
	 */
	public String judgePhone(String Phone) {
		if (Phone.equals("")) {
			System.out.println("Phone:" + Phone + " ||Input should not be empty.");
			return "Input should not be empty.";
		} else if (!Phone.matches("[0-9]{11}")) {
			System.out.println("Phone:" + Phone + " ||Length of phone should be 11 and all number.");
			return "Length of phone should be 11 and all number.";
		} else {
			System.out.println("Phone:" + Phone + " ||Correct phone number.");
			return "";
		}
	}

	/**
	 * Method to check age format
	 * @param Age Input age
	 * @return String of worn
	 */
	public String judgeAge(String Age) {
		if (Age.equals("")) {
			System.out.println("Age:" + Age + " ||Input should not be empty.");
			return "Input should not be empty.";
		} else if (Age.matches("[0-9]{1,}")) {
			if (Integer.parseInt(Age) > 120 || Integer.parseInt(Age) < 1) {
				System.out.println("Age:" + Age + " ||Age should be smaller than 120 and greater than 1.");
				return "Age should be smaller than 120 and greater than 1.";
			} else {
				System.out.println("Age:" + Age + " ||Correct age input.");
				return "";
			}
		} else if (!Age.matches("[0-9]{1,}")) {
			System.out.println("Age:" + Age + " ||Age should be all numbers.");
			return "Age should be all numbers.";
		} else {
			System.out.println("Age:" + Age + " ||Correct age input.");
			return "";
		}
	}

	/**
	 * Method to check passwords are entered the same
	 * @param Password Input password
	 * @param PasswordC Input confirming password
	 * @return String of worn
	 */
	public String judgePassword(String Password, String PasswordC) {
		if (Password.equals("") || Password.equals("")) {
			System.out.println("Password:" + Password + "/PasswordC:" + PasswordC + " ||Input should not be empty.");
			return "Input should not be empty.";
		} else if ((new String(Password).equals(PasswordC)) != true) {
			System.out.println("Password:" + Password + "/PasswordC:" + PasswordC + " ||Inputs are not the same.");
			return "Password inputs are not the same.";
		} else if (Password.length() >= 20) {
			System.out.println(
					"Password:" + Password + "/PasswordC:" + PasswordC + " ||Password should be no longer than 20.");
			return "Password should be no longer than 20.";
		} else {
			System.out.println("Password:" + Password + "/PasswordC:" + PasswordC + " ||Correct inputs.");
			return "";
		}
	}

	/**
	 * Get new user information
	 * @return new user information
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Get check list
	 * @return check list
	 */
	public ArrayList<Boolean> getIdcheck() {
		return idcheck;
	}

	/**
	 * Get right password
	 * @return right password
	 */
	public String getRightPwd() {
		return rightpassword;
	}

	/**
	 * Get right ID number
	 * @return right ID number
	 */
	public String getRightId() {
		return rightid;
	}

	/**
	 * Get new ID number
	 * @return new user ID number
	 */
	public int getNewId() {
		int a = (int) (Math.random() * 9 + 1);
		int b = (int) (Math.random() * 9 + 1);
		int c = (int) (Math.random() * 9 + 1);
		int d = (int) (Math.random() * 9 + 1);
		int f = (int) (Math.random() * 9 + 1);
		int g = (int) (Math.random() * 9 + 1);
		int h = (int) (Math.random() * 9 + 1);
		int k = (int) (Math.random() * 9 + 1);
		int newID = 10000000 * a + 1000000 * b + 100000 * c + 10000 * d + 1000 * f + 100 * g + 10 * h + k;
		return newID;
	}

	/**
	 * Set user information
	 * @param user All information of a user
	 */
	public void setUser(User user) {
		LoginControl.user = user;
	}
}
