package registration.submit.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DoctorCustomerDAOImpl implements DoctorCustomerDAO {

	static Connection con;
	static PreparedStatement ps;
	@Override
	public int insertCustomer(DoctorCustomer dc) {
		int status=0;
		try {
			con=MyConnectionProvider.getCon();
			ps=con.prepareStatement("insert into doctor values (?,?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, dc.getName());
			ps.setString(2, dc.getEmail());
			ps.setString(3, dc.getPassword());
			ps.setString(4, dc.getPhone_no());
			ps.setString(5, dc.getDept());
			ps.setString(6, dc.getHospital());
			ps.setString(7, dc.getHosadd());
			ps.setString(8, dc.getCity());
			ps.setString(9, dc.getOpenhrs());
			ps.setString(10, dc.getVerified());
			ps.setString(11, "y");
			ps.setString(12, "y");
			ps.setString(13, dc.getId());
			status=ps.executeUpdate();
			con.close();
		}catch(Exception e) {
			System.out.println(e);
		}
		return status;
	}

	@Override
	public DoctorCustomer getCustomer(String emailid, String pass) {
		DoctorCustomer dc=new DoctorCustomer();
		try {
			con=MyConnectionProvider.getCon();
			ps=con.prepareStatement("select * from doctor where email=? and password=?");
			ps.setString(1, emailid);
			ps.setString(2, pass);
			
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				dc.setName(rs.getString(1));
				dc.setEmail(rs.getString(2));
				dc.setPassword(rs.getString(3));
				dc.setPhone_no(rs.getString(4));
				dc.setDept(rs.getString(5));
				dc.setHospital(rs.getString(6));
				dc.setCity(rs.getString(7));
				dc.setVerified(rs.getString(10));
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		return dc;
	}

	@Override
	public DoctorCustomer getCustomer(String emailid) {
		DoctorCustomer dc=new DoctorCustomer();
		try {
			con=MyConnectionProvider.getCon();
			ps=con.prepareStatement("select * from doctor where email=?");
			ps.setString(1, emailid);
			
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				dc.setName(rs.getString(1));
				dc.setEmail(rs.getString(2));
				dc.setPassword(rs.getString(3));
				dc.setPhone_no(rs.getString(4));
				dc.setDept(rs.getString(5));
				dc.setHospital(rs.getString(6));
				dc.setCity(rs.getString(7));
				
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		return dc;
	}

	@Override
	public DoctorCustomer getCustomer1(String phone_no) {
		DoctorCustomer dc=new DoctorCustomer();
		try {
			con=MyConnectionProvider.getCon();
			ps=con.prepareStatement("select * from doctor where phone_no=?");
			ps.setString(1, phone_no);
			
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				dc.setName(rs.getString(1));
				dc.setEmail(rs.getString(2));
				dc.setPassword(rs.getString(3));
				dc.setPhone_no(rs.getString(4));
				dc.setDept(rs.getString(5));
				dc.setHospital(rs.getString(6));
				dc.setCity(rs.getString(7));
				
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		return dc;
	}

	@Override
	public String getNotVerify(String verify) {
		DoctorCustomer dc=new DoctorCustomer();
		String verify1="";
		try {
			con=MyConnectionProvider.getCon();
			ps=con.prepareStatement("select * from doctor where verified=?");
			ps.setString(1, verify);
			
			ResultSet rs=ps.executeQuery();
			int i=0;
			while(rs.next()) {
				i++;
				String email=rs.getString(2);
				verify1+="<tr><td>"+i+"</td><td>"+rs.getString(1)+"</td><td>"+email+"</td><td>"+rs.getString(4)+"</td><td>"+rs.getString(5)+"</td><td>"+rs.getString(6)+"</td><td>"+rs.getString(7)+"</td><td>"+rs.getString(8)+"</td><td><button class='btn-info' id='emailver"+i+"' value="+email+" onclick='verifydoc("+i+")'>YES</button><h5>/</h5><button class='btn-danger' id='emailver"+i+"' value="+email+" onclick='verifydoccancel("+i+")'>cancel</button></td></tr>";
				
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		return verify1;
	}

	@Override
	public String getVerify(String ver) {
		String verify1="";
		try {
			con=MyConnectionProvider.getCon();
			ps=con.prepareStatement("update doctor set verified=? where email=?");
			ps.setString(1, "YES");
			ps.setString(2, ver);
			ps.executeUpdate();
			
		}catch(Exception e) {
			System.out.println(e);
		}
		return verify1;
	}

	@Override
	public String cancelverify(String email) {
		String verify1="";
		try {
			con=MyConnectionProvider.getCon();
			ps=con.prepareStatement("delete from doctor where verified=? and email=?");
			ps.setString(1, "NO");
			ps.setString(2, email);
			ps.executeUpdate();
			
		}catch(Exception e) {
			System.out.println(e);
		}
		return verify1;
	}

	@Override
	public DoctorCustomer getdetails(String email, String password) {
		DoctorCustomer dc=new DoctorCustomer();
		try {
			con=MyConnectionProvider.getCon();
			ps=con.prepareStatement("select * from hospital where email=? and password=?");
			ps.setString(1, email);
			ps.setString(2, password);
			
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				dc.setName(rs.getString(1));
				dc.setEmail(rs.getString(2));
				dc.setPhone_no(rs.getString(3));
				dc.setHospital(rs.getString(4));
				dc.setCity(rs.getString(5));
				dc.setOpenhrs(rs.getString(6));
				dc.setVerified(rs.getString(7));
				dc.setPassword(rs.getString(8));
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		return dc;
	}

	@Override
	public DoctorCustomer gethospital(String email) {
		DoctorCustomer dc=new DoctorCustomer();
		try {
			con=MyConnectionProvider.getCon();
			ps=con.prepareStatement("select * from hospital where email=?");
			ps.setString(1, email);
			
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				dc.setName(rs.getString(1));
				dc.setEmail(rs.getString(2));
				dc.setPhone_no(rs.getString(3));
				dc.setHospital(rs.getString(4));
				dc.setCity(rs.getString(5));
				dc.setOpenhrs(rs.getString(6));
				dc.setVerified(rs.getString(7));
				dc.setPassword(rs.getString(8));
				
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		return dc;
	}

	@Override
	public String createid(String name, String email, String phone) {
		String res="";
		res+=name.substring(0, 3)+name.substring(0,3)+phone.substring(0,2);
		int i=0,j=0;
		while(j<1) {
			int k=0;
			try {
				String id_demo=res+i;
				con=MyConnectionProvider.getCon();
				ps=con.prepareStatement("select * from hospital where id=?");
				ps.setString(1, id_demo);
				ResultSet rs=ps.executeQuery();
				while(rs.next()) {
					k++;
					i++;
				}
				if(k==0) {
					res+=i;
					j++;
				}
			}
			catch(Exception e) {
				System.out.println(e);
			}
		}
		return res;
	}

	@Override
	public int inserthospital(DoctorCustomer dc) {
		int status=0;
		try {
			con=MyConnectionProvider.getCon();
			ps=con.prepareStatement("insert into hospital values (?,?,?,?,?,?,?,?,?)");
			ps.setString(1, dc.getName());
			ps.setString(2, dc.getEmail());
			ps.setString(3, dc.getPhone_no());
			ps.setString(4, dc.getHosadd());
			ps.setString(5, dc.getCity());
			ps.setString(6, dc.getOpenhrs());
			ps.setString(7, dc.getVerified());
			ps.setString(8, dc.getPassword());
			ps.setString(9, dc.getId());
			status=ps.executeUpdate();
			con.close();
		}catch(Exception e) {
			System.out.println(e);
		}
		return status;
	}

	@Override
	public String getNotverifiedhospital(String verify) {
		String verify1="";
		try {
			con=MyConnectionProvider.getCon();
			ps=con.prepareStatement("select * from hospital where verified=?");
			ps.setString(1, verify);
			
			ResultSet rs=ps.executeQuery();
			int i=0;
			while(rs.next()) {
				i++;
				String email=rs.getString(2);
				verify1+="<tr><td>"+i+"</td><td>"+rs.getString(1)+"</td><td>"+email+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td><td>"+rs.getString(5)+"</td><td><button class='btn-info' id='emailver"+i+"' value="+email+" onclick='verifyhos("+i+")'>YES</button><h5>/</h5><button class='btn-danger' id='emailver"+i+"' value="+email+" onclick='verifyhoscancel("+i+")'>cancel</button></td></tr>";
				
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		return verify1;
	}

	@Override
	public String getverified(String ver) {
		String verify1="";
		try {
			con=MyConnectionProvider.getCon();
			ps=con.prepareStatement("update hospital set verified=? where email=?");
			ps.setString(1, "YES");
			ps.setString(2, ver);
			ps.executeUpdate();
			
		}catch(Exception e) {
			System.out.println(e);
		}
		return verify1;
	}

	@Override
	public String cancelverified(String email) {
		String verify1="";
		try {
			con=MyConnectionProvider.getCon();
			ps=con.prepareStatement("delete from hospital where verified=? and email=?");
			ps.setString(1, "NO");
			ps.setString(2, email);
			ps.executeUpdate();
			
		}catch(Exception e) {
			System.out.println(e);
		}
		return verify1;
	}

	

}
