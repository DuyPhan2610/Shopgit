/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import control.ControlUtils;
import control.NhapHangHoa;
import entities.CongNoNhaCungCap;
import entities.HangHoa;
import entities.NhaCungCap;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author phamh
 */
public class BangNhaCungCap extends TruyVanDuLieu{

    public final static String MA_NHA_CUNG_CAP = "MANHACUNGCAP";
    public final static String MA_CONG_NO_NHA_CUNG_CAP = "MACONGNONHACUNGCAP";
    public final static String TEN_NHA_CUNG_CAP = "MACONGNONHACUNGCAP";
    public final static String NHOM_NHA_CUNG_CAP = "NHOMNHACUNGCAP";
    public final static String DIA_CHI = "DIACHI";
    public final static String EMAIL = "EMAIL";
    public final static String TONG_MUA = "TONGMUA";
    public final static String GHI_CHU = "GHICHU";
    
    
    
    ////////////////////////////////////////////////////////////////////////////
    // Thêm một nhà cung cấp vào csdl
   public void themNhaCungCap (NhaCungCap nhaCungCap){
       try {
           String sql = "insert into nhacungcap (MANHACUNGCAP, MACONGNONHACUNGCAP, TENNHACUNGCAP, "
                   + "NHOMNHACUNGCAP, DIACHI, EMAIL, TONGMUA, GHICHU) "
                   + "values (?, ?, ?, ?, ?, ?, ?, ?)";
           PreparedStatement preStatement = connection.prepareStatement(sql);
           preStatement.setString(1, nhaCungCap.mMaNhaCungCap);
           preStatement.setString(2, nhaCungCap.mMaCongNoNhaCungCap);
           preStatement.setString(3, nhaCungCap.mTenNhaCungCap);
           preStatement.setString(4, nhaCungCap.mNhomNhaCungCap);
           preStatement.setString(5, nhaCungCap.mDiaChi);
           preStatement.setString(6, nhaCungCap.mEmail);
           preStatement.setInt(7,nhaCungCap.mTongMua);
           preStatement.setString(8, nhaCungCap.mGhiChu);
          
           boolean checkSuccess = preStatement.execute();
           
           if(checkSuccess)
                System.out.print("\n thêm dữ liệu thành công");
       } catch (SQLException ex) {
          System.out.print("\n thêm dữ liệu không thành công");
       }
   }
   
   
   // lấy Nha Cung Cấp từ tên nhà cung cấp
   public NhaCungCap layNhaCungCap(String tenNhaCungCap){
       NhaCungCap nhaCungCap = null;
       String sql = "select MANHACUNGCAP from nhacungcap ncc where ncc.TENNHACUNGCAP = ?";
       PreparedStatement preStatement;
       try {
           preStatement = connection.prepareStatement(sql);
           preStatement.setString(1, tenNhaCungCap);
           ResultSet rs = preStatement.executeQuery();
           nhaCungCap = new NhaCungCap(rs);
       } catch (SQLException ex) {
           Logger.getLogger(BangNhaCungCap.class.getName()).log(Level.SEVERE, null, ex);
       }
     return nhaCungCap;
   }
   
   //Lấy tất cả các nhà cung cấp có trong csdl
    public ArrayList<NhaCungCap> layTatCaNhaCungCapTrongCSDL(){
        
        ArrayList<NhaCungCap> arlNhaCungCap = new ArrayList<>();
        try {
            // mở kết nối csdl

            // thực hiện câu truy vấn đưa kết quả vào result set
            ResultSet rs = this.selectData(CauTruyVan.selectedStatement("nhacungcap"));
            while(rs.next()){
                arlNhaCungCap.add(new NhaCungCap(rs));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(NhapHangHoa.class.getName()).log(Level.SEVERE, null, ex);
        }

        return arlNhaCungCap;
    }
   
   
   // Hàm tự động tạo mã hàng hóa từ hàng hóa cuối cùng
    public String taoMaHangHoa(){
        ArrayList<NhaCungCap> arlNhaCungCap = this.layTatCaNhaCungCapTrongCSDL();
        
        if(arlNhaCungCap.size() > 0){
        //Lấy hàng hóa cuối cùng trong csdl
            NhaCungCap nhaCungCap = arlNhaCungCap.get(arlNhaCungCap.size() - 1);   
            return ControlUtils.taoMaHangHoa(nhaCungCap.mMaNhaCungCap);
        }
        else{
            return ControlUtils.taoMaNhaCungCap("NCC000000");
        }
    }
    
    
    
    
}
