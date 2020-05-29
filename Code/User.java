/**
 * Title : User.java 
 * Description: This class is an entity class of user.
 * 
 * @author : Jiaqi Luo
 * @date : 8/5/2020
 */

public class User {
	private String id = new String(); // user ID number
	private String name = new String(); // user name
	private String email = new String(); // user E-mail
	private String phone = new String(); // user phone number
	private String age = new String(); // user age
	private String password = new String(); // password
	private String stamp = new String("0"); // stamp number of user
	
	/**
	 * Set user ID number
	 * @param id User ID number
	 */
	public void setID(String id) {
		this.id = id;
	}
	
	/**
	 * Set user name
	 * @param name User name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Set user E-mail
	 * @param email User E-mail
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * Set user phone number
	 * @param phone User phone number
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	/**
	 * Set user age
	 * @param age User age
	 */
	public void setAge(String age) {
		this.age = age;
	}
	
	/**
	 * Set password
	 * @param password User password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Set stamp number of user
	 * @param stamp Stamp number of user
	 */
	public void setStamp(String stamp) {
		this.stamp = stamp;
	}
	
	/**
	 * Get user ID number
	 * @return id
	 */
	public String getID(){
		return id;
	}
	
	/**
	 * Get user name
	 * @return name
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * Get user E-mail
	 * @return email
	 */
	public String getEmail(){
		return email;
	}
	
	/**
	 * Get user phone number
	 * @return phone
	 */
	public String getPhone(){
		return phone;
	}
	
	/**
	 * Get user age
	 * @return age
	 */
	public String getAge(){
		return age;
	}
	
	/**
	 * Get password
	 * @return password
	 */
	public String getPassword(){
		return password;
	}
	
	/**
	 * Get stamp number of user
	 * @return stamp
	 */
	public String getStamp(){
		return stamp;
	}
}
