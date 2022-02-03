
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Test_mysql {
	
	PreparedStatement ps;
	static Connection cn;
	static Statement st;
	ResultSet rs;
	private Connection connection;
	
	//....................Create Connection.........................................
	void connectionCreate()throws Exception {
		String driver="oracle.jdbc.driver.OracleDriver";
		String con="jdbc:oracle:thin:@localhost:1521:xe";
		String dbUser="system";
		String pass="1234";
		
		Class.forName(driver);
		cn=DriverManager.getConnection(con,dbUser,pass);
		System.out.println("DriverLoaded");
		st=cn.createStatement();
		
	}
	//..................Insert student data..............................................
		void insertData(Statement st){
		Scanner sc = new Scanner(System.in);
		Scanner sc1 = new Scanner(System.in);
		Scanner sc2 = new Scanner(System.in);
		Scanner sc3 = new Scanner(System.in);
		int id;
		String name, email;
		System.out.println("Enter student id: \n");
		id = sc.nextInt();
		System.out.println("Enter Student Name: \n");
		name = sc1.nextLine();
		System.out.println("Enter Student Email: \n");
		email = sc2.nextLine();
		
		try {
			PreparedStatement pst = cn.prepareStatement("insert into student1 values(?,?,?)");
			pst.setInt(1, id);
			pst.setString(2, name);
			pst.setString(3, email);
			
			pst.executeUpdate();
			System.out.println("Data Inserted Sucessfully...");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
		
	//.........................Update Student Data............................................
	void updateData(Statement st){
		Scanner sc = new Scanner(System.in);
		Scanner sc1 = new Scanner(System.in);
		Scanner sc2 = new Scanner(System.in);
		Scanner sc3 = new Scanner(System.in);
		int id;
		String name, email;
		System.out.println("Update Student id: \n");
		id = sc.nextInt();
		System.out.println("Update Student Name: \n");
		name = sc1.nextLine();
		System.out.println("Update Student Email: \n");
		email = sc2.nextLine();
				
	
		try {
			PreparedStatement pst = cn.prepareStatement("update student1 set name= ?,email = ? where id = ?");
			
			pst.setString(1, name);
			pst.setString(2, email);
			pst.setInt(3, id);
			
			pst.executeUpdate();
			System.out.println("Data Updated Sucessfully...");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	//........................Delete Student Data..........................................
	void deleteData(Statement st) {
		Scanner sc = new Scanner(System.in);
		int id;
		System.out.println("Enter student id: \n");
		id = sc.nextInt();
		try {
			PreparedStatement pst = cn.prepareStatement("delete from student1 where id=?");
			pst.setInt(1, id);
			
			pst.executeUpdate();
		    System.out.println("Record Deleted Successfully...");
		}catch (SQLException e) {
		 e.printStackTrace();
		}
	}
	//..........................Get Student Data...................................
	void GetData(Statement st) {
		Scanner sc = new Scanner(System.in);
		try {
			
			PreparedStatement pst = cn.prepareStatement("select * from student1");
             rs = pst.executeQuery();
             System.out.println("id\t\tname\t\t email");
 
            // Condiion check
            while (rs.next()) {
 
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                System.out.println(id + "\t\t" + name+ "\t\t" + email);
            }
		}
     catch (SQLException e) {
    	 System.out.println(e);
     }
	}
	//........................Search Student Data...........................
	void SearchData(Statement st) {
		Scanner sc = new Scanner(System.in);
		int id1;
		System.out.println("Enter Student Id: \n");
		id1 = sc.nextInt();
		try {
			
			PreparedStatement pst = cn.prepareStatement("select * from student1 where id=?");
			 pst.setInt(1, id1);
             rs = pst.executeQuery();
             System.out.println("id\t\tname\t\t email");
 
            // Condiion check
            while (rs.next()) {
 
                int id11 = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                System.out.println(id11 + "\t\t" + name+ "\t\t" + email);
            }
		}
     catch (SQLException e) {
    	 System.out.println(e);
     }
	}
		
		
	public static void main(String [] args) throws Exception {
		Scanner sc= new Scanner(System.in);
		Test_mysql obj=new Test_mysql();
		obj.connectionCreate();
		
		System.out.println("Enter the Choice:");
		int choice=sc.nextInt();
		switch(choice) {
		case 1:
			obj.insertData(st);
			break;
		case 2:
			obj.updateData(st);
		case 3:
			obj.deleteData(st);
			break;
		case 4:
			obj.GetData(st);
			break;
		case 5:
			obj.SearchData(st);
			break;
		default:
			if(choice<1||choice>5) {
			System.out.println("Wrong choice Entered");
			}
			
		}
	}
}

	
	