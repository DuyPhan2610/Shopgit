package entities;

import org.apache.poi.xssf.usermodel.XSSFCell;

public class ChiTietPhieuNhapHang {
    public String mMaHangHoa;
    public String mMaPhieuNhap;
    
    
    public ChiTietPhieuNhapHang(){
        
    }
    
    public ChiTietPhieuNhapHang(String maHangHoa, String maPhieuNhap){
        
        this.mMaHangHoa = maHangHoa;
        this.mMaPhieuNhap = maPhieuNhap;
       
    }
    
}
