/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import control.ControlUtils;
import entities.HangHoa;
import entities.PhieuNhapHang;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author phamh
 */
public class BangPhieuNhapHang extends TruyVanDuLieu{
    
    public final static String MA_PHIEU_NHAP = "MAPHIEUNHAP";
    public final static String MA_NHA_CUNG_CAP = "phieunhaphang.MANHACUNGCAP";
    public final static String TONG_TIEN = "TONGTIEN";
    public final static String GIA_GIAM = "GIAGIAM";
    public final static String TIEN_DA_TRA = "TIENDATRA";
    public final static String THOI_GIAN = "THOIGIAN";
    public final static String GHI_CHU = "GHICHU";

    public BangPhieuNhapHang(Connection connection) {
        super(connection);
    }
    
        // them 1 phiếu nhập vào csdl
   ////////////////////////////////////////////////////////////////////////////
   public void themPhieuNhapHang (PhieuNhapHang phieuNhapHang){
           String sql = "insert into phieunhaphang (MAPHIEUNHAP, MANHACUNGCAP, TONGTIEN, GIAGIAM, TIENDATRA, THOIGIAN, GHICHU) values (?, ?, ?, ?, ?, ?, ?)";
           PreparedStatement preStatement;
        try {
           preStatement = connection.prepareStatement(sql);
           preStatement.setString(1, phieuNhapHang.mMaPhieuNhap);
           preStatement.setString(2, phieuNhapHang.mMaNhaCungCap);
           preStatement.setInt(3, phieuNhapHang.mTongTien);
           preStatement.setInt(4, phieuNhapHang.mGiaGiam);
           preStatement.setInt(5, phieuNhapHang.mTienDaTra);
           preStatement.setString(6,phieuNhapHang.mThoiGian);
           preStatement.setString(7, phieuNhapHang.mGhiChu);
          
           boolean checkSuccess = preStatement.execute();
           
           if(checkSuccess){
               JOptionPane.showMessageDialog(null,
                "Thêm phiếu nhập thành công!");
                System.out.print("\n thêm phiếu nhập hàng thành công");
           }
           
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,
                "Thêm phiếu nhập không thàng công!",
                "Lỗi",
                JOptionPane.ERROR_MESSAGE);
            System.out.print("\n thêm phiếu nhập hàng không thành công");
            Logger.getLogger(BangPhieuNhapHang.class.getName()).log(Level.SEVERE, null, ex);
        }
           
   }
   
   
   //Hàm lấy tất cả phiếu nhập trong csdl
    public ArrayList<PhieuNhapHang> layTatCaPhieuNhapHangTrongCSDL(){
        ArrayList<PhieuNhapHang> arlPhieuNhapHang = new ArrayList<>();
        try {
            // thực hiện câu truy vấn đưa kết quả vào result set
            ResultSet rs = this.selectData(CauTruyVan.selectedStatement("phieunhaphang"));
            while(rs.next()){
                arlPhieuNhapHang.add(new PhieuNhapHang(rs));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(BangPhieuNhapHang.class.getName()).log(Level.SEVERE, null, ex);
        }

        return arlPhieuNhapHang;
    }
    
    
    // Hàm tự động tạo mã phiếu nhập
    public String taoMaPhieuNhap(){
        ArrayList<PhieuNhapHang> arlPhieuNhap = this.layTatCaPhieuNhapHangTrongCSDL();
        
        if(arlPhieuNhap.size() > 0){
        //Lấy hàng hóa cuối cùng trong csdl
            PhieuNhapHang phieuNhapHang = arlPhieuNhap.get(arlPhieuNhap.size() - 1);   
            return ControlUtils.taoMaPhieuNhap(phieuNhapHang.mMaPhieuNhap);
        }
        else{
            return ControlUtils.taoMaPhieuNhap("PN000000");
        }
    }
    
    // lấy phiếu nhập từ mã phiếu nhập
    public PhieuNhapHang layPhieuNhapTuMaPhieuNhap(String maPhieuNhap){
        String sql = "select * from phieunhaphang where MAPHIEUNHAP = ?";

        try {
            PreparedStatement preStatement = connection.prepareStatement(sql);
            preStatement.setString(1, maPhieuNhap);
            ResultSet rs = preStatement.executeQuery();
            
            if((rs != null)){
                    rs.first();
                    //System.out.println(rs.getString("MAHANGHOA"));
                    PhieuNhapHang pnh = new PhieuNhapHang(rs);
                    rs.close();
                    return pnh;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BangHangHoa.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
    
    
}
