/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author phamh
 */
public class ControlUtils {
    
    //Hàm lấy thời gian hiện tại của hệ thông
    public static String layThoiGian(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("YYYY-MM-DD HH:MM:SS");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }
    
    
    // Tạo mã mã hàng hóa từ mã sản phẩm cuối cùng trong list
    public static String taoMaHangHoa(String str){
        String[] arStr = str.split("P");
        String numStr = arStr[1];
        int code = Integer.parseInt(numStr);
        return createCode(code);
    }
    
    // tạo mã hàng hóa lớn hơn mã cuối cùng trong csdl
    public static String createCode(int code){
        String codeStr = "SP" + String.format("%06d", ++code);
        return codeStr;
    }
    
    
    // Tạo mã mã hàng hóa từ mã sản phẩm cuối cùng trong list
    public static String taoMaPhieuNhap(String str){
        String[] arStr = str.split("N");
        String numStr = arStr[1];
        int code = Integer.parseInt(numStr);
        return taoMaPhieuNhap(code);
    }
    
    // tạo mã hàng hóa lớn hơn mã cuối cùng trong csdl
    public static String taoMaPhieuNhap(int code){
        String codeStr = "PN" + String.format("%06d", ++code);
        return codeStr;
    }
    
    
    // Tạo mã mã hàng hóa từ mã sản phẩm cuối cùng trong list
    public static String taoMaNhaCungCap(String str){
        String[] arStr = str.split("C");
        String numStr = arStr[2];
        int code = Integer.parseInt(numStr);
        return taoMaNhaCungCap(code);
    }
    
    // tạo mã hàng hóa lớn hơn mã cuối cùng trong csdl
    public static String taoMaNhaCungCap(int code){
        String codeStr = "NCC" + String.format("%05d", ++code);
        return codeStr;
    }
    
    // Tạo mã mã hàng hóa từ mã sản phẩm cuối cùng trong list
    public static String taoMaCongNoNhaCungCap(String str){
        String[] arStr = str.split("N");
        String numStr = arStr[1];
        int code = Integer.parseInt(numStr);
        return taoMaCongNoNhaCungCap(code);
    }
    
    // tạo mã hàng hóa lớn hơn mã cuối cùng trong csdl
    public static String taoMaCongNoNhaCungCap(int code){
        String codeStr = "MCN" + String.format("%04d", ++code);
        return codeStr;
    }
}
