/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import DatabaseConnection.ConnectionUtils;
import database.BangHangHoa;
import database.BangHoaDonBanHang;
import entities.HangHoa;
import entities.HoaDonBanHang;
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
public class CHoaDonBanHang {
    public JTable mTable;
    public DefaultTableModel mModel;
    public ArrayList<HoaDonBanHang> dsHoaDon = new ArrayList<>();
    public BangHoaDonBanHang bangHoaDonbanHang;

    
    public CHoaDonBanHang(JTable table) {
        
        
        this.mTable = table;
        this.mModel = (DefaultTableModel) mTable.getModel();
        
        
        try {
            this.bangHoaDonbanHang = new BangHoaDonBanHang(new ConnectionUtils().getMySQLConnection());
        } catch (SQLException ex) {
            Logger.getLogger(CThietLapGia.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CThietLapGia.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
    
    // lấy danh sách hóa đơn trong table
    public ArrayList<HoaDonBanHang> layDSHoaDonTrongTable(){
        ArrayList<HoaDonBanHang> dsHoaDon = new ArrayList<>();
        for(int i = 0; i < this.mModel.getRowCount(); i++){
            HoaDonBanHang hd = new HoaDonBanHang();
            hd.mMaHoaDonBanHang = mModel.getValueAt(i, 0).toString();
            hd.mTongTien = (int)mModel.getValueAt(i, 1);
            
            hd.mGiaGiam = (int)mModel.getValueAt(i, 2);
            
            // số lượng bán
            hd.mKhachDaTra = (int)(mModel.getValueAt(i, 3));
            // đơn giá
            hd.mThoiGian = (String)(mModel.getValueAt(i, 4));
            
            hd.mGhiChu = (String) (mModel.getValueAt(i, 5));
            dsHoaDon.add(hd);
        }
        return dsHoaDon;
    }
    
    // tạo file excel hóa đơn
    public void taoFileExcel(){
        GhiFile ghiFile = new GhiFile();
        ghiFile.taoDanhSachHoaDoa(this.layDSHoaDonTrongTable());
    }
    
    // gán giá trị cho dsHangHoa
    public void ganGiaTriChoDSHangHoa(){
       this.dsHoaDon = this.bangHoaDonbanHang.layTatCaHoaDonBanHangTrongCSDL();
    }
    
    //thêm đưa danh sách dữ liệu hàng hóa vào bảng
    public void duaDuLieuVaoBang(){
        this.ganGiaTriChoDSHangHoa();
        if(this.dsHoaDon != null){
            for(int i = 0; i < this.dsHoaDon.size(); i ++){
                HoaDonBanHang hd = dsHoaDon.get(i);
                this.mModel.addRow(new Object[]{hd.mMaHoaDonBanHang, hd.mTongTien, hd.mGiaGiam, hd.mKhachDaTra,
                                hd.mThoiGian, hd.mGhiChu});
            }
        }
    }
    
    // xóa row trong table
    public void xoaRowTrongTable(){
        int rowNum = this.mTable.getSelectedRow();
        this.mModel.removeRow(rowNum);
        this.mModel.fireTableDataChanged();
    }
}
