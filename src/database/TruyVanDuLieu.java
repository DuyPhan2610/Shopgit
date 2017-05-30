/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author phamh
 */

public class TruyVanDuLieu {
    
   protected Connection connection;

   //private PreparedStatement preStatement;
   //private Statement statement;
   //private ResultSet resultSet;
   public TruyVanDuLieu(Connection connection){
           this.connection = connection;
       
   }
   
   // Câu lệnh query tổng quát
   public ResultSet selectData(String statementString){
       Statement statement;
       try {
           statement = connection.createStatement();
           return statement.executeQuery(statementString);
       } catch (SQLException ex) {
           Logger.getLogger(TruyVanDuLieu.class.getName()).log(Level.SEVERE, null, ex);
       }
       return null;
   }
   
   // Câu lệnh insert tổng quát
   public void insertData(String statementString){
       
       Statement statement;
       try {
           statement = connection.createStatement();
           statement.executeUpdate(statementString);
       } catch (SQLException ex) {
           Logger.getLogger(TruyVanDuLieu.class.getName()).log(Level.SEVERE, null, ex);
       }
       
   }
   
   
   public void clearAllData(String tableName){
       try {
            Statement statement = (Statement) connection.createStatement();
            String sql2 = "delete from " + tableName;
            statement.execute(sql2);
           
           System.out.print("\n xóa dữ liệu từ bảng" + tableName + " thành công!");
       } catch (SQLException ex) {
           System.out.print("\n xóa dữ liệu từ bảng thành công " + tableName + " thành công!");
       }
   }
   
   
   
   public void closeDatabase(){
       try {
           connection.close();
           System.out.print("\nĐóng kết nối cơ sở dữ liệu thành công");
           
       } catch (SQLException ex) {
           System.out.print("\nĐóng kết nối cơ sở dữ liệu không thành công");
       }
   }
}
