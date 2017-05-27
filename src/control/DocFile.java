/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* Lớp này dùng để mở file excel có sẵn để lấy dữ liệu và ghi dữ liệu vào file excel có sẵn*/
package control;



import control.NhapHangHoa;
import entities.HangHoa;
import entities.HangNhap;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 *
 * @author phamh
 */
public class DocFile {
    
    private final int SP_MA_HANG_HOA = 0;
    private final int SP_TEN_HANG_HOA = 1;
    private final int SP_NHOM_HANG_HOA = 2;
    private final int SP_GIA_BAN = 3;
    private final int SP_GIA_VON = 4;
    private final int SP_TON_KHO = 5;
    private final int SP_DINH_MUC_TON_IT_NHAT = 6;
    private final int SP_DINH_MUC_TON_NHIEU_NHAT = 7;
    
    private XSSFSheet  sheet;
    private int sheetNumber;
    XSSFWorkbook  workbook;
    FileInputStream fileInputStream;
    
    // Constructor truyền tên file cần mở
    public DocFile(String fileName) throws FileNotFoundException, IOException {
        fileInputStream = new FileInputStream(new File(fileName));
        workbook = new XSSFWorkbook(fileInputStream);
        sheetNumber= 0;
        sheet = workbook.getSheetAt(sheetNumber);
    }
    
    // Constructor và mở jFileChooser để lấy đường dẫn file 
    public DocFile() throws FileNotFoundException, IOException {
        fileInputStream = new FileInputStream(new File(this.getPath()));
        workbook = new XSSFWorkbook(fileInputStream);
        sheetNumber= 0;
        sheet = workbook.getSheetAt(sheetNumber);
    }
    
    
    // Mở openDialog(jChooseFile) và lấy đường dẫn
    public String getPath(){
        //Tạo file chooser
        JFileChooser fileChooser = new JFileChooser();;
        
        //Mở mở hộp thoai OPEN trên mComponent
        int returnVal = fileChooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION){
            File file = fileChooser.getSelectedFile();
            return file.getAbsolutePath();
        }
        return null;
    }
    
    
    // Lấy số record trong file
    public int laySoRecordTrongSheet(){
        return sheet.getPhysicalNumberOfRows();
    }
    
    
    // Lấy dữ liệu và trả về kiểu  dữ liệu HangHoa trong File excel HangHoa
    public HangHoa layMotHangHoa(int rowNum){
        XSSFRow row = sheet.getRow(rowNum);
        XSSFCell  maHangHoa = row.getCell(SP_MA_HANG_HOA);
        XSSFCell  tenHangHoa = row.getCell(SP_TEN_HANG_HOA);
        XSSFCell  nhomHangHoa = row.getCell(SP_NHOM_HANG_HOA);
        XSSFCell  giaVon = row.getCell(SP_GIA_VON);
        XSSFCell  giaBan = row.getCell(SP_GIA_BAN);
        XSSFCell  tonKho = row.getCell(SP_TON_KHO);
        XSSFCell  dinhMucTonItNhat = row.getCell(SP_DINH_MUC_TON_IT_NHAT);
        XSSFCell  dinhMucTonNhieuNhat = row.getCell(SP_DINH_MUC_TON_NHIEU_NHAT);
        
        return new HangHoa(maHangHoa, tenHangHoa, giaBan, giaVon, tonKho, nhomHangHoa, dinhMucTonItNhat, dinhMucTonNhieuNhat);
    }
    
    
    //Lấy tất cả các hàng hóa trong file và trả về arrayList Hàng Hóa
    public ArrayList<HangHoa> layTatCaHangHoa(){
        
        // Tạo arrayList danh sách hàng hóa lấy đc
        ArrayList<HangHoa> arlHangHoa = new ArrayList<>();
        
        // Lấy từ row 1 (bởi vì row 0 là tên cột)
        for(int i = 1; i < this.laySoRecordTrongSheet(); i ++){
            arlHangHoa.add(this.layMotHangHoa(i));
        }
        return arlHangHoa;
    }
    

    
    // Dua du lieu vao file excel(file excel da co san)
    public void setProductToFile(HangHoa hangHoa){
        XSSFRow row = sheet.createRow(this.laySoRecordTrongSheet()+ 2);
        row.createCell(SP_MA_HANG_HOA, CellType.STRING).setCellValue(hangHoa.getMaHangHoa());
        row.createCell(SP_TEN_HANG_HOA, CellType.STRING).setCellValue(hangHoa.getTenHangHoa());
        row.createCell(SP_NHOM_HANG_HOA, CellType.STRING).setCellValue(hangHoa.getNhomHangHoa());
        row.createCell(SP_GIA_VON, CellType.STRING).setCellValue(hangHoa.getGiaVon());
        row.createCell(SP_GIA_BAN, CellType.STRING).setCellValue(hangHoa.getGiaBan());
        row.createCell(SP_TON_KHO, CellType.STRING).setCellValue(hangHoa.getTonKho());
        row.createCell(SP_DINH_MUC_TON_IT_NHAT, CellType.STRING).setCellValue(hangHoa.getDinhMucTonItNhat());
        row.createCell(SP_DINH_MUC_TON_NHIEU_NHAT, CellType.STRING).setCellValue(hangHoa.getDinhMucTonNhieuNhat());
    }
    
    
    // Lấy 1 hàng hóa trong file nhập hàng
    public HangNhap layMotHangNhap(int rowNum){
        XSSFRow row = sheet.getRow(rowNum);
        XSSFCell  maHangHoa = row.getCell(0);
        XSSFCell  tenHangHoa = row.getCell(1);
        XSSFCell  nhomHangHoa = row.getCell(2);
        XSSFCell  giaVon = row.getCell(3);
        XSSFCell  soLuong = row.getCell(4);
        return new HangNhap(maHangHoa, tenHangHoa, giaVon, soLuong, nhomHangHoa);
    }
    
    
    
    // Lấy danh sách các hàng hóa từ file nhập hàng
    public ArrayList<HangNhap> layDanhSachHangNhap(){
        ArrayList<HangNhap> arlHangNhap = new ArrayList<>();
        for(int i = 1; i < laySoRecordTrongSheet(); i ++){
            arlHangNhap.add(this.layMotHangNhap(i));
        }

        return arlHangNhap;
    }
    
    
    
    
    // Đóng file
    public void closeFile(){
        try {
            //Đóng workbook
            this.workbook.close();
            System.out.print("\n Close file successfully");
            
            // Đóng fileInputStream
            fileInputStream.close();
            
        } catch (IOException ex) {
            System.out.print("\n Close file unsuccessfully");
        }
        
    }
}
