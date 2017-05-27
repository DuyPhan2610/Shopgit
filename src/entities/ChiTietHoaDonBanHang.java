package entities;

import org.apache.poi.xssf.usermodel.XSSFCell;

public class ChiTietHoaDonBanHang {
   public String mMaHangHoa;
    public String mMaHoaDonBanHang;
    
    
    public ChiTietHoaDonBanHang(){
        
    }
    
    public ChiTietHoaDonBanHang(String maHangHoa, String maHoaDonBanHang){
        
        this.mMaHangHoa = maHangHoa;
        this.mMaHoaDonBanHang = maHoaDonBanHang;
       
    }
}
