package registration.submit.login;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


@WebServlet("/appointment")
@MultipartConfig(maxFileSize = 16177215)
public class appointmennt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public appointmennt() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ouefig");
		appointmentCustomerDAO acd=new appointmentCustomerDAOimpl();
		String doc_email=request.getParameter("doc_email");
		String id=request.getParameter("id");
		System.out.println(id);
		String date=request.getParameter("date");
		String dia=request.getParameter("dia");
		String email=acd.takepatientemail(id);
		String check=acd.checkpatientdetails(id);
		System.out.println(check);
		/*InputStream inputStream = null;
		Part filePart = request.getPart("file");
		if (filePart != null) {
            System.out.println(filePart.getName());
            System.out.println(filePart.getSize());
            System.out.println(filePart.getContentType());
            inputStream = filePart.getInputStream();
        }
		System.out.println("out");
		//if(check.equals("YES")) {
			acd.updatepatientdetails(email, id, date, doc_email, dia, inputStream);
		//}
		//else {
			
		//}*/
	}

	
	@SuppressWarnings({ "unused", "unused" })
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		appointmentCustomerDAO acd=new appointmentCustomerDAOimpl();
		
		String type=request.getParameter("type");
		if(type.equals("appointment")) {
			String email=request.getParameter("email");
			String date=request.getParameter("date");
			String str=acd.getCus(email,date);
			response.getOutputStream().print(str);
		}
		else if(type.equals("futureappointment")) {
			String email=request.getParameter("email");
			String date=request.getParameter("date");
			String str=acd.getfutureCus(email, date);
			response.getOutputStream().print(str);
		}
		else if(type.equals("patientdetails")) {
			String id=request.getParameter("patientid");
			String patientdetails=acd.getPatientDetails(id);
			response.getOutputStream().print(patientdetails);
		}
		else if(type.equals("patientdetailsdate")) {
			String date=request.getParameter("date");
			String email=request.getParameter("email");
			String patientdetails=acd.getpatientbydate(date, email);
			response.getOutputStream().print(patientdetails);
			
		}
		else if(type.equals("patientdetailsbyid")) {
			String id=request.getParameter("patientid");
			String email=request.getParameter("email");
			String patientdetails=acd.getPatientHistoryFromDoctorByID(id,email);
			response.getOutputStream().print(patientdetails);
		}
		else if(type.equals("all")) {
			String email=request.getParameter("email");
			String patientdetails=acd.getallhistory(email);
			response.getOutputStream().print(patientdetails);
		}
		else if(type.equals("searchbydoctorname")) {
			String doctorname=request.getParameter("doctorname");
			String res=acd.getbydoctorname(doctorname);
			response.getOutputStream().print(res);
		}
		else if(type.equals("searchdoctordetails")) {
			String doctorEmail=request.getParameter("doctoremail");
			String res=acd.getdoctordetails(doctorEmail);
			response.getOutputStream().print(res);
		}
		else if(type.equals("reqapp")) {
			String date=request.getParameter("date");
			String time=request.getParameter("time");
			String treatment=request.getParameter("treatment");
			String email=request.getParameter("patiemail");
			String name=acd.getpatientname(email);
			String id=acd.getpatientID(email);
			String docname=request.getParameter("docname");
			String docemail=request.getParameter("docemail");
			String hosid=request.getParameter("hosid");
			appointmentCustomer ac=new appointmentCustomer();
			ac.setPatient_name(name);
			ac.setPatient_email(email);
			ac.setPatient_id(id);
			ac.setDoctor_name(docname);
			ac.setDoctor_email(docemail);
			ac.setApp_time(time);
			ac.setApp_date(date);
			ac.setTreatment(treatment);
			ac.setHosid(hosid);
			acd.insertappointment(ac);
			String res="Your Appointment Request is sent to Doctor.They will check and confirm your appointment";
			response.getOutputStream().print(res);
		}
		else if(type.equals("detailsDoctName")) {
			String name=request.getParameter("name");
			String res=acd.getDoctorDetailsName(name);
			response.getOutputStream().print(res);
		}
		else if(type.equals("detailsDoctCity")) {
			String city=request.getParameter("city");
			String res=acd.getDoctorDetailsCity(city);
			response.getOutputStream().print(res);
		}
		else if(type.equals("showappointment")) {
			String email=request.getParameter("email");
			String res=acd.showappointment(email);
			response.getOutputStream().print(res);
		}
		else if(type.equals("cancelappointment")) {
			String pati_email=request.getParameter("pati_email");
			String doc_email=request.getParameter("doc_email");
			String app_time=request.getParameter("app_time");
			String app_date=request.getParameter("app_date");
			String treatment=request.getParameter("treatment");
			acd.cancelappointment(pati_email, doc_email, app_time, app_date, treatment);
			String res=acd.showappointment(pati_email);
			response.getOutputStream().print(res);
		}
		else if(type.equals("requestappointment")) {
			String doc_email=request.getParameter("email");
			String res=acd.requestappointment(doc_email);
			response.getOutputStream().print(res);
		}
		else if(type.equals("totalappsearch")) {
			String doc_email=request.getParameter("email");
			String date=request.getParameter("date");
			String res=acd.dateappsearch(doc_email, date);
			response.getOutputStream().print(res);
		}
		else if(type.equals("fixdatetimeappointment")) {
			
			String doc_email=request.getParameter("email");
			String pati_email=request.getParameter("pati_email");
			String date=request.getParameter("date");
			String time=request.getParameter("time");
			String tokenno=request.getParameter("token");
			acd.updateconfirmappointmentdatetime(doc_email, pati_email, date, time, tokenno);
			String res=acd.requestappointment(doc_email);
			response.getOutputStream().print(res);
			
		}
		else if(type.equals("finishappointment")) {
			System.out.println("hsdgc");
			//Part ff=request.
			//String file=request.getParameter("file");
			//System.out.println(file);
			//String Path=file.substring(5);
			Part filePart = request.getPart("Path");
			//System.out.println(filePart);
			/*String doc_email=request.getParameter("doc_email");
			String id=request.getParameter("id");
			String date=request.getParameter("date");
			String dia=request.getParameter("dia");
			String email=acd.takepatientemail(id);
			String check=acd.checkpatientdetails(id);
			InputStream inputStream = null;
			Part filePart = request.getPart("file");
			if (filePart != null) {
	            System.out.println(filePart.getName());
	            System.out.println(filePart.getSize());
	            System.out.println(filePart.getContentType());
	            inputStream = filePart.getInputStream();
	        }
			System.out.println("out");
			//if(check.equals("YES")) {
				acd.updatepatientdetails(email, id, date, doc_email, dia, inputStream);
			//}
			//else {
				
			//}*/
		}
		else if(type.equals("labname")) {
			String name=request.getParameter("name");
			String res=acd.labname(name);
			response.getOutputStream().print(res);
		}
		else if(type.equals("patiname")) {
			String id=request.getParameter("id");
			String res=acd.patientname(id);
			response.getOutputStream().print(res);
		}
		else if(type.equals("lab")) {
			String pat_id=request.getParameter("id");
			String pat_name=request.getParameter("name");
			String test=request.getParameter("test");
			String date=request.getParameter("date");
			String doc_email=request.getParameter("doc_email");
			String lab_email=request.getParameter("lab_email");
			String doc_name=acd.getDocname(doc_email);
			appointmentCustomer ac=new appointmentCustomer();
			ac.setLab_email(lab_email);
			ac.setDate(date);
			ac.setDoctor_name(doc_name);
			ac.setDoctor_email(doc_email);
			ac.setPatient_name(pat_name);
			ac.setPatient_id(pat_id);
			ac.setTest(test);
			acd.insertlabtest(ac);
		}
		else if(type.equals("todaytest")) {
			String date=request.getParameter("date");
			String email=request.getParameter("email");
			String name=acd.todayLabTest(date, email);
			response.getOutputStream().print(name);
		}
		else if(type.equals("previoustest")) {
			String date=request.getParameter("date");
			String email=request.getParameter("email");
			String name=acd.previousLabTest(date, email);
			response.getOutputStream().print(name);
		}
		else if(type.equals("labtestupload")) {
			String id=request.getParameter("id");
			String email=request.getParameter("email");
			String na=acd.getdoctornameandhospital(email);
			response.getOutputStream().print(na);
		}
		else if(type.equals("doctordetails")) {
			String email=request.getParameter("email");
			String id=acd.gethospitalid(email);
			String na=acd.getdoctordetailshospital(id);
			response.getOutputStream().print(na);
		}
		else if(type.equals("hosappointment")) {
			String date=request.getParameter("date");
			String email=request.getParameter("email");
			String id=acd.gethospitalid(email);
			String na=acd.gethospitaltodayappointment(date, id);
			response.getOutputStream().print(na);
		}
		else if(type.equals("futurehosappointment")) {
			String date=request.getParameter("date");
			String email=request.getParameter("email");
			String id=acd.gethospitalid(email);
			String na=acd.gethospitalfutureappointment(date, id);
			response.getOutputStream().print(na);
		}
		else if(type.equals("hosrequestappointment")){
			String email=request.getParameter("email");
			String id=acd.gethospitalid(email);
			String na=acd.requestapphospital(id);
			response.getOutputStream().print(na);
		}
		else if(type.equals("hosfixdatetimeappointment")) {
			String email=request.getParameter("email");
			String pati_email=request.getParameter("pati_email");
			String date=request.getParameter("date");
			String time=request.getParameter("time");
			String tokenno=request.getParameter("token");
			String id=acd.gethospitalid(email);
			String na=acd.fixappointment(id, pati_email, date, time, tokenno);
			response.getOutputStream().print(na);
		}
		else if(type.equals("hoslab")) {
			String pat_id=request.getParameter("id");
			String pat_name=request.getParameter("name");
			String test=request.getParameter("test");
			String date=request.getParameter("date");
			String doc_email=request.getParameter("doc_email");
			String doc_name=request.getParameter("doc_name");
			String lab_email=request.getParameter("lab_email");
			appointmentCustomer ac=new appointmentCustomer();
			ac.setLab_email(lab_email);
			ac.setDate(date);
			ac.setDoctor_name(doc_name);
			ac.setDoctor_email(doc_email);
			ac.setPatient_name(pat_name);
			ac.setPatient_id(pat_id);
			ac.setTest(test);
			acd.insertlabtest(ac);
		}
		else if(type.equals("hosnamereg")) {
			String id=request.getParameter("id");
			String name=acd.gethospitalnamereg(id);
			response.getOutputStream().print(name);
		}
		else {
			String email=request.getParameter("email");
			String name=acd.getDocname(email);
			response.getOutputStream().print(name);
		}
	}

}
