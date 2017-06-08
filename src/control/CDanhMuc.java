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
public class CDanhMuc {
    public JTable mTable;
    public DefaultTableModel mModel;
    public ArrayList<HangHoa> dsHangHoa = new ArrayList<>();
    public BangHangHoa bangHangHoa;
    
    public CDanhMuc(JTable table) {
        
        
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
                                hh.mNhomHangHoa, hh.mGiaVon, hh.mGiaBan, hh.mTonKho, hh.mDinhMucTonItNhat
                                , hh.mDinhMucTonNhieuNhat});
            }
        }
    }
    
    
    // lấy danh sách hàng hóa trong table 
    public ArrayList<HangHoa> layDSHangHoaTrongTable(){
        ArrayList<HangHoa> dsHangHoa = new ArrayList();
        for(int i = 0; i < this.mModel.getRowCount(); i++){
            HangHoa hh = new HangHoa();
            
            hh.mMaHangHoa = mModel.getValueAt(i, 0).toString();
            hh.mTenHangHoa = mModel.getValueAt(i, 1).toString();
            
            hh.mNhomHangHoa = mModel.getValueAt(i, 2).toString();
            
            // số lượng bán
            hh.mGiaVon = (int)(mModel.getValueAt(i, 3));
            // đơn giá
            hh.mGiaBan = (int)(mModel.getValueAt(i, 4));
            
            // tồn kho
            hh.mTonKho = (int)(mModel.getValueAt(i, 5));
            
            // tồn ít nhất
            hh.mDinhMucTonItNhat = (int)(mModel.getValueAt(i, 6));
            // tồn nhiều nhất
            hh.mDinhMucTonNhieuNhat = (int)(mModel.getValueAt(i, 7));
            
            
            dsHangHoa.add(hh);
        }
        
        return dsHangHoa;
    }
    
    
    // xóa row trong table
    public void xoaRowTrongTable(){
        int rowNum = this.mTable.getSelectedRow();
        this.mModel.removeRow(rowNum);
        this.mModel.fireTableDataChanged();
    }
    
    
    // tạo file excel hàng hóa
    public void taoFileExcel(){
        GhiFile ghiFile = new GhiFile();
        ghiFile.taoFileHangHoa(this.layDSHangHoaTrongTable());
    }
    
    
    // tìm hàng hóa bằng mã
    public void timHangHoaBangMa(String maHangHoa){
        
        HangHoa hh = bangHangHoa.layHangHoaTuMaHangHoa(maHangHoa);
        while(mModel.getRowCount() > 0){
            mModel.removeRow(0);
        }
        if(hh != null){
            this.mModel.addRow(new Object[]{hh.mMaHangHoa, hh.mTenHangHoa,
                                hh.mNhomHangHoa, hh.mGiaVon, hh.mGiaBan, hh.mTonKho, hh.mDinhMucTonItNhat
                                , hh.mDinhMucTonNhieuNhat});
        }
    }
    
    // show hàng hóa dưới định mức tồn ít nhất
    public void hangHoaDuoiDinhMucTon(){
        while(mModel.getRowCount() > 0){
            mModel.removeRow(0);
        }
        
        this.ganGiaTriChoDSHangHoa();
        if(this.dsHangHoa != null){
            for(int i = 0; i < this.dsHangHoa.size(); i ++){
                HangHoa hh = dsHangHoa.get(i);
                if(hh.mTonKho < hh.mDinhMucTonItNhat){
                            this.mModel.addRow(new Object[]{hh.mMaHangHoa, hh.mTenHangHoa,
                                hh.mNhomHangHoa, hh.mGiaVon, hh.mGiaBan, hh.mTonKho, hh.mDinhMucTonItNhat
                                , hh.mDinhMucTonNhieuNhat});
                }
            }
        }
        
    }
    
    
    // hàng hóa vượt mức tồn
    public void hangHoaVuotDinhMucTon(){
        while(mModel.getRowCount() > 0){
            mModel.removeRow(0);
        }
        
        this.ganGiaTriChoDSHangHoa();
        if(this.dsHangHoa != null){
            for(int i = 0; i < this.dsHangHoa.size(); i ++){
                HangHoa hh = dsHangHoa.get(i);
                if(hh.mTonKho > hh.mDinhMucTonNhieuNhat){
                            this.mModel.addRow(new Object[]{hh.mMaHangHoa, hh.mTenHangHoa,
                                hh.mNhomHangHoa, hh.mGiaVon, hh.mGiaBan, hh.mTonKho, hh.mDinhMucTonItNhat
                                , hh.mDinhMucTonNhieuNhat});
                }
            }
        }
        
    }
    
    // hết hàng trong kho
    public void hetHangTrongKho(){
        while(mModel.getRowCount() > 0){
            mModel.removeRow(0);
        }
        
        this.ganGiaTriChoDSHangHoa();
        if(this.dsHangHoa != null){
            for(int i = 0; i < this.dsHangHoa.size(); i ++){
                HangHoa hh = dsHangHoa.get(i);
                if(hh.mTonKho == 0){
                            this.mModel.addRow(new Object[]{hh.mMaHangHoa, hh.mTenHangHoa,
                                hh.mNhomHangHoa, hh.mGiaVon, hh.mGiaBan, hh.mTonKho, hh.mDinhMucTonItNhat
                                , hh.mDinhMucTonNhieuNhat});
                }
            }
        }
        
    }
    
    // còn hàng trong kho
    public void conHangTrongKho(){
        while(mModel.getRowCount() > 0){
            mModel.removeRow(0);
        }
        
        this.ganGiaTriChoDSHangHoa();
        if(this.dsHangHoa != null){
            for(int i = 0; i < this.dsHangHoa.size(); i ++){
                HangHoa hh = dsHangHoa.get(i);
                if(hh.mTonKho > 0){
                            this.mModel.addRow(new Object[]{hh.mMaHangHoa, hh.mTenHangHoa,
                                hh.mNhomHangHoa, hh.mGiaVon, hh.mGiaBan, hh.mTonKho, hh.mDinhMucTonItNhat
                                , hh.mDinhMucTonNhieuNhat});
                }
            }
        }
        
    }
    
    // lọc theo nhóm quần áo
    public void locTheoNhomHangHoa(String nhomHangHoa){

            while(mModel.getRowCount() > 0){
                mModel.removeRow(0);
            }

        
            
            this.ganGiaTriChoDSHangHoa();
            if(this.dsHangHoa != null){
                for(int i = 0; i < this.dsHangHoa.size(); i ++){
                    HangHoa hh = dsHangHoa.get(i);
                    if(hh.mNhomHangHoa.equals(nhomHangHoa)){
                                this.mModel.addRow(new Object[]{hh.mMaHangHoa, hh.mTenHangHoa,
                                    hh.mNhomHangHoa, hh.mGiaVon, hh.mGiaBan, hh.mTonKho, hh.mDinhMucTonItNhat
                                    , hh.mDinhMucTonNhieuNhat});
                    }
                }
            }
               
       
    }
}
