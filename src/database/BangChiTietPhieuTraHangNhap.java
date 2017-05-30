/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;


import entities.ChiTietPhieuTraHangNhap;
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
public class BangChiTietPhieuTraHangNhap extends TruyVanDuLieu{
    
    //lay du lieu ve phai biet ten column
    public final static String MA_HANG_HOA = "MAHANGHOA";
    public final static String MA_PHIEU_TRA_HANG_NHAP ="MAPHIEUTRAHANGNHAP";
    public final static String SO_LUONG ="SOLUONG";

    public BangChiTietPhieuTraHangNhap(Connection connection) {
        super(connection);
    }
    
    public void themChiTietPhieuTraHangNhap(ChiTietPhieuTraHangNhap chiTietPhieuTraHangNhap){
        try {
            String sql = "insert into chitietphieutrahangnhap (MAHANGHOA, MẠPHIEUTRAHANGNHAP, SOLUONG) values (?, ?, ?)";
            PreparedStatement preStatement = connection.prepareStatement(sql);
            preStatement.setString(1, chiTietPhieuTraHangNhap.mMaHangHoa);
            preStatement.setString(2, chiTietPhieuTraHangNhap.mMaPhieuTraHangNhap);
            preStatement.setInt(3, chiTietPhieuTraHangNhap.mSoLuong);
            
            boolean checkSuccess = preStatement.execute();
            if (checkSuccess)
                System.out.print ("\n Thêm dữ liệu thành công");
        } catch (SQLException ex)
        {
            System.out.print ("\n Thêm dữ liệu không thành công");
        }
    }
    
    //Lấy tất cả các chi tiết phiếu trả hàng nhập trong csdl
     public ArrayList<ChiTietPhieuTraHangNhap> layTatCaChiTietPhieuTraHangNhapTrongCSDL() {
         ArrayList<ChiTietPhieuTraHangNhap> arlChiTietPhieuTraHangNhap = new ArrayList<>();
              // mở kết nối csdl

            // thực hiện câu truy vấn đưa kết quả vào result set
         ResultSet rs = this.selectData(CauTruyVan.selectedStatement("chitietphieutrahangnhap"));
        try {
            while (rs.next()){
                arlChiTietPhieuTraHangNhap.add(new ChiTietPhieuTraHangNhap(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BangChiTietPhieuTraHangNhap.class.getName()).log(Level.SEVERE, null, ex);
        }
        return  arlChiTietPhieuTraHangNhap;
     }
}
