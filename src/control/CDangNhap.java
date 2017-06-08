/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import DatabaseConnection.ConnectionUtils;
import database.BangNhanVien;
import entities.NhanVien;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author phamh
 */
public class CDangNhap {
    
    public BangNhanVien bangNhanVien;
    public JTextField mTaiKhoan;
    public JPasswordField mMatKhau;
    
    public String taiKhoan;
    public String matKhau;
    
    public CDangNhap(JTextField tk, JPasswordField mk){
        
        this.mTaiKhoan = tk;
        this.mMatKhau = mk;
        
        try {
            this.bangNhanVien = new BangNhanVien(new ConnectionUtils().getMySQLConnection());
            
        } catch (SQLException ex) {
            Logger.getLogger(CDangNhap.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CDangNhap.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // kiểm tra đăng nhập
    public boolean kiemTraDangNhap(){
        this.taiKhoan = this.mTaiKhoan.getText();
        this.matKhau = this.mMatKhau.getText();
        
        ArrayList<NhanVien> dsNhanVien = bangNhanVien.layTatCaNhanVienTrongCSDL();
        for(int i = 0; i < dsNhanVien.size(); i ++){
            String maNV = dsNhanVien.get(i).mMaNhanVien;
            if(maNV.equals(this.taiKhoan) && maNV.equals(this.matKhau) ){
                return true;
            }
        }
        
        return false;
    }
    
}
