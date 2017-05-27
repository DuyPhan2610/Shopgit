package entities;

import database.BangChiTietPhieuNhapHang;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;

public class ChiTietPhieuNhapHang {
    public String mMaHangHoa;
    public String mMaPhieuNhap;
    public int mSoLuong;
    
    
    public ChiTietPhieuNhapHang(){
        
    }
    
    public ChiTietPhieuNhapHang(String maHangHoa, String maPhieuNhap, int soLuong){
        
        this.mMaHangHoa = maHangHoa;
        this.mMaPhieuNhap = maPhieuNhap;
        this.mSoLuong = soLuong;
    }
    
    public ChiTietPhieuNhapHang(ResultSet rs ) {
        try {
            this.mMaHangHoa = rs.getString(BangChiTietPhieuNhapHang.MA_HANG_HOA);
            this.mMaPhieuNhap =rs.getString(BangChiTietPhieuNhapHang.MA_PHIEU_NHAP);
            this.mSoLuong =rs.getInt(BangChiTietPhieuNhapHang.SO_LUONG);
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietPhieuNhapHang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
