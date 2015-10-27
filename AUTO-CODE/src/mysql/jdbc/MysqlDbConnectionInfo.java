package mysql.jdbc;

import jdbc.DbConnectionInfo;

public class MysqlDbConnectionInfo implements DbConnectionInfo {
	private String hosting   = "";
	private String account   = "";
	private String password  = "";
	private String className = "com.mysql.jdbc.Driver";
	
	public void setHosting(String hosting) {
		if (hosting.indexOf("jdbc:mysql://") < 0) hosting = "jdbc:mysql://" + hosting;
		this.hosting = hosting;
	}

	@Override
	public String getHosting() {
		return hosting;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	@Override
	public String getAccount() {
		return account;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String getPassword() {
		return password;
	}
	
	public void setClassName(String className) {
		this.className = className;
	}

	@Override
	public String getClassName() {
		return className;
	}
}
