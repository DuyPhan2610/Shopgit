package entities;

import org.apache.poi.xssf.usermodel.XSSFCell;

public class NhanVien {
  
   public String mMaNhanVien, mMaHoaDonBanHang,
           mTenNhanVien,
           mNgaySinh, mDiaChi, mGhiChu;
   public int mSoDienThoai;
   public NhanVien(){}
   public NhanVien(String maNhanVien, String maHoaDonBanHang,
           String tenNhanVien, int soDienThoai, 
           String ngaySinh, String diaChi, String ghiChu){
       
       this.mMaNhanVien = maNhanVien;
       this.mMaHoaDonBanHang = maHoaDonBanHang;
       this.mTenNhanVien = tenNhanVien;
       this.mSoDienThoai = soDienThoai;
       this.mNgaySinh = ngaySinh;
       this.mDiaChi = diaChi;
       this.mGhiChu = ghiChu;
   }
}
