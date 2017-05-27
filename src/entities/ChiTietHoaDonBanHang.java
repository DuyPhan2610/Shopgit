package entities;

import org.apache.poi.xssf.usermodel.XSSFCell;

public class ChiTietHoaDonBanHang {
   public String mMaHangHoa;
    public String mMaHoaDonBanHang;
    public int mSoLuong;
    
    public ChiTietHoaDonBanHang(){
        
    }
    
    public ChiTietHoaDonBanHang(String maHangHoa, String maHoaDonBanHang, int soLuong ){
        
        this.mMaHangHoa = maHangHoa;
        this.mMaHoaDonBanHang = maHoaDonBanHang;
        this.mSoLuong = soLuong;
       
    }
}
