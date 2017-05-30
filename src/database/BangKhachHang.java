/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import control.ControlUtils;
import entities.KhachHang;
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
public class BangKhachHang extends TruyVanDuLieu {
    //
    public final static String MA_KHACH_HANG ="MAKHACHHANG";
    public final static String MA_LOAI_KHACH_HANG ="MALOAIKHACHHANG";
    public final static String MA_CONG_NO_CUA_KHACH_HANG ="MACONGNOCUAKHACHHANG";
    public final static String TEN_KHACH_HANG ="TENKHACHHANG";
    public final static String DIEN_THOAI ="DIENTHOAI";
    public final static String NGAY_SINH ="NGAYSINH";
    public final static String GIOI_TINH ="GIOITINH";

    public BangKhachHang(Connection connection) {
        super(connection);
    }
    
    public void themKhachHang(KhachHang khachHang){
        try {
            String sql ="insert into khachhang (MAKHACHHANG, MALOAIKHACHHANG, MACONGNOCUAKHACHHANG, TENKHACHHANG, DIENTHOAI, NGAYSINH, GIOITINH) values (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preStatement = connection.prepareStatement(sql);
            preStatement.setString(1, khachHang.mMaKhachHang);
            preStatement.setString(2, khachHang.mMaLoaiKhacHang);
            preStatement.setString(3, khachHang.mMaCongNoCuaKhachHang);
            preStatement.setString(4, khachHang.mTenKhachHang);
            preStatement.setString(5, khachHang.mDienThoai);
            preStatement.setInt(6, khachHang.mNgaySinh);
            preStatement.setString(7, khachHang.mGioiTinh);
            
            boolean checkSuccess = preStatement.execute();
            if (checkSuccess)
                System.out.print("\n Thêm dữ liệu thành công");
        } catch (SQLException ex)
        {
            System.out.print("\n Thêm dữ liệu không thành công");
        }
    }
    
    //Lấy tất cả các khách hàng trong CSDL;
    public ArrayList<KhachHang> layTatCaKhachHangTrongCSDL(){
        ArrayList <KhachHang> arlKhachHang = new ArrayList<>();
        //mở kết nối CSDL
            
            //thực hiện câu truy vấn đưa kết quả vào Result set
        ResultSet rs = this.selectData(CauTruyVan.selectedStatement("khachhang"));
        try {
            while (rs.next()){
                arlKhachHang.add(new KhachHang(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BangKhachHang.class.getName()).log(Level.SEVERE, null, ex);
        }
            return arlKhachHang;
    }
    
    // Hàm tự động tạo mã khách hàng từ khách hàng cuối cùng
    public String taoMaKhachHang(){
        ArrayList<KhachHang> arlKhachHang = this.layTatCaKhachHangTrongCSDL();
        
        if(arlKhachHang.size() > 0){
        //Lấy khách hàng cuối cùng trong csdl
            KhachHang khachHang = arlKhachHang.get(arlKhachHang.size() - 1);   
            return ControlUtils.taoMaKhachHang(khachHang.mMaKhachHang);
        }
        else{
            return ControlUtils.taoMaKhachHang("KH000000");
        }
    }
}
