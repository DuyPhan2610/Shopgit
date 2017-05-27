/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import entities.ChiTietHoaDonBanHang;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author USER
 */
public class BangChiTietHoaDonBanHang extends TruyVanDuLieu{
    
    //lay du lieu ve phai biet ten column
    public final static String MA_HOA_DON_BAN_HANG = "MAHOADONBANHANG";
    public final static String MA_HANG_HOA = "MAHANGHOA";
    public final static String SO_LUONG = "SOLUONG";
    //...
    
    public void themChiTietHoaDonBanHang (ChiTietHoaDonBanHang chiTietHoaDonBanHang){
        try {
            String sql = "insert into chitiethoadonbanhang (MAHOADONBANHANG, MAHANGHOA, SOLUONG) values (?, ?, ?)";
            PreparedStatement preStatement = connection.prepareStatement(sql);
            preStatement.setString(1, chiTietHoaDonBanHang.mMaHoaDonBanHang);
            preStatement.setString(2, chiTietHoaDonBanHang.mMaHangHoa);
            preStatement.setInt(3, chiTietHoaDonBanHang.mSoLuong);
            
            boolean checkSuccess = preStatement.execute();
            if (checkSuccess)
                System.out.print("\n Thêm dữ liệu thành công");
            
        } catch (SQLException ex) {
          System.out.print("\n Thêm dữ liệu không thành công");
    }        
}
}
