package entities;


import database.BangHoaDonBanHang;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HoaDonBanHang {
    public String mMaHoaDonBanHang, mMaKhachHang, mGhiChu, mMaNhanVien;
    public String mThoiGian;
   
    public int  mGiaGiam, mKhachDaTra, mTongTien;
    
    
    public HoaDonBanHang(){}
    public HoaDonBanHang(String maHoaDonBanHang, String maKhachHang, String maNhanVien,
            String ghiChu, int giaGiam, int khachDaTra, int conNo,
             String thoiGian ){
             
        this.mTongTien = conNo;
        this.mGhiChu = ghiChu;
        this.mGiaGiam = giaGiam;
        this.mKhachDaTra = khachDaTra;
        this.mMaHoaDonBanHang = maHoaDonBanHang;
        this.mMaKhachHang = maKhachHang;
        this.mThoiGian = thoiGian;
        this.mMaNhanVien = maNhanVien;
        
    }
    
    public HoaDonBanHang(ResultSet rs){
        try {
            this.mMaHoaDonBanHang = rs.getString(BangHoaDonBanHang.MA_HOA_DON_BAN_HANG);
            this.mMaNhanVien = rs.getString(BangHoaDonBanHang.MA_NHAN_VIEN);
            this.mMaKhachHang = rs.getString(BangHoaDonBanHang.MA_KHACH_HANG);
            this.mGiaGiam = rs.getInt(BangHoaDonBanHang.GIA_GIAM);
            this.mKhachDaTra = rs.getInt(BangHoaDonBanHang.KHACH_DA_TRA);
            this.mTongTien = rs.getInt(BangHoaDonBanHang.TONG_TIEN);
            this.mGhiChu = rs.getString(BangHoaDonBanHang.GHI_CHU);
            this.mThoiGian = rs.getString(BangHoaDonBanHang.THOI_GIAN);
           
            
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonBanHang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
