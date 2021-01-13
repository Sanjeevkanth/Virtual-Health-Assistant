package registration.submit.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class adminDAOimpl implements adminDAO {

	static Connection con;
	static PreparedStatement ps;
	@Override
	public int insertCustomer(admin a) {
		int status=0;
		try {
			con=MyConnectionProvider.getCon();
			ps=con.prepareStatement("insert into admin values (?,?,?)");
			ps.setString(1, a.getName());
			ps.setString(2, a.getEmail());
			ps.setString(3, a.getPassword());
			status=ps.executeUpdate();
			con.close();
		}catch(Exception e) {
			System.out.println(e);
		}
		return status;
	}

	@Override
	public admin getCustomer(String email, String pass) {
		admin a=new admin();
		try {
			con=MyConnectionProvider.getCon();
			ps=con.prepareStatement("select * from admin where email=? and password=?");
			ps.setString(1, email);
			ps.setString(2, pass);
			
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				a.setName(rs.getString(1));
				a.setEmail(rs.getString(2));
				a.setPassword(rs.getString(3));
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		return a;
	}

	@Override
	public admin getCustomer(String email) {
		admin a=new admin();
		try {
			con=MyConnectionProvider.getCon();
			ps=con.prepareStatement("select * from admin where email=?");
			ps.setString(1, email);
			
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				a.setName(rs.getString(1));
				a.setEmail(rs.getString(2));
				a.setPassword(rs.getString(3));
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		return a;
	}

}
