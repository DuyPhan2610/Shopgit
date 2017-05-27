package entities;


import database.BangChiTietHoaDonBanHang;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;

public class ChiTietHoaDonBanHang {
   public String mMaHangHoa;
    public String mMaHoaDonBanHang;
    public int mSoLuong;
    
    public ChiTietHoaDonBanHang(){
        
    }
    
    public ChiTietHoaDonBanHang(String maHangHoa, String maHoaDonBanHang, int soLuong ){
        
        this.mMaHangHoa = maHangHoa;
        this.mMaHoaDonBanHang = maHoaDonBanHang;
        this.mSoLuong = soLuong;
    }
    
    public ChiTietHoaDonBanHang(ResultSet rs){
        try {
            this.mMaHoaDonBanHang = rs.getString(BangChiTietHoaDonBanHang.MA_HOA_DON_BAN_HANG);
            this.mMaHangHoa = rs.getString(BangChiTietHoaDonBanHang.MA_HANG_HOA);
            this.mSoLuong = rs.getInt(BangChiTietHoaDonBanHang.SO_LUONG);
           
            
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietHoaDonBanHang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
