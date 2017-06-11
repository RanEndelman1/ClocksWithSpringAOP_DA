package com.springaop;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class MyClock extends ClockPane // clockpane extends pane
{
	private Timeline animation;

	public void setMyClock(int idNum, int c) {
		super.setClockPane(c);
		animation = new Timeline(new KeyFrame(Duration.millis(1000), ev -> startAnimation(idNum)));
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.play();
	}

	private void startAnimation(int idNum) {
		if (idNum == 0)
			this.setCurrentTimeMinusHour();
		else if (idNum == 1)
			this.setCurrentTime();
		else if (idNum == 2)
			this.setCurrentTimePlusHour();
	}

	public void stop() {
		animation.stop();
	}

	public void start() {
		animation.play();
	}
}