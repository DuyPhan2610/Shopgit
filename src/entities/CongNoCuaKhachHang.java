package entities;

import org.apache.poi.xssf.usermodel.XSSFCell;

public class CongNoCuaKhachHang {
   public String mMaCongNoCuaKhachHang, mTenKhachHang, mGhiChu;
   public int mTongNo, mNoCanTra;
    public CongNoCuaKhachHang(){}
    public CongNoCuaKhachHang(String maCongNoCuaKhachHang, String tenKhachHang, String ghiChu,
            int tongNo, int noCanTra){
            
        this.mMaCongNoCuaKhachHang = maCongNoCuaKhachHang;
        this.mTenKhachHang = tenKhachHang;
        this.mTongNo = tongNo;
        this.mNoCanTra = noCanTra;
        this.mGhiChu = ghiChu;
    }
}
