package entities;


import database.BangChiTietPhieuTraHang;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;

public class ChiTietPhieuTraHang {
    public String mMaHangHoa;
    public String mMaPhieuTraHang;
    public int mSoLuong;
   
    
    public ChiTietPhieuTraHang(){
        
    }
    
    public ChiTietPhieuTraHang(String maHangHoa, String maPhieuTraHang, int soLuong){
        
        this.mMaHangHoa = maHangHoa;
        this.mMaPhieuTraHang = maPhieuTraHang;
        this.mSoLuong = soLuong;
    }
    
     public ChiTietPhieuTraHang(ResultSet rs ) {
        try {
            this.mMaHangHoa = rs.getString(BangChiTietPhieuTraHang.MA_HANG_HOA);
            this.mMaPhieuTraHang =rs.getString(BangChiTietPhieuTraHang.MA_PHIEU_TRA_HANG);
            this.mSoLuong =rs.getInt(BangChiTietPhieuTraHang.SO_LUONG);
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietPhieuTraHang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
