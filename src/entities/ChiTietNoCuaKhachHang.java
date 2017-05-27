package entities;

import org.apache.poi.xssf.usermodel.XSSFCell;

public class ChiTietNoCuaKhachHang {
   public String mMaCongNoCuaKhachHang;
   public String mMaHoaDonBanHang;
   public int mTienNo;
   
   public ChiTietNoCuaKhachHang(){}
   public ChiTietNoCuaKhachHang(String maCongNoCuaKhachHang, String maHoaDonBanHang, int tienNo)
   {
       this.mMaHoaDonBanHang = maHoaDonBanHang;
       this.mTienNo = tienNo;
       this.mMaCongNoCuaKhachHang = maCongNoCuaKhachHang;
   } 
}
