package entities;

import database.BangCongNoNhaCungCap;
import database.BangNhaCungCap;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class NhaCungCap {
    
    
    
    
   public String mMaNhaCungCap, mTenNhaCungCap, mNhomNhaCungCap, mMaCongNoNhaCungCap,
           mDiaChi, mEmail, mGhiChu;
   public int mTongMua;
   public NhaCungCap(){}
   public NhaCungCap(String maNhaCungCap, String tenNhaCungCap,
           String nhomNhaCungCap, String maCongNoNhaCungCap, String diaChi, String email,
           String ghiChu, int tongMua){
       this.mDiaChi = diaChi;
       this.mEmail = email;
       this.mGhiChu = ghiChu;
       this.mMaNhaCungCap = maNhaCungCap;
       this.mNhomNhaCungCap = nhomNhaCungCap;
       this.mTenNhaCungCap = tenNhaCungCap;
       this.mTongMua = tongMua;
       this.mMaCongNoNhaCungCap = maCongNoNhaCungCap;
       
   }
   
   public NhaCungCap(ResultSet rs){
        try {
            this.mDiaChi = rs.getString(BangNhaCungCap.DIA_CHI);
            this.mEmail = rs.getString(BangNhaCungCap.EMAIL);
            this.mGhiChu = rs.getString(BangNhaCungCap.GHI_CHU);
            this.mMaNhaCungCap = rs.getString(BangNhaCungCap.MA_NHA_CUNG_CAP);
            this.mNhomNhaCungCap = rs.getString(BangNhaCungCap.NHOM_NHA_CUNG_CAP);
            this.mTenNhaCungCap = rs.getString(BangNhaCungCap.TEN_NHA_CUNG_CAP);
            this.mTongMua = rs.getInt(BangNhaCungCap.TONG_MUA);
            this.mMaCongNoNhaCungCap = rs.getString(BangNhaCungCap.MA_CONG_NO_NHA_CUNG_CAP);
        } catch (SQLException ex) {
            Logger.getLogger(NhaCungCap.class.getName()).log(Level.SEVERE, null, ex);
        }
       
   }
}
