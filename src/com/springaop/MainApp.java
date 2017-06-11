package com.springaop;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MainApp extends Application {
	UserJDBCTemplate userJDBCTemplate;
	ApplicationContext context;
	public MyClockControl clockControl1, clockControl2, clockControl3;
	private Button jbtStartAll, jbtStopAll;
	private final static int NUM_OF_CLOCKS = 3;
	private final static int INITIAL_PANE_H = 320, INITIAL_CLOCK_W = 250;
	Scene scene;

	@Override // Override the start method in the Application class
	public void start(Stage primaryStage) {
		context = new ClassPathXmlApplicationContext("Beans.xml");
		userJDBCTemplate = (UserJDBCTemplate) context.getBean("UserJDBCTemplate");
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		clockControl1 = (MyClockControl) context.getBean("clockControl1");
		clockControl2 = (MyClockControl) context.getBean("clockControl2");
		clockControl3 = (MyClockControl) context.getBean("clockControl3");

		clockControl1.setClockControl();
		clockControl2.setClockControl();
		clockControl3.setClockControl();

		GridPane gpane = new GridPane();
		BorderPane bp = new BorderPane();
		HBox hbox = new HBox(15); // buttons stop , and start all
		gpane.add(clockControl1, 0, 0);
		gpane.add(clockControl2, 1, 0);
		gpane.add(clockControl3, 2, 0);
		ColumnConstraints cs = new ColumnConstraints(clockControl1.myclock.getW());
		gpane.getColumnConstraints().addAll(cs, cs, cs);
		gpane.setAlignment(Pos.CENTER);
		hbox.getChildren().addAll(jbtStartAll = new Button("Start All"), jbtStopAll = new Button("Stop All"));
		hbox.setAlignment(Pos.CENTER);
		hbox.setPadding(new Insets(0));
		bp.setCenter(gpane);
		bp.setBottom(hbox);
		scene = new Scene(bp, INITIAL_CLOCK_W * NUM_OF_CLOCKS, INITIAL_PANE_H);
		initiateStage(primaryStage);
		// assigning commands to buttons
		jbtStartAll.setOnAction(soa -> {
			clockControl1.start();
			clockControl2.start();
			clockControl3.start();
			try {
				userJDBCTemplate.insertClickLog(InetAddress.getLocalHost().toString(), "StartAll",
						new SimpleDateFormat("dd-MM-yyyy").format(new Date()),
						new SimpleDateFormat("HH.mm.ss").format(new Date()));
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		jbtStopAll.setOnAction(soa -> {
			clockControl1.stop();
			clockControl2.stop();
			clockControl3.stop();
			try {
				userJDBCTemplate.insertClickLog(InetAddress.getLocalHost().toString(), "StopAll",
						new SimpleDateFormat("dd-MM-yyyy").format(new Date()),
						new SimpleDateFormat("HH.mm.ss").format(new Date()));
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}

	public void initiateStage(Stage primaryStage) {
		primaryStage.setTitle("Clocks");
		primaryStage.setScene(scene);
		primaryStage.show(); // Display the stage
		primaryStage.setAlwaysOnTop(true);
	}

	public static void mainExe() {
		launch();
	}

}