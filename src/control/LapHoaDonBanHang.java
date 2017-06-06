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
import database.BangNhanVien;
import entities.ChiTietHoaDonBanHang;
import entities.HangHoa;
import entities.HangNhap;
import entities.HoaDonBanHang;
import entities.NhanVien;
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
    public BangNhanVien bangNhanVien;
    
    public String maHangHoa;
    public int tongTienHoaDon;
    public int giaGiam;
    public int conNo;
    public int tienDaTra;
    public String maHoaDon;
    public String maNhanVien;
    
    
    
    public LapHoaDonBanHang (JTextField jTextFieldNhapMaHangHoa, JTable tableBangHangHoa,
                    JComboBox<String> comboBoxTenNhanVien, JLabel labelMaHoaDonBanHang,
                    JLabel labelTenKhachHang,JLabel labelMaKhachHang,
                    JLabel labelTongTien,JLabel labelGiaGiam,
                    JTextField textFieldDaTra,JLabel labelConNo,JTextField textFieldGhiChu) {
        
        this.mComboBoxTenNhanVien = comboBoxTenNhanVien;
        this.mTableBangHangHoa = tableBangHangHoa;
        model = (DefaultTableModel) mTableBangHangHoa.getModel();
        
        
        this.mLabelConNo = labelConNo;
        this.mLabelGiaGiam = labelGiaGiam;
        this.mLabelMaHoaDonBanHang = labelMaHoaDonBanHang;
        this.mLabelMaKhachHang = labelMaKhachHang;
        this.mLabelTongTien = labelTongTien;
        this.mLabelTenKhachHang = labelTenKhachHang;
        
        this.mTextFieldDaTra = textFieldDaTra;
        this.mTextFieldGhiChu = textFieldGhiChu;
        this.mTextFieldMaHangHoa = jTextFieldNhapMaHangHoa;
        
        
        try {
            this.bangChiTietHoaDonBanHang = new BangChiTietHoaDonBanHang(new ConnectionUtils().getMySQLConnection());
            this.bangChiTietNoCuaKhachHang = new BangChiTietNoCuaKhachHang(new ConnectionUtils().getMySQLConnection());
            this.bangCongNoCuaKhachHang = new BangCongNoCuaKhachHang(new ConnectionUtils().getMySQLConnection());
            this.bangHangHoa = new BangHangHoa(new ConnectionUtils().getMySQLConnection());
            this.bangHoaDonBanHang = new BangHoaDonBanHang(new ConnectionUtils().getMySQLConnection());
            this.bangKhachHang = new BangKhachHang(new ConnectionUtils().getMySQLConnection());
            this.bangNhanVien = new BangNhanVien(new ConnectionUtils().getMySQLConnection());
            
        } catch (SQLException ex) {
            Logger.getLogger(LapHoaDonBanHang.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LapHoaDonBanHang.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.taoMaHoaDon();
    }
    
    //cập nhật các textField
    public void capNhapCacTextField(){
        this.maHangHoa = this.mTextFieldMaHangHoa.getText();
        this.taoMaHoaDon();
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
                
//            model.addRow(new Object[]{hangHoa.mMaHangHoa, hangHoa.mTenHangHoa,
//                                1,  hangHoa.mGiaBan, 0, hangHoa.mGiaBan});
            
                model.addRow(new Object[]{hangHoa.mMaHangHoa, hangHoa.mTenHangHoa,
                                1,  hangHoa.mGiaVon * 1.5, 0});
            }
        }
    }
    
    
    
    //Tạo lại bảng hàng hóa và phiếu nhập hàng
    public void taoLai(){
        this.model.setRowCount(0);
        this.mTextFieldMaHangHoa.setText("");
        this.mTextFieldDaTra.setText(String.valueOf(0));
        this.mTextFieldGhiChu.setText(String.valueOf(0));
        this.capNhatManHinhHoaDonBanHang();
    }
    
    
    // Thêm danh sach nhân viên vào comboBox
    public void themTenNhanVienVaoComboBox(){
        ArrayList<NhanVien> dsNhanVien = this.bangNhanVien.layTatCaNhanVienTrongCSDL();
        if(dsNhanVien.size() > 0){
            for(int i = 0; i < dsNhanVien.size(); i++){
                this.mComboBoxTenNhanVien.addItem(dsNhanVien.get(i).mTenNhanVien);
            }
           this.mComboBoxTenNhanVien.setSelectedIndex(0);
        }
    }
    
    // lấy mã nhân viên từ tên nhân viên trong comboBox
    public void layMaNhanVienTrongComboBox(){
        ArrayList<NhanVien> dsNhanVien = this.bangNhanVien.layTatCaNhanVienTrongCSDL();
        if(dsNhanVien.size() > 0){
            for(int i = 0; i < dsNhanVien.size(); i++){
                if(dsNhanVien.get(i).mTenNhanVien.equals((String)this.mComboBoxTenNhanVien.getSelectedItem())){
                    this.maNhanVien = dsNhanVien.get(i).mMaNhanVien;
                }
            }
        }
    }
    
    // tạo mã hóa đơn bán hàng
    public void taoMaHoaDon(){
        this.maHoaDon = this.bangHoaDonBanHang.taoMaHoaDonBanHang();
        this.mLabelMaHoaDonBanHang.setText(maHoaDon);
    }
    
    
    //lấy danh sách hàng hóa trong table
    public ArrayList<HangHoa> layDanhSachHangHoaTrongTable(){
        ArrayList<HangHoa> arlHangHoa = new ArrayList<>();
        for(int i = 0; i < model.getRowCount(); i++){
            HangHoa hh = new HangHoa();
            hh.mMaHangHoa = model.getValueAt(i, 0).toString();
            hh.mTenHangHoa = model.getValueAt(i, 1).toString();
            // số lượng bán
            hh.mTonKho = (int)Integer.parseInt(model.getValueAt(i, 2).toString());
            // đơn giá
            hh.mGiaBan = (int)Float.parseFloat(model.getValueAt(i, 3).toString());
            //giá giảm
            hh.mDinhMucTonItNhat = (int)Float.parseFloat(model.getValueAt(i, 4).toString());
       
            
            arlHangHoa.add(hh);
        }
        return arlHangHoa;
    }
    
    
    //Lấy tổng tiền của các hàng nhập trong bảng
    public void layTongTien(){
        
        int tongTien = 0;
        ArrayList<HangHoa> arlHangHoa = this.layDanhSachHangHoaTrongTable();
        if(arlHangHoa.size() > 0){
            for(int i = 0; i < arlHangHoa.size(); i ++){
                
                tongTien += (arlHangHoa.get(i).mGiaBan * arlHangHoa.get(i).mTonKho - 
                        arlHangHoa.get(i).mDinhMucTonItNhat * arlHangHoa.get(i).mTonKho);
                
                giaGiam += arlHangHoa.get(i).mDinhMucTonItNhat * arlHangHoa.get(i).mTonKho;
            }
        }
        
        this.tongTienHoaDon = tongTien;
    }
    
    //tinh con no
    public void tinhNo(){
        this.conNo = this.tongTienHoaDon - this.giaGiam - Integer.parseInt(this.mTextFieldDaTra.getText());
    }
    

    // refresh lại màn hình hóa đơn bán hàng
    public void capNhatManHinhHoaDonBanHang(){
        this.layTongTien();
        this.tinhNo();
        this.mLabelTongTien.setText(String.valueOf(this.tongTienHoaDon));
       this.mLabelConNo.setText(String.valueOf(this.conNo));
   }
    
    //cập nhật bảng hóa đơn bán hàng
    public void taoHoaDonBanHang(){
        HoaDonBanHang hoaDonBanHang = new HoaDonBanHang();
        hoaDonBanHang.mTongTien = this.tongTienHoaDon;
        hoaDonBanHang.mGhiChu = this.mTextFieldGhiChu.getText();
        hoaDonBanHang.mGiaGiam = this.giaGiam;
        hoaDonBanHang.mKhachDaTra = this.tienDaTra;
        hoaDonBanHang.mMaHoaDonBanHang = this.mLabelMaHoaDonBanHang.getText();
        hoaDonBanHang.mMaKhachHang = this.mLabelMaKhachHang.getText();
        hoaDonBanHang.mMaNhanVien = this.maNhanVien;
        hoaDonBanHang.mThoiGian = ControlUtils.layThoiGian();
        
        bangHoaDonBanHang.themHoaDonBanHang(hoaDonBanHang);
    }
    
    //cập nhật bảng chi tiết hóa đơn bán hàng
    public void taoChiTietHoaDonBanHang(){
        ArrayList<HangHoa> arlHangHoa = this.layDanhSachHangHoaTrongTable();
        if(arlHangHoa != null){
            for(int i = 0; i < arlHangHoa.size(); i ++){
                ChiTietHoaDonBanHang chiTietHoaDonBanHang = new ChiTietHoaDonBanHang();
                chiTietHoaDonBanHang.mMaHangHoa = arlHangHoa.get(i).mMaHangHoa;
                chiTietHoaDonBanHang.mMaHoaDonBanHang = this.mLabelMaHoaDonBanHang.getText();
                chiTietHoaDonBanHang.mSoLuong = arlHangHoa.get(i).mTonKho;
                bangChiTietHoaDonBanHang.themChiTietHoaDonBanHang(chiTietHoaDonBanHang);
            }
        }
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
        model.addRow( new Object[]{hoaDonBanHang.mMaHoaDonBanHang, hoaDonBanHang.mMaKhachHang, hoaDonBanHang.mTongTien
                ,hoaDonBanHang.mGiaGiam, hoaDonBanHang.mGhiChu, hoaDonBanHang.mKhachDaTra, hoaDonBanHang.mMaNhanVien, hoaDonBanHang.mThoiGian});
    }
    
    // lấy danh sách các hàng bán trong table
    
    
}
