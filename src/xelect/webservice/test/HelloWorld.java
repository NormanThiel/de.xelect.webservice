package xelect.webservice.test;




import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.ws.rs.*;


@Path( "/helloworld" )
public class HelloWorld{
	
	
	private DataSource ds;
	public String text;
	Connection con;
 
	public HelloWorld() {
	  try {
		Context ctx = new InitialContext();
		ds = (DataSource)ctx.lookup("java:comp/env/jdbc/xelectdb");
	  } catch (NamingException e) {
		e.printStackTrace();
	  }
	
	  try {
		 con = ds.getConnection();
		settext(con, "xelectdb");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
	}



   private void settext(Connection con, String dbname) throws SQLException{

	   Statement stmt = null;
	    String query = "select * FROM Testeintrag";
	  
	    try {
	        stmt = con.createStatement();
	        ResultSet rs = stmt.executeQuery(query);
	        while (rs.next()) {
	        	  String ergebnis = rs.getString("Testeintrag");  	
	        	  text=ergebnis;
	           
	        }
	    } catch (SQLException e ) {
	        e.printStackTrace();
	    } finally {
	        if (stmt != null) { stmt.close(); }}
	            
	    
	      
	    
	    
	    
	}



@GET @Produces( "text/plain" )
   public String halloText()
   {
	   
  
      return "Hallo " + text;
   }

   
  
   
   

	
 
}