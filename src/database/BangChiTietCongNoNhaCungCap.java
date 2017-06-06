/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import entities.ChiTietCongNoNhaCungCap;
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
public class BangChiTietCongNoNhaCungCap extends TruyVanDuLieu{
    
    public final static String MA_CONG_NO_NHA_CUNG_CAP ="MACONGNONHACUNGCAP";
    public final static String MA_PHIEU_NHAP ="MAPHIEUNHAP";
    public final static String TONG_NO ="TONGNO";
    //...

    public BangChiTietCongNoNhaCungCap(Connection connection) {
        super(connection);
    }

    public void themChiTietCongNoNhaCungCap ( ChiTietCongNoNhaCungCap chiTietCongNoNhaCungcap){
        try {
            String sql = "insert into chitietcongnonhacungcap (MACONGNONHACUNGCAP, MAPHIEUNHAP, TONGNO) values (?, ?, ? )";
            PreparedStatement preStatement = connection.prepareStatement(sql);
            preStatement.setString(1, chiTietCongNoNhaCungcap.mMaCongNoNhaCungCap);
            preStatement.setString(2, chiTietCongNoNhaCungcap.mMaPhieuNhap);
            preStatement.setInt(3, chiTietCongNoNhaCungcap.mTongNo);
            
            boolean checkSuccess = preStatement.execute();
            
            if (checkSuccess)
                System.out.print("\n Thêm dữ liệu thành công");
        }catch (SQLException ex) {
          System.out.print("\n thêm dữ liệu không thành công");
       }
    }
    
    //Lấy tất cả các chi tiết công nợ nhà cung cấp trong csdl
     public ArrayList<ChiTietCongNoNhaCungCap> layTatCaChiTietConNoNhaCungCapTrongCSDL() {
         ArrayList<ChiTietCongNoNhaCungCap> arlChiTietCongNoNhaCungCap = new ArrayList<>();
              // mở kết nối csdl

            // thực hiện câu truy vấn đưa kết quả vào result set
         ResultSet rs = this.selectData(CauTruyVan.selectedStatement("chitietcongnonhacungcap"));
        
        try {
            while (rs.next()){
                arlChiTietCongNoNhaCungCap.add(new ChiTietCongNoNhaCungCap(rs));
            }
                
        } catch (SQLException ex) {
            Logger.getLogger(BangChiTietCongNoNhaCungCap.class.getName()).log(Level.SEVERE, null, ex);
        }
       return arlChiTietCongNoNhaCungCap;
    }
     
     
}
