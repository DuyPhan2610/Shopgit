/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import entities.HangHoa;
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
   public ResultSet selectData(String statementString) throws SQLException{
       Statement statement = connection.createStatement();
       return statement.executeQuery(statementString);
   }
   
   // Câu lệnh insert tổng quát
   public void insertData(String statementString) throws SQLException{
       
       Statement statement = connection.createStatement();
       statement.executeUpdate(statementString);
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
   
   
   
   
//   // them 1 recode hang hoa
//   //////////////////////////////////////////////////////////////////////////
//    public void insertProduct(HangHoa product, int tonKho, int dinhMucTonItNhat, int dinhMucTonNhieuNhat){
//       
//       try {
//           String sql = "insert into hanghoa (MAHANGHOA, TENHANGHOA, GIABAN, GIAVON, TONKHO, NHOMHANGHOA, DINHMUCTONITNHAT, DINHMUCTONNHIEUNHAT) values (?, ?, ?, ?, ?, ?, ?, ?)";
//           PreparedStatement preStatement = connection.prepareStatement(sql);
//           preStatement.setString(1, product.getMaHangHoa());
//           preStatement.setString(2, product.getTenHangHoa());
//           preStatement.setInt(3, product.getGiaBan());
//           preStatement.setInt(4, product.getGiaVon());
//           preStatement.setInt(5, tonKho);
//           preStatement.setString(6, product.getNhomHangHoa());
//           preStatement.setInt(7, dinhMucTonItNhat);
//           preStatement.setInt(8, dinhMucTonNhieuNhat);
//
//
////           PreparedStatement preStatement = connection.prepareStatement(sql);
////           preStatement.setString(1, "SP003");
////           preStatement.setString(2, "Ao Nike");
////           preStatement.setInt(3, 300);
////           preStatement.setInt(4,250);
////           preStatement.setInt(5, 5);
////           preStatement.setString(6, "Ao the thao");
////           preStatement.setInt(7,3);
////           preStatement.setInt(8, 7);
//           
//           preStatement.execute();
//           
//           
//           System.out.print("\n thêm dữ liệu thành công");
//        }catch (SQLException ex) {
//            System.out.print("\n thêm dữ liệu không thành công");
//        }
//   }
//    
//    
//    public void insertChiTietPhieuNhap(String maHangHoa, String maPhieuNhap){
//        try {
//           String sql = "insert into hanghoa (MAHANGHOA, TENHANGHOA) values (?, ?)";
//           PreparedStatement preStatement = connection.prepareStatement(sql);
//           preStatement.setString(1,maHangHoa);
//           preStatement.setString(2, maPhieuNhap);
//    
//           preStatement.execute();
//           
//           
//           System.out.print("\n thêm dữ liệu thành công");
//        }catch (SQLException ex) {
//            System.out.print("\n thêm dữ liệu không thành công");
//        }
//    }
//   
    
    //////////////////////////LAM TOI DAY ROI////////////////////////////
//    public HangHoa getProductFromDatabase(String maHangHoa){
//        
//        return null;
//    }
    //  xoa het du lieu cua table
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
