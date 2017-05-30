/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import control.ControlUtils;
import entities.PhieuTraHang;
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
public class BangPhieuTraHang extends TruyVanDuLieu {
    //
    public final static String MA_PHIEU_TRA_HANG = "MAPHIEUTRAHANG";
    public final static String MA_KHACH_HANG = "MAKHACHHANG";
    public final static String TONG_GIA = "TONGGIA";
    public final static String TONG_GIA_NHAP_LAI = "TONGGIANHAPLAI";
    public final static String TIEN_HOAN_LAI = "TIENHOANLAI";
    public final static String THOI_GIAN = "THOIGIAN";
    public final static String GHI_CHU = "GHICHU";
    
    public void themPhieuTraHang(PhieuTraHang phieuTraHang){
        try {
            String sql = "insert into phieutrahang (MAPHIEUTRAHANG, MAKHACHHANG, TONGGIA, TONGGIANHAPLAI, TIENHOANLAI, THOIGIAN, GHICHU) values (?, ?, ?, ?, ?, ?, ?))";
            PreparedStatement preStatement = connection.prepareStatement(sql);
            preStatement.setString(1,phieuTraHang.mMaPhieuTraHang);
            preStatement.setString(2, phieuTraHang.mMaKhachHang);
            preStatement.setInt(3, phieuTraHang.mTongGia);
            preStatement.setInt(4, phieuTraHang.mTongGiaNhapLai);
            preStatement.setInt(5, phieuTraHang.mTienHoanLai);
            preStatement.setInt(6, phieuTraHang.mThoiGian);
            preStatement.setString(7, phieuTraHang.mGhiChu);
            
            boolean checkSuccess = preStatement.execute();
            if (checkSuccess)
                System.out.print("\n Thêm dữ liệu thành công");
        } catch (SQLException ex) {
                System.out.print("\n Thêm dữ liệu không thành công");
        }
    }
    
    //Lấy tất cả các phiếu trả hàng trong CSDL
    public ArrayList<PhieuTraHang> layTatCaPhieuTraHangTrongCSDL(){
        ArrayList<PhieuTraHang> arlPhieuTraHang = new ArrayList<>();
        
        //thực hiện câu truy vấn để đưa kết quả vào result set
        ResultSet rs = this.selectData(CauTruyVan.selectedStatement("phieutrahang"));
        try {
            while (rs.next()){
                arlPhieuTraHang.add(new PhieuTraHang(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BangPhieuTraHang.class.getName()).log(Level.SEVERE, null, ex);
        }
            return arlPhieuTraHang;
    }
    
    // Hàm tự động tạo mã phiếu trả hàng từ mã phiếu trả hàng cuối cùng
    public String taoMaPhieuTraHang(){
        ArrayList<PhieuTraHang> arlPhieuTraHang = this.layTatCaPhieuTraHangTrongCSDL();
        
        if(arlPhieuTraHang.size() > 0){
        //Lấy phiếu trả hàng cuối cùng trong csdl
            PhieuTraHang phieuTraHang = arlPhieuTraHang.get(arlPhieuTraHang.size() - 1);   
            return ControlUtils.taoMaPhieuTraHang(phieuTraHang.mMaPhieuTraHang);
        }
        else{
            return ControlUtils.taoMaPhieuTraHang("PTH00000");
        }
    }
}
