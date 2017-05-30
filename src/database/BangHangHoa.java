/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import control.ControlUtils;
import control.NhapHangHoa;


import entities.HangHoa;
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
public class BangHangHoa extends TruyVanDuLieu{


    public final static String HANG_HOA_MA_HANG_HOA = "MAHANGHOA";
    public final static String HANG_HOA_TEN_HANG_HOA = "TENHANGHOA";
    public final static String HANG_HOA_GIA_BAN = "GIABAN";
    public final static String HANG_HOA_GIA_VON = "GIAVON";
    public final static String HANG_HOA_TON_KHO = "TONKHO";
    public final static String HANG_HOA_NHOM_HANG_HOA = "NHOMHANGHOA";
    public final static String HANG_HOA_TON_IT_NHAT = "DINHMUCTONITNHAT";
    public final static String HANG_HOA_TON_NHIEU_NHAT = "DINHMUCTONNHIEUNHAT";

    
    
    // Thêm danh sách các hàng hóa vào table hàng hóa
    public void themDuLieuVaoHangHoa(ArrayList<HangHoa> arlHangHoa){
    
        for(int i = 0; i < arlHangHoa.size(); i ++){
            themMotHangHoa(arlHangHoa.get(i));
        }
    }
   // them 1 record hang hoa
   ////////////////////////////////////////////////////////////////////////////
   public void themMotHangHoa (HangHoa product){
       try {
           String sql = "insert into hanghoa (MAHANGHOA, TENHANGHOA, GIABAN, GIAVON, TONKHO, NHOMHANGHOA, DINHMUCTONITNHAT, DINHMUCTONNHIEUNHAT) values (?, ?, ?, ?, ?, ?, ?, ?)";
           PreparedStatement preStatement = connection.prepareStatement(sql);
           preStatement.setString(1, product.getMaHangHoa());
           preStatement.setString(2, product.getTenHangHoa());
           preStatement.setInt(3, product.getGiaBan());
           preStatement.setInt(4, product.getGiaVon());
           preStatement.setInt(5, product.getTonKho());
           preStatement.setString(6, product.getNhomHangHoa());
           preStatement.setInt(7,product.getDinhMucTonItNhat());
           preStatement.setInt(8, product.getDinhMucTonNhieuNhat());
          
           boolean checkSuccess = preStatement.execute();
           
           if(checkSuccess)
                System.out.print("\n thêm dữ liệu thành công");
       } catch (SQLException ex) {
          System.out.print("\n thêm dữ liệu không thành công");
       }
   }
   
   
   
   //Lấy tất cả các hàng hóa trong csdl
    public ArrayList<HangHoa> layTatCaHangHoaTrongCSDL(){
        
        ArrayList<HangHoa> arlHangHoa = new ArrayList<>();
        try {
            // mở kết nối csdl

            // thực hiện câu truy vấn đưa kết quả vào result set
            ResultSet rs = this.selectData(CauTruyVan.selectedStatement("hanghoa"));
            while(rs.next()){
                arlHangHoa.add(new HangHoa(rs));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(NhapHangHoa.class.getName()).log(Level.SEVERE, null, ex);
        }

        return arlHangHoa;
    }
    
    
    // Hàm tự động tạo mã hàng hóa từ hàng hóa cuối cùng
    public String taoMaHangHoa(){
        ArrayList<HangHoa> arlHangHoa = this.layTatCaHangHoaTrongCSDL();
        
        if(arlHangHoa.size() > 0){
        //Lấy hàng hóa cuối cùng trong csdl
            HangHoa hangHoa = arlHangHoa.get(arlHangHoa.size() - 1);   
            return ControlUtils.taoMaHangHoa(hangHoa.mMaHangHoa);
        }
        else{
            return ControlUtils.taoMaHangHoa("SP0000000");
        }
    }
    
    //Lấy danh sách các nhóm hàng hóa có trong csdl
    public ArrayList<String> layTatCaNhomHangHoa(){
        ArrayList<HangHoa> arlHangHoa = this.layTatCaHangHoaTrongCSDL();
        ArrayList<String> arlNhomHangHoa = new ArrayList<>();
        for(int i = 0; i < arlHangHoa.size(); i++){
            arlNhomHangHoa.add(arlHangHoa.get(i).mNhomHangHoa);
        }
        return arlNhomHangHoa;
    }
}
