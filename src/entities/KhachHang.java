package entities;

import database.BangKhachHang;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;

public class KhachHang {
    public String mMaKhachHang, mMaCongNoKhachHang, mTenKhachHang,
            mDienThoai, mNgaySinh, mGioiTinh;
    public KhachHang(){}
    public KhachHang(String maKhachHang, String maCongNoKhachHang, String tenKhachHang,
            String dienThoai, String ngaySinh, String gioiTinh){
           
        this.mDienThoai = dienThoai;
        this.mGioiTinh = gioiTinh;
        this.mMaKhachHang = maKhachHang;
        this.mNgaySinh = ngaySinh;
        this.mTenKhachHang = tenKhachHang;
        this.mMaCongNoKhachHang = maCongNoKhachHang;
    }
    public KhachHang(ResultSet rs) {
       
        try {
            this.mMaKhachHang = rs.getString(BangKhachHang.MA_KHACH_HANG);
            this.mMaCongNoKhachHang=rs.getString(BangKhachHang.MA_CONG_NO_CUA_KHACH_HANG);
            this.mTenKhachHang=rs.getString(BangKhachHang.TEN_KHACH_HANG);
            this.mDienThoai =rs.getString(BangKhachHang.DIEN_THOAI);
            this.mNgaySinh =rs.getString(BangKhachHang.NGAY_SINH);
            this.mGioiTinh=rs.getString(BangKhachHang.GIOI_TINH);
        } catch (SQLException ex) {
            Logger.getLogger(KhachHang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
