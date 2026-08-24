import java.sql.*;

public class ProductCRUD {
    public static void main(String[] args) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Load Oracle JDBC Driver
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Connect to Oracle DB
            con = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:xe", // adjust if service name differs
                "system",                             // username
                "system"                       // password
            );

            // 1️⃣ Create Table
            String createTable = "CREATE TABLE product (" +
                                 "product_no NUMBER PRIMARY KEY, " +
                                 "name VARCHAR2(50), " +
                                 "image VARCHAR2(100), " +
                                 "price NUMBER(10,2))";
            pstmt = con.prepareStatement(createTable);
            pstmt.executeUpdate();
            System.out.println("Table created.");

            // 2️⃣ Insert Records
            String insertSQL = "INSERT INTO product VALUES (?, ?, ?, ?)";
            pstmt = con.prepareStatement(insertSQL);

            pstmt.setInt(1, 101);
            pstmt.setString(2, "Laptop");
            pstmt.setString(3, "laptop.png");
            pstmt.setDouble(4, 75000);
            pstmt.executeUpdate();

            pstmt.setInt(1, 102);
            pstmt.setString(2, "Phone");
            pstmt.setString(3, "phone.png");
            pstmt.setDouble(4, 35000);
            pstmt.executeUpdate();

            System.out.println("Records inserted.");

            // 3️⃣ Retrieve Records
            String selectSQL = "SELECT * FROM product";
            pstmt = con.prepareStatement(selectSQL);
            rs = pstmt.executeQuery();
            System.out.println("Product Table:");
            while (rs.next()) {
                System.out.println(rs.getInt("product_no") + " | " +
                                   rs.getString("name") + " | " +
                                   rs.getString("image") + " | " +
                                   rs.getDouble("price"));
            }

            // 4️⃣ Update Record
            String updateSQL = "UPDATE product SET price = ? WHERE product_no = ?";
            pstmt = con.prepareStatement(updateSQL);
            pstmt.setDouble(1, 38000);
            pstmt.setInt(2, 102);
            pstmt.executeUpdate();
            System.out.println("Record updated.");

            // 5️⃣ Delete Record
            String deleteSQL = "DELETE FROM product WHERE product_no = ?";
            pstmt = con.prepareStatement(deleteSQL);
            pstmt.setInt(1, 101);
            pstmt.executeUpdate();
            System.out.println("Record deleted.");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (con != null) con.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}
