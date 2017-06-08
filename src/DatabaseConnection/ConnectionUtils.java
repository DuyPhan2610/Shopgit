/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author phamh
 */
public class ConnectionUtils {
    public Connection getMySQLConnection() throws SQLException, ClassNotFoundException{
//        String hostName = "localhost";
//        String dbName = "shop";
//        String userName = "root ";
//        String password = "123456";
       
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/shop?useSSL=false", "root", "123456");  
                //getMySQLConnection(hostName, dbName, userName, password);
    }
    
    
//    // tao truyền tham số cho vui thôi :))))))
//    public Connection getMySQLConnection(String hostName, String dbName,
//         String userName, String password) {
//
//     String connectionURL = "jdbc:mysql://localhost:3306/shop?useSSL=false";
//     
//     Connection conn = null;
//        try {
//            conn = DriverManager.getConnection(connectionURL, "root", "123456");
//            System.out.print("Get connection successfully");
//        } catch (SQLException ex) {
//            
//            System.out.print("Get connection unsuccessfully");
//        }
////    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop", "root", "123456");  
//         return conn;
//    }
    

}
