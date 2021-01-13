package registration.submit.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class labDAOimpl implements labDAO {

	static Connection con;
	static PreparedStatement ps;
	@Override
	public int insertCustomer(lab l) {
		int status=0;
		try {
			con=MyConnectionProvider.getCon();
			ps=con.prepareStatement("insert into lab values (?,?,?,?,?,?,?)");
			ps.setString(1, l.getName());
			ps.setString(2, l.getEmail());
			ps.setString(3, l.getPhone_no());
			ps.setString(4, l.getAddress());
			ps.setString(5, l.getCity());
			ps.setString(6, l.getPassword());
			ps.setString(7, "NO");
			status=ps.executeUpdate();
			con.close();
		}catch(Exception e) {
			System.out.println(e);
		}
		return status;
	}

	@Override
	public lab getCustomer(String email, String pass) {
		lab l=new lab();
		try {
			con=MyConnectionProvider.getCon();
			ps=con.prepareStatement("select * from lab where email=? and password=?");
			ps.setString(1, email);
			ps.setString(2, pass);
			
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				l.setName(rs.getString(1));
				l.setEmail(rs.getString(2));
				l.setPhone_no(rs.getString(3));
				l.setAddress(rs.getString(4));
				l.setCity(rs.getString(5));
				l.setPassword(rs.getString(6));
				l.setVerified(rs.getString(7));
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		return l;
	}

	@Override
	public lab getcus(String email) {
		lab l=new lab();
		try {
			con=MyConnectionProvider.getCon();
			ps=con.prepareStatement("select * from lab where email=?");
			ps.setString(1, email);
			
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				l.setName(rs.getString(1));
				l.setEmail(rs.getString(2));
				l.setPhone_no(rs.getString(3));
				l.setAddress(rs.getString(4));
				l.setCity(rs.getString(5));
				l.setPassword(rs.getString(6));
				l.setVerified(rs.getString(7));
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		return l;
	}

	@Override
	public String getNotVerify(String verify) {
		String verify1="";
		try {
			con=MyConnectionProvider.getCon();
			ps=con.prepareStatement("select * from lab where verified=?");
			ps.setString(1, verify);
			
			ResultSet rs=ps.executeQuery();
			int i=0;
			while(rs.next()) {
				i++;
				String email=rs.getString(2);
				verify1+="<tr><td>"+i+"</td><td>"+rs.getString(1)+"</td><td>"+email+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td><td>"+rs.getString(5)+"</td><td><button class='btn-info' style='border-radius: 18px;' id='emailver"+i+"' value="+email+" onclick='verifylab("+i+")'>YES</button><h5>/</h5><button class='btn-danger' style='border-radius: 18px;' id='emailver"+i+"' value="+email+" onclick='verifylabcancel("+i+")'>CANCEL</button></td></tr>";
				
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		return verify1;
	}

	@Override
	public String getverify(String verify) {
		String verify1="";
		try {
			con=MyConnectionProvider.getCon();
			ps=con.prepareStatement("update lab set verified=? where email=?");
			ps.setString(1, "YES");
			ps.setString(2, verify);
			ps.executeUpdate();
			
		}catch(Exception e) {
			System.out.println(e);
		}
		return verify1;
	}

	@Override
	public String cancelverify(String verify) {
		String verify1="";
		try {
			con=MyConnectionProvider.getCon();
			ps=con.prepareStatement("delete from lab where verified=? and email=?");
			ps.setString(1, "NO");
			ps.setString(2, verify);
			ps.executeUpdate();
			
		}catch(Exception e) {
			System.out.println(e);
		}
		return verify1;
	}

}
