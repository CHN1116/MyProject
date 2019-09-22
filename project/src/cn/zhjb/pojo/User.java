package cn.zhjb.pojo;

public class User {
	private int userId;
	private String userName;
	private String userpwd;
	private String userSex;
	private int userAge;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserpwd() {
		return userpwd;
	}
	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}
	public String getUserSex() {
		return userSex;
	}
	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}
	public int getUserAge() {
		return userAge;
	}
	public void setUserAge(int userAge) {
		this.userAge = userAge;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName
				+ ", userpwd=" + userpwd + ", userSex=" + userSex
				+ ", userAge=" + userAge + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + userAge;
		result = prime * result + userId;
		result = prime * result
				+ ((userName == null) ? 0 : userName.hashCode());
		result = prime * result + ((userSex == null) ? 0 : userSex.hashCode());
		result = prime * result + ((userpwd == null) ? 0 : userpwd.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (userAge != other.userAge)
			return false;
		if (userId != other.userId)
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		if (userSex == null) {
			if (other.userSex != null)
				return false;
		} else if (!userSex.equals(other.userSex))
			return false;
		if (userpwd == null) {
			if (other.userpwd != null)
				return false;
		} else if (!userpwd.equals(other.userpwd))
			return false;
		return true;
	}
	public User(int userId, String userName, String userpwd, String userSex,
			int userAge) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userpwd = userpwd;
		this.userSex = userSex;
		this.userAge = userAge;
	}
	public User() {
		super();
	}
	
	
	
}
