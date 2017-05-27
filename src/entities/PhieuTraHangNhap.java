package entities;

import org.apache.poi.xssf.usermodel.XSSFCell;

public class PhieuTraHangNhap {
    public String mMaPhieuTraHangNhap;
    public String mMaNhaCungCap;
    public String mTenNhaCungCap;
    public int mTienNhaCungCapCanTra;
    public int mTienNhaCungCapDaTra;
    public int mThoiGian;
    public String mTrangThai;
    public String mGhiChu;
    
    public PhieuTraHangNhap(){
        
    }
    
    public PhieuTraHangNhap(String maPhieuTraHangNhap, String maNhaCungCap, String tenNhaCungCap, int tienNhaCungCapCanTra
    ,int tienNhaCungCapDaTra, int thoiGian, String trangThai, String ghiChu){
        
        this.mMaPhieuTraHangNhap = maPhieuTraHangNhap;
        this.mMaNhaCungCap = maNhaCungCap;
        this.mTenNhaCungCap = tenNhaCungCap;
        this.mTienNhaCungCapCanTra = tienNhaCungCapCanTra;
        this.mTienNhaCungCapDaTra = tienNhaCungCapDaTra;
        this.mTrangThai = trangThai;
        this.mGhiChu = ghiChu;
        
        
    }

}
