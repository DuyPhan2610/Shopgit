package entities;

import database.BangHangHoa;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;

public class HangHoa {
    public String mMaHangHoa;
    public String mTenHangHoa;
    public int mGiaBan;
    public int mGiaVon;
    public int mTonKho;
    public String mNhomHangHoa;
    public int mDinhMucTonItNhat;
    public int mDinhMucTonNhieuNhat;
    
    public HangHoa(){
        
    }
    
    public HangHoa(String maHangHoa, String tenHangHoa, int giaBan, int giaVon, int tonKho
            , String nhomHangHoa, int dinhMucTonItNhat, int dinhMucTonNhieuNhat){
        
        this.mMaHangHoa = maHangHoa;
        this.mTenHangHoa = tenHangHoa;
        this.mGiaBan = giaBan;
        this.mGiaVon = giaVon;
        this.mTonKho = tonKho;
        this.mNhomHangHoa = nhomHangHoa;
        this.mDinhMucTonItNhat = dinhMucTonItNhat;
        this.mDinhMucTonNhieuNhat = dinhMucTonNhieuNhat;
    }

    
    //Tao constructor tu cac Cell
    public HangHoa(XSSFCell maHangHoa, XSSFCell tenHangHoa, XSSFCell giaBan, XSSFCell giaVon, XSSFCell tonKho, XSSFCell nhomHangHoa,
                     XSSFCell dinhMucTonItNhat, XSSFCell dinhMucTonNhieuNhat) {
        
        this.mMaHangHoa = maHangHoa.getStringCellValue();
        this.mTenHangHoa = tenHangHoa.getStringCellValue();
        this.mGiaBan = (int)giaBan.getNumericCellValue();
        this.mGiaVon = (int)giaVon.getNumericCellValue();
        this.mTonKho = (int)tonKho.getNumericCellValue();
        this.mNhomHangHoa = nhomHangHoa.getStringCellValue();
        this.mDinhMucTonItNhat = (int)dinhMucTonItNhat.getNumericCellValue();
        this.mDinhMucTonNhieuNhat = (int)dinhMucTonNhieuNhat.getNumericCellValue();
    }
    
    
    public HangHoa(ResultSet rs){
        try {
            this.mMaHangHoa = rs.getString(BangHangHoa.HANG_HOA_MA_HANG_HOA);
            this.mTenHangHoa = rs.getString(BangHangHoa.HANG_HOA_TEN_HANG_HOA);
            this.mNhomHangHoa = rs.getString(BangHangHoa.HANG_HOA_NHOM_HANG_HOA);
            this.mGiaBan = rs.getInt(BangHangHoa.HANG_HOA_GIA_BAN);
            this.mGiaVon = rs.getInt(BangHangHoa.HANG_HOA_GIA_VON);
            this.mTonKho = rs.getInt(BangHangHoa.HANG_HOA_TON_KHO);
            this.mDinhMucTonItNhat = rs.getInt(BangHangHoa.HANG_HOA_TON_IT_NHAT);
            this.mDinhMucTonNhieuNhat = rs.getInt(BangHangHoa.HANG_HOA_TON_NHIEU_NHAT);
            
        } catch (SQLException ex) {
            Logger.getLogger(HangHoa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Tạo một hàng hóa từ hàng nhập trong file
    public HangHoa(HangNhap hangNhap){
        this.mMaHangHoa = hangNhap.mMaHangHoa;
        this.mTenHangHoa = hangNhap.mTenHangHoa;
        this.mGiaBan = 0;
        this.mGiaVon = (int)hangNhap.mGiaMua;
        this.mTonKho = 0;
        this.mNhomHangHoa = hangNhap.mNhomHangHoa;
        this.mDinhMucTonItNhat = 0;
        this.mDinhMucTonNhieuNhat = 0;
    }
    
    public String  getMaHangHoa(){
        return this.mMaHangHoa;
    }
    public String getTenHangHoa(){
        return this.mTenHangHoa;
    }
    public int getGiaBan(){
        return this.mGiaBan;
    }
    
    public int getGiaVon(){
        return this.mGiaVon;
    }
    public int getTonKho(){
        return this.mTonKho;
    }
    public String getNhomHangHoa(){
        return this.mNhomHangHoa;
    }
    public int getDinhMucTonItNhat(){
        return this.mDinhMucTonItNhat;
    }
    public int getDinhMucTonNhieuNhat(){
        return this.mDinhMucTonNhieuNhat;
    }
    
}
