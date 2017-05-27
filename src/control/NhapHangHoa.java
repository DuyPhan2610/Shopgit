/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import database.BangHangHoa;
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
    public TruyVanDuLieu truyVanDuLieu;
    public DocFile docFile;
    
    // arrayList này sẽ chứa các hàng hóa mà import từng sản phẩm
    private ArrayList<HangHoa> arl;
    DocFile usingExcelFile;
    
    public NhapHangHoa(JTable table){
        this.tb_screennhaphang = table;
        model = (DefaultTableModel) tb_screennhaphang.getModel();

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
        
        if(arlHangNhap.size() == 0){
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
    public void  kiemTraMaHangHoa(){
        ArrayList<HangHoa> arlHangHoa = this.layTatCaHangHoaTrongCSDL();
        ArrayList<HangNhap> arlHangNhap = this.layDanhSachHangNhapTrongTable();
        
        
        
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
    
    
    // Lấy dữ liệu từ file vào cơ sở dữ liệu
    public void themDuLieuTuTableVaoCoSoDuLieu( ArrayList <HangHoa> arlHangHoa){
    
    BangHangHoa bangHangHoa;
        try {
            bangHangHoa = new BangHangHoa();
            bangHangHoa.themDuLieuVaoHangHoa(arlHangHoa);
        } catch (SQLException ex) {
            Logger.getLogger(NhapHangHoa.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(NhapHangHoa.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    // Thêm từng hàng hóa vào arrayList
    public void nhapTungHangHoa(String maHangHoa, String tenHangHoa, int giaBan, int giaVon, int tonKho
            , String nhomHangHoa, int dinhMucTonItNhat, int dinhMucTonNhieuNhat){
       
        arl.add( new HangHoa(maHangHoa, tenHangHoa, giaBan, giaVon, tonKho
            , nhomHangHoa, dinhMucTonItNhat, dinhMucTonNhieuNhat));

    }
    
    
    public ArrayList<HangHoa> getArl(){
        return this.arl;
    }
    
    // Tạo phiếu nhập
    public void taoPhieuNhap(String maPhieuNhap, String maNhaCungCap,
                    int tongTien, int giaGiam, int tienDaTra, int conNo, String thoiGian, String ghiChu){
    
        PhieuNhapHang phieuNhapHang = new PhieuNhapHang(maPhieuNhap, maNhaCungCap,
            tongTien, giaGiam, tienDaTra, conNo, thoiGian, ghiChu);
        TruyVanDuLieu queryData;
        try {
            // Kết nối cơ sở dữ liệu
            queryData = new TruyVanDuLieu();
            
            //Thực hiện câu truy vấn chèn record vào csdl
            //queryData.insertProduct(hangHoa);
            
            // Đóng kết nối cơ sở dữ liệu khi chèn xong
            queryData.closeDatabase();
        } catch (SQLException ex) {
            System.out.println("Chèn record không thành công");
        } catch (ClassNotFoundException ex) {
            System.out.println("Chèn record không thành công");
        }
    }
    
    
    // Tạo chi tiết phiếu nhập
    public void taoChiTietPhieuNhap(ArrayList<String> arlMaHangHoa, String maPhieuNhap){
        
        TruyVanDuLieu queryData;
        try {
            // Kết nối cơ sở dữ liệu
            queryData = new TruyVanDuLieu();
            
            for(int i = 0; i < arlMaHangHoa.size(); i ++ ){
            
                //Thực hiện câu truy vấn chèn record vào csdl
                //queryData.insertChiTietPhieuNhap(new ChiTietPhieuNhapHang(arlMaHangHoa.get(i), maPhieuNhap));
            }
            
            // Đóng kết nối cơ sở dữ liệu khi chèn xong
            queryData.closeDatabase();
        } catch (SQLException ex) {
            System.out.println("Chèn record không thành công");
        } catch (ClassNotFoundException ex) {
            System.out.println("Chèn record không thành công");
        }
    }
    
    
    
    //Lấy tất cả các hàng hóa trong csdl
    public ArrayList<HangHoa> layTatCaHangHoaTrongCSDL(){
        
        ArrayList<HangHoa> arlHangHoa = new ArrayList<>();
        try {
            // mở kết nối csdl
            truyVanDuLieu = new TruyVanDuLieu();
            
            // thực hiện câu truy vấn đưa kết quả vào result set
            ResultSet rs = truyVanDuLieu.selectData(CauTruyVan.selectedStatement("hanghoa"));
            while(rs.next()){
                arlHangHoa.add(new HangHoa(rs));
            }
            truyVanDuLieu.closeDatabase();
            
        } catch (SQLException ex) {
            Logger.getLogger(NhapHangHoa.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(NhapHangHoa.class.getName()).log(Level.SEVERE, null, ex);
        }

        return arlHangHoa;
    }
    
    
    
    
    // Hàm tự động tạo mã phiếu nhập
    public String taoMaPhieuNhap(){
        
        return null;
    }
    
    
    
    
    
    // Hàm tự động tạo mã hàng hóa
    public String taoMaHangHoa(){
        
        // Mở csdl để lấy mã sản phẩm cuối cùng
        HangHoa hangHoa;
        TruyVanDuLieu queryData;
        try {
            // Kết nối cơ sở dữ liệu
            queryData = new TruyVanDuLieu();
                
            // Đóng kết nối cơ sở dữ liệu khi chèn xong
            queryData.closeDatabase();
        } catch (SQLException ex) {
            System.out.println("Chèn record không thành công");
        } catch (ClassNotFoundException ex) {
            System.out.println("Chèn record không thành công");
        }
        return null;
    }
    
    
    
    public static void main(String[] args){

    }
}
