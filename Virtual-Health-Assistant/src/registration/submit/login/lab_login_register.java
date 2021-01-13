package registration.submit.login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/lab_login_register")
public class lab_login_register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public lab_login_register() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		labDAO ld=new labDAOimpl();
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		String type=request.getParameter("type");
		lab l=ld.getCustomer(email, password);
		if(type.equals("login") && l!=null) {
			String s1=l.getVerified();
			if(s1.equals("NO")) {
				String str="verification";
				response.getOutputStream().print(str);
			}
			else {
				String str="success";
				response.getOutputStream().print(str);
			}
		}
		else if(type.equals("register")) {
			lab l1=ld.getcus(email);
			if(l1.getEmail()!=null) {
				String str="This Email is Already Registered!!!";
				response.getOutputStream().print(str);
			}
			else {
				l.setName(request.getParameter("name"));
				l.setEmail(email);
				l.setPhone_no(request.getParameter("phone"));
				l.setAddress(request.getParameter("address"));
				l.setCity(request.getParameter("city"));
				l.setPassword(password);
				ld.insertCustomer(l);
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
