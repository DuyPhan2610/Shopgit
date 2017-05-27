package entities;

public class PhieuNhapHang {
    public String mMaPhieuNhap;
    public String mMaNhaCungCap;
    public int mTongTien;
    public int mGiaGiam;
    public int mTienDaTra;
    public int mConNo;
    public String mThoiGian;
    public String mGhiChu;
    
    public PhieuNhapHang(){}
    public PhieuNhapHang(String maPhieuNhap,String maNhaCungCap,
            int tongTien, int giaGiam, int tienDaTra, int conNo, String thoiGian, String ghiChu){
        
        this.mMaPhieuNhap = maPhieuNhap;
        this.mMaNhaCungCap = maNhaCungCap;
        this.mTongTien = tongTien;
        this.mGiaGiam = giaGiam;
        this.mTienDaTra = tienDaTra;
        this.mConNo = conNo;
        this.mThoiGian = thoiGian;
        this.mGhiChu = ghiChu;
    }
}
