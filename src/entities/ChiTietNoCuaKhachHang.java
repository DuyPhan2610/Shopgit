package entities;

import org.apache.poi.xssf.usermodel.XSSFCell;

public class ChiTietNoCuaKhachHang {
   public String mMaHoaDonBanHang;
   public int mTienNo;
   public ChiTietNoCuaKhachHang(){}
   public ChiTietNoCuaKhachHang(String maHoaDonBanHang, int tienNo)
   {
       this.mMaHoaDonBanHang = maHoaDonBanHang;
       this.mTienNo = tienNo;
   } 
}
