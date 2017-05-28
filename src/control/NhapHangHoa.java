/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import database.BangChiTietCongNoNhaCungCap;
import database.BangChiTietPhieuNhapHang;
import database.BangCongNoNhaCungCap;
import database.BangHangHoa;
import database.BangNhaCungCap;
import database.BangPhieuNhapHang;
import entities.ChiTietPhieuNhapHang;
import entities.HangHoa;
import entities.HangNhap;
import entities.NhaCungCap;
import entities.PhieuNhapHang;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.ComboBox;
import javax.swing.JComboBox;
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
    private BangChiTietPhieuNhapHang bangChiTietPhieuNhapHang;
    private BangCongNoNhaCungCap bangCongNoNhaCungCap;
    private BangChiTietCongNoNhaCungCap bangChiTietCongNoNhaCungCap;
    private JComboBox<String> comboBox;
    
    public DocFile docFile;
    
    
    public NhapHangHoa(JTable table, JComboBox comboBox){
        this.tb_screennhaphang = table;
        this.comboBox = comboBox;
        model = (DefaultTableModel) tb_screennhaphang.getModel();
        bangHangHoa = new BangHangHoa();
        bangNhaCungCap = new BangNhaCungCap();
        bangPhieuNhapHang = new BangPhieuNhapHang();
        bangChiTietPhieuNhapHang = new BangChiTietPhieuNhapHang();
        bangCongNoNhaCungCap = new BangCongNoNhaCungCap();
        bangChiTietCongNoNhaCungCap = new BangChiTietCongNoNhaCungCap();      
    }
    
    // Thêm danh sach nhà cung cấp vào comboBox
    public void themNCCVaoComboBox(){
        ArrayList<String> arlTenNhaCungCap = new ArrayList<>();
        arlTenNhaCungCap.add("Chọn nhà cung cấp");
        for(int i = 0; i < arlTenNhaCungCap.size(); i++){
            comboBox.addItem(arlTenNhaCungCap.get(i));
        }
        comboBox.setSelectedIndex(0);
    }
    
    //Lấy nhà cung cấp đã chọn trong comboBox
    public NhaCungCap layNhaCungCapDaChon(){
        
        return null;
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
    
    
    //Đưa hàng nhập vào trong csdl Hàng Hóa
    public void choHangNhapVaoCSDL(){
        ArrayList<HangNhap> arlHangNhap = this.layDanhSachHangNhapTrongTable();
        ArrayList<HangHoa> arlHangHoa = new ArrayList<>();
        
        for(int i = 0; i < arlHangNhap.size(); i ++){
            // tạo hàng hóa từ hàng nhập
            arlHangHoa.add(new HangHoa(arlHangNhap.get(i)));
        }
    }
    
    // Tạo phiếu nhập
    public void taoPhieuNhap(String maPhieuNhap, String maNhaCungCap,
                    int tongTien, int giaGiam, int tienDaTra, int conNo, String thoiGian, String ghiChu){
    
        PhieuNhapHang phieuNhapHang = new PhieuNhapHang(maPhieuNhap, maNhaCungCap,
            tongTien, giaGiam, tienDaTra, conNo, thoiGian, ghiChu);
        bangPhieuNhapHang.themPhieuNhapHang(phieuNhapHang);
    }
    
    
    // Tạo chi tiết phiếu nhập hàng
    public void taoChiTietPhieuNhap(ArrayList<HangNhap> arlHangNhap, String maPhieuNhap){
        for(int i = 0; i < arlHangNhap.size(); i ++){
            ChiTietPhieuNhapHang chiTietPhieuNhapHang = new ChiTietPhieuNhapHang(arlHangNhap.get(i).mMaHangHoa,
                                                            maPhieuNhap, arlHangNhap.get(i).mSoLuong);
            bangChiTietPhieuNhapHang.themChiTietPhieuNhapHang(chiTietPhieuNhapHang);
        }
    }
    
    
    //Tạo Nhà cung cấp
    

    
    
    
    
    
    
     
    
    // khi nhấn nút lưu thì kiểm tra
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
    
    // khi nhấn nút lưu thì kiểm tra
    //kiểm tra có trùng mã hàng hóa khi nhập dữ liệu không
    // Nếu trùng thì hiện danh sách các hàng nhập bị trùng
    public  ArrayList<PhieuNhapHang>  kiemTraMaPhieuNhap(PhieuNhapHang phieuNhapHang){
        ArrayList<PhieuNhapHang> arlPhieuNhapHang = bangPhieuNhapHang.layTatCaPhieuNhapHangTrongCSDL();
       
        //Danh sách các hàng nhập bị trùng mã sản phẩm
        ArrayList<PhieuNhapHang> phieuNhapTrung = new ArrayList<>();
        for(int i = 0; i < arlPhieuNhapHang.size(); i++){
            if(phieuNhapHang.mMaPhieuNhap.equals(arlPhieuNhapHang.get(i).mMaPhieuNhap));
                phieuNhapTrung.add(arlPhieuNhapHang.get(i));
        }
        return phieuNhapTrung;
    }
}
