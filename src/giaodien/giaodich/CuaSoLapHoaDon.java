/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giaodien.giaodich;

import javax.swing.JFrame;

/**
 *
 * @author USER
 */
public class CuaSoLapHoaDon extends javax.swing.JFrame{
    //public JFrame_Hoadon(){
    //}
    
public static void main (String[] args) {
    JFrame frame_hoadon = new JFrame("HÓA ĐƠN BÁN HÀNG");
    HoaDon jp_hoadon = new HoaDon();
    frame_hoadon.setVisible(true);
    frame_hoadon.setSize(1500, 1300);
    frame_hoadon.add(jp_hoadon);
    frame_hoadon.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}
}
