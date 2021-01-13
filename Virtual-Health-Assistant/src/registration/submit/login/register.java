package registration.submit.login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class register
 */
@WebServlet("/register")
public class register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DoctorCustomerDAO dcd=new DoctorCustomerDAOImpl();
		String email=request.getParameter("demail");
		String password=request.getParameter("dpassword1");
		String submitType=request.getParameter("dsubmit");
		
		DoctorCustomer dc=dcd.getCustomer(email, password);
		if(submitType.equals("dlogin") && dc!=null && dc.getEmail()!=null) {
			String str="success";
			response.getOutputStream().print(str);
		}else if(submitType.equals("dregister")) {
			System.out.println("out1");
			dc.setName(request.getParameter("dname"));
			dc.setEmail(email);
			dc.setPassword(password);
			dc.setPhone_no(request.getParameter("dcontact"));
			dc.setHospital(request.getParameter("dhospital"));
			dc.setDept(request.getParameter("ddept"));
			dc.setCity(request.getParameter("ddistrict"));
			dc.setOpenhrs(request.getParameter("openhrs"));
			dcd.insertCustomer(dc);
			System.out.println("out");
			String str="success";
			response.getOutputStream().print(str);
			//request.setAttribute("message", "Registration Successfully");
			//request.getRequestDispatcher("welcome1.html").forward(request, response);
		}
		else {			
			System.out.println("fail");
			String str="invalid userID or password!";
			response.getOutputStream().print(str);
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DoctorCustomerDAO dcd=new DoctorCustomerDAOImpl();
		String email=request.getParameter("demail");
		String password=request.getParameter("dpassword1");
		String submitType=request.getParameter("dsubmit");
		
		DoctorCustomer dc=dcd.getCustomer(email, password);
		if(submitType.equals("dlogin") && dc!=null && dc.getEmail()!=null) {
			String str="success";
			response.getOutputStream().print(str);
		}else if(submitType.equals("dregister")) {
			System.out.println("out1");
			dc.setName(request.getParameter("dname"));
			dc.setEmail(email);
			dc.setPassword(password);
			dc.setPhone_no(request.getParameter("dcontact"));
			dc.setHospital(request.getParameter("dhospital"));
			dc.setDept(request.getParameter("ddept"));
			dc.setCity(request.getParameter("ddistrict"));
			dc.setOpenhrs(request.getParameter("openhrs"));
			dcd.insertCustomer(dc);
			System.out.println("out");
			String str="success";
			response.getOutputStream().print(str);
			//request.setAttribute("message", "Registration Successfully");
			//request.getRequestDispatcher("welcome1.html").forward(request, response);
		}
		else {			
			System.out.println("fail");
			String str="invalid userID or password!";
			response.getOutputStream().print(str);
			
		}
	}

}
