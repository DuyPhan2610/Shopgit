package entities;

import database.BangPhieuTraHang;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;

public class PhieuTraHang {
    public String mMaPhieuTraHang;
    public String mMaKhachHang;
    public int mTongGia;
    public int mTongGiaNhapLai;
    public int mTienHoanLai;
    public int mThoiGian;
    public String mGhiChu;
    public PhieuTraHang(){
        
    }
    
    public PhieuTraHang(String maPhieuTraHang, String maKhachHang, int tongGia, int tongGiaNhapLai, int tienHoanLai
            , int thoiGian, String ghiChu){
        
        this.mMaPhieuTraHang = maPhieuTraHang;
        this.mMaKhachHang = maKhachHang;
        this.mTongGia = tongGia;
        this.mTongGiaNhapLai = tongGiaNhapLai;
        this.mTienHoanLai = tienHoanLai;
        this.mThoiGian = thoiGian;
        this.mGhiChu = ghiChu;
    }

    
    //Tao constructor tu cac Cell
    public PhieuTraHang(XSSFCell maPhieuTraHang, XSSFCell maKhachHang, XSSFCell tongGia, XSSFCell tongGiaNhapLai, XSSFCell tienHoanLai, XSSFCell thoiGian,
                     XSSFCell ghiChu) {
        
        this.mMaPhieuTraHang = maPhieuTraHang.getStringCellValue();
        this.mMaKhachHang = maKhachHang.getStringCellValue();
        this.mTongGia = (int)tongGia.getNumericCellValue();
        this.mTongGiaNhapLai = (int)tongGiaNhapLai.getNumericCellValue();
        this.mTienHoanLai = (int)tienHoanLai.getNumericCellValue();
        this.mThoiGian = (int)thoiGian.getNumericCellValue();
        this.mGhiChu = ghiChu.getStringCellValue();
        
    }

    public PhieuTraHang(ResultSet rs) {
       try {
            this.mMaPhieuTraHang =rs.getString(BangPhieuTraHang.MA_PHIEU_TRA_HANG);
            this.mMaKhachHang = rs.getString(BangPhieuTraHang.MA_KHACH_HANG);
            this.mTongGia =rs.getInt(BangPhieuTraHang.TONG_GIA);
            this.mTongGiaNhapLai = rs.getInt(BangPhieuTraHang.TONG_GIA_NHAP_LAI);
            this.mTienHoanLai = rs.getInt(BangPhieuTraHang.TIEN_HOAN_LAI);
            this.mThoiGian =rs.getInt(BangPhieuTraHang.THOI_GIAN);
            this.mGhiChu =rs.getString(BangPhieuTraHang.GHI_CHU);
        } catch (SQLException ex) {
            Logger.getLogger(PhieuTraHang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
