/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import entities.NhaCungCap;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author phamh
 */
public class BangNhaCungCap extends TruyVanDuLieu{

    ////////////////////////////////////////////////////////////////////////////
   public void themNhaCungCap (NhaCungCap nhaCungCap){
       try {
           String sql = "insert into nhacungcap (MANHACUNGCAP, MACONGNONHACUNGCAP, TENNHACUNGCAP, NHOMNHACUNGCAP, DIACHI, EMAIL, TONGMUA, GHICHU) values (?, ?, ?, ?, ?, ?, ?, ?)";
           PreparedStatement preStatement = connection.prepareStatement(sql);
           preStatement.setString(1, nhaCungCap.mMaNhaCungCap);
           preStatement.setString(2, nhaCungCap.mMaNhaCungCap);
           preStatement.setString(3, nhaCungCap.mMaCongNoNhaCungCap);
           preStatement.setString(4, nhaCungCap.mNhomNhaCungCap);
           preStatement.setString(5, nhaCungCap.mDiaChi);
           preStatement.setString(6, nhaCungCap.mEmail);
           preStatement.setInt(7,nhaCungCap.mTongMua);
           preStatement.setString(8, nhaCungCap.mGhiChu);
          
           boolean checkSuccess = preStatement.execute();
           
           if(checkSuccess)
                System.out.print("\n thêm dữ liệu thành công");
       } catch (SQLException ex) {
          System.out.print("\n thêm dữ liệu không thành công");
       }
   }
}
