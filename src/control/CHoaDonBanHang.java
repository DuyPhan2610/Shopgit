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
            Logger.getLogger(ThietLapGiaHangHoa.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ThietLapGiaHangHoa.class.getName()).log(Level.SEVERE, null, ex);
        }
       
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
}
