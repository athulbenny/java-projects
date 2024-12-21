package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcDemo {

	public static void main(String args[]) throws ClassNotFoundException, SQLException {
		Class.forName("org.postgresql.Driver");
		
		Connection conn = DriverManager.getConnection(
				"jdbc:postgresql://localhost:5432/hr_db",
				"postgres","root");
		if(!conn.isValid(0)) {
			System.out.println("connection failed");
		}else {
			String sqlstmt = "insert into employee(emp_id, name, age, address, salary) values (?,?,?,?,?)";
			
			PreparedStatement stmt = conn.prepareStatement(sqlstmt);
			stmt.setInt(1, 1008);
			stmt.setString(2,"postgres");
			stmt.setInt(3, 32);
			stmt.setString(4,"chennai");
			stmt.setDouble(5, 35000.0);
			
//			int count = stmt.executeUpdate();
			boolean flag = stmt.execute();
			
			System.out.println("Is executed sucessfully?: "+(flag?"No":"Yes"));
			
			ResultSet result = conn.prepareStatement("select * from employee").executeQuery();
			
			while(result.next()) {
				int empNo = result.getInt("emp_id");
				String name = result.getString("name");
				int age = result.getInt("age");
				String address = result.getString("address");
				double salary = result.getDouble("salary");
				
				System.out.println(empNo +" | "+age+" | "+salary+" | "+name+" | "+address);
				}
			
			stmt.close();
			conn.close();
			
		}
	}
}
