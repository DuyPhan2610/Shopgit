/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import control.ControlUtils;
import entities.HoaDonBanHang;
import entities.KhachHang;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author USER
 */
public class BangKhachHang extends TruyVanDuLieu {
    //
    public final static String MA_KHACH_HANG ="MAKHACHHANG";
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
            String sql ="insert into khachhang (MAKHACHHANG, MACONGNOCUAKHACHHANG, TENKHACHHANG, DIENTHOAI, NGAYSINH, GIOITINH) values (?, ?, ?, ?, ?, ?)";
            PreparedStatement preStatement = connection.prepareStatement(sql);
            preStatement.setString(1, khachHang.mMaKhachHang);
            preStatement.setString(2, khachHang.mMaCongNoKhachHang);
            preStatement.setString(3, khachHang.mTenKhachHang);
            preStatement.setString(4, khachHang.mDienThoai);
            preStatement.setString(5, khachHang.mNgaySinh);
            preStatement.setString(6, khachHang.mGioiTinh);
            
            boolean checkSuccess = true;

            preStatement.executeUpdate();
            if (checkSuccess){
                //default title and icon
                JOptionPane.showMessageDialog(null,
                    "Thêm khách hàng thành công!");
                System.out.print("\n Thêm khach hang thành công");
            }
        } catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null,
            "Thêm khách hàng không thành công",
            "Lỗi",
            JOptionPane.ERROR_MESSAGE);
            System.out.print("\n Thêm khach hàng không thành công");
        }
    }
    
    //lấy khách hàng từ mã khách hàng
    public KhachHang layKhachHangTuMaKH(String maKH){
        
        try {
            String sql = "select * from khachhang where MAKHACHHANG = ?";
            PreparedStatement preStatement = connection.prepareStatement(sql);
            preStatement.setString(1, maKH);
            ResultSet rs = preStatement.executeQuery();
            rs.first();
            KhachHang kh = new KhachHang(rs);
            return kh;
        } catch (SQLException ex) {
            Logger.getLogger(BangKhachHang.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
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
    
    
    //lấy khách hàng từ mã khách hàng
    public KhachHang layKhachHangTuMaKhachHang(String maKhachHang){
        String sql = "select * from khachhang where MAKHACHHANG = ?";

        try {
            PreparedStatement preStatement = connection.prepareStatement(sql);
            preStatement.setString(1, maKhachHang);
            ResultSet rs = preStatement.executeQuery();
            
            if((rs != null)){
                    rs.first();
                    //System.out.println(rs.getString("MAHANGHOA"));
                    KhachHang kh = new KhachHang(rs);
                    rs.close();
                    return kh;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BangHangHoa.class.getName()).log(Level.SEVERE, null, ex);
        }

       return null;
    }
}
