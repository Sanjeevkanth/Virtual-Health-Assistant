package registration.submit.login;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


@WebServlet("/updateprescription")
@MultipartConfig(maxFileSize = 16177215)  
public class updateprescription extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String dbURL = "jdbc:mysql://localhost:3306/virtusa?autoReconnect=true&useSSL=false";
    private String dbUser = "root";
    private String dbPass = "sanjeev06";
    
    public updateprescription() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		appointmentCustomerDAO acd=new appointmentCustomerDAOimpl();
		String doc_email=request.getParameter("doc_email");
		String id=request.getParameter("id");
		String date=request.getParameter("date");
		String dia=request.getParameter("dia");
		String email=acd.takepatientemail(id);
		String check=acd.checkpatientdetails(id);         
        InputStream inputStream = null;
        Part filePart = request.getPart("file");
        String doc_name=acd.getDocname(doc_email);
        
        if (filePart != null) {
            // prints out some information for debugging
            System.out.println(filePart.getName());
            System.out.println(filePart.getSize());
            System.out.println(filePart.getContentType());
             
            // obtains input stream of the upload file
            inputStream = filePart.getInputStream();
            Connection conn = null; // connection to the database
            String date1="",docName="",dia1="",pres="",date2="",doctorName="",diag="";
            try {
                // connects to the database
            	Class.forName("com.mysql.jdbc.Driver");
            	//DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                conn = DriverManager.getConnection(dbURL, dbUser, dbPass);
     
                // constructs SQL statement
                String sql = "select * from patient_details where id=?";
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setString(1, id);
    			ResultSet rs=statement.executeQuery();
    			while(rs.next()) {
    				date1=rs.getString(3);
    				docName=rs.getString(4);
    				dia1=rs.getString(5);
    				pres=rs.getString(6);
    			}
    			date2=date1+date;
    			doctorName=docName+doc_name;
    			diag=dia1+dia;
            }
            catch(Exception e) {
            	System.out.println(e);
            }
            try {
            	Class.forName("com.mysql.jdbc.Driver");
            	//DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                conn = DriverManager.getConnection(dbURL, dbUser, dbPass);
                PreparedStatement ps=conn.prepareStatement("update patient_details set date=? where date=?");
    			ps.setString(1, date2);
    			ps.setString(2, date1);
    			/*ps.setString(3, diag);
    			if (inputStream != null) {
                    
                    ps.setBlob(3, inputStream);
                }*/
    			ps.executeUpdate();
    		}
    		catch(Exception e) {
    			System.out.println(e);
    		}
        }
	}

}
