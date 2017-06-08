/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import control.ControlUtils;
import control.NhapHangHoa;
import entities.NhaCungCap;
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
public class BangNhaCungCap extends TruyVanDuLieu{

    public final static String MA_NHA_CUNG_CAP = "MANHACUNGCAP";
    public final static String MA_CONG_NO_NHA_CUNG_CAP = "MACONGNONHACUNGCAP";
    public final static String TEN_CUA_NHA_CUNG_CAP = "TENNHACUNGCAP";
    public final static String NHOM_NHA_CUNG_CAP = "NHOMNHACUNGCAP";
    public final static String DIA_CHI = "DIACHI";
    public final static String EMAIL = "EMAIL";
    public final static String GHI_CHU = "GHICHU";

    public BangNhaCungCap(Connection connection) {
        super(connection);
    }
    
    
    
    ////////////////////////////////////////////////////////////////////////////
    // Thêm một nhà cung cấp vào csdl
   public void themNhaCungCap (NhaCungCap nhaCungCap){
       try {
           String sql = "insert into nhacungcap (MANHACUNGCAP, MACONGNONHACUNGCAP, TENNHACUNGCAP, "
                   + "NHOMNHACUNGCAP, DIACHI, EMAIL, GHICHU) "
                   + "values (?, ?, ?, ?, ?, ?, ?)";
           PreparedStatement preStatement = connection.prepareStatement(sql);
           preStatement.setString(1, nhaCungCap.mMaNhaCungCap);
           preStatement.setString(2, nhaCungCap.mMaCongNoNhaCungCap);
           preStatement.setString(3, nhaCungCap.mTenNhaCungCap);
           preStatement.setString(4, nhaCungCap.mNhomNhaCungCap);
           preStatement.setString(5, nhaCungCap.mDiaChi);
           preStatement.setString(6, nhaCungCap.mEmail);
           preStatement.setString(7, nhaCungCap.mGhiChu);
          
           boolean checkSuccess = preStatement.execute();
           
           if(checkSuccess){
               JOptionPane.showMessageDialog(null,
                "Thêm nhà cung cấp thành công!");
                System.out.print("\n thêm nhà cung cấp thành công");
           }
       } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null,
                "Thêm nhà cung cấp thất bại!",
                "Lỗi",
                JOptionPane.ERROR_MESSAGE);
          System.out.print("\n thêm nhà cung cấp không thành công");
       }
   }
   
   
   // lấy Nha Cung Cấp từ tên nhà cung cấp
   public NhaCungCap layNhaCungCap(String tenNhaCungCap){
       NhaCungCap nhaCungCap = null;
       String sql = "select * from nhacungcap ncc where ncc.TENNHACUNGCAP = ?";
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
   
   
   // lấy Nha Cung Cấp từ mã nhà cung cấp
   public NhaCungCap layNhaCungCapTuMaNCC(String maNCC){
       NhaCungCap nhaCungCap = null;
       String sql = "select * from nhacungcap ncc where ncc.MANHACUNGCAP = ?";
       PreparedStatement preStatement;
       try {
           preStatement = connection.prepareStatement(sql);
           preStatement.setString(1, maNCC);
           ResultSet rs = preStatement.executeQuery();
           rs.first();
           
//           String maNCCap = rs.getString("MANHACUNGCAP");
//           String maCongNoNCC = rs.getString("MACONGNONHACUNGCAP");
           String tenNCC = rs.getString("TENNHACUNGCAP");
           
           //System.out.println("\n" + maNCCap + " " + maCongNoNCC + " " + tenNCC);
           nhaCungCap = new NhaCungCap(rs);
       } catch (SQLException ex) {
           Logger.getLogger(BangNhaCungCap.class.getName()).log(Level.SEVERE, null, ex);
       }
     return nhaCungCap;
   }
   
   
   //Lấy mã NCC từ tên NCC
   // lấy Nha Cung Cấp từ tên nhà cung cấp
   public String layMaNhaCungCap(String tenNhaCungCap){
       String maNhaCungCap = null;
       String sql = "select * from nhacungcap ncc where ncc.TENNHACUNGCAP = ?";
       PreparedStatement preStatement;
       try {
           preStatement = connection.prepareStatement(sql);
           preStatement.setString(1, tenNhaCungCap);
           ResultSet rs = preStatement.executeQuery();
           maNhaCungCap = rs.getString("MANHACUNGCAP");
       } catch (SQLException ex) {
           Logger.getLogger(BangNhaCungCap.class.getName()).log(Level.SEVERE, null, ex);
       }
     return maNhaCungCap;
   }
   
   
   //Lấy tất cả các nhà cung cấp có trong csdl
    public ArrayList<NhaCungCap> layTatCaNhaCungCapTrongCSDL(){
        
        ArrayList<NhaCungCap> arlNhaCungCap = new ArrayList<>();
            // thực hiện câu truy vấn đưa kết quả vào result set
            ResultSet rs = this.selectData(CauTruyVan.selectedStatement("nhacungcap"));
            
        try {
            int size = rs.getRow();
            while(rs.next()){
                arlNhaCungCap.add(new NhaCungCap(rs));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(BangNhaCungCap.class.getName()).log(Level.SEVERE, null, ex);
        }

        return arlNhaCungCap;
    }
   
   
   // Hàm tự động tạo mã nhà cung cấp
    public String taoMaNhaCungCap(){
        ArrayList<NhaCungCap> arlNhaCungCap = this.layTatCaNhaCungCapTrongCSDL();
        
        if(arlNhaCungCap.size() > 0){
        //Lấy hàng hóa cuối cùng trong csdl
            NhaCungCap nhaCungCap = arlNhaCungCap.get(arlNhaCungCap.size() - 1);
            String maNCC = nhaCungCap.mMaNhaCungCap;
            String ma = ControlUtils.taoMaNhaCungCap(maNCC);
            return ma;
        }
        else{
            return ControlUtils.taoMaNhaCungCap("NCC00000");
        }
    }

}
