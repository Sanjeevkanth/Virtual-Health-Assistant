package registration.submit.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/login-register")
public class Loginregister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public Loginregister() {
        super();
        
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DoctorCustomerDAO dcd=new DoctorCustomerDAOImpl();
		String email=request.getParameter("demail");
		String password=request.getParameter("dpassword1");
		String submitType=request.getParameter("dsubmit");
		
		
		if(submitType.equals("dlogin")) {
			DoctorCustomer dc=dcd.getCustomer(email, password);
			if(dc!=null && dc.getEmail()!=null) {
				String s1=dc.getVerified();
				if(s1.equals("NO")) {
					String str="verification";
					response.getOutputStream().print(str);
				}
				else {
					String str="success";
					response.getOutputStream().print(str);
				}
			}
			else {			
				String str="invalid EmailID or password!";
				response.getOutputStream().print(str);
				
			}
		}
		else if(submitType.equals("dregister")) {
			DoctorCustomer dc=dcd.getCustomer(email, password);
			DoctorCustomer dc1=dcd.getCustomer(email);
			if(dc1.getEmail()!=null) {
				String str="This Email is Already Registered!!!";
				response.getOutputStream().print(str);
			}
			else {
			dc.setName(request.getParameter("dname"));
			dc.setEmail(email);
			dc.setPassword(password);
			dc.setPhone_no(request.getParameter("dcontact"));
			dc.setHospital(request.getParameter("dhospital"));
			dc.setHosadd(request.getParameter("hosadd"));
			dc.setDept(request.getParameter("ddept"));
			dc.setCity(request.getParameter("ddistrict"));
			dc.setOpenhrs(request.getParameter("openhrs"));
			dc.setId(request.getParameter("hosid"));
			dc.setVerified("NO");
			dcd.insertCustomer(dc);
			String str="success";
			response.getOutputStream().print(str);
			}
			
		}
		else if(submitType.equals("login")) {
			DoctorCustomer dc=dcd.getdetails(email, password);
			if(dc!=null && dc.getEmail()!=null) {
				String s1=dc.getVerified();
				if(s1.equals("NO")) {
					String str="verification";
					response.getOutputStream().print(str);
				}
				else {
					String str="success";
					response.getOutputStream().print(str);
				}
			}
			else {			
				String str="invalid EmailID or password!";
				response.getOutputStream().print(str);
				
			}
		}
		else if(submitType.equals("register")) {
			DoctorCustomer dc=dcd.getCustomer(email, password);
			DoctorCustomer dc1=dcd.gethospital(email);
			if(dc1.getEmail()!=null) {
				String str="This Email is Already Registered!!!";
				response.getOutputStream().print(str);
			}
			else {
				String name=request.getParameter("dname");
				dc.setName(name);
				dc.setEmail(email);
				dc.setPassword(password);
				String phone=request.getParameter("dcontact");
				dc.setPhone_no(phone);
				dc.setHosadd(request.getParameter("hosadd"));
				dc.setCity(request.getParameter("ddistrict"));
				dc.setOpenhrs(request.getParameter("openhrs"));
				dc.setVerified("NO");
				String ss=dcd.createid(name, email, phone);
				dc.setId(ss);
				dcd.inserthospital(dc);
				String str="success";
				response.getOutputStream().print(str);
			}
		}
		
		else {			
			String str="invalid EmailID or password!";
			response.getOutputStream().print(str);
			
		}
	}

}
