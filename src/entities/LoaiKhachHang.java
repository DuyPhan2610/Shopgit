package entities;

import org.apache.poi.xssf.usermodel.XSSFCell;

public class LoaiKhachHang {
    public String mMaLoaiKhachHang, mLoaiKhachHang;
    public LoaiKhachHang(){}
    public LoaiKhachHang(String maLoaiKhachHang, String loaiKhachHang)
    {
        this.mLoaiKhachHang = loaiKhachHang;
        this.mMaLoaiKhachHang = maLoaiKhachHang;
    }
}
