package entities;

import org.apache.poi.xssf.usermodel.XSSFCell;

public class ChiTietCongNoNhaCungCap {
   public String mMaCongNoNhaCungCap;
   public String mMaPhieuNhap;
   public int mTOngNo;
   public ChiTietCongNoNhaCungCap(){}
   public ChiTietCongNoNhaCungCap(String maCongNoNhaCungCap, String maPhieuNhap, int tongNo){
       this.mMaPhieuNhap = maPhieuNhap;
       this.mTOngNo = tongNo;
       this.mMaCongNoNhaCungCap =maCongNoNhaCungCap;
   }
}
