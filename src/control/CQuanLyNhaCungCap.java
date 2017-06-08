/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import DatabaseConnection.ConnectionUtils;
import database.BangCongNoNhaCungCap;
import database.BangNhaCungCap;
import entities.CongNoNhaCungCap;
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
public class CQuanLyNhaCungCap {
    public JTable jTable;
    public BangNhaCungCap bangNhaCungCap;
    public DefaultTableModel mModel;
    public BangCongNoNhaCungCap bangCongNoNhaCungCap;

    public CQuanLyNhaCungCap(JTable jTable) {
        
        this.jTable = jTable;
        this.mModel = (DefaultTableModel) this.jTable.getModel();
        try {
            bangNhaCungCap = new BangNhaCungCap(new ConnectionUtils().getMySQLConnection());
            bangCongNoNhaCungCap = new BangCongNoNhaCungCap(new ConnectionUtils().getMySQLConnection());
        } catch (SQLException ex) {
            Logger.getLogger(CQuanLyNhaCungCap.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CQuanLyNhaCungCap.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
     // đưa dữ liệu khách hàng vào bảng
    public void duaDuLieuVaoBang(){
        ArrayList<NhaCungCap> dsNCC = bangNhaCungCap.layTatCaNhaCungCapTrongCSDL();
        
        if(dsNCC != null){
            for(int i = 0; i < dsNCC.size(); i ++){
                NhaCungCap ncc = dsNCC.get(i);
                ArrayList<CongNoNhaCungCap> dsCongNoNCC = this.bangCongNoNhaCungCap.layTatCaConNoNhaCungCapTrongCSDL();
                int noHienTai = 0;
                for(int j = 0; j < dsCongNoNCC.size(); j++){
                    if(dsNCC.get(i).mMaCongNoNhaCungCap.equals((dsCongNoNCC.get(j).mMaCongNoNhaCungCap)))
                        noHienTai = (int)dsCongNoNCC.get(j).mTongNo;
                }
                
                this.mModel.addRow(new Object[]{ ncc.mMaNhaCungCap, ncc.mTenNhaCungCap, ncc.mNhomNhaCungCap , noHienTai, ncc.mEmail,
                                                    ncc.mDiaChi});
            }
        }
    }
    
    // lấy dữ liệu từ bảng
    public ArrayList<NhaCungCap> layDuLieuTuTable(){
        ArrayList<NhaCungCap> dsNCC = new ArrayList<>();
        for(int i = 0; i < this.mModel.getColumnCount(); i++){
            NhaCungCap ncc = new NhaCungCap();
            ncc.mDiaChi = (String)mModel.getValueAt(i, 5);
            ncc.mEmail = (String)mModel.getValueAt(i, 4);
            
            // nợ hiện tại
            ncc.mMaCongNoNhaCungCap = mModel.getValueAt(i, 3).toString();
            ncc.mMaNhaCungCap = (String)mModel.getValueAt(i, 0);
            ncc.mNhomNhaCungCap = (String)mModel.getValueAt(i, 2);
            ncc.mTenNhaCungCap = (String)mModel.getValueAt(i, 1);
            
            dsNCC.add(ncc);
        }
        return dsNCC;
    }
    
    // tạo file excel
    public void taoFileExcel(){
        GhiFile ghiFile = new GhiFile();
        ghiFile.taoFileNhaCungCap(this.layDuLieuTuTable());
    }
    
    
    // xóa row trong table
    public void xoaRowTrongTable(){
        int rowNum = this.jTable.getSelectedRow();
        this.mModel.removeRow(rowNum);
        this.mModel.fireTableDataChanged();
    }
}
