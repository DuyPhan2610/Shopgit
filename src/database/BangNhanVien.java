/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import control.ControlUtils;
import entities.KhachHang;
import entities.NhanVien;
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
public class BangNhanVien extends TruyVanDuLieu{
    //
    public final static String MA_NHAN_VIEN ="MANHANVIEN";
    public final static String TEN_NHAN_VIEN ="TENNHANVIEN";
    public final static String SO_DIEN_THOAI ="SODIENTHOAI";
    public final static String NGAY_SINH = "NGAYSINH";
    public final static String DIA_CHI ="DIACHI";
    public final static String GHI_CHU ="GHICHU";

    public BangNhanVien(Connection connection) {
        super(connection);
    }
    
    public void themNhanVien(NhanVien nhanVien){
        try{
            String sql = "insert into nhanvien (MANHANVIEN, TENNHANVIEN, SODIENTHOAI, NGAYSINH, DIACHI, GHICHU) values (?, ?, ?, ?, ?, ?)";
            PreparedStatement preStatement = connection.prepareStatement(sql);
            preStatement.setString(1, nhanVien.mMaNhanVien);
            preStatement.setString(2, nhanVien.mTenNhanVien);
            preStatement.setString(3, nhanVien.mSoDienThoai);
            preStatement.setString(4, nhanVien.mNgaySinh);
            preStatement.setString(5, nhanVien.mDiaChi);
            preStatement.setString(6, nhanVien.mGhiChu);
            
            boolean checkSuccess = preStatement.execute();
            if (checkSuccess){
                JOptionPane.showMessageDialog(null,
                "Thêm nhân viên thành công!");
                System.out.print("\n Thêm dữ liệu thành công");
            }
        } catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null,
                "Thêm nhân viên thất bại!",
                "Lỗi",
                JOptionPane.ERROR_MESSAGE);
            System.out.print ("\n Thêm dữ liệu không thành công");
        }
    }
    
     //Lấy tất cả nhân viên trong CSDL;
    public ArrayList<NhanVien> layTatCaNhanVienTrongCSDL(){
        ArrayList <NhanVien> arlNhanVien = new ArrayList<>();
        //mở kết nối CSDL
            
            //thực hiện câu truy vấn đưa kết quả vào Result set
        ResultSet rs = this.selectData(CauTruyVan.selectedStatement("nhanvien"));
        try {
            while (rs.next()){
                arlNhanVien.add(new NhanVien(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BangNhanVien.class.getName()).log(Level.SEVERE, null, ex);
        }
            return arlNhanVien;
    }
    
    // Hàm tự động tạo mã nhân viên từ nhân viên cuối cùng
    public String taoMaNhanVien(){
        ArrayList<NhanVien> arlNhanVien = this.layTatCaNhanVienTrongCSDL();
        
        if(arlNhanVien.size() > 0){
        //Lấy nhân viên cuối cùng trong csdl
            NhanVien nhanVien = arlNhanVien.get(arlNhanVien.size() - 1);   
            return ControlUtils.taoMaNhanVien(nhanVien.mMaNhanVien);
        }
        else{
            return ControlUtils.taoMaNhanVien("NV000000");
        }
    }
    
    //lấy Nhân viên từ mà nhân viên
    public NhanVien layNhanVienTuMaNhanVien(String maNhanVien){
        String sql = "select * from nhanvien where MANHANVIEN = ?";

        try {
            PreparedStatement preStatement = connection.prepareStatement(sql);
            preStatement.setString(1, maNhanVien);
            ResultSet rs = preStatement.executeQuery();
            
            if((rs != null)){
                    rs.first();
                    //System.out.println(rs.getString("MAHANGHOA"));
                    NhanVien nv = new NhanVien(rs);
                    rs.close();
                    return nv;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BangHangHoa.class.getName()).log(Level.SEVERE, null, ex);
        }

       return null;
    }
}
