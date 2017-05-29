package entities;


import database.BangNhanVien;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;

public class NhanVien {
  
   public String mMaNhanVien, mTenNhanVien, mDiaChi, mGhiChu;
   public String mSoDienThoai;
   public int mNgaySinh;
   public NhanVien(){}
   public NhanVien(String maNhanVien,
           String tenNhanVien, String soDienThoai, 
           int ngaySinh, String diaChi, String ghiChu){
       
       this.mMaNhanVien = maNhanVien;
       this.mTenNhanVien = tenNhanVien;
       this.mSoDienThoai = soDienThoai;
       this.mNgaySinh = ngaySinh;
       this.mDiaChi = diaChi;
       this.mGhiChu = ghiChu;
   }
   
    public NhanVien(ResultSet rs) {
       
        try {
            this.mMaNhanVien = rs.getString(BangNhanVien.MA_NHAN_VIEN);
            this.mTenNhanVien =rs.getString(BangNhanVien.TEN_NHAN_VIEN);
            this.mSoDienThoai=rs.getString(BangNhanVien.SO_DIEN_THOAI);
            this.mNgaySinh=rs.getInt(BangNhanVien.NGAY_SINH);
            this.mDiaChi=rs.getString(BangNhanVien.DIA_CHI);
            this.mGhiChu=rs.getString(BangNhanVien.GHI_CHU);
            
        } catch (SQLException ex) {
            Logger.getLogger(NhanVien.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
