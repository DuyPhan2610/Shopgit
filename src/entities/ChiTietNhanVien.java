package entities;

import database.BangChiTietNhanVien;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;

public class ChiTietNhanVien {
    public String mMaChiTietNhanVien;
    public String mMaNhanVien;
    public int mSoNgayLam;
    public int mSoGioLamTrongNgay;
    public int mTongSoGioLam;
    public int mTienLuong;
    public int mTienThuong;
    public int mTongLuong;
    public String mGhiChu;
    public String mNgayVaoLam;
    
    public ChiTietNhanVien(){}
    public ChiTietNhanVien(String maChiTietNhanVien ,
    String maNhanVien ,
    int soNgayLam ,
      int soGioLamTrongNgay ,
      int tongSoGioLam ,
      int tienLuong ,
      int tienThuong ,
      int tongLuong ,
      String ghiChu, String ngayVaoLam ){
        
        this.mMaChiTietNhanVien = maChiTietNhanVien;
        this.mMaNhanVien = maNhanVien;
        this.mSoNgayLam = soNgayLam;
        this.mSoGioLamTrongNgay = soGioLamTrongNgay;
        this.mTongSoGioLam = tongSoGioLam;
        this.mTienLuong = tienLuong;
        this.mTienThuong = tienThuong;
        this.mTongLuong = tongLuong;
        this.mGhiChu = ghiChu;
        this.mNgayVaoLam = ngayVaoLam;
    }
    
     public ChiTietNhanVien(ResultSet rs){
        try {
            this.mMaChiTietNhanVien = rs.getString(BangChiTietNhanVien.MA_CHI_TIET_NHAN_VIEN);
            this.mMaNhanVien = rs.getString(BangChiTietNhanVien.MA_NHAN_VIEN);
            this.mSoNgayLam = rs.getInt(BangChiTietNhanVien.SO_NGAY_LAM);
            this.mSoGioLamTrongNgay = rs.getInt(BangChiTietNhanVien.SO_GIO_LAM_TRONG_NGAY);
            this.mTongSoGioLam = rs.getInt(BangChiTietNhanVien.TONG_SO_GIO_LAM);
            this.mTienLuong = rs.getInt(BangChiTietNhanVien.TIEN_LUONG);
            this.mTienThuong = rs.getInt(BangChiTietNhanVien.TIEN_THUONG);
            this.mTongLuong = rs.getInt(BangChiTietNhanVien.TONG_LUONG);
            this.mGhiChu = rs.getString(BangChiTietNhanVien.GHI_CHU);
            this.mNgayVaoLam = rs.getString (BangChiTietNhanVien.NGAY_VAO_LAM);
            
        } catch (SQLException ex) {
            Logger.getLogger(HangHoa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
