package com.springaop;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ImportDB {
	 public static void main(String[] args) {
		 Connection con = null;
	      String url = "jdbc:mysql://localhost/";
	      String db = "";
	      String driver = "com.mysql.jdbc.Driver";
	      try
	      {  Class.forName(driver);
	         System.out.println("Driver Loaded");
	         con = DriverManager.getConnection(url+db,"root","");
	         System.out.println("Connection Established");
	         Statement st = con.createStatement();
	 
	       FileInputStream fstream = new FileInputStream("import.txt");
	       // Get the object of DataInputStream
	       DataInputStream in = new DataInputStream(fstream);
	       BufferedReader br = new BufferedReader(new InputStreamReader(in));
	       String strLine;
	       //Read File Line By Line
	       while ((strLine = br.readLine()) != null)
	       { System.out.println(strLine);
	         if (strLine != null && !strLine.equals("")) st.execute(strLine);          
	       }
	    }
	    catch(Exception e)
	    { System.out.println(e);
	    }  
	    finally {}
	   }
}
