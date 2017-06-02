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
import entities.HangHoa;
import entities.HangNhap;
import entities.HoaDonBanHang;
import entities.KhachHang;
import entities.NhanVien;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author USER
 */
public class LapHoaDonBanHang {
    
    
    public JTable mTableBangHangHoa;
    public DefaultTableModel model;
    public JComboBox<String> mComboBoxTenNhanVien;
    
    public JLabel mLabelMaNhanVien;
    public JLabel mLabelMaHoaDonBanHang;
    public JLabel mLabelTenKhachHang;
    public JLabel mLabelMaKhachHang;
    public JLabel mLabelTongTien;
    public JLabel mLabelGiaGiam;
    public JLabel mLabelConNo;
    
    public JTextField mTextFieldMaHangHoa;
    public JTextField mTextFieldDaTra;
    public JTextField mTextFieldGhiChu;
    
    
    
  
    public BangHangHoa bangHangHoa;
    public BangKhachHang bangKhachHang;
    public BangHoaDonBanHang bangHoaDonBanHang;
    public BangChiTietHoaDonBanHang bangChiTietHoaDonBanHang;
    public BangCongNoCuaKhachHang bangCongNoCuaKhachHang;
    public BangChiTietNoCuaKhachHang bangChiTietNoCuaKhachHang;
    
    public String maHangHoa;
    
    public LapHoaDonBanHang (JTextField textFieldMaHangHoa, JTable tableBangHangHoa,
                    JComboBox<String> comboBoxTenNhanVien,
                    JLabel labelMaNhanVien,JLabel labelMaHoaDonBanHang,
                    JLabel labelTenKhachHang,JLabel labelMaKhachHang,
                    JLabel labelTongTien,JLabel labelGiaGiam,
                    JTextField textFieldDaTra,JLabel labelConNo,JTextField textFieldGhiChu) {
        
        this.mComboBoxTenNhanVien = comboBoxTenNhanVien;
        this.mTableBangHangHoa = tableBangHangHoa;
        
        this.mLabelMaNhanVien = labelMaNhanVien;
        this.mLabelConNo = labelConNo;
        this.mLabelGiaGiam = labelGiaGiam;
        this.mLabelMaHoaDonBanHang = labelMaHoaDonBanHang;
        this.mLabelMaKhachHang = labelMaKhachHang;
        this.mLabelTongTien = labelTongTien;
        this.mLabelTenKhachHang = labelTenKhachHang;
        
        this.mTextFieldDaTra = textFieldDaTra;
        this.mTextFieldGhiChu = textFieldGhiChu;
        this.mTextFieldMaHangHoa = textFieldMaHangHoa;
        
        
        try {
            this.bangChiTietHoaDonBanHang = new BangChiTietHoaDonBanHang(new ConnectionUtils().getMySQLConnection());
            this.bangChiTietNoCuaKhachHang = new BangChiTietNoCuaKhachHang(new ConnectionUtils().getMySQLConnection());
            this.bangCongNoCuaKhachHang = new BangCongNoCuaKhachHang(new ConnectionUtils().getMySQLConnection());
            this.bangHangHoa = new BangHangHoa(new ConnectionUtils().getMySQLConnection());
            this.bangHoaDonBanHang = new BangHoaDonBanHang(new ConnectionUtils().getMySQLConnection());
            this.bangKhachHang = new BangKhachHang(new ConnectionUtils().getMySQLConnection());
            
        } catch (SQLException ex) {
            Logger.getLogger(LapHoaDonBanHang.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LapHoaDonBanHang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //cập nhật các textField
    public void capNhapCacTextField(){
        this.maHangHoa = this.mTextFieldMaHangHoa.getText();
    }
    
    
    
    //thêm hàng hóa tìm được vào bảng
    public void themHangHoaVaoBang(){
        HangHoa hangHoa = bangHangHoa.layHangHoaTuMaHangHoa(this.maHangHoa);
        // nếu không tìm thấy hàng hóa phù hợp thì hiện thông báo
        if(hangHoa == null){
            JOptionPane.showMessageDialog(null, "Mã hàng hóa không đúng", "Thông báo!", JOptionPane.INFORMATION_MESSAGE);
        }
        else {
            if(hangHoa.mTonKho < 1){
                JOptionPane.showMessageDialog(null, "Hàng hóa đã hết!", "Thông báo!", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
            model.addRow(new Object[]{hangHoa.mMaHangHoa, hangHoa.mTenHangHoa,
                                1,  hangHoa.mGiaBan, 0, hangHoa.mGiaBan});
            }
        }
    }
    
    
    //cập nhật lại bảng khi thay đổi số lượng hàng hóa
    public void capNhatBangKhiSuaSoLuong(){
        if(this.model.getRowCount() > 0){
            for(int i = 0; i < this.model.getRowCount(); i ++){
                int thanhTien = (Integer)this.model.getValueAt(i, 2) * (Integer)this.model.getValueAt(i, 3)
                                        - (Integer)this.model.getValueAt(i, 4) * (Integer)this.model.getValueAt(i, 2);
                this.model.setValueAt(thanhTien, i, 5);
            }
        }
    }
    
    
    
  
    
    //Lấy danh sách hàng hóa trong bảng
    public ArrayList<HangHoa> layDSHangHoaTrongBang(){
        
        return null;
    }
    
    //Thêm danh sách khách hàng vào comboBox
//    public void themKhachHangVaoComboBox(){
//        ArrayList<KhachHang> arlKhachHang = bangKhachHang.layTatCaKhachHangTrongCSDL();
//        if(arlKhachHang.size() >0){
//            for (int i=0; i < arlKhachHang.size(); i++){
//                this.mComboBoxTenNhanVien.addItem(arlKhachHang.get(i).mTenKhachHang); //lấy tên khách hàng thứ i và show lên màn hình
//            }
//            comboBox.setSelectedIndex(0);
//        }
//    }
    
    //Thêm từng dữ liệu vào table
    public void themMotHangBanVaoBang(HoaDonBanHang hoaDonBanHang){
        model.addRow( new Object[]{hoaDonBanHang.mMaHoaDonBanHang, hoaDonBanHang.mMaKhachHang, hoaDonBanHang.mConNo
                ,hoaDonBanHang.mGiaGiam, hoaDonBanHang.mGhiChu, hoaDonBanHang.mKhachDaTra, hoaDonBanHang.mMaNhanVien, hoaDonBanHang.mThoiGian});
    }
    
    // lấy danh sách các hàng bán trong table
    
    
}
