package MyPackage;

import java.util.HashMap;

public class Users {
	private String uid;			
	private String password;
	private String fullname;		
	private String email;
	private HashMap<String, Integer> booklist;
	private Boolean state;
	
	public Users(){
		uid = new String();
		password = new String();
		fullname = new String();
		email = new String();
		state = false;
		booklist = new HashMap<String, Integer>();
	}
	public Boolean getState(){
		return state;
	}
	public void setState(Boolean b){
		this.state = b;
	}
	public String getId() {
		return uid;
	}
	public void setId(String uid) {
		this.uid = uid;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname=fullname;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public HashMap<String, Integer> getBooklist(){
		return booklist;
	}
	public void addBook(String k, Integer v){
		booklist.put(k, v);
	}
	
	

}