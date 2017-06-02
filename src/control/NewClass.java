/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import DatabaseConnection.ConnectionUtils;
import database.BangHangHoa;
import database.BangPhieuNhapHang;
import entities.HangHoa;
import entities.PhieuNhapHang;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author phamh
 */
public class NewClass {
    public static void main(String[] args){
        System.out.println(layHangHoaTuMaHangHoa("SP000008").mMaHangHoa);
            
    }
    
    //lấy hàng hóa từ mã hàng hóa
    public static HangHoa layHangHoaTuMaHangHoa(String maHangHoa){
        String sql = "select * from hanghoa where MAHANGHOA = ?";

        try {
            PreparedStatement preStatement = new ConnectionUtils().getMySQLConnection().prepareStatement(sql);
            preStatement.setString(1, maHangHoa);
            ResultSet rs = preStatement.executeQuery();
            
            if((rs != null)){
                    rs.first();
                    //System.out.println(rs.getString("MAHANGHOA"));
                    HangHoa hangHoa = new HangHoa(rs);
                    rs.close();
                    return hangHoa;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BangHangHoa.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
        }

       return null;
    }
    
     // Tạo mã mã hàng hóa từ mã sản phẩm cuối cùng trong list
    public static String taoMaHangHoa(String str){
        String[] arStr = str.split("N");
        String numStr = arStr[1];
        int code = Integer.parseInt(numStr);
        return createCode(code);
    }
    
    // tạo mã hàng hóa lớn hơn mã cuối cùng trong csdl
    public static String createCode(int code){
        String codeStr = "MCN" + String.format("%05d", ++code);
        return codeStr;
    }
}
