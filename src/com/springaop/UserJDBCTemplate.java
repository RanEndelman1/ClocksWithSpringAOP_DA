package com.springaop;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserJDBCTemplate implements UserDAO {
	private JdbcTemplate jdbcTemplateObject;

	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	@Override
	public void insertLogInLog(String ip, String date, String time) {
		String SQL = "insert into login (ip, time, date) values (?, ?, ?)";
		jdbcTemplateObject.update(SQL, ip, time, date);
		System.out.println("Created Record ip = " + ip + " date = " + date + " time = " + time);
	}

	@Override
	public void insertLogOutLog(String ip, String date, String time) {
		String SQL = "insert into logout (ip, time, date) values (?, ?, ?)";
		jdbcTemplateObject.update(SQL, ip, time, date);
		System.out.println("Created Record ip = " + ip + " date = " + date + " time = " + time);
	}

	@Override
	public void insertClickLog(String ip, String buttonName, String date, String time) {
		String SQL = "insert into buttons (ip, type, time, date) values (?, ?, ?, ?)";
		jdbcTemplateObject.update(SQL, ip, buttonName, time, date);
		System.out.println(
				"Created Record ip = " + ip + " buttonName = " + buttonName + " date = " + date + " time = " + time);
	}

	@Override
	public void insertUsageTimeLog(String ip, String date, String time, String usageTime) {
		String SQL = "insert into usagetime (ip, Date, Time, UsageTime) values (?, ?, ?, ?)";
		jdbcTemplateObject.update(SQL, ip, date, time, usageTime);
		System.out.println("Created Record ip = " + ip + " date = " + date + " time = " + time + date + " usageTime = "
				+ usageTime);
	}
}