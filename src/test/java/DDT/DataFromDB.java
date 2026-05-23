package DDT;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class DataFromDB {

	public static void main(String[] args) throws SQLException {
		
		Driver driver = new Driver();
		DriverManager.registerDriver(driver);
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/A23","root","root");
		Statement s = con.createStatement();
		ResultSet res = s.executeQuery("SELECT campname from NinzaCRM");
		
		while(res.next())
		{
			System.out.println(res.getString(1));
		}
	}

}
