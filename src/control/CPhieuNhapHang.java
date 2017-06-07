/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import DatabaseConnection.ConnectionUtils;
import database.BangNhaCungCap;
import database.BangPhieuNhapHang;
import entities.PhieuNhapHang;
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
public class CPhieuNhapHang {
    public JTable mTable;
    public DefaultTableModel mModel;
    public ArrayList<PhieuNhapHang> dsPhieuNhap = new ArrayList<>();
    public BangPhieuNhapHang bangPhieuNhapHang;
    public BangNhaCungCap bangNhaCungCap;
    
    public CPhieuNhapHang(JTable table) {
        
        
        this.mTable = table;
        this.mModel = (DefaultTableModel) mTable.getModel();
        
        try {
            this.bangPhieuNhapHang = new BangPhieuNhapHang(new ConnectionUtils().getMySQLConnection());
            this.bangNhaCungCap = new BangNhaCungCap(new ConnectionUtils().getMySQLConnection());
        } catch (SQLException ex) {
            Logger.getLogger(ThietLapGiaHangHoa.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ThietLapGiaHangHoa.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
    // gán giá trị cho dsHangHoa
    public void ganGiaTriChoDSPhieuNhap(){
       this.dsPhieuNhap = this.bangPhieuNhapHang.layTatCaPhieuNhapHangTrongCSDL();
    }
    
    //thêm đưa danh sách dữ liệu hàng hóa vào bảng
    public void duaDuLieuVaoBang(){
        this.ganGiaTriChoDSPhieuNhap();
        if(this.dsPhieuNhap != null){
            for(int i = 0; i < this.dsPhieuNhap.size(); i ++){
                PhieuNhapHang pnh = dsPhieuNhap.get(i);
                
                String tenNhaCungCap = (bangNhaCungCap.layNhaCungCapTuMaNCC(pnh.mMaNhaCungCap)).mTenNhaCungCap;
                
                this.mModel.addRow(new Object[]{pnh.mMaPhieuNhap, tenNhaCungCap, pnh.mTongTien, pnh.mGiaGiam,
                                pnh.mTienDaTra, pnh.mThoiGian, pnh.mGhiChu});
            }
        }
    }
    
}
