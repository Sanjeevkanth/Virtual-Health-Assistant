package registration.submit.login;

import java.io.InputStream;

import javax.servlet.http.Part;

public interface appointmentCustomerDAO {

	public String getCus(String email,String todaydate);
	public String getDocname(String email);
	public String getfutureCus(String email,String date);
	public String getPatientDetails(String id);
	public String getPatientHistoryFromDoctorByID(String id,String email);
	public String getpatientbydate(String date,String email);
	public String getallhistory(String email);
	public String getbydoctorname(String name);
	public String getdoctordetails(String email);
	public String getpatientname(String email);
	public String getpatientID(String email);
	public int insertappointment(appointmentCustomer ac);
	public String getDoctorDetailsName(String name);
	public String getDoctorDetailsCity(String City);
	public String getDoctorHospitalAddress(String email);
	public String showappointment(String email);
	public String cancelappointment(String pati_email,String doc_email,String app_time,String app_date,String treatment);
	public String requestappointment(String email);
	public String dateappsearch(String email,String date);
	public String updateconfirmappointmentdatetime(String doc_email,String pati_email,String date,String time,String token);
	public String checkpatientdetails(String id);
	public String updatepatientdetails(String email,String id,String date,String doc_name,String diagnosis,InputStream prescription);
	public String takepatientemail(String id);
	public String labname(String name);
	public String patientname(String id);
	public int insertlabtest(appointmentCustomer ac);
	public String todayLabTest(String date,String email);
	public String previousLabTest(String date,String email);
	public String getdoctornameandhospital(String email);
	public String getdoctordetailshospital(String email);
	public String gethospitalid(String email);
	public String gethospitaltodayappointment(String date,String id);
	public String gethospitalfutureappointment(String date,String id);
	public String fixappointment(String id,String pati_email,String date,String time,String tokenno);
	public String requestapphospital(String id);
	public String gethospitalnamereg(String id);
}