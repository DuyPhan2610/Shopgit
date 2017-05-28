/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import entities.ChiTietCongNoNhaCungCap;
import entities.CongNoNhaCungCap;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author phamh
 */
public class BangCongNoNhaCungCap extends TruyVanDuLieu{
    public final static String MA_CONG_NO_NHA_CUNG_CAP = "MACONGNONHACUNGCAP";
    public final static String TONG_NO = "TONGNO";
    public final static String NO_CAN_TRA = "NOCANTRA";
    public final static String GHI_CHU = "GHICHU";
    
    //...

    public void themCongNoNhaCungCap (CongNoNhaCungCap chiTietNhaCungcap){
        try {
            String sql = "insert into congnonhacungcap (MACONGNONHACUNGCAP, TONGNO, NOCANTRA, GHICHU) values (?, ?, ?, ? )";
            PreparedStatement preStatement = connection.prepareStatement(sql);
            preStatement.setString(1, chiTietNhaCungcap.mMaCongNoNhaCungCap);
            preStatement.setInt(2, chiTietNhaCungcap.mTongNo);
            preStatement.setInt(3, chiTietNhaCungcap.mNoCanTra);
            preStatement.setString(1, chiTietNhaCungcap.mGhiChu);
            
            boolean checkSuccess = preStatement.execute();
            
            if (checkSuccess)
                System.out.print("\n Thêm dữ liệu thành công");
        }catch (SQLException ex) {
          System.out.print("\n thêm dữ liệu không thành công");
       }
    }
    
    //Lấy tất cả các chi tiết công nợ nhà cung cấp trong csdl
     public ArrayList<CongNoNhaCungCap> layTatCaConNoNhaCungCapTrongCSDL() {
         ArrayList<CongNoNhaCungCap> arlCongNoNhaCungCap = new ArrayList<>();
              // mở kết nối csdl

            // thực hiện câu truy vấn đưa kết quả vào result set
         ResultSet rs = this.selectData(CauTruyVan.selectedStatement("congnonhacungcap"));
        try {
            while (rs.next()){
                arlCongNoNhaCungCap.add(new CongNoNhaCungCap(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BangChiTietPhieuNhapHang.class.getName()).log(Level.SEVERE, null, ex);
        }
        return  arlCongNoNhaCungCap;
     }
}
