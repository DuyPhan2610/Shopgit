/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;
import control.NhapHangHoa;
import entities.HoaDonBanHang;
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
public class BangHoaDonBanHang extends TruyVanDuLieu{
    //
    public final static String MA_HOA_DON_BAN_HANG ="MAHOADONBANHANG";
    public final static String MA_NHAN_VIEN ="MANHANVIEN";
    public final static String MA_KHACH_HANG ="MAKHACHHANG";
    public final static String GIA_GIAM ="GIAGIAM";
    public final static String KHACH_DA_TRA ="KHACHDATRA";
    public final static String CON_NO ="CONNO";
    public final static String GHI_CHU ="GHICHU";
    public final static String THOI_GIAN ="THOIGIAN";
    
    public void themHoaDonBanHang (HoaDonBanHang hoaDonBanHang){
        try{
        String sql ="insert into hoadonbanhang (MAHOADONBANHANG, MANHANVIEN, MAKHACHHANG, GIAGIAM, KHACHDATRA, CONNO, GHICHU, THOIGIAN) values (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preStatement = connection.prepareStatement(sql);
        preStatement.setString(1, hoaDonBanHang.mMaHoaDonBanHang);
        preStatement.setString(2, hoaDonBanHang.mMaNhanVien);
        preStatement.setString(3, hoaDonBanHang.mMaKhachHang);
        preStatement.setInt(4, hoaDonBanHang.mGiaGiam);
        preStatement.setInt(5, hoaDonBanHang.mKhachDaTra);
        preStatement.setInt(6, hoaDonBanHang.mConNo);
        preStatement.setString(7, hoaDonBanHang.mGhiChu);
        preStatement.setString(8, hoaDonBanHang.mThoiGian);
        
        boolean checkSuccess = preStatement.execute();
        if (checkSuccess)
            System.out.print("\n Thêm dữ liệu thành công");
        } catch (SQLException ex)
        {
            System.out.print("\n Thêm dữ liệu không thành công");
        }
    }
    
    //Lấy tất cả các hóa đơn bán hàng trong csdl
     public ArrayList<HoaDonBanHang> layTatCaHoaDonBanHangTrongCSDL() {
         ArrayList<HoaDonBanHang> arlHoaDonBanHang = new ArrayList<>();
              // mở kết nối csdl

            // thực hiện câu truy vấn đưa kết quả vào result set
         ResultSet rs = this.selectData(CauTruyVan.selectedStatement("hoadonbanhang"));
        
        try {
            while (rs.next()){
                arlHoaDonBanHang.add(new HoaDonBanHang(rs));
            }
                
        } catch (SQLException ex) {
            Logger.getLogger(BangHoaDonBanHang.class.getName()).log(Level.SEVERE, null, ex);
        }
       return arlHoaDonBanHang;
    }
}
