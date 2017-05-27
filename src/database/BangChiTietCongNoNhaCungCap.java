/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import entities.ChiTietCongNoNhaCungCap;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author USER
 */
public class BangChiTietCongNoNhaCungCap extends TruyVanDuLieu{
    
    public final static String MA_CONG_NO_NHA_CUNG_CAP ="MACONGNONHACUNGCAP";
    public final static String MA_PHIEU_NHAP ="MAPHIEUNHAP";
    public final static String TONG_NO ="TONGNO";
    //...

    public void themChiTietCongNoNhaCungCap ( ChiTietCongNoNhaCungCap chiTietCongNoNhaCungcap){
        try {
            String sql = "insert into chitietcongnonhacungcap (MACONGNONHACUNGCAP, MAPHIEUNHAP, TONGNO) values (?, ?, ? )";
            PreparedStatement preStatement = connection.prepareStatement(sql);
            preStatement.setString(1, chiTietCongNoNhaCungcap.mMaCongNoNhaCungCap);
            preStatement.setString(2, chiTietCongNoNhaCungcap.mMaPhieuNhap);
            preStatement.setInt(3, chiTietCongNoNhaCungcap.mTOngNo);
            
            boolean checkSuccess = preStatement.execute();
            
            if (checkSuccess)
                System.out.print("\n Thêm dữ liệu thành công");
        }catch (SQLException ex) {
          System.out.print("\n thêm dữ liệu không thành công");
       }
    }
}
