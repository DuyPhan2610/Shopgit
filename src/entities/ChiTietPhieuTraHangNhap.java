package entities;

import org.apache.poi.xssf.usermodel.XSSFCell;

public class ChiTietPhieuTraHangNhap {
    public String mMaHangHoa;
    public String mMaChiTietPhietPhieuTraHangNhap;
    
    
    public ChiTietPhieuTraHangNhap(){
        
    }
    
    public ChiTietPhieuTraHangNhap(String maHangHoa, String maChiTietPhieuTraHangNhap){
        
        this.mMaHangHoa = maHangHoa;
        this.mMaChiTietPhietPhieuTraHangNhap = maChiTietPhieuTraHangNhap;
       
    }
}
