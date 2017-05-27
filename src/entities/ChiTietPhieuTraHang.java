package entities;

import org.apache.poi.xssf.usermodel.XSSFCell;

public class ChiTietPhieuTraHang {
    public String mMaHangHoa;
    public String mMaPhieuTraHang;
    public int mSoLuong;
    public int mDonGia;
    
    
    public ChiTietPhieuTraHang(){
        
    }
    
    public ChiTietPhieuTraHang(String maHangHoa, String maPhieuTraHang, int soLuong, int donGia){
        
        this.mMaHangHoa = maHangHoa;
        this.mMaPhieuTraHang = maPhieuTraHang;
        this.mSoLuong = soLuong;
        this.mDonGia = donGia;
       
    }
    
}
