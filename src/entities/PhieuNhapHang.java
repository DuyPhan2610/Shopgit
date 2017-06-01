package entities;

import database.BangPhieuNhapHang;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class PhieuNhapHang {
    
    
    
    public String mMaPhieuNhap;
    public String mMaNhaCungCap;
    public int mTongTien;
    public int mGiaGiam;
    public int mTienDaTra;
    public String mThoiGian;
    public String mGhiChu;
    
    public PhieuNhapHang(){}
    public PhieuNhapHang(String maPhieuNhap, String maNhaCungCap,
            int tongTien, int giaGiam, int tienDaTra, String thoiGian, String ghiChu){
        
        this.mMaPhieuNhap = maPhieuNhap;
        this.mMaNhaCungCap = maNhaCungCap;
        this.mTongTien = tongTien;
        this.mGiaGiam = giaGiam;
        this.mTienDaTra = tienDaTra;
        this.mThoiGian = thoiGian;
        this.mGhiChu = ghiChu;
    }
    
    public PhieuNhapHang(ResultSet rs){
        try {
            this.mMaPhieuNhap = rs.getString(BangPhieuNhapHang.MA_PHIEU_NHAP);
            this.mMaNhaCungCap = rs.getString(BangPhieuNhapHang.MA_PHIEU_NHAP);
            this.mTongTien = rs.getInt(BangPhieuNhapHang.TONG_TIEN);
            this.mTienDaTra = rs.getInt(BangPhieuNhapHang.TIEN_DA_TRA);
            this.mGiaGiam = rs.getInt(BangPhieuNhapHang.GIA_GIAM);
            this.mThoiGian = rs.getString(BangPhieuNhapHang.THOI_GIAN);
            this.mGhiChu = rs.getString(BangPhieuNhapHang.GHI_CHU);
            
        } catch (SQLException ex) {
            Logger.getLogger(PhieuNhapHang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
