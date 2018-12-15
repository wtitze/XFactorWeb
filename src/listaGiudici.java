// Import required java libraries
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


// Extend HttpServlet class
public class listaGiudici extends HttpServlet {
 
   private String message;
   private String connectionUrl = "jdbc:sqlserver://213.140.22.237\\SQLEXPRESS:1433;databaseName=XFactor;user=titze.walter;password=galvani@2018";

   public void init() throws ServletException {
      // Do required initialization
      message = "Lista dei giudici di XFactor di tutte le edizioni<br>";
   }

   public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
      
      // Set response content type
      response.setContentType("text/html");
      
      // accesso a database XFactor
      
      try {
          
            // l'istruzione seguente è fondamentale altrimenti non viene caricato il driver
            
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            
			// Load SQL Server JDBC driver and establish connection.
            Connection connection = DriverManager.getConnection(connectionUrl);
			
			Statement stmt = connection.createStatement();

			String sql = "SELECT * FROM Giudice";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				//Retrieve by column name
				String first = rs.getString("Nome");
				String last = rs.getString("Cognome");
                
                message += first + " " + last + "<br>";

	        }
	        rs.close();
	        connection.close();
		} catch (Exception e) {
		    
			message += "problems...";
		}
      
      // Set response content type
      response.setContentType("text/html");     
      // Actual logic goes here.
      PrintWriter out = response.getWriter();
      out.println(message);
   }

   public void destroy() {
      // do nothing.
   }
}
