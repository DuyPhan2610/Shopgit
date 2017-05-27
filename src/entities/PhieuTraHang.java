package entities;

import org.apache.poi.xssf.usermodel.XSSFCell;

public class PhieuTraHang {
    private String mMaHangHoa;
    private String mTenHangHoa;
    private int mGiaBan;
    private int mGiaVon;
    private int mTonKho;
    private String mNhomHangHoa;
    private int mDinhMucTonItNhat;
    private int mDinhMucTonNhieuNhat;
    
    public PhieuTraHang(){
        
    }
    
    public PhieuTraHang(String maHangHoa, String tenHangHoa, int giaBan, int giaVon, int tonKho
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
    public PhieuTraHang(XSSFCell maHangHoa, XSSFCell tenHangHoa, XSSFCell giaBan, XSSFCell giaVon, XSSFCell tonKho, XSSFCell nhomHangHoa,
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
