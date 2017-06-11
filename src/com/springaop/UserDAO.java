package com.springaop;

import javax.sql.DataSource;

public interface UserDAO {
	/**
	 * This is the method to be used to initialize database resources ie.
	 * connection.
	 */
	public void setDataSource(DataSource ds);

	/**
	 * This is the method to be used to create a record in the logIn table.
	 */
	public void insertLogInLog(String ip, String date, String time);

	/**
	 * This is the method to be used to create a record in the LogOut table.
	 */
	public void insertLogOutLog(String ip, String date, String time);

	/**
	 * This is the method to be used to create a record in the Button table.
	 */
	public void insertClickLog(String ip, String buttonName, String date, String time);
	
	/**
	 * This is the method to be used to create a record in the UsageTime table.
	 */
	public void insertUsageTimeLog(String ip, String date, String time, String usageTime);
}