import java.sql.*;

public class EmployeeCRUD {
    public static void main(String[] args) {
        Connection con = null;
        Statement stmt = null;

        try {
            // Load Oracle JDBC Driver
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Connect to Oracle DB (update with your DB details)
            con = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:xe", // host:port:service
                "system",                             // username
                "system"                       // password
            );

            stmt = con.createStatement();

            // 1️⃣ Create Table
            String createTable = "CREATE TABLE employee (" +
                                 "emp_no NUMBER PRIMARY KEY, " +
                                 "name VARCHAR2(50), " +
                                 "salary NUMBER(10,2), " +
                                 "department VARCHAR2(30))";
            stmt.executeUpdate(createTable);
            System.out.println("Table created.");

            // 2️⃣ Insert Records
            stmt.executeUpdate("INSERT INTO employee VALUES (1, 'Aafiya', 50000, 'ECE')");
            stmt.executeUpdate("INSERT INTO employee VALUES (2, 'Tabasum', 60000, 'CSE')");
            System.out.println("Records inserted.");

            // 3️⃣ Retrieve Records
            ResultSet rs = stmt.executeQuery("SELECT * FROM employee");
            System.out.println("Employee Table:");
            while (rs.next()) {
                System.out.println(rs.getInt("emp_no") + " | " +
                                   rs.getString("name") + " | " +
                                   rs.getDouble("salary") + " | " +
                                   rs.getString("department"));
            }

            // 4️⃣ Update Record
            stmt.executeUpdate("UPDATE employee SET salary = 65000 WHERE emp_no = 2");
            System.out.println("Record updated.");

            // 5️⃣ Delete Record
            stmt.executeUpdate("DELETE FROM employee WHERE emp_no = 1");
            System.out.println("Record deleted.");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}
