/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import DatabaseConnection.ConnectionUtils;
import database.BangPhieuNhapHang;
import entities.PhieuNhapHang;
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
        
        
    }
    
    public void taoPhieuNhapHang(){
        BangPhieuNhapHang bangPhieuNhapHang = null;
        try {
            bangPhieuNhapHang = new BangPhieuNhapHang(new ConnectionUtils().getMySQLConnection());
        } catch (SQLException ex) {
            Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        PhieuNhapHang phieuNhapHang = new PhieuNhapHang();
        phieuNhapHang.mTienDaTra = 10000;
        phieuNhapHang.mMaPhieuNhap = "PN000004";
        phieuNhapHang.mMaNhaCungCap = "NCC00001";
        phieuNhapHang.mGhiChu = "khong co";
        phieuNhapHang.mGiaGiam = 1000;
        phieuNhapHang.mThoiGian = ControlUtils.layThoiGian();
        phieuNhapHang.mTongTien = 10000000;
          bangPhieuNhapHang.themPhieuNhapHang(phieuNhapHang);
        bangPhieuNhapHang.closeDatabase();
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
