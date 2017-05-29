/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import entities.LoaiKhachHang;
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
public class BangLoaiKhachHang extends TruyVanDuLieu{
    //lay du lieu ve phai biet ten column
    public final static String MA_LOAI_KHACH_HANG ="MALOAIKHACHHANG";
    public final static String LOAI_KHACH_HANG ="LOAIKHACHHANG";
    
    public void themLoaiKhachHang(LoaiKhachHang loaiKhachHang){
        try {
            String sql = "insert into loaikhachhang (MALOAIKHACHHANG, LOAIKHACHHANG) values (?, ?)";
            PreparedStatement preStatement = connection.prepareStatement(sql);
            preStatement.setString(1, loaiKhachHang.mMaLoaiKhachHang);
            preStatement.setString(2, loaiKhachHang.mLoaiKhachHang);
            
            boolean checkSuccess = preStatement.execute();
            if (checkSuccess)
                System.out.print("\n Thêm dữ liệu thành công");
        } catch (SQLException ex)
        {
            System.out.print ("\n Thêm dữ liệu không thành công");
        }            
    }
    //Lấy tất cả các loại khách hàng trong CSDL;
    public ArrayList<LoaiKhachHang> layTatCaLoaiKhachHangTrongCSDL(){
        ArrayList <LoaiKhachHang> arlLoaiKhachHang = new ArrayList<>();
        //mở kết nối CSDL
            
            //thực hiện câu truy vấn đưa kết quả vào Result set
        ResultSet rs = this.selectData(CauTruyVan.selectedStatement("loaikhachhang"));
        try {
            while (rs.next()){
                arlLoaiKhachHang.add(new LoaiKhachHang(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BangLoaiKhachHang.class.getName()).log(Level.SEVERE, null, ex);
        }
            return arlLoaiKhachHang;
    }
    
}
