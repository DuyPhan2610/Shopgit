package entities;

import database.BangKhachHang;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;

public class KhachHang {
    public String mMaLoaiKhacHang, mMaKhachHang, mTenKhachHang,
            mDienThoai, mGioiTinh, mMaCongNoCuaKhachHang;
    public int mNgaySinh;
    public KhachHang(){}
    public KhachHang(String maLoaiKhachHang, String maKhachHang, String maCongNoCuaKhachHang, String tenKhachHang,
            String dienThoai, String gioiTinh, int ngaySinh){
           
        this.mDienThoai = dienThoai;
        this.mGioiTinh = gioiTinh;
        this.mMaKhachHang = maKhachHang;
        this.mMaLoaiKhacHang = maLoaiKhachHang;
        this.mNgaySinh = ngaySinh;
        this.mTenKhachHang = tenKhachHang;
        this.mMaCongNoCuaKhachHang =maCongNoCuaKhachHang;
    }
    public KhachHang(ResultSet rs) {
       
        try {
            this.mMaKhachHang = rs.getString(BangKhachHang.MA_KHACH_HANG);
            this.mMaLoaiKhacHang =rs.getString(BangKhachHang.MA_LOAI_KHACH_HANG);
            this.mMaCongNoCuaKhachHang=rs.getString(BangKhachHang.MA_CONG_NO_CUA_KHACH_HANG);
            this.mTenKhachHang=rs.getString(BangKhachHang.TEN_KHACH_HANG);
            this.mDienThoai =rs.getString(BangKhachHang.DIEN_THOAI);
            this.mNgaySinh =rs.getInt(BangKhachHang.NGAY_SINH);
            this.mGioiTinh=rs.getString(BangKhachHang.GIOI_TINH);
        } catch (SQLException ex) {
            Logger.getLogger(KhachHang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
