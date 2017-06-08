/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import DatabaseConnection.ConnectionUtils;
import database.BangHangHoa;
import entities.HangHoa;
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
public class CThietLapGia {

    public JTable mTable;
    public DefaultTableModel mModel;
    public ArrayList<HangHoa> dsHangHoa = new ArrayList<>();
    public BangHangHoa bangHangHoa;
    
    public CThietLapGia(JTable table) {
        
        
        this.mTable = table;
        this.mModel = (DefaultTableModel) mTable.getModel();
        
        try {
            this.bangHangHoa = new BangHangHoa(new ConnectionUtils().getMySQLConnection());
        } catch (SQLException ex) {
            Logger.getLogger(CThietLapGia.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CThietLapGia.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
    // gán giá trị cho dsHangHoa
    public void ganGiaTriChoDSHangHoa(){
       this.dsHangHoa = this.bangHangHoa.layTatCaHangHoaTrongCSDL();
    }
    
    //thêm đưa danh sách dữ liệu hàng hóa vào bảng
    public void duaDuLieuVaoBang(){
        this.ganGiaTriChoDSHangHoa();
        if(this.dsHangHoa != null){
            for(int i = 0; i < this.dsHangHoa.size(); i ++){
                HangHoa hh = dsHangHoa.get(i);
                this.mModel.addRow(new Object[]{hh.mMaHangHoa, hh.mTenHangHoa,
                                hh.mNhomHangHoa, hh.mGiaVon, hh.mGiaBan});
            }
        }
    }
    
    //lấy danh sách hàng hóa trong table
    public ArrayList<HangHoa> layDanhSachHangHoaTrongTable(){
        ArrayList<HangHoa> arlHangHoa = new ArrayList<>();
        for(int i = 0; i < this.mModel.getRowCount(); i++){
            HangHoa hh = new HangHoa();
            hh.mMaHangHoa = mModel.getValueAt(i, 0).toString();
            hh.mTenHangHoa = mModel.getValueAt(i, 1).toString();
            
            hh.mNhomHangHoa = mModel.getValueAt(i, 2).toString();
            
            // số lượng bán
            hh.mGiaVon = (int)(mModel.getValueAt(i, 3));
            // đơn giá
            hh.mGiaBan = (int)(mModel.getValueAt(i, 4));
            
            arlHangHoa.add(hh);
        }
        return arlHangHoa;
    }
    
    // cập nhật lại giá cho hàng hóa
    public void capNhatGia(){
        this.mModel.fireTableDataChanged();
        
        ArrayList<HangHoa> dsHHTrongTable = this.layDanhSachHangHoaTrongTable();
        
        for(int i = 0; i < dsHHTrongTable.size(); i ++){
            String mHH = dsHHTrongTable.get(i).mMaHangHoa;
            int giaBan = dsHHTrongTable.get(i).mGiaBan;
            this.bangHangHoa.capNhatGia(mHH, giaBan);
        }
    }
    // xuất file excecl
    public void xuatFileExcel(){
        ArrayList<HangHoa> dsHHTrongTable = this.layDanhSachHangHoaTrongTable();
        GhiFile ghiFile = new GhiFile();
        ghiFile.taoFileBangGiaHangHoa(dsHHTrongTable);
    }
    
    
}
