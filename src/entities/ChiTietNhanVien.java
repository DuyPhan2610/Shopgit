package entities;

import org.apache.poi.xssf.usermodel.XSSFCell;

public class ChiTietNhanVien {
    public String mMaChiTietNhanVien;
    public String mMaNhanVien;
    public int mSoNgayLam;
    public int mSoGioLamTrongNgay;
    public int mTongSoGioLam;
    public int mTienLuong;
    public int mTienThuong;
    public int mTongLuong;
    public String mGhiChu;
    public String mNgayVaoLam;
    
    public ChiTietNhanVien(){}
    public ChiTietNhanVien(String maChiTietNhanVien ,
    String maNhanVien ,
    int soNgayLam ,
      int soGioLamTrongNgay ,
      int tongSoGioLam ,
      int tienLuong ,
      int tienThuong ,
      int tongLuong ,
      String ghiChu, String ngayVaoLam ){
        
        this.mMaChiTietNhanVien = maChiTietNhanVien;
        this.mMaNhanVien = maNhanVien;
        this.mSoNgayLam = soNgayLam;
        this.mSoGioLamTrongNgay = soGioLamTrongNgay;
        this.mTongSoGioLam = tongSoGioLam;
        this.mTienLuong = tienLuong;
        this.mTienThuong = tienThuong;
        this.mTongLuong = tongLuong;
        this.mGhiChu = ghiChu;
        this.mNgayVaoLam = ngayVaoLam;
        
        
    }
}
