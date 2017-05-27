package entities;

import java.sql.Date;
import java.sql.Time;
import org.apache.poi.xssf.usermodel.XSSFCell;

public class HoaDonBanHang {
    public String mMaHoaDonBanHang, mTenKhachHang, mGhiChu;
    public Date mThoiGian;
    public Time mGio;
    public int mTongTienHang, mGiaGiam, mKhachDaTra, mCongNo;
    
    public HoaDonBanHang(){}
    public HoaDonBanHang(String maHoaDonBanHang, String tenKhachHang,
            String ghiChu, int tongTienHang, int giaGiam, int khachDaTra, int congNo,
             Date thoiGian, Time gio ){
             
        this.mCongNo = congNo;
        this.mGhiChu = ghiChu;
        this.mGiaGiam = giaGiam;
        this.mGio = gio;
        this.mKhachDaTra = khachDaTra;
        this.mMaHoaDonBanHang = maHoaDonBanHang;
        this.mTenKhachHang = tenKhachHang;
        this.mThoiGian = thoiGian;
        this.mTongTienHang = tongTienHang;
    }
}
