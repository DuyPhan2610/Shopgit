/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import static control.TaoMaHangHoa.productQuantity;

/**
 *
 * @author phamh
 */
public class ControlUtils {
    
    
    
    
    
    // Tạo mã mã hàng hóa từ mã sản phẩm cuối cùng trong list
    public static String taoMaHangHoa(String str){
        String[] arStr = str.split("\\P");
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
    public static String tachMaPhieuNhap(String str){
        String[] arStr = str.split("\\N");
        String numStr = arStr[1];
        int code = Integer.parseInt(numStr);
        return createCode(code);
    }
    
    // tạo mã hàng hóa lớn hơn mã cuối cùng trong csdl
    public static String taoMaPhieuNhap(int code){
        String codeStr = "PN" + String.format("%06d", ++code);
        return codeStr;
    }
    
    
    // Tạo mã mã hàng hóa từ mã sản phẩm cuối cùng trong list
    public static String tachMaNhaCungCap(String str){
        String[] arStr = str.split("\\C");
        String numStr = arStr[1];
        int code = Integer.parseInt(numStr);
        return createCode(code);
    }
    
    // tạo mã hàng hóa lớn hơn mã cuối cùng trong csdl
    public static String taoMaNhaCungCap(int code){
        String codeStr = "NCC" + String.format("%06d", ++code);
        return codeStr;
    }
}
