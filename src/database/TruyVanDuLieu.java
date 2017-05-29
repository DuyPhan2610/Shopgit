/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import entities.PhieuNhapHang;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
   public TruyVanDuLieu(){
       try {
           connection = new ConnectionUtils().getMySQLConnection();
       } catch (SQLException ex) {
           Logger.getLogger(TruyVanDuLieu.class.getName()).log(Level.SEVERE, null, ex);
       } catch (ClassNotFoundException ex) {
           Logger.getLogger(TruyVanDuLieu.class.getName()).log(Level.SEVERE, null, ex);
       }
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
   
   
   
   // Thêm Phiếu nhập hàng vào cơ sở dữ liệu
   public void themPhieuNhapHang(PhieuNhapHang phieuNhapHang){
       try {
           String sql = "insert into phieunhaphang (MAPHIEUNHAP, MANHACUNGCAP, TONGTIEN, GIAGIAM, TIENDATRA, CONNO, THOIGIAN, GHICHU) values (?, ?, ?, ?, ?, ?, ?, ?)";
           PreparedStatement preStatement = connection.prepareStatement(sql);
           preStatement.setString(1, phieuNhapHang.mMaPhieuNhap);
           preStatement.setString(2, phieuNhapHang.mMaNhaCungCap);
           preStatement.setInt(3, phieuNhapHang.mTongTien);
           preStatement.setInt(4, phieuNhapHang.mGiaGiam);
           preStatement.setInt(5, phieuNhapHang.mTienDaTra);
           preStatement.setInt(6, phieuNhapHang.mConNo);
           preStatement.setString(7,phieuNhapHang.mThoiGian);
           preStatement.setString(8, phieuNhapHang.mGhiChu);


             int i = preStatement.executeUpdate();
           
           if(i > 0)
                System.out.print("\n thêm dữ liệu thành công");
           else{
               System.out.print("\n Không thêm được phiếu nhập hàng");
           }
       } catch (SQLException ex) {
          System.out.print("\n thêm dữ liệu không thành công");
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
