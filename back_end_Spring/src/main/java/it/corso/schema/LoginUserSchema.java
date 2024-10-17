package it.corso.schema;

public class LoginUserSchema {
	private String username, password;

	public String getNickname() {
		return username;
	}

	public void setNickname(String nickname) {
		this.username = nickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
