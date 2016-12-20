package at.fhj.swd14.pse.database;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


//import org.apache.ibatis.jdbc.ScriptRunner;

/**
 * PREPARE FOR SQL-TESTS
 * 
 * @author Schönegger, Koch
 */

public class ScriptHandler {
	
	/**
	 * The LOGGER to use
	 */
	private static final Logger LOGGER = LogManager.getLogger(ScriptHandler.class);


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
//		 LOGGER.error("Failed to Execute" + path + " The error is " + e.getMessage());
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
