package entities;

import database.BangCongNoCuaKhachHang;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CongNoCuaKhachHang {
   public String mMaCongNoCuaKhachHang, mGhiChu;
   public int mTongNo, mNoCanTra;
    public CongNoCuaKhachHang(){}
    public CongNoCuaKhachHang(String maCongNoCuaKhachHang,
            int tongNo, int noCanTra , String ghiChu){
            
        this.mMaCongNoCuaKhachHang = maCongNoCuaKhachHang;
        this.mTongNo = tongNo;
        this.mNoCanTra = noCanTra;
        this.mGhiChu = ghiChu;
    }
    
    public CongNoCuaKhachHang(ResultSet rs ) {
        try {
            this.mMaCongNoCuaKhachHang = rs.getString(BangCongNoCuaKhachHang.MA_CONG_NO_CUA_KHACH_HANG);
            this.mTongNo =rs.getInt(BangCongNoCuaKhachHang.TONG_NO);
            this.mNoCanTra =rs.getInt(BangCongNoCuaKhachHang.NO_CAN_TRA);
            this.mGhiChu = rs.getString(BangCongNoCuaKhachHang.GHI_CHU);
        } catch (SQLException ex) {
            Logger.getLogger(CongNoCuaKhachHang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
