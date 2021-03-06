/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import control.ControlUtils;
import entities.CongNoNhaCungCap;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public class BangCongNoNhaCungCap extends TruyVanDuLieu {
    //
    public final static String MA_CONG_NO_NHA_CUNG_CAP ="MACONGNONHACUNGCAP";
    public final static String TONG_NO ="TONGNO";
    public final static String NO_CAN_TRA ="NOCANTRA";
    public final static String GHI_CHU ="GHICHU";

    public BangCongNoNhaCungCap(Connection connection) {
        super(connection);
    }
    
     public void themCongNoNhaCungCap (CongNoNhaCungCap congNoNhaCungCap){
        try {
            String sql = "insert into congnonhacungcap (MACONGNONHACUNGCAP, TONGNO, NOCANTRA, GHICHU) values (?, ?, ?, ? )";
            PreparedStatement preStatement = connection.prepareStatement(sql);
            preStatement.setString(1, congNoNhaCungCap.mMaCongNoNhaCungCap);
            preStatement.setInt(2, congNoNhaCungCap.mTongNo);
            preStatement.setInt(3, congNoNhaCungCap.mNoCanTra);
            preStatement.setString(4, congNoNhaCungCap.mGhiChu);
            
            boolean checkSuccess = preStatement.execute();
            
            if (checkSuccess)
                System.out.print("\n Thêm congnonhacungcap thành công");
        }catch (SQLException ex) {
          System.out.print("\n thêm congnonhacungcap không thành công");
       }
    }
    
     
    // thêm công nợ nhà cung cấp
    public void themCongNoNhaCungCap(){
        CongNoNhaCungCap congNoNhaCungCap = new CongNoNhaCungCap(this.taoMaCongNoNhaCungCap(), " ", 0, 0);
        this.themCongNoNhaCungCap(congNoNhaCungCap);
    }
     
     //Update lại công nợ của từ mã nhà cung cấp
     public void capNhatCongNo(String maCongNoNhaCungCap){
         
         ResultSet rs = null;
         
         
        try {

            String sql = "SELECT MACONGNONHACUNGCAP, SUM(TONGNO) as TONGTIEN "
                    + "FROM chitietcongnonhacungcap where MACONGNONHACUNGCAP = ? "
                    + "GROUP BY chitietcongnonhacungcap.MACONGNONHACUNGCAP";
            PreparedStatement preStatement = connection.prepareStatement(sql);
            preStatement.setString(1, maCongNoNhaCungCap);
            rs = preStatement.executeQuery();
            System.out.println("\n Tính SUM bảng chitietcongnonhacungcap thành công");
            // rs.getString(MACONGNONHACUNGCAP);
            // rs.getInt(TONGTIEN);
        } catch (SQLException ex) {
            Logger.getLogger(BangCongNoNhaCungCap.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        if(rs != null){
            try {
                    rs.first();
                    String maNCC = rs.getString("MACONGNONHACUNGCAP");
                    int tongTien = (int)rs.getInt("TONGTIEN");
                    
                    
                    String sql = "update congnonhacungcap SET TONGNO = ?, NOCANTRA = ? where MACONGNONHACUNGCAP = ?";
                    PreparedStatement preStatement = connection.prepareStatement(sql);//
                                      
                    preStatement.setInt(1, tongTien);//
                    preStatement.setInt(2, tongTien);//
                    preStatement.setString(3, maNCC);

                    preStatement.execute();
                    System.out.println("\n Cập nhật Bảng công nợ thành công" + tongTien);

                
            } catch (SQLException ex) {
                Logger.getLogger(BangCongNoNhaCungCap.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
     }
     
     
    //Lấy tất cả các chi tiết công nợ nhà cung cấp trong csdl
     public ArrayList<CongNoNhaCungCap> layTatCaConNoNhaCungCapTrongCSDL() {
         ArrayList<CongNoNhaCungCap> arlCongNoNhaCungCap = new ArrayList<>();
         

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
     
     //Hàm tự động tạo mã công nợ nhà cung cấp
    public String taoMaCongNoNhaCungCap(){
        ArrayList<CongNoNhaCungCap> arlCongNoNhaCungCap = this.layTatCaConNoNhaCungCapTrongCSDL();
        
        if(arlCongNoNhaCungCap.size() > 0){
            //Lấy công nợ cuối cùng trong csdl
            CongNoNhaCungCap congNoNhaCungCap = arlCongNoNhaCungCap.get(arlCongNoNhaCungCap.size() - 1);   
            return ControlUtils.taoMaCongNoNhaCungCap(congNoNhaCungCap.mMaCongNoNhaCungCap);
        }
        else{
            return ControlUtils.taoMaCongNoNhaCungCap("MCN00000");
        }
    }
}
