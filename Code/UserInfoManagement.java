import java.io.*;
import java.util.ArrayList;

/**
 * Title 		: UserInfoManagement.java
 * Description	: This class is used to deal with the file userInfo.txt.
 * @author		: Mingzhi Tang
 * @author      : Yang Hu
 * @author      : Jiaqi Luo
 * @date      	: 12/5/2020
 */

public class UserInfoManagement {
	private String userInfoPath = new String("Files/userInfo.txt"); // path of user information file
	private static ArrayList<User> users = new ArrayList<User>(); // create an array list to store all user information
	
	/**
	 * Read userInfo.txt
	 */
	public void readUserInfo() {
		/*
		 * Read file and store information to entity class user and array list users
		 */
		try {
			FileReader fileReader = new FileReader(userInfoPath);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String str;
			while ((str = bufferedReader.readLine()) != null) {
				User user = new User();
				user.setID(str.split(":")[1]);
				user.setName(bufferedReader.readLine().split(":")[1]);
				user.setEmail(bufferedReader.readLine().split(":")[1]);
				user.setPhone(bufferedReader.readLine().split(":")[1]);
				user.setAge(bufferedReader.readLine().split(":")[1]);
				user.setPassword(bufferedReader.readLine().split(":")[1]);
				user.setStamp(bufferedReader.readLine().split(":")[1]);
				users.add(user);
			}
			bufferedReader.close();
			fileReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Print out userInfo.txt
	 */
	public void writeUserInfo() {
		try {
			PrintStream bw = new PrintStream(userInfoPath);
			for (int i = 0; i < users.size(); i++) {
				bw.print("ID:"+users.get(i).getID() + "\n");
				bw.print("Name:"+users.get(i).getName() + "\n");
				bw.print("Email:"+users.get(i).getEmail() + "\n");
				bw.print("Phone:"+users.get(i).getPhone() + "\n");
				bw.print("Age:"+users.get(i).getAge() + "\n");
				bw.print("Password:"+users.get(i).getPassword() + "\n");
				bw.print("Stamp:"+users.get(i).getStamp() + "\n");
			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Set information of all users
	 * @param users Information of all users
	 */
	public void setUsers(ArrayList<User> users) {
		UserInfoManagement.users = users;;
	}
	
	/**
	 * Get one user information
	 * @return user
	 */
	public ArrayList<User> getUsers(){
		return users;
	}
}
