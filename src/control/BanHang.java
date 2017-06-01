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
import database.ConnectionUtils;
import entities.HoaDonBanHang;
import entities.KhachHang;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author USER
 */
public class BanHang {
    public javax.swing.JTable tb_banhang;
    public DefaultTableModel model;
    public BangHangHoa bangHangHoa;
    public BangKhachHang bangKhachHang;
    public BangHoaDonBanHang bangHoaDonBanHang;
    public BangChiTietHoaDonBanHang bangChiTietHoaDonBanHang;
    public BangCongNoCuaKhachHang bangCongNoCuaKhachHang;
    public BangChiTietNoCuaKhachHang bangChiTietNoCuaKhachHang;
    public JComboBox<String> comboBox;
    public Connection connection;
    
    public BanHang (JTable table, JComboBox comboBox) {
        this.tb_banhang = table;
        try {
            connection = new ConnectionUtils().getMySQLConnection();
            this.comboBox =comboBox;
            model = (DefaultTableModel) tb_banhang.getModel();
            bangHangHoa = new BangHangHoa(connection);
            bangKhachHang = new BangKhachHang(connection);
            bangHoaDonBanHang = new BangHoaDonBanHang(connection);
            bangChiTietHoaDonBanHang = new BangChiTietHoaDonBanHang(connection);
            bangCongNoCuaKhachHang = new BangCongNoCuaKhachHang(connection);
            bangChiTietNoCuaKhachHang = new BangChiTietNoCuaKhachHang(connection);
            System.out.println("\n Ket noi co so du lieu thanh cong");
        } catch (SQLException ex) {
            System.out.println("\n Ket noi co so du lieu khong thanh cong");
        } catch (ClassNotFoundException ex) {
            System.out.println("\n Ket noi co so du lieu khong thanh cong");
        }
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
