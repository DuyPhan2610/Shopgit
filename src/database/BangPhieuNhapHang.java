/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import entities.HangHoa;
import entities.PhieuNhapHang;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author phamh
 */
public class BangPhieuNhapHang extends TruyVanDuLieu{

    
        // them 1 record hang hoa
   ////////////////////////////////////////////////////////////////////////////
   public void themPhieuNhapHang (PhieuNhapHang phieuNhapHang){
       try {
           String sql = "insert into hanghoa (MAPHIEUNHAP, MANHACUNGCAP, TONGTIEN, GIAGIAM, TIENDATRA, CONNO, THOIGIAN, GHICHU) values (?, ?, ?, ?, ?, ?, ?, ?)";
           PreparedStatement preStatement = connection.prepareStatement(sql);
           preStatement.setString(1, phieuNhapHang.mMaPhieuNhap);
           preStatement.setString(2, phieuNhapHang.mMaNhaCungCap);
           preStatement.setInt(3, phieuNhapHang.mTongTien);
           preStatement.setInt(4, phieuNhapHang.mGiaGiam);
           preStatement.setInt(5, phieuNhapHang.mTienDaTra);
           preStatement.setInt(6, phieuNhapHang.mConNo);
           preStatement.setString(7,phieuNhapHang.mThoiGian);
           preStatement.setString(8, phieuNhapHang.mGhiChu);
          
           boolean checkSuccess = preStatement.execute();
           
           if(checkSuccess)
                System.out.print("\n thêm dữ liệu thành công");
       } catch (SQLException ex) {
          System.out.print("\n thêm dữ liệu không thành công");
       }
   }
}
