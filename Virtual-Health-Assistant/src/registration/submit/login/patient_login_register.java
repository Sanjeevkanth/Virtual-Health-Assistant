package registration.submit.login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/patient_login_register")
public class patient_login_register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public patient_login_register() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PatientCustomerDAO pcd=new PatientCustomerDAOimpl();
		String email=request.getParameter("pemail");
		String password=request.getParameter("ppassword");
		String submitType=request.getParameter("psubmit");
		PatientCustomer pc=pcd.getCustomer(email, password);
		if(submitType.equals("plogin") && pc!=null && pc.getName()!=null) {
			String str="success";
			response.getOutputStream().print(str);
		}
		else if(submitType.equals("pregister")) {
			String name=request.getParameter("pname");
			pc.setName(name);
			pc.setEmail(email);
			pc.setPassword(password);
			pc.setGender(request.getParameter("gender"));
			String DOB=request.getParameter("dob");
			pc.setDob(DOB);
			pc.setPhone_no(request.getParameter("pcontactid"));
			pc.setBlood_grp(request.getParameter("pbloodgroup"));
			pc.setAddress(request.getParameter("paddress"));
			pc.setCity(request.getParameter("pdistrict"));
			String id=pcd.getuserid(name, email, DOB);
			pc.setId(id);
			pcd.insertCustomer(pc);
			String str="success";
			response.getOutputStream().print(str);
		}
		else {			
			String str="invalid EmailID or password!";
			response.getOutputStream().print(str);
			
		}
	}

}
