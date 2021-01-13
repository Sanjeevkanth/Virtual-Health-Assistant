package registration.submit.login;

public interface labDAO {
	public int insertCustomer(lab l);
	public lab getCustomer(String email,String pass);
	public lab getcus(String email);
	public String getNotVerify(String verify);
	public String getverify(String verify);
	public String cancelverify(String verify);
}
