/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import database.BangCongNoNhaCungCap;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public class CongNoNhaCungCap {
    public String mMaCongNoNhaCungCap, mGhiChu;
    public int mTongNo, mNoCanTra;
    
    public CongNoNhaCungCap(){}
    public CongNoNhaCungCap(String maCongNoNhaCungCap, String ghiChu,
            int tongNo, int noCanTra){
            
        this.mMaCongNoNhaCungCap = maCongNoNhaCungCap;
        this.mTongNo = tongNo;
        this.mNoCanTra = noCanTra;
        this.mGhiChu = ghiChu;
    }
    
    public CongNoNhaCungCap(ResultSet rs){
        try {
            this.mMaCongNoNhaCungCap = rs.getString(BangCongNoNhaCungCap.MA_CONG_NO_NHA_CUNG_CAP);
            this.mTongNo = rs.getInt(BangCongNoNhaCungCap.TONG_NO);
            this.mNoCanTra = rs.getInt(BangCongNoNhaCungCap.NO_CAN_TRA);
            this.mGhiChu = rs.getString(BangCongNoNhaCungCap.GHI_CHU);
        } catch (SQLException ex) {
            Logger.getLogger(CongNoNhaCungCap.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}

