package registration.submit.login;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.Part;



public class appointmentCustomerDAOimpl implements appointmentCustomerDAO {

	static Connection con;
	static PreparedStatement ps;
	@Override
	public String getCus(String email,String todaydate) {
		String app_detail="";
		try {
			con=MyConnectionProvider.getCon();
			ps=con.prepareStatement("select * from appointment where doctor_email=?");
			ps.setString(1, email);
			ResultSet rs=ps.executeQuery();
			int i=0;
			
			while(rs.next()) {
				String date1=rs.getString(7);
				int dd=Integer.valueOf(date1.substring(8, 10));
				
				String mm=date1.substring(5, 7);
				
				
				int yy=Integer.valueOf(date1.substring(0, 4));
				date1=dd+"-"+mm+"-"+yy;
				
				
				if(date1.equals(todaydate)) {
				i++;
				String id=rs.getString(3);
				String value=id+"&&"+rs.getString(8)+"&&"+i;
				app_detail+="<tr><td>"+i+"</td><td>"+rs.getString(6)+"</td><td>"+id+"</td><td>"+rs.getString(1)+"</td><td>"+rs.getString(8)+"</td><td><button class='btn-info text-center byID111' style='border-radius: 18px;' id='todayapp"+i+"' value="+id+" onclick='todayapp1("+i+")'>details</button></td><td><input type='file' id='uploadPrescriptionFile"+i+"'></td><td><button class='btn-info text-center' style='border-radius: 18px;' id='finishappointment"+i+"' value="+value+" onclick=finishappointment("+i+")>Finish</button><br>";
				//app_detail+="<tr><td>"+i+"</td><td>"+rs.getString(1)+"</td><td>"+email+"</td><td>"+rs.getString(6)+"</td><td><button class='btn-info' id='emailver"+i+"' value="+email+" onclick='verifydoc("+i+")'>YES</button></td></tr>";
				}
			}
			
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return app_detail;
	}
	@Override
	public String getDocname(String email) {
		String doc_name="";
		try {
			con=MyConnectionProvider.getCon();
			
			ps=con.prepareStatement("select * from doctor where email=?");
			ps.setString(1, email);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
			doc_name=rs.getString(1);
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
		return doc_name;
	}
	@Override
	public String getfutureCus(String email, String date) {
		String app_detail="";
		try {
			con=MyConnectionProvider.getCon();
			ps=con.prepareStatement("select * from appointment where doctor_email=?");
			ps.setString(1, email);
			ResultSet rs=ps.executeQuery();
			int i=0;
			
			while(rs.next()) {
				String date1=rs.getString(7);
				int dd=Integer.valueOf(date1.substring(8, 10));
				
				int mm=Integer.valueOf(date1.substring(5, 7));
				
				int yy=Integer.valueOf(date1.substring(0, 4));
				
				int dd1=Integer.valueOf(date.substring(8, 10));
				int mm1=Integer.valueOf(date.substring(5, 7));
				int yy1=Integer.valueOf(date.substring(0, 4));
				
				if(dd1<dd || mm1<mm || yy1<yy) {
				i++;
				String id=rs.getString(3);
				app_detail+="<tr><td>"+i+"</td><td>"+rs.getString(7)+"</td><td>"+rs.getString(6)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(1)+"</td><td>"+rs.getString(8)+"</td><td><button class='btn-info text-center' style='border-radius: 18px;' id='todayapp"+i+"' value="+id+" onclick='todayapp1("+i+")'>"+"details"+"</button></td></tr><br>";
				}
			}
			
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return app_detail;
	}
	@Override
	public String getPatientDetails(String id) {
		String patientdetails="";
		try {
			con=MyConnectionProvider.getCon();
			ps=con.prepareStatement("select * from patient_details where id=?");
			ps.setString(1, id);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				int i=0;
				String date1=rs.getString(3);
				String[] date=date1.split("&");
				String doctor1=rs.getString(4);
				String[] doctor=doctor1.split("&");
				String diagnosis1=rs.getString(5);
				String[] diagnosis=diagnosis1.split("&");
				String prescription1=rs.getString(6);
				String[] prescription=prescription1.split("&");
				String labtest1=rs.getString(7);
				String[] labtest=labtest1.split("&");
				String report1=rs.getString(8);
				String[] report=report1.split("&");
				for(int j=date.length-1;j>=0;j--) {
					i++;
					patientdetails+="<tr><td>"+i+"</td><td>"+date[j]+"</td><td>"+doctor[j]+"</td><td>"+diagnosis[j]+"</td><td><button class='btn-info text-center' style='border-radius: 18px;'>Prescription</botton></td>";
					if(labtest[j].equals("YES")) {
						patientdetails+="<td>YES</td><td><button class='btn-info text-center' style='border-radius: 18px;'>report</botton></td></tr><br>";
					}
					else {
						patientdetails+="<td>NO</td></tr><br>";
					}
				}
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return patientdetails;
	}
	@Override
	public String getPatientHistoryFromDoctorByID(String id,String email) {
		String patientdetails="";
		String patientname="";
		try {
			con=MyConnectionProvider.getCon();
			ps=con.prepareStatement("select * from doctor where email=?");
			ps.setString(1, email);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				int i=0;
				String date1=rs.getString(11);
				String[] datesearch=date1.split("&");
				String patientid1=rs.getString(12);
				String[] patientid=patientid1.split("&");
				for(int j=datesearch.length-1;j>=0;j--) {
					String nn=patientid[j];
					if(nn.equals(id)) {
						i++;
						try {
							ps=con.prepareStatement("select * from patient where id=?");
							ps.setString(1, nn);
							ResultSet rs1=ps.executeQuery();
							while(rs1.next()) {
								patientname=rs1.getString(1);
							}
						}
						catch(Exception e) {
							System.out.println(e);
						}
						String xx=datesearch[j];
						patientdetails+="<tr><td>"+i+"</td><td>"+xx+"</td><td>"+patientname+"</td><td><button class='btn-info text-center' style='border-radius: 18px;' id='bydate"+i+"' value="+nn+" onclick='bypatientidsearch11("+i+")'>Details</botton></td></tr><br>";
					}
				}
			}
		}
		catch(Exception e) {
			System.out.print(e);
		}
		return patientdetails;
	}
	@Override
	public String getpatientbydate(String date,String email) {
		String patientdetails="";
		String patientname="";
		try {
			con=MyConnectionProvider.getCon();
			ps=con.prepareStatement("select * from doctor where email=?");
			ps.setString(1, email);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				int i=0;
				String date1=rs.getString(11);
				String[] datesearch=date1.split("&");
				String patientid1=rs.getString(12);
				String[] patientid=patientid1.split("&");
				int dd1=Integer.valueOf(date.substring(8, 10));
				int mm1=Integer.valueOf(date.substring(5, 7));				
				int yy1=Integer.valueOf(date.substring(0, 4));
				for(int j=datesearch.length-1;j>=0;j--) {
					String xx=datesearch[j];
					int dd=Integer.valueOf(xx.substring(8, 10));
					int mm=Integer.valueOf(xx.substring(5, 7));
					int yy=Integer.valueOf(xx.substring(0, 4));
					if(dd==dd1 && mm==mm1 && yy==yy1) {
						i++;
						try {
							ps=con.prepareStatement("select * from patient where id=?");
							ps.setString(1, patientid[j]);
							ResultSet rs1=ps.executeQuery();
							while(rs1.next()) {
								patientname=rs1.getString(1);
							}
						}
						catch(Exception e) {
							System.out.println(e);
						}
						var nn=patientid[j];
						patientdetails+="<tr><td>"+i+"</td><td>"+xx+"</td><td>"+patientname+"</td><td><button class='btn-info text-center' style='border-radius: 18px;' id='bydate"+i+"' value="+nn+" onclick='bypatientidsearch11("+i+")'>Details</botton></td></tr><br>";
					}
				}
				
			}
		}
		catch(Exception e) {
			System.out.print(e);
		}
		return patientdetails;
	}
	@Override
	public String getallhistory(String email) {
		String patientdetails="";
		String patientname="";
		try {
			con=MyConnectionProvider.getCon();
			ps=con.prepareStatement("select * from doctor where email=?");
			ps.setString(1, email);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				int i=0;
				String date1=rs.getString(11);
				String[] datesearch=date1.split("&");
				String patientid1=rs.getString(12);
				String[] patientid=patientid1.split("&");
				for(int j=datesearch.length-1;j>=0;j--) {
					String xx=datesearch[j];
					
						i++;
						try {
							ps=con.prepareStatement("select * from patient where id=?");
							ps.setString(1, patientid[j]);
							ResultSet rs1=ps.executeQuery();
							while(rs1.next()) {
								patientname=rs1.getString(1);
							}
						}
						catch(Exception e) {
							System.out.println(e);
						}
						var nn=patientid[j];
						patientdetails+="<tr><td>"+i+"</td><td>"+xx+"</td><td>"+patientname+"</td><td><button class='btn-info text-center' style='border-radius: 18px;' id='bydate"+i+"' value="+nn+" onclick='bypatientidsearch11("+i+")'>Details</button></td></tr><br>";
					
				}
				
			}
		}
		catch(Exception e) {
			System.out.print(e);
		}
		return patientdetails;
	}
	@Override
	public String getbydoctorname(String name) {
		String doctorname="";
		try {
			con=MyConnectionProvider.getCon();
			ps=con.prepareStatement("select * from doctor where name=? and verified='YES'");
			ps.setString(1, name);
			ResultSet rs=ps.executeQuery();
			int i=0;
			while(rs.next()) {
				i++;
				doctorname+="<tr><td>"+i+"</td><td>"+rs.getString(1)+"</td><td>"+rs.getString(6)+"</td><td>"+rs.getString(7)+"</td><td>"+rs.getString(5)+"</td><td>"+rs.getString(8)+"</td><td><button class='btn-info text-center' style='border-radius: 18px;' id='byname"+i+"' value="+rs.getString(2)+" onclick='makeanappointment("+i+")'>Make an Appointment</button></td></tr><br>";
			}
			
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return doctorname;
	}
	@Override
	public String getdoctordetails(String email) {
		String doctordetails="";
		try {
			con=MyConnectionProvider.getCon();
			ps=con.prepareStatement("select * from doctor where email=? and verified='YES'");
			ps.setString(1, email);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				doctordetails+=rs.getString(1)+"|||"+rs.getString(2)+"|||"+rs.getString(4)+"|||"+rs.getString(9)+"|||"+rs.getString(13);
			}
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return doctordetails;
	}
	@Override
	public String getpatientname(String email) {
		String name="";
		try {
			con=MyConnectionProvider.getCon();
			ps=con.prepareStatement("select * from patient where email=?");
			ps.setString(1, email);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				name=rs.getString(1);
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return name;
	}
	@Override
	public String getpatientID(String email) {
		String id="";
		try {
			con=MyConnectionProvider.getCon();
			ps=con.prepareStatement("select * from patient where email=?");
			ps.setString(1, email);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				id=rs.getString(4);
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return id;
	}
	@Override
	public int insertappointment(appointmentCustomer ac) {
		int status=0;
		try {
			con=MyConnectionProvider.getCon();
			ps=con.prepareStatement("insert into appointment values (?,?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, ac.getPatient_name());
			ps.setString(2, ac.getPatient_email());
			ps.setString(3, ac.getPatient_id());
			ps.setString(4, ac.getDoctor_name());
			ps.setString(5, ac.getDoctor_email());
			ps.setString(6, ac.getApp_time());
			ps.setString(7, ac.getApp_date());
			ps.setString(8, ac.getTreatment());
			ps.setString(9, "0");
			ps.setString(10, "NO");
			ps.setString(11, ac.getHosid());
			status=ps.executeUpdate();
			con.close();
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return status;
	}
	@Override
	public String getDoctorDetailsName(String name) {
		String result="";
		try {
			con=MyConnectionProvider.getCon();
			ps=con.prepareStatement("select * from doctor where name=? and verified='YES'");
			ps.setString(1, name);
			ResultSet rs=ps.executeQuery();
			int i=0;
			while(rs.next()) {
				i++;
				result+="<tr><td>"+i+"</td><td>"+rs.getString(1)+"</td><td>"+rs.getString(6)+"</td><td>"+rs.getString(5)+"</td><td>"+rs.getString(8)+"</td><td>"+rs.getString(9)+"</td><td>"+rs.getString(4)+"</td></tr>";
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return result;
	}
	@Override
	public String getDoctorDetailsCity(String City) {
		String result="";
		try {
			con=MyConnectionProvider.getCon();
			ps=con.prepareStatement("select * from doctor where city=? and verified='YES'");
			ps.setString(1, City);
			ResultSet rs=ps.executeQuery();
			int i=0;
			while(rs.next()) {
				i++;
				result+="<tr><td>"+i+"</td><td>"+rs.getString(1)+"</td><td>"+rs.getString(6)+"</td><td>"+rs.getString(7)+"</td><td>"+rs.getString(5)+"</td><td>"+rs.getString(8)+"</td><td>"+rs.getString(9)+"</td><td>"+rs.getString(4)+"</td></tr>";
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return result;
	}
	@Override
	public String showappointment(String email) {
		String result="";
		try {
			con=MyConnectionProvider.getCon();
			ps=con.prepareStatement("select * from appointment where patient_email=?");
			ps.setString(1, email);
			ResultSet rs=ps.executeQuery();
			int i=0;
			
			while(rs.next()) {
				i++;
				String verify=rs.getString(10);
				ps=con.prepareStatement("select * from doctor where email=?");
				ps.setString(1, rs.getString(5));
				ResultSet rs1=ps.executeQuery();
				String hosname="",hosaddress="",hoscity="",contact="";
				while(rs1.next()) {
					hosname=rs1.getString(6);
					hosaddress=rs1.getString(7);
					hoscity=rs1.getString(8);
					contact=rs1.getString(4);
				}
				String cancel=rs.getString(2)+"&&"+rs.getString(5)+"&&"+rs.getString(6)+"&&"+rs.getString(7)+"&&"+rs.getString(8);
				if(verify.equals("NO")) {
					result+="<tr><td>"+i+"</td><td>"+rs.getString(4)+"</td><td>"+hosname+"</td><td>"+hosaddress+"</td><td>"+rs.getString(8)+"</td><td>"+hoscity+"</td><td>"+rs.getString(7)+"</td><td>"+rs.getString(6)+"</td><td>"+contact+"</td><td>Your appointment request is still now not fixed by doctor<br><button class='btn btn-danger' style='border-radius: 18px;' id='cancelappointment"+i+"' value="+cancel+" onclick='cancelappointment("+i+")'>Cancel the appointment</button></td><td>NA</td></tr>";
				}
				else {
					result+="<tr><td>"+i+"</td><td>"+rs.getString(4)+"</td><td>"+hosname+"</td><td>"+hosaddress+"</td><td>"+rs.getString(8)+"</td><td>"+hoscity+"</td><td>"+rs.getString(7)+"</td><td>"+rs.getString(6)+"</td><td>"+contact+"</td><td>Your appointment request is still now not fixed by doctor<br><button class='btn btn-danger' style='border-radius: 18px;' id='cancelappointment"+i+"' value="+cancel+" onclick='cancelappointment("+i+")'>Cancel the appointment</button></td><td>"+rs.getString(9)+"</td></tr>";
				}
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return result;
	}
	@Override
	public String getDoctorHospitalAddress(String email) {
		String result="";
		try {
			con=MyConnectionProvider.getCon();
			ps=con.prepareStatement("select * from doctor where email=? and verified='YES'");
			ps.setString(1, email);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				result+=rs.getString(6)+"&&"+rs.getString(7)+"&&"+rs.getString(8)+"&&"+rs.getString(4);
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return result;
	}
	@Override
	public String cancelappointment(String pati_email, String doc_email, String app_time, String app_date,
			String treatment) {
		try {
			con=MyConnectionProvider.getCon();
			ps=con.prepareStatement("delete from appointment where patient_email=? and doctor_email=? and app_time=? and app_date=? and treatment=?");
			ps.setString(1, pati_email);
			ps.setString(2, doc_email);
			ps.setString(3, app_time);
			ps.setString(4, app_date);
			ps.setString(5, treatment);
			ps.executeUpdate();
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return null;
	}
	@Override
	public String requestappointment(String email) {
		String reqapp="";
		try {
			con=MyConnectionProvider.getCon();
			ps=con.prepareStatement("select * from appointment where doctor_email=? and verified='NO'");
			ps.setString(1, email);
			ResultSet rs=ps.executeQuery();
			int i=0;
			while(rs.next()) {
				i++;
				String fixapp=rs.getString(3)+"&&"+rs.getString(7);
				reqapp+="<tr><td>"+i+"</td><td>"+rs.getString(1)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(8)+"</td><td>"+rs.getString(7)+"    "+rs.getString(6)+"</td><td><input type='date' id='fixappdate"+i+"'>  <input type='time' id='fixapptime"+i+"'></td><td><input type='number' id='token"+i+"'</td><td><button class='btn btn-success' style='border-radius: 18px;' id='fixappointment"+i+"' value="+rs.getString(3)+" onclick='fixappointment("+i+")'>Confirm</button>/<button class='btn btn-danger' style='border-radius: 18px;' id='deniedappointment"+i+"' value="+fixapp+" onclick='deniedappointment("+i+")'>Cancel</button></td>";
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return reqapp;
	}
	@Override
	public String dateappsearch(String email, String date) {
		String searchappdate="";
		try {
			con=MyConnectionProvider.getCon();
			ps=con.prepareStatement("select * from appointment where doctor_email=? and app_date=? and verified='YES'");
			ps.setString(1, email);
			ps.setString(2, date);
			ResultSet rs=ps.executeQuery();
			int i=0;
			while(rs.next()) {
				i++;
			}
			searchappdate+=i;
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return searchappdate;
	}
	
	@Override
	public String updateconfirmappointmentdatetime(String doc_email, String pati_email, String date, String time,
			String token) {
		String res="";
		try {
			con=MyConnectionProvider.getCon();
			ps=con.prepareStatement("update appointment set app_time=?,app_date=?,verified='YES',token_no=? where patient_id=? and doctor_email=?");
			ps.setString(1, time);
			ps.setString(2, date);
			ps.setString(3, token);
			ps.setString(4, pati_email);
			ps.setString(5, doc_email);
			ps.executeUpdate();
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return null;
	}
	@Override
	public String checkpatientdetails(String id) {
		String res="ds";
		try {
			con=MyConnectionProvider.getCon();
			ps=con.prepareStatement("select * from patient_details where id=?");
			ps.setString(1, id);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				res="YES";
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return res;
	}
	@Override
	public String updatepatientdetails(String email, String id, String date, String doc_name, String diagnosis,
			InputStream prescription) {
		String date1="",docName="",dia="",pres="",date2="",doctorName="",diag="";
		try {
			con=MyConnectionProvider.getCon();
			ps=con.prepareStatement("select * from patient_details where id=?");
			ps.setString(1, id);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				date1=rs.getString(3);
				docName=rs.getString(4);
				dia=rs.getString(5);
				pres=rs.getString(6);
			}
			date2=date1+date;
			doctorName=docName+doc_name;
			diag=dia+diagnosis;
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
		try {
			ps=con.prepareStatement("update patient_details set date=? where date=?");
			ps.setString(1, date2);
			ps.setString(2, doctorName);
			
			ps.executeUpdate();
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return null;
	}
	@Override
	public String takepatientemail(String id) {
		String email="";
		try {
			con=MyConnectionProvider.getCon();
			ps=con.prepareStatement("select * from patient_details where id=?");
			ps.setString(1, id);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				email=rs.getString(2);
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return email;
	}
	@Override
	public String labname(String name) {
		String details="";
		try {
			con=MyConnectionProvider.getCon();
			ps=con.prepareStatement("select * from lab where name LIKE '"+name+"%'");
			//ps.setString(1, name);
			ResultSet rs=ps.executeQuery();
			int i=0;
			while(rs.next()) {
				i++;
				details+="<tr><td>"+i+"</td><td>"+rs.getString(1)+"</td><td>"+rs.getString(4)+"</td><td>"+rs.getString(5)+"</td><td>"+rs.getString(3)+"</td><td><button class='btn btn-success' style='border-radius: 18px;' id='labmakeappointment"+i+"' value="+rs.getString(2)+" onclick='labmakeappointment("+i+")'>Make a Test</button></td></tr>";
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return details;
	}
	@Override
	public String patientname(String id) {
		String name="";
		try {
			con=MyConnectionProvider.getCon();
			ps=con.prepareStatement("select * from patient where id=?");
			ps.setString(1, id);
			ResultSet rs=ps.executeQuery();
			int i=0;
			while(rs.next()) {
				i++;
				name=rs.getString(1);
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return name;
	}
	@Override
	public int insertlabtest(appointmentCustomer ac) {
		int status=0;
		try {
			con=MyConnectionProvider.getCon();
			ps=con.prepareStatement("insert into lab_test values (?,?,?,?,?,?,?)");
			ps.setString(1, ac.getLab_email());
			ps.setString(2, ac.getDate());
			ps.setString(3, ac.getDoctor_name());
			ps.setString(4, ac.getDoctor_email());
			ps.setString(5, ac.getPatient_name());
			ps.setString(6, ac.getPatient_id());
			ps.setString(7, ac.getTest());
			status=ps.executeUpdate();
			con.close();
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return status;
	}
	@Override
	public String todayLabTest(String date, String email) {
		String result="";
		try {
			con=MyConnectionProvider.getCon();
			ps=con.prepareStatement("select * from lab_test where lab_email=? and date=?");
			ps.setString(1, email);
			ps.setString(2, date);
			ResultSet rs=ps.executeQuery();
			int i=0;
			while(rs.next()) {
				i++;
				result+="<tr><td>"+i+"</td><td>"+rs.getString(7)+"</td><td>"+rs.getString(5)+"</td><td>"+rs.getString(6)+"</td><td>"+rs.getString(3)+"</td><td><button class='btn btn-success' style='border-radius: 18px;' id='labtodaytest"+i+"' value="+rs.getString(6)+" onclick='labtestupload("+i+")'>Make a Test</button></td></tr>";
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return result;
	}
	@Override
	public String previousLabTest(String date, String email) {
		String result="";
		try {
			con=MyConnectionProvider.getCon();
			ps=con.prepareStatement("select * from lab_test where lab_email=?");
			ps.setString(1, email);
			ResultSet rs=ps.executeQuery();
			int i=0;
			while(rs.next()) {
				String date1=rs.getString(2);
				int dd=Integer.valueOf(date1.substring(8, 10));
				int mm=Integer.valueOf(date1.substring(5, 7));
				int yy=Integer.valueOf(date1.substring(0, 4));
				int dd1=Integer.valueOf(date.substring(8, 10));
				int mm1=Integer.valueOf(date.substring(5, 7));
				int yy1=Integer.valueOf(date.substring(0, 4));
				
				if(dd1>dd || mm1>mm || yy1>yy) {
					i++;
					String nn=rs.getString(6)+"&&"+rs.getString(4)+"&&"+rs.getString(5)+"&&"+rs.getString(7);
					result+="<tr><td>"+i+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(7)+"</td><td>"+rs.getString(5)+"</td><td>"+rs.getString(6)+"</td><td>"+rs.getString(3)+"</td><td><button class='btn btn-info' style='border-radius: 18px;' id='labresultpdf"+i+"' value="+nn+" onclick='labtestupload("+i+")'>Upload Result</button></td></tr>";
				}
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return result;
	}
	@Override
	public String getdoctornameandhospital(String email) {
		String res="";
		try {
			con=MyConnectionProvider.getCon();
			ps=con.prepareStatement("select * from doctor where email=? and verified=?");
			ps.setString(1, email);
			ps.setString(2, "YES");
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				res+=rs.getString(1)+"&&"+rs.getString(6);
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return res;
	}
	@Override
	public String getdoctordetailshospital(String email) {
		String doctordetails="";
		try {
			con=MyConnectionProvider.getCon();
			ps=con.prepareStatement("select * from doctor where hosid=? and verified='YES'");
			ps.setString(1, email);
			ResultSet rs=ps.executeQuery();
			int i=0;
			while(rs.next()) {
				i++;
				doctordetails+="<tr><td>"+i+"</td><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(4)+"</td><td>"+rs.getString(9)+"</td></tr>";
			}
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return doctordetails;
	}
	@Override
	public String gethospitalid(String email) {
		String id="";
		try {
			con=MyConnectionProvider.getCon();
			ps=con.prepareStatement("select * from hospital where email=? and verified='YES'");
			ps.setString(1, email);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				id=rs.getString(9);
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return id;
	}
	@Override
	public String gethospitaltodayappointment(String date, String id) {
		String app_detail="";
		try {
			con=MyConnectionProvider.getCon();
			ps=con.prepareStatement("select * from appointment where hosid=?");
			ps.setString(1, id);
			ResultSet rs=ps.executeQuery();
			int i=0;
			
			while(rs.next()) {
				String date1=rs.getString(7);
				int dd=Integer.valueOf(date1.substring(8, 10));
				
				String mm=date1.substring(5, 7);
				
				
				int yy=Integer.valueOf(date1.substring(0, 4));
				date1=dd+"-"+mm+"-"+yy;
				
				
				if(date1.equals(date)) {
					i++;
					String id1=rs.getString(3);
					String value=id1+"&&"+rs.getString(8)+"&&"+i;
					app_detail+="<tr><td>"+i+"</td><td>"+rs.getString(6)+"</td><td>"+rs.getString(4)+"</td><td>"+rs.getString(1)+"</td><td>"+rs.getString(8)+"</td><td><button class='btn-info text-center byID111' style='border-radius: 18px;' id='todayapp"+i+"' value="+id1+" onclick='todayapp1("+i+")'>details</button></td><td><input type='file' id='uploadPrescriptionFile"+i+"'></td><td><button class='btn-info text-center' style='border-radius: 18px;' id='finishappointment"+i+"' value="+value+" onclick=finishappointment("+i+")>Finish</button><br>";
					
				}
			}
			
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return app_detail;
	}
	@Override
	public String gethospitalfutureappointment(String date, String id) {
		String app_detail="";
		try {
			con=MyConnectionProvider.getCon();
			ps=con.prepareStatement("select * from appointment where hosid=?");
			ps.setString(1, id);
			ResultSet rs=ps.executeQuery();
			int i=0;
			
			while(rs.next()) {
				String date1=rs.getString(7);
				int dd=Integer.valueOf(date1.substring(8, 10));
				
				int mm=Integer.valueOf(date1.substring(5, 7));
				
				int yy=Integer.valueOf(date1.substring(0, 4));
				
				int dd1=Integer.valueOf(date.substring(8, 10));
				int mm1=Integer.valueOf(date.substring(5, 7));
				int yy1=Integer.valueOf(date.substring(0, 4));
				
				if(dd1<dd || mm1<mm || yy1<yy) {
				i++;
				String id1=rs.getString(3);
				app_detail+="<tr><td>"+i+"</td><td>"+rs.getString(4)+"</td><td>"+rs.getString(7)+"</td><td>"+rs.getString(6)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(1)+"</td><td>"+rs.getString(8)+"</td><td><button class='btn-info text-center' style='border-radius: 18px;' id='todayapp"+i+"' value="+id1+" onclick='todayapp1("+i+")'>"+"details"+"</button></td></tr><br>";
				}
			}
			
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return app_detail;
	}
	@Override
	public String fixappointment(String id, String pati_email, String date, String time, String tokenno) {
		String res="";
		try {
			con=MyConnectionProvider.getCon();
			ps=con.prepareStatement("update appointment set app_time=?,app_date=?,verified='YES',token_no=? where patient_id=? and hosid=?");
			ps.setString(1, time);
			ps.setString(2, date);
			ps.setString(3, tokenno);
			ps.setString(4, pati_email);
			ps.setString(5, id);
			ps.executeUpdate();
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return null;
	}
	@Override
	public String requestapphospital(String id) {
		String reqapp="";
		try {
			con=MyConnectionProvider.getCon();
			ps=con.prepareStatement("select * from appointment where hosid=? and verified='NO'");
			ps.setString(1, id);
			ResultSet rs=ps.executeQuery();
			int i=0;
			while(rs.next()) {
				i++;
				String fixapp=rs.getString(3)+"&&"+rs.getString(7);
				reqapp+="<tr><td>"+i+"</td><td>"+rs.getString(4)+"</td><td>"+rs.getString(1)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(8)+"</td><td>"+rs.getString(7)+"    "+rs.getString(6)+"</td><td><input type='date' id='fixappdate"+i+"'>  <input type='time' id='fixapptime"+i+"'></td><td><input type='number' id='token"+i+"'</td><td><button class='btn btn-success' style='border-radius: 18px;' id='fixappointment"+i+"' value="+rs.getString(3)+" onclick='fixappointment("+i+")'>Confirm</button>/<button class='btn btn-danger' style='border-radius: 18px;' id='deniedappointment"+i+"' value="+fixapp+" onclick='deniedappointment("+i+")'>Cancel</button></td>";
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return reqapp;
	}
	@Override
	public String gethospitalnamereg(String id) {
		String res="";
		try {
			con=MyConnectionProvider.getCon();
			ps=con.prepareStatement("select * from hospital where id=? and verified='YES'");
			ps.setString(1, id);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				res=rs.getString(1)+"&&"+rs.getString(4);
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return res;
	}
	

}
