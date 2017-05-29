package entities;

import database.BangChiTietNoCuaKhachHang;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;

public class ChiTietNoCuaKhachHang {
   public String mMaCongNoCuaKhachHang;
   public String mMaHoaDonBanHang;
   public int mTienNo;
   
   public ChiTietNoCuaKhachHang(){}
   public ChiTietNoCuaKhachHang(String maCongNoCuaKhachHang, String maHoaDonBanHang, int tienNo)
   {
       this.mMaHoaDonBanHang = maHoaDonBanHang;
       this.mTienNo = tienNo;
       this.mMaCongNoCuaKhachHang = maCongNoCuaKhachHang;
   }
   
   public ChiTietNoCuaKhachHang(ResultSet rs){
        try {
            this.mMaCongNoCuaKhachHang = rs.getString(BangChiTietNoCuaKhachHang.MA_CONG_NO_CUA_KHACH_HANG);
            this.mMaHoaDonBanHang = rs.getString(BangChiTietNoCuaKhachHang.MA_HOA_DON_BAN_HANG);
            this.mTienNo = rs.getInt(BangChiTietNoCuaKhachHang.TIEN_NO);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietNoCuaKhachHang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
