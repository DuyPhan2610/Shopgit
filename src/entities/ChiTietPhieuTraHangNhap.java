package entities;

import database.BangChiTietPhieuTraHangNhap;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;

public class ChiTietPhieuTraHangNhap {
    public String mMaHangHoa;
    public String mMaPhieuTraHangNhap;
    public int mSoLuong;
    
    public ChiTietPhieuTraHangNhap(){
        
    }
    
    public ChiTietPhieuTraHangNhap(String maHangHoa, String maPhieuTraHangNhap, int soLuong){
        
        this.mMaHangHoa = maHangHoa;
        this.mMaPhieuTraHangNhap = maPhieuTraHangNhap;
        this.mSoLuong = soLuong;
    }
    public ChiTietPhieuTraHangNhap(ResultSet rs ) {
        try {
            this.mMaHangHoa = rs.getString(BangChiTietPhieuTraHangNhap.MA_HANG_HOA);
            this.mMaPhieuTraHangNhap =rs.getString(BangChiTietPhieuTraHangNhap.MA_PHIEU_TRA_HANG_NHAP);
            this.mSoLuong =rs.getInt(BangChiTietPhieuTraHangNhap.SO_LUONG);
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietPhieuTraHangNhap.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
