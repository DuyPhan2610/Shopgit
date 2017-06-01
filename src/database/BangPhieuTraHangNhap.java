/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import control.ControlUtils;
import entities.PhieuTraHangNhap;
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
public class BangPhieuTraHangNhap extends TruyVanDuLieu{
    //
    public final static String MA_PHIEU_TRA_HANG_NHAP = "MAPHIEUTRAHANGNHAP";
    public final static String MA_NHA_CUNG_CAP = "MANHACUNGCAP";
    public final static String TIEN_NHA_CUNG_CAP_DA_TRA = "TIENNHACUNGCAPDATRA";
    public final static String THOI_GIAN = "THOIGIAN";
    public final static String TRANG_THAI = "TRANGTHAI";
    public final static String GHI_CHU = "GHICHU";

    public BangPhieuTraHangNhap(Connection connection) {
        super(connection);
    }
    
    public void themPhieuTraHangNhap (PhieuTraHangNhap phieuTraHangNhap){
        
        try {
            String sql = "insert into phieutrahangnhap (MAPHIEUTRAHANGNHAP, MANHACUNGCAP, TIENNHACUNGCAPDATRA, THOIGIAN, TRANGTHAI, GHICHU) values (?, ?, ?, ?, ?, ?)";
            PreparedStatement preStatement = connection.prepareStatement(sql);
            preStatement.setString(1, phieuTraHangNhap.mMaPhieuTraHangNhap);
            preStatement.setString(2, phieuTraHangNhap.mMaNhaCungCap);
            preStatement.setInt(3, phieuTraHangNhap.mTienNhaCungCapDaTra);
            preStatement.setString(4,phieuTraHangNhap.mThoiGian);
            preStatement.setString(5, phieuTraHangNhap.mTrangThai);
            preStatement.setString(6, phieuTraHangNhap.mGhiChu);
            
            boolean checkSuccess = preStatement.execute();
            if (checkSuccess)
                System.out.print("\n Thêm dữ liệu thành công");
        } catch (SQLException ex) {
                System.out.print("\n Thêm dữ liệu không thành công");
        }
    }
    
    //lấy tất cả phiếu trả hàng nhập trong csdl
    public ArrayList<PhieuTraHangNhap> layTatCaPhieuTraHangNhapTrongCSDL(){
           ArrayList<PhieuTraHangNhap> arlPhieuTraHangNhap = new ArrayList<>();
           
           //thực hiện câu truy vấn để đưa kết quả vào result set
           ResultSet rs = this.selectData(CauTruyVan.selectedStatement("phieutrahangnhap"));
        try {
            while (rs.next()){
                arlPhieuTraHangNhap.add( new PhieuTraHangNhap(rs));
                
            }
                } catch (SQLException ex) {
            Logger.getLogger(BangPhieuTraHangNhap.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arlPhieuTraHangNhap;
    }
    
    // Hàm tự động tạo mã phiếu trả hàng nhập từ mã phiếu trả hàng nhập cuối cùng
    public String taoMaPhieuTraHangNhap(){
        ArrayList<PhieuTraHangNhap> arlPhieuTraHangNhap = this.layTatCaPhieuTraHangNhapTrongCSDL();
        
        if(arlPhieuTraHangNhap.size() > 0){
        //Lấy phiếu trả hàng nhập cuối cùng trong csdl
            PhieuTraHangNhap phieuTraHangNhap = arlPhieuTraHangNhap.get(arlPhieuTraHangNhap.size() - 1);   
            return ControlUtils.taoMaPhieuTraHangNhap(phieuTraHangNhap.mMaPhieuTraHangNhap);
        }
        else{
            return ControlUtils.taoMaPhieuTraHangNhap("PTHN0000");
        }
    }
    
    public static void main(String[] args){
        
    }
}
