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
import DatabaseConnection.ConnectionUtils;
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
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author phamh
 */




public class NhapHangHoa {
    
    public BangHangHoa bangHangHoa;
    public BangNhaCungCap bangNhaCungCap;
    public BangPhieuNhapHang bangPhieuNhapHang;
    public BangChiTietPhieuNhapHang bangChiTietPhieuNhapHang;
    public BangCongNoNhaCungCap bangCongNoNhaCungCap;
    public BangChiTietCongNoNhaCungCap bangChiTietCongNoNhaCungCap;
    
    
    public JComboBox<String> comboBox;
    public Connection connection;
    public javax.swing.JTable bangHangNhap;
    public DefaultTableModel model;
    public JLabel mLabelMaPhieuNhap;
    public JLabel mLabelTongTien;
    public JTextField mTextFieldGiamGia;
    public JTextField mTextFieldGhiChu;
    public JTextField mTextFieldTraTruoc;
    public JLabel mLabelConNo;
    
    public ArrayList<HangNhap> dsHangNhapTrongBang;
    public ArrayList<HangNhap> dsHangNhapBiTrung;
    public ArrayList<NhaCungCap> dsNhaCungCapTrongCSDL;
   
    public String ghiChu = "";
    public int giamGia = 0;
    public int traTruoc = 0;
    public int tongTien = 0;
    public int conNo = 0;
    public String maPhieuNhap;
    public DocFile docFile;
   
    
    public NhapHangHoa(JTable table, JComboBox comboBox, JLabel jLabelMaPhieuNhap, JLabel jLabelTongTien
                        ,JLabel jLabelConNo, JTextField jTextFieldGiamGia, JTextField jTextFieldTraTruoc, JTextField jTextFieldGhiChu){
        this.bangHangNhap = table;
        try {
            connection = new ConnectionUtils().getMySQLConnection();
            this.comboBox = comboBox;
            model = (DefaultTableModel) bangHangNhap.getModel();
            
            this.mLabelMaPhieuNhap = jLabelMaPhieuNhap;
            this.mLabelTongTien = jLabelTongTien;
            this.mTextFieldGiamGia = jTextFieldGiamGia;
            this.mTextFieldTraTruoc = jTextFieldTraTruoc;
            this.mLabelConNo = jLabelConNo;
            this.mTextFieldGhiChu = jTextFieldGhiChu;
            
            this.dsHangNhapTrongBang = new ArrayList<>();
            bangHangHoa = new BangHangHoa(new ConnectionUtils().getMySQLConnection());
            bangNhaCungCap = new BangNhaCungCap(new ConnectionUtils().getMySQLConnection());
            bangPhieuNhapHang = new BangPhieuNhapHang(new ConnectionUtils().getMySQLConnection());
            bangChiTietPhieuNhapHang = new BangChiTietPhieuNhapHang(new ConnectionUtils().getMySQLConnection());
            bangCongNoNhaCungCap = new BangCongNoNhaCungCap(new ConnectionUtils().getMySQLConnection());
            bangChiTietCongNoNhaCungCap = new BangChiTietCongNoNhaCungCap(new ConnectionUtils().getMySQLConnection());
            System.out.println("\nKet noi co so du lieu thanh cong");
        } catch (SQLException ex) {
            System.out.println("\nKet noi co so du lieu khong thanh cong");
        } catch (ClassNotFoundException ex) {
            System.out.println("\nKet noi co so du lieu khong thanh cong");
        }
              
    }
    
    // Khởi tạo giá trị ban đầu cho các Label và TextField
    
    public void capNhatManHinhPhieuNhapHang(){
        
        maPhieuNhap = this.bangPhieuNhapHang.taoMaPhieuNhap();
        this.mLabelMaPhieuNhap.setText(maPhieuNhap);
        this.ghiChu = this.mTextFieldGhiChu.getText();
        
        this.mTextFieldGhiChu.setText("");
        this.mTextFieldGiamGia.setText(String.valueOf(0));
        this.mTextFieldTraTruoc.setText(String.valueOf(0));
        
        tongTien = this.layTongTien();
        giamGia = Integer.parseInt(this.mTextFieldGiamGia.getText());
        traTruoc = Integer.parseInt(this.mTextFieldTraTruoc.getText());
        conNo = tongTien - giamGia - traTruoc;
        
        this.mLabelTongTien.setText(String.valueOf(tongTien));
        this.mLabelConNo.setText(String.valueOf(conNo));
        
    }
    
    
    //lấy danh sách nhà cung cấp trong csdl
    public void layDSNhaCungCap(){
        this.dsNhaCungCapTrongCSDL = bangNhaCungCap.layTatCaNhaCungCapTrongCSDL();
    }
    
    
    // Thêm danh sach nhà cung cấp vào comboBox
    public void themNCCVaoComboBox(){
        this.layDSNhaCungCap();
        if(dsNhaCungCapTrongCSDL.size() > 0 || dsNhaCungCapTrongCSDL == null){
            for(int i = 0; i < dsNhaCungCapTrongCSDL.size(); i++){
                comboBox.addItem(dsNhaCungCapTrongCSDL.get(i).mTenNhaCungCap);
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
    public void capNhatDanhSachHangNhapTrongTable(){
        this.dsHangNhapTrongBang = null;
        this.dsHangNhapTrongBang = new ArrayList<>();
        for(int i = 0; i < model.getRowCount(); i++){
            String maHangNhap = model.getValueAt(i, 0).toString();
            String tenHangNhap = model.getValueAt(i, 1).toString();
            String nhomHangNhap = model.getValueAt(i, 2).toString();
            int soLuong = Integer.parseInt(model.getValueAt(i, 4).toString());
            int donGia = Integer.parseInt(model.getValueAt(i, 3).toString());
            
            HangNhap hangNhap = new HangNhap(maHangNhap, tenHangNhap, donGia, soLuong, nhomHangNhap);
            this.dsHangNhapTrongBang.add(hangNhap);
        }
        
    }
    
    
    
    
    //Đưa hàng nhập vào trong csdl Hàng Hóa
    public void choHangNhapVaoCSDL(){
        
        ArrayList<HangHoa> arlHangHoa = new ArrayList<>();
        if(this.dsHangNhapTrongBang.size() > 0){
            for(int i = 0; i < this.dsHangNhapTrongBang.size(); i ++){
                // tạo hàng hóa từ hàng nhập
                arlHangHoa.add(new HangHoa(this.dsHangNhapTrongBang.get(i)));
            }
        }
        bangHangHoa.themDuLieuVaoHangHoa(arlHangHoa);
    }
    
    
    // Tạo mã hàng nhập
    public String taoMaHangNhap(){
        String maHangNhap = null;
        if(this.dsHangNhapTrongBang.size() <= 0){
             maHangNhap = this.bangHangHoa.taoMaHangHoa();
        }
        else{
            String maHangNhapMoiNhat = this.model.getValueAt(this.bangHangNhap.getRowCount() - 1, 0).toString();
            maHangNhap = ControlUtils.taoMaHangHoa(maHangNhapMoiNhat);
        }
        return maHangNhap;
    }
    
    
    //Tạo chi tiết công nợ sau đó cập nhật lại công nợ nhà cung cấp
    public void capNhatCongNoVaChiTietCongNo(){
        ChiTietCongNoNhaCungCap chiTietCongNoNhaCungCap = new ChiTietCongNoNhaCungCap();
        chiTietCongNoNhaCungCap.mMaCongNoNhaCungCap = this.layMaCongNoNCCTrongComboBox();
        chiTietCongNoNhaCungCap.mMaPhieuNhap = this.maPhieuNhap;
        chiTietCongNoNhaCungCap.mTongNo = this.conNo;
        bangChiTietCongNoNhaCungCap.themChiTietCongNoNhaCungCap(chiTietCongNoNhaCungCap);
    }
    
    
    // Tạo chi tiết phiếu nhập hàng
    public void taoChiTietPhieuNhap(){
        
        for(int i = 0; i < this.dsHangNhapTrongBang.size(); i ++){
            ChiTietPhieuNhapHang chiTietPhieuNhapHang = new ChiTietPhieuNhapHang(this.dsHangNhapTrongBang.get(i).mMaHangHoa,
                                                            this.maPhieuNhap, this.dsHangNhapTrongBang.get(i).mSoLuong);
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
    
    
    //Tạo lại bảng hàng hóa và phiếu nhập hàng
    public void taoLai(){
        this.model.setRowCount(0);
        this.capNhatManHinhPhieuNhapHang();
    }
    
    //Lấy mã công nợ NCC trong ComboBox
    public String layMaCongNoNCCTrongComboBox(){
        // lấy mã nhà cung cấp từ bảng nhà cung cấp
        NhaCungCap ncc = this.layNCCTrongComboBox();
        return ncc.mMaCongNoNhaCungCap;
    }
    
    
    // Cập nhật lại công nợ của nhà cung cấp
    
    
    //Lấy tổng tiền của các hàng nhập trong bảng
    public int layTongTien(){
        
        int tongTien = 0;
        this.capNhatDanhSachHangNhapTrongTable();
        if(this.dsHangNhapTrongBang.size() > 0 || this.dsHangNhapTrongBang == null){
            for(int i = 0; i < this.dsHangNhapTrongBang.size(); i ++){
                tongTien += (this.dsHangNhapTrongBang.get(i).mGiaMua * this.dsHangNhapTrongBang.get(i).mSoLuong);
            }
        }
        return tongTien;
    }
    
    // Tạo phiếu nhập hàng
    public void taoPhieuNhapHang(){
        PhieuNhapHang phieuNhapHang = new PhieuNhapHang();


        phieuNhapHang.mTienDaTra = this.traTruoc;
        phieuNhapHang.mMaPhieuNhap = this.maPhieuNhap;
        phieuNhapHang.mMaNhaCungCap = this.layMaNCCTrongComboBox();
        phieuNhapHang.mGhiChu = this.ghiChu;
        phieuNhapHang.mGiaGiam = this.giamGia;
        phieuNhapHang.mThoiGian = ControlUtils.layThoiGian();
        phieuNhapHang.mTongTien = this.tongTien;
        bangPhieuNhapHang.themPhieuNhapHang(phieuNhapHang);
    }
    
    
    
    // Tạo mới nhà cung cấp(trước hết phải tạo công nợ của nhà cung cấp)
    public void taoNhaCungCap(String maNhaCungCap, String tenNhaCungCap, String maCongNo,
           String nhomNhaCungCap, String diaChi, String email,
           String ghiChu, int tongMua){
       
        CongNoNhaCungCap congNoNhaCungCap = new CongNoNhaCungCap(maCongNo, "", 0 , 0);
        
        //thêm bảng công nợ nhà cung cấp vào csdl
        bangCongNoNhaCungCap.themCongNoNhaCungCap(congNoNhaCungCap);
        
        NhaCungCap nhaCungCap = new NhaCungCap(maNhaCungCap, tenNhaCungCap, nhomNhaCungCap, maCongNo
                                            , diaChi, email, ghiChu);
        
        bangNhaCungCap.themNhaCungCap(nhaCungCap);
    }
    
    
    // khi nhấn nút lưu thì kiểm tra
    //kiểm tra có trùng mã hàng hóa khi nhập dữ liệu không
    // Nếu trùng thì hiện danh sách các hàng nhập bị trùng
    public  ArrayList<HangNhap>  kiemTraMaHangHoa(){
        ArrayList<HangHoa> arlHangHoa = bangHangHoa.layTatCaHangHoaTrongCSDL();
        
        
        //Danh sách các hàng nhập bị trùng mã sản phẩm
        ArrayList<HangNhap> hangNhapTrung = new ArrayList<>();
        for(int i = 0; i < this.dsHangNhapTrongBang.size(); i++){
            for(int j = 0; j < arlHangHoa.size(); j ++){
                if(this.dsHangNhapTrongBang.get(i).mMaHangHoa.equals(arlHangHoa.get(j).mMaHangHoa)){
                    hangNhapTrung.add(this.dsHangNhapTrongBang.get(i));
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
