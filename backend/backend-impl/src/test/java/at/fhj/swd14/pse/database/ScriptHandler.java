package at.fhj.swd14.pse.database;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;

//import org.apache.ibatis.jdbc.ScriptRunner;

/**
 * PREPARE FOR SQL-TESTS
 * 
 * @author Schönegger, Koch
 */

public class ScriptHandler {

	 String host;

	 public ScriptHandler(String host) {
	  setHost(host);
	 }

	 /**
	  * executes a SQL SCRIPT by path
	  * 
	  * @param path
	  * @param database
	  * @param user
	  * @param pwd
	  * @return
	  */
	 public boolean executeSqlScript(String path, String database, String user, String pwd) {
//	  try {
//	   Class.forName("com.mysql.jdbc.Driver");
//	   Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + database, user, pwd);
//
//	   // Initialize object for ScripRunner
//	   ScriptRunner sr = new ScriptRunner(con);
//
//	   // Give the input file to Reader
//	   Reader reader = new BufferedReader(new FileReader(path));
//
//	   // Exctute script
//	   sr.runScript(reader);
//
//	  } catch (Exception e) {
//	   System.err.println("Failed to Execute" + path + " The error is " + e.getMessage());
//	  }
	  return true;
	 }

	 public String getHost() {
	  return host;
	 }

	 public void setHost(String host) {
	  this.host = host;
	 }

}
