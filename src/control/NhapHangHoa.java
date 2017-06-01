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
import database.ConnectionUtils;
import entities.ChiTietCongNoNhaCungCap;
import entities.ChiTietPhieuNhapHang;
import entities.CongNoNhaCungCap;
import entities.HangHoa;
import entities.HangNhap;
import entities.NhaCungCap;
import entities.PhieuNhapHang;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author phamh
 */




public class NhapHangHoa {
    public javax.swing.JTable tb_screennhaphang;
    public DefaultTableModel model;
    public BangHangHoa bangHangHoa;
    public BangNhaCungCap bangNhaCungCap;
    public BangPhieuNhapHang bangPhieuNhapHang;
    public BangChiTietPhieuNhapHang bangChiTietPhieuNhapHang;
    public BangCongNoNhaCungCap bangCongNoNhaCungCap;
    public BangChiTietCongNoNhaCungCap bangChiTietCongNoNhaCungCap;
    public JComboBox<String> comboBox;
    public Connection connection;
    
    public DocFile docFile;
    
    
    public NhapHangHoa(JTable table, JComboBox comboBox){
        this.tb_screennhaphang = table;
        try {
            connection = new ConnectionUtils().getMySQLConnection();
            this.comboBox = comboBox;
            model = (DefaultTableModel) tb_screennhaphang.getModel();
            bangHangHoa = new BangHangHoa(connection);
            bangNhaCungCap = new BangNhaCungCap(connection);
            bangPhieuNhapHang = new BangPhieuNhapHang(connection);
            bangChiTietPhieuNhapHang = new BangChiTietPhieuNhapHang(connection);
            bangCongNoNhaCungCap = new BangCongNoNhaCungCap(connection);
            bangChiTietCongNoNhaCungCap = new BangChiTietCongNoNhaCungCap(connection);
            System.out.println("\nKet noi co so du lieu thanh cong");
        } catch (SQLException ex) {
            System.out.println("\nKet noi co so du lieu khong thanh cong");
        } catch (ClassNotFoundException ex) {
            System.out.println("\nKet noi co so du lieu khong thanh cong");
        }
              
    }
    
    // Thêm danh sach nhà cung cấp vào comboBox
    public void themNCCVaoComboBox(){
        ArrayList<NhaCungCap> arlTenNhaCungCap = bangNhaCungCap.layTatCaNhaCungCapTrongCSDL();
        if(arlTenNhaCungCap.size() > 0){
            for(int i = 0; i < arlTenNhaCungCap.size(); i++){
                comboBox.addItem(arlTenNhaCungCap.get(i).mTenNhaCungCap);
            }
            comboBox.setSelectedIndex(0);
        }
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
            int soLuong = Integer.parseInt(model.getValueAt(i, 4).toString());
            int donGia = Integer.parseInt(model.getValueAt(i, 3).toString());
            
            HangNhap hangNhap = new HangNhap(maHangNhap, tenHangNhap, donGia, soLuong, nhomHangNhap);
            arlHangNhap.add(hangNhap);
        }
        return arlHangNhap;
    }
    
    
    
    
    //Đưa hàng nhập vào trong csdl Hàng Hóa
    public void choHangNhapVaoCSDL(){
        ArrayList<HangNhap> arlHangNhap = this.layDanhSachHangNhapTrongTable();
        ArrayList<HangHoa> arlHangHoa = new ArrayList<>();
        if(arlHangNhap.size() > 0){
            for(int i = 0; i < arlHangNhap.size(); i ++){
                // tạo hàng hóa từ hàng nhập
                arlHangHoa.add(new HangHoa(arlHangNhap.get(i)));
            }
        }
        bangHangHoa.themDuLieuVaoHangHoa(arlHangHoa);
    }
    
    // Tạo phiếu nhập
    public void taoPhieuNhap(String maPhieuNhap, String maNhaCungCap,
                    int tongTien, int giaGiam, int tienDaTra, int conNo, String thoiGian, String ghiChu){
    
        PhieuNhapHang phieuNhapHang = new PhieuNhapHang(maPhieuNhap, maNhaCungCap,
            tongTien, giaGiam, tienDaTra, conNo, thoiGian, ghiChu);
        bangPhieuNhapHang.themPhieuNhapHang(phieuNhapHang);
    }
    
    // Tạo mã hàng nhập
    public String taoMaHangNhap(){
        String maHangNhap = null;
        if(this.layDanhSachHangNhapTrongTable().size() <= 0){
             maHangNhap = this.bangHangHoa.taoMaHangHoa();
        }
        else{
            String maHangNhapMoiNhat = this.model.getValueAt(this.tb_screennhaphang.getRowCount() - 1, 0).toString();
            maHangNhap = ControlUtils.taoMaHangHoa(maHangNhapMoiNhat);
        }
        return maHangNhap;
    }
    
    
    //Cập nhật lại công nợ và chi tiết công nợ
    public void capNhatCongNoVaChiTietCongNo(String maPhieuNhap, int conNo){
        ChiTietCongNoNhaCungCap chiTietCongNoNhaCungCap = new ChiTietCongNoNhaCungCap();
        chiTietCongNoNhaCungCap.mMaCongNoNhaCungCap = this.layMaCongNoNCCTrongComboBox();
        chiTietCongNoNhaCungCap.mMaPhieuNhap = maPhieuNhap;
        chiTietCongNoNhaCungCap.mTongNo = conNo;
        bangChiTietCongNoNhaCungCap.themChiTietCongNoNhaCungCap(chiTietCongNoNhaCungCap);
        
    }
    
    
    // Tạo chi tiết phiếu nhập hàng
    public void taoChiTietPhieuNhap(String maPhieuNhap){
        ArrayList<HangNhap> arlHangNhap = this.layDanhSachHangNhapTrongTable();
        for(int i = 0; i < arlHangNhap.size(); i ++){
            ChiTietPhieuNhapHang chiTietPhieuNhapHang = new ChiTietPhieuNhapHang(arlHangNhap.get(i).mMaHangHoa,
                                                            maPhieuNhap, arlHangNhap.get(i).mSoLuong);
            bangChiTietPhieuNhapHang.themChiTietPhieuNhapHang(chiTietPhieuNhapHang);
        }
    }
    
    // Lấy mã nhà cung cấp từ comboBox
    public String layMaNCCTrongComboBox(){
        // lấy mã nhà cung cấp từ bảng nhà cung cấp
        ArrayList<NhaCungCap> arlNCC = bangNhaCungCap.layTatCaNhaCungCapTrongCSDL();
        for(int i = 0; i < arlNCC.size(); i ++){
            NhaCungCap ncc = arlNCC.get(i);
            if(((String)comboBox.getSelectedItem()).equals(ncc.mTenNhaCungCap))
                return ncc.mMaNhaCungCap;
        }
        return null;
    }
    
    public NhaCungCap layNCCTrongComboBox(){
        ArrayList<NhaCungCap> arlNCC = bangNhaCungCap.layTatCaNhaCungCapTrongCSDL();
        for(int i = 0; i < arlNCC.size(); i ++){
            NhaCungCap ncc = arlNCC.get(i);
            if(((String)comboBox.getSelectedItem()).equals(ncc.mTenNhaCungCap))
                return ncc;
        }
        return null;
    }
    
    //Lấy mã công nợ trong ComboBox
    public String layMaCongNoNCCTrongComboBox(){
        // lấy mã nhà cung cấp từ bảng nhà cung cấp
        return bangNhaCungCap.layNhaCungCap((String)comboBox.getSelectedItem()).mMaCongNoNhaCungCap;
    }
    
    
    // Cập nhật lại công nợ của nhà cung cấp
    
    
    //Lấy tổng tiền của các hàng nhập trong bảng
    public int layTongTien(){
        ArrayList<HangNhap> arlHangNhap = this.layDanhSachHangNhapTrongTable();
        int tongTien = 0;
        if(arlHangNhap.size() > 0){
            for(int i = 0; i < arlHangNhap.size(); i ++){
                tongTien += (arlHangNhap.get(i).mGiaMua * arlHangNhap.get(i).mSoLuong);
            }
        }
        return tongTien;
    }
    
    
    
    // Tạo mới nhà cung cấp(trước hết phải tạo công nợ của nhà cung cấp)
    public void taoNhaCungCap(String maNhaCungCap, String tenNhaCungCap, String maCongNo,
           String nhomNhaCungCap, String diaChi, String email,
           String ghiChu, int tongMua){
       
        CongNoNhaCungCap congNoNhaCungCap = new CongNoNhaCungCap(maCongNo, "", 0 , 0);
        
        //thêm bảng công nợ nhà cung cấp vào csdl
        bangCongNoNhaCungCap.themCongNoNhaCungCap(congNoNhaCungCap);
        
        NhaCungCap nhaCungCap = new NhaCungCap(maNhaCungCap, tenNhaCungCap, nhomNhaCungCap, maCongNo
                                            , diaChi, email, ghiChu, tongMua);
        
        bangNhaCungCap.themNhaCungCap(nhaCungCap);
    }
    
    // Tạo chi tiết công nợ nhà cung cấp
     public void taoChiTietCongNo(String maNhaCungCap, String maPhieuNhap, int tongNo){
         ChiTietCongNoNhaCungCap chiTietCongNoNhaCungcap = new ChiTietCongNoNhaCungCap(maNhaCungCap, maPhieuNhap, tongNo);
         bangChiTietCongNoNhaCungCap.themChiTietCongNoNhaCungCap(chiTietCongNoNhaCungcap);
     }
    
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
