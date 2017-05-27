package entities;

import database.BangChiTietCongNoNhaCungCap;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;

public class ChiTietCongNoNhaCungCap {
   public String mMaCongNoNhaCungCap;
   public String mMaPhieuNhap;
   public int mTongNo;
   public ChiTietCongNoNhaCungCap(){}
   public ChiTietCongNoNhaCungCap(String maCongNoNhaCungCap, String maPhieuNhap, int tongNo){
       this.mMaCongNoNhaCungCap =maCongNoNhaCungCap;
       this.mMaPhieuNhap = maPhieuNhap;
       this.mTongNo = tongNo;
   }
   
    public ChiTietCongNoNhaCungCap(ResultSet rs){
        try {
            this.mMaCongNoNhaCungCap = rs.getString(BangChiTietCongNoNhaCungCap.MA_CONG_NO_NHA_CUNG_CAP);
            this.mMaPhieuNhap = rs.getString(BangChiTietCongNoNhaCungCap.MA_PHIEU_NHAP);
            this.mTongNo = rs.getInt(BangChiTietCongNoNhaCungCap.TONG_NO);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietCongNoNhaCungCap.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
