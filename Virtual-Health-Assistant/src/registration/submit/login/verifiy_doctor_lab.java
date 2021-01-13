package registration.submit.login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/verifiy_doctor_lab")
public class verifiy_doctor_lab extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public verifiy_doctor_lab() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type=request.getParameter("type");
		if(type.equals("doctor")) {
			DoctorCustomerDAO dcd=new DoctorCustomerDAOImpl();
			String verify="NO";
			String res=dcd.getNotVerify(verify);
			response.getOutputStream().print(res);
		}
		else if(type.equals("docver")) {
			DoctorCustomerDAO dcd=new DoctorCustomerDAOImpl();
			String str=request.getParameter("emailid");
			String res1=dcd.getVerify(str);
			String verify="NO";
			String res=dcd.getNotVerify(verify);
			response.getOutputStream().print(res);
		}
		else if(type.equals("docvercancel")) {
			DoctorCustomerDAO dcd=new DoctorCustomerDAOImpl();
			String str=request.getParameter("emailid");
			String res1=dcd.cancelverify(str);
			String verify="NO";
			String res=dcd.getNotVerify(verify);
			response.getOutputStream().print(res);
		}
		else if(type.equals("lab")) {
			labDAO ld=new labDAOimpl();
			String verify="NO";
			String res=ld.getNotVerify(verify);
			response.getOutputStream().print(res);
		}
		else if(type.equals("labver")) {
			labDAO ld=new labDAOimpl();
			String str=request.getParameter("emailid");
			String res1=ld.getverify(str);
			String verify="NO";
			String res=ld.getNotVerify(verify);
			response.getOutputStream().print(res);
		}
		else if(type.equals("labvercancel")) {
			labDAO ld=new labDAOimpl();
			String str=request.getParameter("emailid");
			String res1=ld.cancelverify(str);
			String verify="NO";
			String res=ld.getNotVerify(verify);
			response.getOutputStream().print(res);
		}
		else if(type.equals("hos")) {
			DoctorCustomerDAO dcd=new DoctorCustomerDAOImpl();
			String verify="NO";
			String res=dcd.getNotverifiedhospital(verify);
			response.getOutputStream().print(res);
		}
		else if(type.equals("hosver")) {
			DoctorCustomerDAO dcd=new DoctorCustomerDAOImpl();
			String str=request.getParameter("emailid");
			String res1=dcd.getverified(str);
			String verify="NO";
			String res=dcd.getNotverifiedhospital(verify);
			response.getOutputStream().print(res);
		}
		else if(type.equals("hosvercancel")) {
			DoctorCustomerDAO dcd=new DoctorCustomerDAOImpl();
			String str=request.getParameter("emailid");
			String res1=dcd.cancelverified(str);
			String verify="NO";
			String res=dcd.getNotverifiedhospital(verify);
			response.getOutputStream().print(res);
		}
		else {
			
		}
	}

}
