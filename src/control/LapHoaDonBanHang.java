/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import database.BangChiTietHoaDonBanHang;
import database.BangChiTietNoCuaKhachHang;
import database.BangCongNoCuaKhachHang;
import database.BangHangHoa;
import database.BangHoaDonBanHang;
import database.BangKhachHang;
import DatabaseConnection.ConnectionUtils;
import entities.HoaDonBanHang;
import entities.KhachHang;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author USER
 */
public class LapHoaDonBanHang {
    
    public JTextField mTextFieldMaHangHoa;
    public JTable mTableBangHangHoa;
    public DefaultTableModel model;
    public JComboBox<String> mComboBoxTenNhanVien;
    public JLabel mLabelMaNhanVien;
    public JLabel mLabelMaHoaDonBanHang;
    public JLabel mLabelTenKhachHang;
    public JLabel mLabelMaKhachHang;
    public JLabel mLabelTongTien;
    public JLabel mLabelGiaGiam;
    public JTextField mTextFieldDaTra;
    public JLabel mLabelConNo;
    public JTextField mTextFieldGhiChu;
    
    
    
  
    public BangHangHoa bangHangHoa;
    public BangKhachHang bangKhachHang;
    public BangHoaDonBanHang bangHoaDonBanHang;
    public BangChiTietHoaDonBanHang bangChiTietHoaDonBanHang;
    public BangCongNoCuaKhachHang bangCongNoCuaKhachHang;
    public BangChiTietNoCuaKhachHang bangChiTietNoCuaKhachHang;
    
    
    public LapHoaDonBanHang (JTextField mTextFieldMaHangHoa, JTable mTableBangHangHoa,
            DefaultTableModel model, JComboBox<String> mComboBoxTenNhanVien,
     JLabel mLabelMaNhanVien,JLabel mLabelMaHoaDonBanHang,
     JLabel mLabelTenKhachHang,JLabel mLabelMaKhachHang,
     JLabel mLabelTongTien,JLabel mLabelGiaGiam,
     JTextField mTextFieldDaTra,JLabel mLabelConNo,JTextField mTextFieldGhiChu) {
        
    }
    
    //Thêm danh sách khách hàng vào comboBox
    public void themKhachHangVaoComboBox(){
        ArrayList<KhachHang> arlKhachHang = bangKhachHang.layTatCaKhachHangTrongCSDL();
        if(arlKhachHang.size() >0){
            for (int i=0; i < arlKhachHang.size(); i++){
                comboBox.addItem(arlKhachHang.get(i).mTenKhachHang); //lấy tên khách hàng thứ i và show lên màn hình
            }
            comboBox.setSelectedIndex(0);
        }
    }
    
    //Thêm từng dữ liệu vào table
    public void themMotHangBanVaoBang(HoaDonBanHang hoaDonBanHang){
        model.addRow( new Object[]{hoaDonBanHang.mMaHoaDonBanHang, hoaDonBanHang.mMaKhachHang, hoaDonBanHang.mConNo
                ,hoaDonBanHang.mGiaGiam, hoaDonBanHang.mGhiChu, hoaDonBanHang.mKhachDaTra, hoaDonBanHang.mMaNhanVien, hoaDonBanHang.mThoiGian});
    }
    
    // lấy danh sách các hàng bán trong table
    
    
}
