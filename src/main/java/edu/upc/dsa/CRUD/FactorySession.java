package edu.upc.dsa.CRUD;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class FactorySession {

    //private IUserDAO em;
    public static Session openSession() {


        Connection conn = getConnection();

        Session session = new SessionImpl(conn);

        return session;
    }

    private static Connection getConnection() {
        Connection conn = null;
        try {
            conn =
                    DriverManager.getConnection("jdbc:mariadb://localhost:3306/dsaDB?" + "user=tonib&password=12345");

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return conn;
    }

    public static void main(String[] args) {
        Connection con = getConnection();
        Statement st = null;

        try {
            st = con.createStatement();
            st.execute("INSERT INTO items (name) VALUES ('Jordi')");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }




    }

}
