/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import entities.ChiTietNhanVien;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public class BangChiTietNhanVien extends TruyVanDuLieu {
    
    //lay du lieu ve phai biet ten column
    public final static String MA_CHI_TIET_NHAN_VIEN = "MACHITIETNHANVIEN";
    public final static String MA_NHAN_VIEN = "MANHANVIEN";
    public final static String SO_NGAY_LAM = "SONGAYLAM";
    public final static String SO_GIO_LAM_TRONG_NGAY = "SOGIOLAMTRONGNGAY";
    public final static String TONG_SO_GIO_LAM = "TONGSOGIOLAM";
    public final static String TIEN_LUONG = "TIENLUONG";
    public final static String TIEN_THUONG = "TIENTHUONG";
    public final static String TONG_LUONG = "TONGLUONG";
    public final static String GHI_CHU = "GHICHU";
    public final static String NGAY_VAO_LAM = "NGAYVAOLAM";
    
    public void themChiTietNhanVien (ChiTietNhanVien chiTietNhanVien){
        try {
            String sql = "insert into chitietnhanvien (MACHITIETNHANVIEN, MANHANVIEN, SONGAYLAM, SOGIOLAMTRONGNGAY, TONGSOGIOLAM, TIENLUONG, TIENTHUONG, TONGLUONG, GHICHU, NGAYVAOLAM) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preStatement = connection.prepareStatement(sql);
            preStatement.setString(1, chiTietNhanVien.mMaChiTietNhanVien);
            preStatement.setString(2, chiTietNhanVien.mMaNhanVien);
            preStatement.setInt(3, chiTietNhanVien.mSoNgayLam);
            preStatement.setInt(4, chiTietNhanVien.mSoGioLamTrongNgay);
            preStatement.setInt(5, chiTietNhanVien.mTongSoGioLam);
            preStatement.setInt(6, chiTietNhanVien.mTienLuong);
            preStatement.setInt(7, chiTietNhanVien.mTienThuong);
            preStatement.setInt(8, chiTietNhanVien.mTongLuong);
            preStatement.setString(9, chiTietNhanVien.mGhiChu);
            preStatement.setString(10, chiTietNhanVien.mNgayVaoLam);
            
            boolean checkSuccess = preStatement.execute();
            if (checkSuccess)
                System.out.print("\n Thêm dữ liệu thành công");
        } catch (SQLException ex)
        {
            System.out.print("\n Thêm dữ liệu không thành công");
        }
    }
    
    //Lấy tất cả các chi tiết nhân viên trong csdl
     public ArrayList<ChiTietNhanVien> layTatCaChiTietNhanVienTrongCSDL() {
         ArrayList<ChiTietNhanVien> arlChiTietNhanVien = new ArrayList<>();
              // mở kết nối csdl

            // thực hiện câu truy vấn đưa kết quả vào result set
         ResultSet rs = this.selectData(CauTruyVan.selectedStatement("chitietnhanvien"));
        try {
            while (rs.next()){
                arlChiTietNhanVien.add(new ChiTietNhanVien(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BangChiTietPhieuNhapHang.class.getName()).log(Level.SEVERE, null, ex);
        }
        return  arlChiTietNhanVien;
     }
}
