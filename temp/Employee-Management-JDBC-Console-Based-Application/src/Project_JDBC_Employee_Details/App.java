package Project_JDBC_Employee_Details;
import java.sql.*;
import java.util.*;
import java.util.Formatter;
public class App {
	public static void main(String[] args) throws ClassNotFoundException, SQLException,Exception {
		System.out.println( "Employee Management System using JDBC" );
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/comeback","root","root");
        

    	Scanner scanner = new Scanner(System.in);
        int count=0;
        while(count!=1) {
        	System.out.println("\nSelect choice :");
        	System.out.println("1: Show Employee details");
        	System.out.println("2: Insert Employee details");
        	System.out.println("3: Update Employee details");	
        	System.out.println("4: Delete Employee details\n");
        	
        	int input = 0;
        	
        	input = scanner.nextInt();
        	switch (input) {
			case 1:
				Statement stmt=connection.createStatement();  
				ResultSet rs=stmt.executeQuery("select * from employee_class_jdbc_project");
				System.out.println("Id\tName\tSalary"); 
				int cnt=0;
				while(rs.next()) {
					System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getInt(3));
					
					cnt++;
				}
				if(cnt==0) {
					System.out.println("No Data found..!!");
				}
	
				break;
				
			case 2:
				try {
				System.out.println("Enter ID : ");
				int id = scanner.nextInt(); 
				Statement stmt1=connection.createStatement();  
				ResultSet rs1=stmt1.executeQuery("select * from employee_class_jdbc_project");
				
				int cnt1=0;
				while(rs1.next()) {
					if(rs1.getInt(1)==id) {
						cnt1++;
						break;
					}
					
				}
				if(cnt1!=0) {
					System.out.println("ID already present..!!");
					break;
				}
				else {
					String name;
					int salary;
					try {
						
						System.out.println("Enter Name :");
						name = scanner.next();
						scanner.nextLine();
						System.out.println("Enter Salary :");
						salary = scanner.nextInt();

					} catch (Exception e) {
						System.out.println("Please enter valid values..!");
						break;
					}			
						String sql = " insert into employee_class_jdbc_project(id,name,salary)"+" values (?, ?, ?)";
						Employee_Class_JDBC_Project employee = new Employee_Class_JDBC_Project();
										
						employee.setId(id);
						employee.setName(name);
						employee.setSalary(salary);
						
						PreparedStatement preparedStmt = connection.prepareStatement(sql);
						preparedStmt.setInt(1,employee.getId());
						preparedStmt.setString(2, employee.getName());
						preparedStmt.setInt(3, employee.getSalary());
						
						preparedStmt.execute();
						System.out.println("Data saved successfully...!!");
					
					}
				}catch (Exception e) {
					System.out.println("Enter valid values..!!");
				}
				break;

			case 3:
				System.out.println("Enter ID to update : ");
				int id2 = scanner.nextInt(); 
				Statement stmt2=connection.createStatement();  
				ResultSet rs2=stmt2.executeQuery("select * from employee_class_jdbc_project");
				
				int cnt2=0;
				while(rs2.next()) {
					if(rs2.getInt(1)==id2) {
						cnt2++;
						break;
					}
					
				}
				if(cnt2!=0) {
					try {
						System.out.println("Enter Name :");
						String name = scanner.next();
						scanner.nextLine();
						System.out.println("Enter Salary :");
						int salary = scanner.nextInt();
									
						String sql = "update employee_class_jdbc_project set name = ?, salary = ? where id =?";
						
						
						PreparedStatement preparedStmt = connection.prepareStatement(sql);
						preparedStmt.setString(1,name);
						preparedStmt.setInt(2, salary);
						preparedStmt.setInt(3, id2);
						
						preparedStmt.execute();
						System.out.println("Data updated successfully...!!");
					}catch (Exception e) {
						System.out.println("Enter valid values..!!");
					}
				}
				else {
					System.out.println("ID Not Present..!!");
				}
				
				break;
				
			case 4:
				System.out.println("Enter ID to delete : ");
				int id3 = scanner.nextInt(); 
				Statement stmt3=connection.createStatement();  
				ResultSet rs3=stmt3.executeQuery("select * from employee_class_jdbc_project");
				
				int cnt3=0;
				while(rs3.next()) {
					if(rs3.getInt(1)==id3) {
						cnt3++;
						break;
					}
					
				}
				if(cnt3!=0) {
					try {			
						String sql = "delete from employee_class_jdbc_project where id =?";
						
						
						PreparedStatement preparedStmt = connection.prepareStatement(sql);
						preparedStmt.setInt(1, id3);
						
						preparedStmt.execute();
						System.out.println("Data deleted successfully...!!");
					}catch (Exception e) {
						System.out.println("Enter valid values..!!");
					}
				}
				else {
					System.out.println("ID Not Present..!!");
				}
				
				break;
				
			default:
				System.out.println("Please select valid input..!!");
				break;
			}

        }
	}
}
