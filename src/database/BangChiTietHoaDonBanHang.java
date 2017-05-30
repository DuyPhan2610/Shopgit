/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import control.NhapHangHoa;
import entities.ChiTietHoaDonBanHang;
import entities.HangHoa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    public BangChiTietHoaDonBanHang(Connection connection) {
        super(connection);
    }
    
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
    
       //Lấy tất cả các chi tiết hóa đơn bán hàng trong csdl
    public ArrayList<ChiTietHoaDonBanHang> layTatCaChiTietHoaDonBanHangTrongCSDL(){
        
        ArrayList<ChiTietHoaDonBanHang> arlChiTietHoaDonBanHang = new ArrayList<>();
        try {
            // mở kết nối csdl

            // thực hiện câu truy vấn đưa kết quả vào result set
            ResultSet rs = this.selectData(CauTruyVan.selectedStatement("chitiethoadonbanhang"));
            while(rs.next()){
                arlChiTietHoaDonBanHang.add(new ChiTietHoaDonBanHang(rs));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(BangChiTietHoaDonBanHang.class.getName()).log(Level.SEVERE, null, ex);
        }

        return arlChiTietHoaDonBanHang;
    }
}
