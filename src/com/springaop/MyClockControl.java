package com.springaop;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class MyClockControl extends BorderPane {
	public MyClock myclock; // clock extends pane
	private Button jbtStop = new Button("Stop");
	private Button jbtStart = new Button("Start");
	UserJDBCTemplate userJDBCTemplate;
	ApplicationContext dbContext;
	public int idNum;
	public int c;

	public void setClockControl() {
		dbContext = new ClassPathXmlApplicationContext("Beans.xml");
		userJDBCTemplate = (UserJDBCTemplate) dbContext.getBean("UserJDBCTemplate");
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		myclock = (MyClock) context.getBean("myClock");
		myclock.setMyClock(getIdNum(), getC());
		HBox hbox = new HBox();
		HBox.setMargin(jbtStart, new Insets(15));
		hbox.setAlignment(Pos.CENTER);
		jbtStart.setOnAction(sa -> {
			start();
			try {
				userJDBCTemplate.insertClickLog(InetAddress.getLocalHost().toString(), "Play",
						new SimpleDateFormat("dd-MM-yyyy").format(new Date()),
						new SimpleDateFormat("HH.mm.ss").format(new Date()));
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		jbtStop.setOnAction(sa -> {
			stop();
			try {
				userJDBCTemplate.insertClickLog(InetAddress.getLocalHost().toString(), "Stop",
						new SimpleDateFormat("dd-MM-yyyy").format(new Date()),
						new SimpleDateFormat("HH.mm.ss").format(new Date()));
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		hbox.getChildren().addAll(jbtStart, jbtStop);
		setTop(myclock);
		setBottom(hbox);
	}

	public int getIdNum() {
		return idNum;
	}

	public void setIdNum(int idNum) {
		this.idNum = idNum;
	}

	public int getC() {
		return c;
	}

	public void setC(int c) {
		this.c = c;
	}

	public void stop() {
		myclock.stop();
	}

	public void start() {

		myclock.start();
	}

}