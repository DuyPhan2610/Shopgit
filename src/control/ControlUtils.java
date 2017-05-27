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
    
    // Tạo mã sản phẩm từ mã sản phẩm cuối cùng trong list
    public static String taoMaHangHoa(String str){
        String[] arStr = str.split("\\P");
        String numStr = arStr[1];
        int code = Integer.parseInt(numStr);
        return createCode(code);
    }
    
    // tạo mã lớn hơn mã cuối cùng trong csdl
    public static String createCode(int code){
        String codeStr = "SP" + String.format("%06d", ++code);
        return codeStr;
    
    }
}
