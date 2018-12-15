// Import required java libraries
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;

// Extend HttpServlet class
public class inserisciGiudice extends HttpServlet {
 
   private String message;
   private String connectionUrl = "jdbc:sqlserver://213.140.22.237\\SQLEXPRESS:1433;databaseName=XFactor;user=titze.walter;password=galvani@2018";

   public void init() throws ServletException {
      // Do required initialization
      message = "Inserimento giudice<br>";
   }
   
   public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
			    
	  response.setContentType("text/html");
	  
	  RequestDispatcher view = request.getRequestDispatcher("formInserisciGiudice.jsp");      
      view.forward(request, response);
		
	}

   public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
      
      // Set response content type
      response.setContentType("text/html");
      
      // accesso a database XFactor
      
      try {
          
            // l'istruzione seguente è fondamentale altrimenti non viene caricato il driver
            
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            
			// Load SQL Server JDBC driver and establish connection.
            Connection connection = DriverManager.getConnection(connectionUrl);
			
			//inserimento di un nuovo giudice
            String nome = request.getParameter("nome");
            String cognome = request.getParameter("cognome");
            // selezione dell'ultimo ID
            Statement stmt4 = connection.createStatement();
            String sqlId = "Select Max(ID) as MaxId from Giudice";
            ResultSet rs4 = stmt4.executeQuery(sqlId);
            rs4.next();
            // calcolo nuovo ID
            int newId = rs4.getInt("MaxId") + 1;
            // inserimento del nuovo giudice
            String sql4 = "INSERT INTO Giudice (ID, Nome, Cognome) VALUES (?, ?, ?)";
            PreparedStatement prepStmt = connection.prepareStatement(sql4);
            prepStmt.setInt(1, newId);
            prepStmt.setString(2, nome);
            prepStmt.setString(3, cognome);
            prepStmt.executeUpdate();
            message += "giudice inserito<br>";
	        connection.close();
		} catch (Exception e) {
		    
			message += "problems...";
		}
      
      /*
      // Set response content type
      response.setContentType("text/html");     
      // Actual logic goes here.
      PrintWriter out = response.getWriter();
      out.println(message);
      */
     
      request.setAttribute("message", message);              
      RequestDispatcher view = request.getRequestDispatcher("risposta.jsp");      
      view.forward(request, response);
     
   }

   public void destroy() {
      // do nothing.
   }
}
