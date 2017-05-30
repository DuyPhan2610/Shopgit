/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;


import entities.ChiTietPhieuTraHang;
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
public class BangChiTietPhieuTraHang extends TruyVanDuLieu{
    
    //lay du lieu ve phai biet ten column
    public final static String MA_HANG_HOA ="MAHANGHOA";
    public final static String MA_PHIEU_TRA_HANG = "MAPHIEUTRAHANG";
    public final static String SO_LUONG = "SOLUONG";

    public BangChiTietPhieuTraHang(Connection connection) {
        super(connection);
    }
    
    public void themChiTietPhieuTraHang (ChiTietPhieuTraHang chiTietPhieuTraHang) {
        try {
            String sql = "insert into chitietphieutrahang (MAHANGHOA, MAPHIEUTRAHANG, SOLUONG) values (?, ?, ?)";
            PreparedStatement preStatement = connection.prepareStatement(sql);
            preStatement.setString(1, chiTietPhieuTraHang.mMaHangHoa);
            preStatement.setString(2,chiTietPhieuTraHang.mMaPhieuTraHang);
            preStatement.setInt(3, chiTietPhieuTraHang.mSoLuong);
            
            boolean checkSucess = preStatement.execute();
            if (checkSucess)
                System.out.print("\n Thêm dữ liệu thành công");
        } catch (SQLException ex)
                {
                   System.out.print ("\n Thêm dữ liệu không thành công"); 
                }
    }
    
    //Lấy tất cả các chi tiết phiếu trả hàng trong csdl
     public ArrayList<ChiTietPhieuTraHang> layTatCaChiTietPhieuTraHangTrongCSDL() {
         ArrayList<ChiTietPhieuTraHang> arlChiTietPhieuTraHang = new ArrayList<>();
              // mở kết nối csdl

            // thực hiện câu truy vấn đưa kết quả vào result set
         ResultSet rs = this.selectData(CauTruyVan.selectedStatement("chitietphieutrahang"));
        try {
            while (rs.next()){
                arlChiTietPhieuTraHang.add(new ChiTietPhieuTraHang(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BangChiTietPhieuTraHang.class.getName()).log(Level.SEVERE, null, ex);
        }
        return  arlChiTietPhieuTraHang;
     }
}
