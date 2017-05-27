package entities;

import org.apache.poi.xssf.usermodel.XSSFCell;

public class KhachHang {
    public String mMaLoaiKhacHang, mMaKhachHang, mTenKhachHang,
            mDienThoai, mGioiTinh;
    public int mNgaySinh;
    public KhachHang(){}
    public KhachHang(String maLoaiKhachHang, String maKhachHang, String tenKhachHang,
            String dienThoai, String gioiTinh, int ngaySinh){
           
        this.mDienThoai = dienThoai;
        this.mGioiTinh = gioiTinh;
        this.mMaKhachHang = maKhachHang;
        this.mMaLoaiKhacHang = maLoaiKhachHang;
        this.mNgaySinh = ngaySinh;
        this.mTenKhachHang = tenKhachHang;
    }
}
