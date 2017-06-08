/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import entities.HangHoa;
import entities.HangNhap;
import entities.HoaDonBanHang;
import entities.KhachHang;
import entities.NhaCungCap;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author phamh
 */
public class GhiFile {
    private XSSFSheet  sheet;
    private int sheetNumber;
    XSSFWorkbook  workbook;

    public GhiFile(){
      workbook = new XSSFWorkbook();
      sheet = workbook.createSheet();
    }
    
    
    // tạo file và ghi dữ liệu vào file
    public void taoFileHangHoa(ArrayList<HangHoa> arlHangHoa){
        
        XSSFRow rowTitle = sheet.createRow(0);
        rowTitle.createCell(0).setCellValue("Mã sản phẩm");
        rowTitle.createCell(1).setCellValue("Tên sản phẩm");
        rowTitle.createCell(2).setCellValue("Nhóm sản phẩm");
        rowTitle.createCell(3).setCellValue("Giá bán");
        rowTitle.createCell(4).setCellValue("Giá vốn");
        rowTitle.createCell(5).setCellValue("Tồn kho");
        rowTitle.createCell(6).setCellValue("Định mức tồn ít nhất");
        rowTitle.createCell(7).setCellValue("Định mức tồn nhiều nhất");
        
        int rowNum = sheet.getLastRowNum();
        for(int i = 0; i < arlHangHoa.size(); i++){
            rowNum ++;
            XSSFRow row = sheet.createRow(rowNum);
            HangHoa hh = arlHangHoa.get(i);
            
            row.createCell(0).setCellValue(hh.getMaHangHoa());
            row.createCell(1).setCellValue(hh.getTenHangHoa());
            row.createCell(2).setCellValue(hh.getNhomHangHoa());
            row.createCell(3).setCellValue(hh.getGiaBan());
            row.createCell(4).setCellValue(hh.getGiaBan() - (hh.getGiaBan() * 40 / 100 ));
            row.createCell(5).setCellValue(hh.getTonKho());
            row.createCell(6).setCellValue(hh.getDinhMucTonItNhat());
            row.createCell(7).setCellValue(hh.getDinhMucTonNhieuNhat());
        }
        File myFile = new File(path());
        FileOutputStream os = null;
        try {
            
            // Khởi tạo Stream để ghi file
            os = new FileOutputStream(myFile);
            
            //Ghi dữ liệu từ workbook vào 
            workbook.write(os);
            System.out.println("Viết dữ liệu vào file excel thành công");
            
            //Đóng workbook
            workbook.close();
            
            // Đóng stream
            os.close();
            
        } catch (FileNotFoundException ex) {
            
            Logger.getLogger(GhiFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            
            Logger.getLogger(GhiFile.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
    //tạo file bảng giá hàng hóa
     // tạo file và ghi dữ liệu vào file
    public void taoFileBangGiaHangHoa(ArrayList<HangHoa> arlHangHoa){
        
        XSSFRow rowTitle = sheet.createRow(0);
        rowTitle.createCell(0).setCellValue("Mã sản phẩm");
        rowTitle.createCell(1).setCellValue("Tên sản phẩm");
        rowTitle.createCell(2).setCellValue("Nhóm sản phẩm");
        rowTitle.createCell(3).setCellValue("Giá vốn");
        rowTitle.createCell(4).setCellValue("Giá bán");

        
        int rowNum = sheet.getLastRowNum();
        for(int i = 0; i < arlHangHoa.size(); i++){
            rowNum ++;
            XSSFRow row = sheet.createRow(rowNum);
            HangHoa hh = arlHangHoa.get(i);
            
            row.createCell(0).setCellValue(hh.getMaHangHoa());
            row.createCell(1).setCellValue(hh.getTenHangHoa());
            row.createCell(2).setCellValue(hh.getNhomHangHoa());
            row.createCell(3).setCellValue(hh.getGiaBan());
            row.createCell(4).setCellValue(hh.mGiaVon);

        }
        File myFile = new File(path());
        FileOutputStream os = null;
        try {
            
            // Khởi tạo Stream để ghi file
            os = new FileOutputStream(myFile);
            
            //Ghi dữ liệu từ workbook vào 
            workbook.write(os);
            System.out.println("Viết dữ liệu vào file excel thành công");
            
            //Đóng workbook
            workbook.close();
            
            // Đóng stream
            os.close();
            
        } catch (FileNotFoundException ex) {
            
            Logger.getLogger(GhiFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            
            Logger.getLogger(GhiFile.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        
    }
    
    
    //tạo danh sách các hóa đơn
    public void taoDanhSachHoaDoa(ArrayList<HoaDonBanHang> dsHoaDon){
        XSSFRow rowTitle = sheet.createRow(0);
        rowTitle.createCell(0).setCellValue("Mã hóa đơn");
        rowTitle.createCell(1).setCellValue("Tổng tiền");
        rowTitle.createCell(2).setCellValue("Giảm giá");
        rowTitle.createCell(3).setCellValue("Khách đã trả");
        rowTitle.createCell(4).setCellValue("Thời gian");
        rowTitle.createCell(5).setCellValue("Ghi chú");
        
        int rowNum = sheet.getLastRowNum();
        for(int i = 0; i < dsHoaDon.size(); i++){
            rowNum ++;
            XSSFRow row = sheet.createRow(rowNum);
            HoaDonBanHang hd = dsHoaDon.get(i);
            
            row.createCell(0).setCellValue(hd.mMaHoaDonBanHang);
            row.createCell(1).setCellValue(hd.mTongTien);
            row.createCell(2).setCellValue(hd.mGiaGiam);
            row.createCell(3).setCellValue(hd.mKhachDaTra);
            row.createCell(4).setCellValue(hd.mThoiGian);
            row.createCell(5).setCellValue(hd.mGhiChu);
            

        }
        File myFile = new File(path());
        FileOutputStream os = null;
        try {
            
            // Khởi tạo Stream để ghi file
            os = new FileOutputStream(myFile);
            
            //Ghi dữ liệu từ workbook vào 
            workbook.write(os);
            System.out.println("Viết dữ liệu vào file excel thành công");
            
            //Đóng workbook
            workbook.close();
            
            // Đóng stream
            os.close();
            
        } catch (FileNotFoundException ex) {
            
            Logger.getLogger(GhiFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            
            Logger.getLogger(GhiFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    //Mở hộp thoại và nhập tên file cần lưu trả về đường dẫn lưu file
    // được gọi trong taoFileHangHoa, taoFileNhapHangHoa
    public String path(){
        //Tạo file chooser
        final JFileChooser fc = new JFileChooser();
        //fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        
        //Mở mở hộp thoai SAVE trên mComponent
        int returnVal = fc.showSaveDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION){
            File file = fc.getSelectedFile();
            System.out.println(fc.getSelectedFile() + ".xlsx");
            return fc.getSelectedFile() + ".xlsx";
        }
        return null;
    }
    
    
    
    // Tạo file chứa hàng hóa cần nhập
    public void taoFileHangNhap(ArrayList<HangHoa> arlHangNhap){
        
        
        // Đặt tên cột
        XSSFRow rowTitle = sheet.createRow(0);
        rowTitle.createCell(0).setCellValue("Mã sản phẩm");
        rowTitle.createCell(1).setCellValue("Tên sản phẩm");
        rowTitle.createCell(2).setCellValue("Nhóm sản phẩm");
        rowTitle.createCell(3).setCellValue("Giá mua");
        rowTitle.createCell(4).setCellValue("Số lượng");

        
        int rowNum = sheet.getLastRowNum();
        for(int i = 0; i < arlHangNhap.size(); i++){
            rowNum ++;
            XSSFRow row = sheet.createRow(rowNum);
            
            HangHoa hn = arlHangNhap.get(i);
            
            row.createCell(0).setCellValue(hn.mMaHangHoa);
            row.createCell(1).setCellValue(hn.mTenHangHoa);
            row.createCell(2).setCellValue(hn.mNhomHangHoa);
            row.createCell(3).setCellValue(hn.mGiaVon);
            row.createCell(4).setCellValue(3); // số lượng cứng
        }
        File myFile = new File(path());
        FileOutputStream os = null;
        try {
            
            // Khởi tạo Stream để ghi file
            os = new FileOutputStream(myFile);
            
            //Ghi dữ liệu từ workbook vào 
            workbook.write(os);
            System.out.println("Viết dữ liệu vào file excel thành công");
            
            //Đóng workbook
            workbook.close();
            
            // Đóng stream
            os.close();
            
        } catch (FileNotFoundException ex) {
            
            Logger.getLogger(GhiFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            
            Logger.getLogger(GhiFile.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }
    
    // Tạo file chứa hàng hóa cần nhập
    public void taoFileNhaCungCap(ArrayList<NhaCungCap> dsNhaCungCap){
        
        
        // Đặt tên cột
        XSSFRow rowTitle = sheet.createRow(0);
        rowTitle.createCell(0).setCellValue("Mã nhà cung cấp");
        rowTitle.createCell(1).setCellValue("Tên nhà cung cấp");
        rowTitle.createCell(2).setCellValue("Nhóm nhà cung cấp");
        rowTitle.createCell(3).setCellValue("Còn nợ");
        rowTitle.createCell(4).setCellValue("Email");
        rowTitle.createCell(4).setCellValue("Địa chỉ");

        int rowNum = sheet.getLastRowNum();
        for(int i = 0; i < dsNhaCungCap.size(); i++){
            rowNum ++;
            XSSFRow row = sheet.createRow(rowNum);
            
            NhaCungCap ncc = dsNhaCungCap.get(i);
            
            row.createCell(0).setCellValue(ncc.mMaNhaCungCap);
            row.createCell(1).setCellValue(ncc.mTenNhaCungCap);
            row.createCell(2).setCellValue(ncc.mNhomNhaCungCap);
            // nợ hiện tại
            row.createCell(3).setCellValue(ncc.mMaCongNoNhaCungCap);
            row.createCell(4).setCellValue(ncc.mEmail); 
            row.createCell(5).setCellValue(ncc.mDiaChi);
        }
        File myFile = new File(path());
        FileOutputStream os = null;
        try {
            
            // Khởi tạo Stream để ghi file
            os = new FileOutputStream(myFile);
            
            //Ghi dữ liệu từ workbook vào 
            workbook.write(os);
            System.out.println("Viết dữ liệu vào file excel thành công");
            
            //Đóng workbook
            workbook.close();
            
            // Đóng stream
            os.close();
            
        } catch (FileNotFoundException ex) {
            
            Logger.getLogger(GhiFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            
            Logger.getLogger(GhiFile.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }
    
    
    // tạo file danh sách khách hang
    // Tạo file chứa hàng hóa cần nhập
    public void taoFileKhachHang(ArrayList<KhachHang> dsKhachHang){
        
        
        // Đặt tên cột
        XSSFRow rowTitle = sheet.createRow(0);
        rowTitle.createCell(0).setCellValue("Mã khách hàng");
        rowTitle.createCell(1).setCellValue("Tên tên khách hàng");
        rowTitle.createCell(2).setCellValue("Nợ hiện tại");
        rowTitle.createCell(3).setCellValue("Điện thoại");
        rowTitle.createCell(4).setCellValue("Ngày sinh");


        int rowNum = sheet.getLastRowNum();
        for(int i = 0; i < dsKhachHang.size(); i++){
            rowNum ++;
            XSSFRow row = sheet.createRow(rowNum);
            
            KhachHang kh = dsKhachHang.get(i);
            
            row.createCell(0).setCellValue(kh.mMaKhachHang);
            row.createCell(1).setCellValue(kh.mTenKhachHang);
            row.createCell(2).setCellValue(kh.mMaCongNoKhachHang);
            // nợ hiện tại
            row.createCell(3).setCellValue(kh.mDienThoai);
            row.createCell(4).setCellValue(kh.mNgaySinh); 
        }
        File myFile = new File(path());
        FileOutputStream os = null;
        try {
            
            // Khởi tạo Stream để ghi file
            os = new FileOutputStream(myFile);
            
            //Ghi dữ liệu từ workbook vào 
            workbook.write(os);
            System.out.println("Viết dữ liệu vào file excel thành công");
            
            //Đóng workbook
            workbook.close();
            
            // Đóng stream
            os.close();
            
        } catch (FileNotFoundException ex) {
            
            Logger.getLogger(GhiFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            
            Logger.getLogger(GhiFile.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }
}
