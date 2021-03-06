/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import entities.ChiTietHoaDonBanHang;
import entities.ChiTietPhieuNhapHang;
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
public class BangChiTietPhieuNhapHang extends TruyVanDuLieu {
    
    //lay du lieu ve phai biet ten column
    public final static String MA_HANG_HOA ="MAHANGHOA";
    public final static String MA_PHIEU_NHAP ="MAPHIEUNHAP";
    public final static String SO_LUONG = "SOLUONG";

    public BangChiTietPhieuNhapHang(Connection connection) {
        super(connection);
    }
    
    public void themChiTietPhieuNhapHang (ChiTietPhieuNhapHang chiTietPhieuNhapHang){
        try {
            String insertChiTietPhieuNhap = "insert into chitietphieunhapnhang (MAHANGHOA, MAPHIEUNHAP, SOLUONG) values (?, ?, ?)";
            PreparedStatement preStatement = connection.prepareStatement(insertChiTietPhieuNhap);
            preStatement.setString(1, chiTietPhieuNhapHang.mMaHangHoa);
            preStatement.setString(2, chiTietPhieuNhapHang.mMaPhieuNhap);
            preStatement.setInt(3, chiTietPhieuNhapHang.mSoLuong);
            
            preStatement.execute();
            System.out.print ("\n Thêm chi tiết phiếu nhập thành công");
        } catch (SQLException ex)
        {
            System.out.print("\n Thêm chi tiết phiếu nhập không thành công");
        }
    }
    
    //Lấy tất cả các chi tiết phiếu nhập hàng trong csdl
     public ArrayList<ChiTietPhieuNhapHang> layTatCaChiTietPhieuNhapHangTrongCSDL() {
         ArrayList<ChiTietPhieuNhapHang> arlChiTietPhieuNhapHang = new ArrayList<>();
              // mở kết nối csdl

            // thực hiện câu truy vấn đưa kết quả vào result set
         ResultSet rs = this.selectData(CauTruyVan.selectedStatement("chitietphieunhapnhang"));
        try {
            while (rs.next()){
                arlChiTietPhieuNhapHang.add(new ChiTietPhieuNhapHang(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BangChiTietPhieuNhapHang.class.getName()).log(Level.SEVERE, null, ex);
        }
        return  arlChiTietPhieuNhapHang;
     }
     
     
     //lấy chi các tiết hóa đơn bán hàng từ mã hóa đơn
    public ArrayList<ChiTietPhieuNhapHang> layChiTietPhieuNhapTuMaPhieuNhap(String maPhieuNhap){
        String sql = "select * from chitietphieunhapnhang where MAPHIEUNHAP = ?";

        ArrayList<ChiTietPhieuNhapHang> dsCTPN = new ArrayList<>();
        try {
            PreparedStatement preStatement = connection.prepareStatement(sql);
            preStatement.setString(1, maPhieuNhap);
            ResultSet rs = preStatement.executeQuery();
            
            if((rs != null)){
                while(rs.next()){
                    
                    ChiTietPhieuNhapHang ctpn = new ChiTietPhieuNhapHang(rs);
                    dsCTPN.add(ctpn);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(BangHangHoa.class.getName()).log(Level.SEVERE, null, ex);
        }

       return dsCTPN;
    }
}
     
