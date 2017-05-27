package entities;

import org.apache.poi.xssf.usermodel.XSSFCell;

public class CongNoNhaCungCap {
    public String mMaCongNoNhaCungCap, mTenNhaCungCap, mGhiChu;
    public int mTongNo, mNoCanTra;
    
    public CongNoNhaCungCap(){}
    public CongNoNhaCungCap(String maCongNoNhaCungCap, String tenNhaCungCap, String ghiChu,
            int tongNo, int noCanTra){
            
        this.mMaCongNoNhaCungCap = maCongNoNhaCungCap;
        this.mTenNhaCungCap = tenNhaCungCap;
        this.mTongNo = tongNo;
        this.mNoCanTra = noCanTra;
        this.mGhiChu = ghiChu;
    }
}
