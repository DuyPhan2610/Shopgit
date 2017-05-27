/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import org.apache.poi.xssf.usermodel.XSSFCell;

/**
 *
 * @author phamh
 */
public class HangNhap {
    public String mMaHangHoa;
    public String mTenHangHoa;
    public String mNhomHangHoa;
    public int mGiaMua;
    public int mSoLuong;
    //Tao constructor tu cac Cell
    public HangNhap(XSSFCell maHangHoa, XSSFCell tenHangHoa, XSSFCell giaMua, XSSFCell soLuong, XSSFCell nhomHangHoa) {
        
        this.mMaHangHoa = maHangHoa.getStringCellValue();
        this.mTenHangHoa = tenHangHoa.getStringCellValue();
        this.mGiaMua = (int)giaMua.getNumericCellValue();
        this.mSoLuong = (int)soLuong.getNumericCellValue();
        this.mNhomHangHoa = nhomHangHoa.getStringCellValue();
        
    }
    
    public HangNhap(String maHangHoa, String tenHangHoa, int giaMua, int soLuong, String nhomHangHoa) {
        
        this.mMaHangHoa = maHangHoa;
        this.mTenHangHoa = tenHangHoa;
        this.mGiaMua = giaMua;
        this.mSoLuong = soLuong;
        this.mNhomHangHoa = nhomHangHoa;
        
    }
}
