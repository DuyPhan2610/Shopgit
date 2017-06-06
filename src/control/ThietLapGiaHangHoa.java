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
public class ThietLapGiaHangHoa {

    public JTable mTable;
    public DefaultTableModel mModel;
    public ArrayList<HangHoa> dsHangHoa = new ArrayList<>();
    public BangHangHoa bangHangHoa;
    
    public ThietLapGiaHangHoa(JTable table) {
        
        
        this.mTable = table;
        this.mModel = (DefaultTableModel) mTable.getModel();
        
        try {
            this.bangHangHoa = new BangHangHoa(new ConnectionUtils().getMySQLConnection());
        } catch (SQLException ex) {
            Logger.getLogger(ThietLapGiaHangHoa.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ThietLapGiaHangHoa.class.getName()).log(Level.SEVERE, null, ex);
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
    
}
