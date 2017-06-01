package entities;


import database.BangPhieuTraHangNhap;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;

public class PhieuTraHangNhap {
    public String mMaPhieuTraHangNhap;
    public String mMaNhaCungCap;
    public int mTienNhaCungCapDaTra;
    public String mThoiGian;
    public String mTrangThai;
    public String mGhiChu;
    
    public PhieuTraHangNhap(){
        
    }
    
    public PhieuTraHangNhap(String maPhieuTraHangNhap, String maNhaCungCap, int tienNhaCungCapDaTra, String thoiGian, String trangThai, String ghiChu){
        
        this.mMaPhieuTraHangNhap = maPhieuTraHangNhap;
        this.mMaNhaCungCap = maNhaCungCap;
        this.mTienNhaCungCapDaTra = tienNhaCungCapDaTra;
        this.mTrangThai = trangThai;
        this.mGhiChu = ghiChu;
        this.mThoiGian = thoiGian;
        
        
    }

    public PhieuTraHangNhap(ResultSet rs) {
        try {
            this.mMaPhieuTraHangNhap =rs.getString(BangPhieuTraHangNhap.MA_PHIEU_TRA_HANG_NHAP);
            this.mMaNhaCungCap = rs.getString(BangPhieuTraHangNhap.MA_NHA_CUNG_CAP);
            this.mTienNhaCungCapDaTra =rs.getInt(BangPhieuTraHangNhap.TIEN_NHA_CUNG_CAP_DA_TRA);
            this.mThoiGian = rs.getString(BangPhieuTraHangNhap.THOI_GIAN);
            this.mTrangThai = rs.getString(BangPhieuTraHangNhap.TRANG_THAI);
            this.mGhiChu =rs.getString(BangPhieuTraHangNhap.GHI_CHU);
        } catch (SQLException ex) {
            Logger.getLogger(PhieuTraHangNhap.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
