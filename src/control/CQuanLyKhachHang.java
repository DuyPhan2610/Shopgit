/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import DatabaseConnection.ConnectionUtils;
import database.BangCongNoCuaKhachHang;
import database.BangKhachHang;
import entities.CongNoCuaKhachHang;
import entities.KhachHang;
import entities.NhaCungCap;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author phamh
 */
public class CQuanLyKhachHang {
    
    public JTable jTable;
    public BangKhachHang bangKhachHang;
    public BangCongNoCuaKhachHang bangCongNoCuaKhachHang;
    public ArrayList<KhachHang> dsKhachHang;
    public DefaultTableModel mModel; 
    
    public CQuanLyKhachHang(JTable table){
        this.jTable = table;
        this.mModel = (DefaultTableModel) this.jTable.getModel();
        
        try {
            bangKhachHang = new BangKhachHang(new ConnectionUtils().getMySQLConnection());
            bangCongNoCuaKhachHang = new BangCongNoCuaKhachHang(new ConnectionUtils().getMySQLConnection());
            
        } catch (SQLException ex) {
            Logger.getLogger(CQuanLyKhachHang.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CQuanLyKhachHang.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    //lấy danh sách khách hàng có trong csdl
    public void layDanhSachKhachHang(){
        this.dsKhachHang = bangKhachHang.layTatCaKhachHangTrongCSDL();
    }
    
    // đưa dữ liệu khách hàng vào bảng
    public void duaDuLieuVaoBang(){
        this.layDanhSachKhachHang();
        if(this.dsKhachHang != null){
            for(int i = 0; i < this.dsKhachHang.size(); i ++){
                KhachHang kh = dsKhachHang.get(i);
                ArrayList<CongNoCuaKhachHang> dsCongNoKH = this.bangCongNoCuaKhachHang.layTatCaCongNoCuaKhachHangTrongCSDL();
                int noHienTai = 0;
                for(int j = 0; j < dsCongNoKH.size(); j++){
                    if(dsKhachHang.get(i).mMaCongNoKhachHang.equals((dsCongNoKH.get(j).mMaCongNoCuaKhachHang)))
                     noHienTai = (int)dsCongNoKH.get(j).mTongNo;
                }
                
                this.mModel.addRow(new Object[]{ kh.mMaKhachHang, kh.mTenKhachHang , noHienTai, kh.mDienThoai,
                                                    kh.mNgaySinh});
            }
        }
    }
    
    
    // lấy danh sách hàng hóa trong table
    public ArrayList<KhachHang> layDSKhachHangTrongTable(){
        
        ArrayList<KhachHang> dsKhachHang = new ArrayList<>();
        for(int i = 0; i < this.mModel.getColumnCount(); i++){
            KhachHang kh = new KhachHang();
            kh.mMaKhachHang = (String)mModel.getValueAt(i, 0);
            kh.mTenKhachHang = (String)mModel.getValueAt(i, 1);
            
            // nợ hiện tại
            kh.mMaCongNoKhachHang = mModel.getValueAt(i, 2).toString();
            
            kh.mDienThoai = (String)mModel.getValueAt(i, 3);
            kh.mNgaySinh = (String)mModel.getValueAt(i, 4);
            
            dsKhachHang.add(kh);
        }
        
        return dsKhachHang;
    }
    
    // tạo file Excel
    public void taoFileExcel(){
        GhiFile ghiFile = new GhiFile();
        ghiFile.taoFileKhachHang(this.layDSKhachHangTrongTable());
    }
        
    
    // xóa row trong table
    public void xoaRowTrongTable(){
        int rowNum = this.jTable.getSelectedRow();
        this.mModel.removeRow(rowNum);
        this.mModel.fireTableDataChanged();
    }
    
}
