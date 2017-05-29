package entities;

import database.BangLoaiKhachHang;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;

public class LoaiKhachHang {
    public String mMaLoaiKhachHang, mLoaiKhachHang;
    public LoaiKhachHang(){}
    public LoaiKhachHang(String maLoaiKhachHang, String loaiKhachHang)
    {
        this.mLoaiKhachHang = loaiKhachHang;
        this.mMaLoaiKhachHang = maLoaiKhachHang;
    }
    
     public LoaiKhachHang(ResultSet rs) {
       
        try {
            this.mMaLoaiKhachHang = rs.getString(BangLoaiKhachHang.MA_LOAI_KHACH_HANG);
            this.mLoaiKhachHang =rs.getString(BangLoaiKhachHang.LOAI_KHACH_HANG);
            
        } catch (SQLException ex) {
            Logger.getLogger(LoaiKhachHang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
