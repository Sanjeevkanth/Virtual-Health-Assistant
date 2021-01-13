package registration.submit.login;

public interface DoctorCustomerDAO {

	public int insertCustomer(DoctorCustomer dc);
	public DoctorCustomer getCustomer(String emailid,String pass);
	public DoctorCustomer getCustomer(String emailid);
	public DoctorCustomer getCustomer1(String phone_no);
	public String getNotVerify(String verify);
	public String getVerify(String ver);
	public String cancelverify(String email);
	public DoctorCustomer getdetails(String email,String password);
	public DoctorCustomer gethospital(String email);
	public String createid(String name,String email,String phone);
	public int inserthospital(DoctorCustomer dc);
	public String getNotverifiedhospital(String verify);
	public String getverified(String ver);
	public String cancelverified(String email);
}
