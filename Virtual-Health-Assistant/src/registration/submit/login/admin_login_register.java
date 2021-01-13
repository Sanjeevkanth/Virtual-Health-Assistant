package registration.submit.login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/admin_login_register")
public class admin_login_register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public admin_login_register() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		adminDAO ad=new adminDAOimpl();
		String email=request.getParameter("ademail");
		String password=request.getParameter("adpassword");
		String type=request.getParameter("submit");
		
		admin a=ad.getCustomer(email, password);
		if(type.equals("adminlogin") && a!=null) {
			String str="success";
			response.getOutputStream().print(str);
		}
		else if(type.equals("adreg")) {
			admin a1=ad.getCustomer(email);
			if(a1.getEmail()!=null) {
				String str="This Email is Already Registered!!!";
				response.getOutputStream().print(str);
			}
			else {
				a.setName(request.getParameter("adname"));
				a.setEmail(email);
				a.setPassword(password);
				ad.insertCustomer(a);
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
