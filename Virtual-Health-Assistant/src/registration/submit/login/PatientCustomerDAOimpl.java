package registration.submit.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PatientCustomerDAOimpl implements PatientCustomerDAO {

	static Connection con;
	static PreparedStatement ps;
	@Override
	public int insertCustomer(PatientCustomer pc) {
		int status=0;
		try {
			con=MyConnectionProvider.getCon();
			ps=con.prepareStatement("insert into patient values (?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, pc.getName());
			ps.setString(2, pc.getEmail());
			ps.setString(3, pc.getPassword());
			ps.setString(4, pc.getId());
			ps.setString(5, pc.getPhone_no());
			ps.setString(6, pc.getBlood_grp());
			ps.setString(7, pc.getAddress());
			ps.setString(8, pc.getCity());
			ps.setString(9, pc.getGender());
			ps.setString(10, pc.getDob());
			status=ps.executeUpdate();
			con.close();
		}catch(Exception e) {
			System.out.println(e);
		}
		return status;
	}

	@Override
	public PatientCustomer getCustomer(String emailid, String password) {
		PatientCustomer pc=new PatientCustomer();
		try {
			con=MyConnectionProvider.getCon();
			ps=con.prepareStatement("select * from patient where email=? and password=?");
			ps.setString(1, emailid);
			ps.setString(2, password);
			
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				pc.setName(rs.getString(1));
				pc.setEmail(rs.getString(2));
				pc.setPassword(rs.getString(3));
				pc.setId(rs.getString(4));
				pc.setPhone_no(rs.getString(5));
				pc.setBlood_grp(rs.getString(6));
				pc.setAddress(rs.getString(7));
				pc.setCity(rs.getString(8));
				
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		return pc;
		
	}

	@Override
	public String getuserid(String name, String email, String DOB) {
		String id="";
		id+=name.substring(0, 3);
		id+=email.substring(0, 2);
		id+=DOB.substring(2, 4);
		int i=0,j=0;
		while(j<1) {
			int k=0;
			try {
				String id_demo=id+i;
				con=MyConnectionProvider.getCon();
				ps=con.prepareStatement("select * from patient where id=?");
				ps.setString(1, id_demo);
				ResultSet rs=ps.executeQuery();
				while(rs.next()) {
					k++;
					i++;
				}
				if(k==0) {
					id+=i;
					j++;
				}
			}
			catch(Exception e) {
				System.out.println(e);
			}
		}
		return id;
	}

}
