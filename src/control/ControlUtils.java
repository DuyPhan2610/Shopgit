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
        java.util.Date dt = new java.util.Date();

    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String currentTime = sdf.format(dt);
        return currentTime;
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
    
    // Tạo mã công nợ từ mã công nợ cuối cùng trong list
    public static String taoMaCongNoNhaCungCap(String str){
        String[] arStr = str.split("N");
        String numStr = arStr[1];
        int code = Integer.parseInt(numStr);
        return taoMaCongNoNhaCungCap(code);
    }
    
    // tạo mã hàng hóa lớn hơn mã cuối cùng trong csdl
    public static String taoMaCongNoNhaCungCap(int code){
        String codeStr = "MCN" + String.format("%05d", ++code);
        return codeStr;
    }
    
    //tạo mã công nợ của khách hàng từ mã CN khách hàng cuối cùng trong list
    public static String taoMaCongNoCuaKhachHang(String str) {
        String[] arStr =str.split("H");
        String numStr =arStr[1];
        int code =Integer.parseInt(numStr);
        return taoMaCongNoCuaKhachHang(code);
    }
    
    //tạo mã công nợ của khách hàng lớn hơn mã cuối cùng trong csdl
    public static String taoMaCongNoCuaKhachHang(int code){
        String codeStr = "CNKH" +String.format("%04d", ++code);
        return codeStr;
    }
    
    //tạo mã hóa đơn bán hàng từ mã HĐBH cuói cùng trong list
    public static String taoMaHoaDonBanHang(String str) {
        String[] arStr =str.split("D");
        String numStr =arStr[1];
        int code =Integer.parseInt(numStr);
        return taoMaHoaDonBanHang(code);
    }
    
    //tạo mã hóa đơn bán hàng lớn hơn mã cuối cùng trong csdl
    public static String taoMaHoaDonBanHang(int code){
        String codeStr ="HD" +String.format("%06d", ++code);
        return codeStr;
    }
    
    //tạo mã khách hàng từ mã khách hàng cuói cùng trong list
    public static String taoMaKhachHang(String str){
        String[] arStr =str.split("H");
        String numStr =arStr[1];
        int code =Integer.parseInt(numStr);
        return taoMaKhachHang(code);
    }
    
    //tạo mã khách hàng lớn hơn mã cuối cùng trong CSDL
    public static String taoMaKhachHang(int code){
        String codeStr = "KH" +String.format("%06d", ++code);
        return codeStr;
    }
    
    //tạo mã nhân viên từ mã nhân viên cuối cùng trong list
    public static String taoMaNhanVien(String str){
        String[] arStr =str.split("V");
        String numStr =arStr[1];
        int code =Integer.parseInt(numStr);
        return taoMaNhanVien(code);
    }
    
    //tạo mã nhân viên lớn hơn mã cuói cùng trong csdl
    public static String taoMaNhanVien(int code){
        String codeStr ="NV" +String.format("%06d", ++code);
        return codeStr;
    }
    
    //tạo mã phiếu trả hàng từ mã phiếu trả hàng cuối cùng trong list
    public static String taoMaPhieuTraHang (String str){
        String[] arStr =str.split("H");
        String numStr =arStr[1];
        int code =Integer.parseInt(numStr);
        return taoMaPhieuTraHang(code);
    }
    
    //tạo mã phiếu trả hàng lớn hơn mã cuối cùng trong csdl
    public static String taoMaPhieuTraHang (int code){
        String codeStr ="PTH" +String.format("%05d", ++code);
        return codeStr;
    }
    
    //tạo mã phiếu trả hàng nhập từ mã phiếu trả hàng nhập cuối cùng trong list
    public static String taoMaPhieuTraHangNhap (String str){
        String[] arStr =str.split("N");
        String numStr =arStr[1];
        int code =Integer.parseInt(numStr);
        return taoMaPhieuTraHangNhap(code);
    }
    
    //tạo mã phiếu trả hàng nhập lớn hơn mã cuối cùng trong csdl
    public static String taoMaPhieuTraHangNhap (int code){
        String codeStr ="PTHN" +String.format("%04d", ++code);
        return codeStr;
    }
    
    //tạo mã phiếu trả hàng nhập từ mã phiếu trả hàng nhập cuối cùng trong list
    public static String taoMaLoaiKhachHang (String str){
        String[] arStr =str.split("H");
        String numStr =arStr[1];
        int code =Integer.parseInt(numStr);
        return taoMaLoaiKhachHang(code);
    }
    
    //tạo mã phiếu trả hàng nhập lớn hơn mã cuối cùng trong csdl
    public static String taoMaLoaiKhachHang (int code){
        String codeStr ="LKH" +String.format("%05d", ++code);
        return codeStr;
    }
}
