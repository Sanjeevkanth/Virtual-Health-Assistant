package registration.submit.login;

public interface PatientCustomerDAO {

	public int insertCustomer(PatientCustomer pc);
	public PatientCustomer getCustomer(String emailid,String password);
	public String getuserid(String name,String email,String DOB);
}
