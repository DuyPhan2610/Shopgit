/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import control.ControlUtils;
import entities.CongNoCuaKhachHang;
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
public class BangCongNoCuaKhachHang extends TruyVanDuLieu{
    //lay du lieu ve phai biet ten column
    public final static String MA_CONG_NO_CUA_KHACH_HANG = "MACONGNOCUAKHACHHANG";
    public final static String TONG_NO = "TONGNO";
    public final static String NO_CAN_TRA = "NOCANTRA";
    public final static String GHI_CHU = "GHICHU";

    public BangCongNoCuaKhachHang(Connection connection) {
        super(connection);
    }
    
    public void themCongNoCuaKhachHang (CongNoCuaKhachHang congNoCuaKhachHang){
        try {
            String sql = "insert into congnocuakhachhang (MACONGNOCUAKHACHHANG, TONGNO, NOCANTRA, GHICHU) values (?, ?, ?, ?)";
            PreparedStatement preStatement = connection.prepareStatement(sql);
            preStatement.setString(1, congNoCuaKhachHang.mMaCongNoCuaKhachHang);
            preStatement.setInt(2, congNoCuaKhachHang.mTongNo);
            preStatement.setInt(3, congNoCuaKhachHang.mNoCanTra);
            preStatement.setString(4, congNoCuaKhachHang.mGhiChu);
            
            boolean checkSuccess = preStatement.execute();
            if (checkSuccess)
                System.out.print("\n Thêm dữ liệu thành công");
        } catch (SQLException ex)
        {
            System.out.print("\n Thêm dữ liệu không thành công");
        }
    }
    
    //Lấy tất cả các công nợ của khách hàng trong csdl
     public ArrayList<CongNoCuaKhachHang> layTatCaCongNoCuaKhachHangTrongCSDL() {
         ArrayList<CongNoCuaKhachHang> arlCongNoCuaKhachHang = new ArrayList<>();
              // mở kết nối csdl

            // thực hiện câu truy vấn đưa kết quả vào result set
         ResultSet rs = this.selectData(CauTruyVan.selectedStatement("congnocuakhachhang"));
        try {
            while (rs.next()){
                arlCongNoCuaKhachHang.add(new CongNoCuaKhachHang(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BangCongNoCuaKhachHang.class.getName()).log(Level.SEVERE, null, ex);
        }
        return  arlCongNoCuaKhachHang;
     }
     
     // thêm công nợ khách hàng
     public void themCongNoKhachHang(){
         CongNoCuaKhachHang congNoKH = new CongNoCuaKhachHang(this.taoMaCongNoCuaKhachHang(), 0, 0, "");
         this.themCongNoCuaKhachHang(congNoKH);
     }
     
     
      //Update lại công nợ của từ mã nhà cung cấp
     public void capNhatCongNo(String maCongNoCuaKhachHang){
         
         ResultSet rs = null;
         
         
        try {

            String sql = "SELECT MACONGNOCUAKHACHHANG, SUM(TIENNO) as TONGTIEN "
                    + "FROM chitietnocuakhachhang where MACONGNOCUAKHACHHANG = ? "
                    + "GROUP BY chitietnocuakhachhang.MACONGNOCUAKHACHHANG";
            PreparedStatement preStatement = connection.prepareStatement(sql);
            preStatement.setString(1, maCongNoCuaKhachHang);
            rs = preStatement.executeQuery();
            System.out.println("\n Tính SUM bảng chitietnocuakhachhang không thành công");
            // rs.getString(MACONGNONHACUNGCAP);
            // rs.getInt(TONGTIEN);
        } catch (SQLException ex) {
            System.out.println("\n Tính SUM bảng chitietnocuakhachhang thành công");
            Logger.getLogger(BangCongNoNhaCungCap.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        if(rs != null){
            try {
                    rs.first();
                    String maNCC = rs.getString("MACONGNOCUAKHACHHANG");
                    int tongTien = (int)rs.getInt("TONGTIEN");
                    
                    
                    String sql = "update congnocuakhachhang SET TONGNO = ?, NOCANTRA = ? where MACONGNOCUAKHACHHANG = ?";
                    PreparedStatement preStatement = connection.prepareStatement(sql);//
                                      
                    preStatement.setInt(1, tongTien);//
                    preStatement.setInt(2, tongTien);//
                    preStatement.setString(3, maNCC);

                    preStatement.execute();
                    System.out.println("\n Cập nhật Bảng công nợ khách hàng thành công" + tongTien);

                
            } catch (SQLException ex) {
                Logger.getLogger(BangCongNoNhaCungCap.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
     }
     
      // Hàm tự động tạo mã công nợ của khách hàng từ công nợ của kh cuối cùng
    public String taoMaCongNoCuaKhachHang(){
        ArrayList<CongNoCuaKhachHang> arlCongNoCuaKhachHang = this.layTatCaCongNoCuaKhachHangTrongCSDL();
        
        if(arlCongNoCuaKhachHang.size() > 0){
        //Lấy công nợ của KH cuối cùng trong csdl
            CongNoCuaKhachHang congNoCuaKhachHang = arlCongNoCuaKhachHang.get(arlCongNoCuaKhachHang.size() - 1);   
            return ControlUtils.taoMaCongNoCuaKhachHang(congNoCuaKhachHang.mMaCongNoCuaKhachHang);
        }
        else{
            return ControlUtils.taoMaCongNoCuaKhachHang("CNKH0000");
        }
    }
}
