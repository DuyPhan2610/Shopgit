/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;
import control.ControlUtils;
import entities.HoaDonBanHang;
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
public class BangHoaDonBanHang extends TruyVanDuLieu{
    //
    public final static String MA_HOA_DON_BAN_HANG ="MAHOADONBANHANG";
    public final static String MA_NHAN_VIEN ="MANHANVIEN";
    public final static String MA_KHACH_HANG ="MAKHACHHANG";
    public final static String GIA_GIAM ="GIAGIAM";
    public final static String KHACH_DA_TRA ="KHACHDATRA";
    public final static String TONG_TIEN ="TONGTIEN";
    public final static String GHI_CHU ="GHICHU";
    public final static String THOI_GIAN ="THOIGIAN";

    public BangHoaDonBanHang(Connection connection) {
        super(connection);
    }
    
    public void themHoaDonBanHang (HoaDonBanHang hoaDonBanHang){
        try{
        String sql ="insert into hoadonbanhang (MAHOADONBANHANG, MANHANVIEN, MAKHACHHANG, GIAGIAM, KHACHDATRA, TONGTIEN, GHICHU, THOIGIAN) values (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preStatement = connection.prepareStatement(sql);
        preStatement.setString(1, hoaDonBanHang.mMaHoaDonBanHang);
        preStatement.setString(2, hoaDonBanHang.mMaNhanVien);
        preStatement.setString(3, hoaDonBanHang.mMaKhachHang);
        preStatement.setInt(4, hoaDonBanHang.mGiaGiam);
        preStatement.setInt(5, hoaDonBanHang.mKhachDaTra);
        preStatement.setInt(6, hoaDonBanHang.mTongTien);
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
     
     // Hàm tự động tạo mã hóa đơn bán hàng từ hóa đơn bán hàng cuối cùng
    public String taoMaHoaDonBanHang(){
        ArrayList<HoaDonBanHang> arlHoaDonBanHang = this.layTatCaHoaDonBanHangTrongCSDL();
        
        if(arlHoaDonBanHang.size() > 0){
        //Lấy HÓA ĐƠN BÁN HÀNG cuối cùng trong csdl
            HoaDonBanHang hoaDonBanHang = arlHoaDonBanHang.get(arlHoaDonBanHang.size() - 1);   
            return ControlUtils.taoMaHoaDonBanHang(hoaDonBanHang.mMaHoaDonBanHang);
        }
        else{
            return ControlUtils.taoMaHoaDonBanHang("HD000000");
        }
    }
}
