/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import database.BangChiTietCongNoNhaCungCap;
import database.BangHangHoa;
import database.BangNhaCungCap;
import database.BangPhieuNhapHang;
import database.CauTruyVan;
import database.TruyVanDuLieu;
import entities.ChiTietPhieuNhapHang;
import entities.HangHoa;
import entities.HangNhap;
import entities.PhieuNhapHang;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author phamh
 */




public class NhapHangHoa {
    private javax.swing.JTable tb_screennhaphang;
    private DefaultTableModel model;
    private BangHangHoa bangHangHoa;
    private BangNhaCungCap bangNhaCungCap;
    private BangPhieuNhapHang bangPhieuNhapHang;
    
    public DocFile docFile;
    
    
    public NhapHangHoa(JTable table){
        this.tb_screennhaphang = table;
        model = (DefaultTableModel) tb_screennhaphang.getModel();
        bangHangHoa = new BangHangHoa();
        bangNhaCungCap = new BangNhaCungCap();
        bangPhieuNhapHang = new BangPhieuNhapHang();
        
    }
    
    // Khi click vào IMPORT
    // Mở jChooser và lấy đường dẫn file và mở file rồi đưa dữ liệu vào bảng
    public void importFile(){
        try {
            // mở file bằng jchooser
            docFile = new DocFile();
            this.duaDuLieuVaoBang();
            System.out.println("Đưa dữ liệu vào bảng thành công");
        } catch (IOException ex) {
            Logger.getLogger(NhapHangHoa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // đọc dữ liệu từ file và đưa vào table
    private void duaDuLieuVaoBang(){
        ArrayList<HangNhap> arlHangNhap = docFile.layDanhSachHangNhap();
        
        if(arlHangNhap.isEmpty()){
            System.out.println("Khong co cai nao trong mang");
        }
        for(int i = 0; i < arlHangNhap.size(); i ++){
            this.themMotHangNhapVaoBang(arlHangNhap.get(i));
        }
        
    }
    
    
    // Thêm từng dữ liệu vào table
    public void themMotHangNhapVaoBang(HangNhap hangNhap){
        model.addRow(new Object[]{hangNhap.mMaHangHoa, hangNhap.mTenHangHoa,
                                hangNhap.mNhomHangHoa,  hangNhap.mGiaMua, hangNhap.mSoLuong});
    }
    
    //kiểm tra có trùng mã hàng hóa khi nhập dữ liệu không
    // Nếu trùng thì hiện danh sách các hàng nhập bị trùng
    public  ArrayList<HangNhap>  kiemTraMaHangHoa(){
        ArrayList<HangHoa> arlHangHoa = bangHangHoa.layTatCaHangHoaTrongCSDL();
        ArrayList<HangNhap> arlHangNhap = this.layDanhSachHangNhapTrongTable();
        
        //Danh sách các hàng nhập bị trùng mã sản phẩm
        ArrayList<HangNhap> hangNhapTrung = new ArrayList<>();
        for(int i = 0; i < arlHangNhap.size(); i++){
            for(int j = 0; j < arlHangHoa.size(); j ++){
                if(arlHangNhap.get(i).mMaHangHoa.equals(arlHangHoa.get(j).mMaHangHoa)){
                    hangNhapTrung.add(arlHangNhap.get(i));
                }
            }
        }
        return hangNhapTrung;
    }
    
    // lấy danh sách các hàng nhập trong table
    public ArrayList<HangNhap> layDanhSachHangNhapTrongTable(){
        ArrayList<HangNhap> arlHangNhap = new ArrayList<>();
        for(int i = 0; i < model.getRowCount(); i++){
            String maHangNhap = model.getValueAt(i, 0).toString();
            String tenHangNhap = model.getValueAt(i, 1).toString();
            String nhomHangNhap = model.getValueAt(i, 2).toString();
            int soLuong = Integer.parseInt(model.getValueAt(i, 3).toString());
            int donGia = Integer.parseInt(model.getValueAt(i, 4).toString());
            
            HangNhap hangNhap = new HangNhap(maHangNhap, tenHangNhap, donGia, soLuong, nhomHangNhap);
            arlHangNhap.add(hangNhap);
        }
        return arlHangNhap;
    }
    

    
    // Tạo phiếu nhập
    public void taoPhieuNhap(String maPhieuNhap, String maNhaCungCap,
                    int tongTien, int giaGiam, int tienDaTra, int conNo, String thoiGian, String ghiChu){
    
        PhieuNhapHang phieuNhapHang = new PhieuNhapHang(maPhieuNhap, maNhaCungCap,
            tongTien, giaGiam, tienDaTra, conNo, thoiGian, ghiChu);
        TruyVanDuLieu queryData;
        // Kết nối cơ sở dữ liệu
        queryData = new TruyVanDuLieu();
        //Thực hiện câu truy vấn chèn record vào csdl
        //queryData.insertProduct(hangHoa);
        
        // Đóng kết nối cơ sở dữ liệu khi chèn xong
        queryData.closeDatabase();
    }
    
    
    // Tạo chi tiết phiếu nhập
    public void taoChiTietPhieuNhap(ArrayList<String> arlMaHangHoa, String maPhieuNhap){
        
        TruyVanDuLieu queryData;
        // Kết nối cơ sở dữ liệu
        queryData = new TruyVanDuLieu();
        for(int i = 0; i < arlMaHangHoa.size(); i ++ ){
            
            //Thực hiện câu truy vấn chèn record vào csdl
            //queryData.insertChiTietPhieuNhap(new ChiTietPhieuNhapHang(arlMaHangHoa.get(i), maPhieuNhap));
        }
        // Đóng kết nối cơ sở dữ liệu khi chèn xong
        queryData.closeDatabase();
    }
    
    
    
    
    
    
    
    
    
    // Hàm tự động tạo mã hàng hóa từ hàng hóa cuối cùng
    public String taoMaHangHoa(){
        ArrayList<HangHoa> arlHangHoa = bangHangHoa.layTatCaHangHoaTrongCSDL();
        
        if(arlHangHoa.size() > 0){
        //Lấy hàng hóa cuối cùng trong csdl
            HangHoa hangHoa = arlHangHoa.get(arlHangHoa.size() - 1);   
            return ControlUtils.taoMaHangHoa(hangHoa.mMaHangHoa);
        }
        else{
            return ControlUtils.taoMaHangHoa("SP0000000");
        }
    }
    
    
    
    // Hàm tự động tạo mã phiếu nhập
    public String taoMaPhieuNhap(){
        ArrayList<PhieuNhapHang> arlPhieuNhap = bangPhieuNhapHang.layTatCaPhieuNhapHangTrongCSDL();
        
        if(arlPhieuNhap.size() > 0){
        //Lấy hàng hóa cuối cùng trong csdl
            PhieuNhapHang phieuNhapHang = arlPhieuNhap.get(arlPhieuNhap.size() - 1);   
            return ControlUtils.taoMaHangHoa(phieuNhapHang.mMaPhieuNhap);
        }
        else{
            return ControlUtils.taoMaHangHoa("SP0000000");
        }
    }
}
