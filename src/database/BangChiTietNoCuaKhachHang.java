/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import entities.ChiTietNoCuaKhachHang;
import java.sql.Connection;
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
public class BangChiTietNoCuaKhachHang extends TruyVanDuLieu {
    
    //lay du lieu ve phai biet ten column
    public final static String MA_CONG_NO_CUA_KHACH_HANG = "MACONGNOCUAKHACHHANG";
    public final static String MA_HOA_DON_BAN_HANG = "MAHOADONBANHANG";
    public final static String TIEN_NO ="TIENNO";

    public BangChiTietNoCuaKhachHang(Connection connection) {
        super(connection);
    }
    
    public void themChiTietNoCuaKhachHang (ChiTietNoCuaKhachHang chiTietNoCuaKhachHang) {
        try {
            String sql = "insert into chitietnocuakhachhang (MACONGNOCUAKHACHHANG, MAHOADONBANHANG, TIENNO) values (?, ?, ?) ";
            PreparedStatement preStatement = connection.prepareStatement(sql);
            preStatement.setString(1, chiTietNoCuaKhachHang.mMaCongNoCuaKhachHang);
            preStatement.setString(2, chiTietNoCuaKhachHang.mMaHoaDonBanHang );
            preStatement.setInt(3, chiTietNoCuaKhachHang.mTienNo);
            
            boolean checkSuccess = preStatement.execute();
            if (checkSuccess)
                System.out.print("\n Thêm dữ liệu thành công");
        } catch (SQLException ex)
        {
            System.out.print ("\n Thêm dữ liệu không thành công");
        }
    }
    
    //Lấy tất cả các chi tiết nhân viên trong csdl
     public ArrayList<ChiTietNoCuaKhachHang> layTatCaChiTietNoCuaKhachHangTrongCSDL() {
         ArrayList<ChiTietNoCuaKhachHang> arlChiTietNoCuaKhachHang = new ArrayList<>();
              // mở kết nối csdl

            // thực hiện câu truy vấn đưa kết quả vào result set
         ResultSet rs = this.selectData(CauTruyVan.selectedStatement("chitietnocuakhachhang"));
        try {
            while (rs.next()){
                arlChiTietNoCuaKhachHang.add(new ChiTietNoCuaKhachHang(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BangChiTietPhieuNhapHang.class.getName()).log(Level.SEVERE, null, ex);
        }
        return  arlChiTietNoCuaKhachHang;
     }
}
