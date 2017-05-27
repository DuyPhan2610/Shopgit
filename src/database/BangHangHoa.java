/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import entities.HangHoa;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author phamh
 */
public class BangHangHoa extends TruyVanDuLieu{

    public BangHangHoa() throws SQLException, ClassNotFoundException {
        super();
    }
    
    
    // Thêm danh sách các hàng hóa vào table hàng hóa
    public void themDuLieuVaoHangHoa(ArrayList<HangHoa> arlHangHoa){
    
        for(int i = 0; i < arlHangHoa.size(); i ++){
            themMotHangHang(arlHangHoa.get(i));
        }
    }
        // them 1 record hang hoa
   ////////////////////////////////////////////////////////////////////////////
   public void themMotHangHang (HangHoa product){
       try {
           String sql = "insert into hanghoa (MAHANGHOA, TENHANGHOA, GIABAN, GIAVON, TONKHO, NHOMHANGHOA, DINHMUCTONITNHAT, DINHMUCTONNHIEUNHAT) values (?, ?, ?, ?, ?, ?, ?, ?)";
           PreparedStatement preStatement = connection.prepareStatement(sql);
           preStatement.setString(1, product.getMaHangHoa());
           preStatement.setString(2, product.getTenHangHoa());
           preStatement.setInt(3, product.getGiaBan());
           preStatement.setInt(4, product.getGiaVon());
           preStatement.setInt(5, product.getTonKho());
           preStatement.setString(6, product.getNhomHangHoa());
           preStatement.setInt(7,product.getDinhMucTonItNhat());
           preStatement.setInt(8, product.getDinhMucTonNhieuNhat());
          
           boolean checkSuccess = preStatement.execute();
           
           if(checkSuccess)
                System.out.print("\n thêm dữ liệu thành công");
       } catch (SQLException ex) {
          System.out.print("\n thêm dữ liệu không thành công");
       }
   }
}
