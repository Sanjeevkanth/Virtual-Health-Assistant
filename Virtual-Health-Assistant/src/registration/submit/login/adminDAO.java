package registration.submit.login;

public interface adminDAO {
	public int insertCustomer(admin a);
	public admin getCustomer(String email,String pass);
	public admin getCustomer(String email);
}
