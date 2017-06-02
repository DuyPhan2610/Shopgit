/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import database.BangChiTietPhieuTraHangNhap;
import database.BangPhieuTraHangNhap;
import entities.ChiTietPhieuTraHangNhap;
import entities.HangHoa;
import entities.HangNhap;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JTable;

/**
 *
 * @author phamh
 */
public class TraHangNhap extends NhapHangHoa{
    
    public BangPhieuTraHangNhap bangPhieuTraHangNhap;
    public BangChiTietPhieuTraHangNhap bangChiTietPhieuTraHangNhap;
    public TraHangNhap(JTable table, JComboBox comboBox) {
        super(table, comboBox);
        bangChiTietPhieuTraHangNhap = new BangChiTietPhieuTraHangNhap(connection);
        bangPhieuTraHangNhap = new BangPhieuTraHangNhap(connection);
    }
    
    //Xóa hàng nhập trong csdl và trả về những hàng hóa đã xóa
    public ArrayList<HangHoa> xoaHangNhapTrongCSDL(){
        ArrayList<HangNhap> arlHangNhap = this.layDanhSachHangNhapTrongTable();
        ArrayList<HangHoa> arlHangHoa = bangHangHoa.layTatCaHangHoaTrongCSDL();
        ArrayList<HangHoa> arlHangDaXoa = new ArrayList<>();
        for(int i = 0; i < arlHangNhap.size(); i++){
            for(int j = 0; j < arlHangHoa.size(); j ++){
                if(arlHangNhap.get(i).mMaHangHoa.equals(arlHangHoa.get(j).mMaHangHoa)){
                    bangHangHoa.xoaMotHangHoaTuMaHangHoa(arlHangHoa.get(j).mMaHangHoa);
                    arlHangDaXoa.add(arlHangHoa.get(j));
                }
            }
        }
        
        return arlHangDaXoa;
    }
    
    public void taoChiTietPhieuTraHangNhap(String maPhieuTraHangNhap){
        ArrayList<HangNhap> arlHangNhap = this.layDanhSachHangNhapTrongTable();
        for(int i = 0; i < arlHangNhap.size(); i ++){
            ChiTietPhieuTraHangNhap chiTietPhieuTraHangNhap = new ChiTietPhieuTraHangNhap(arlHangNhap.get(i).mMaHangHoa,
                                                            maPhieuTraHangNhap, arlHangNhap.get(i).mSoLuong);
            bangChiTietPhieuTraHangNhap.themChiTietPhieuTraHangNhap(chiTietPhieuTraHangNhap);
        }
    }
    
    
}
