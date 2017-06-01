/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import giaodien.tongquan.TongQuan;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author USER
 */
public class Window {
    
    private static JFrame frame;
    
    JTextArea output;
    JScrollPane scrollPane;
    TongQuan tongQuan;
    Container contentPane;
    
    private final int TONG_QUAN = 1;
    private final int DANH_MUC =21;
    private final int HOA_DON = 22;
    private final int TRA_HANG =23;
    private final int NHAP_HANG =24;
    private final int TRA_HANG_NHAP =25;
    private int idPanel;
    String newline ="\n";
    
    public JMenuBar createMenuBar() {
        JMenuBar menuBar;
        
        JRadioButtonMenuItem rbMenuItem;
        JCheckBoxMenuItem cbMenuItem;
        
        //Create the menu bar.
        menuBar = new JMenuBar();
        
        //Thêm menu Tổng quan
        JMenu menuTongQuan = new JMenu("Tổng quan");
        //Thêm icon
        ImageIcon icon =createImageIcon("images/miđle.gif");
        menuTongQuan.setIcon(icon);
        
        menuBar.add(menuTongQuan);
        //---------------------Menu Hàng Hóa--------------------
        //Thêm menu Hàng hóa
        JMenu menuHangHoa = new JMenu("Hàng hóa");
        menuBar.add(menuHangHoa);
        
        //MenuItem trong menu Hàng hóa
       JMenuItem menuItemDanhMuc = new JMenuItem("Danh mục", KeyEvent.VK_T);
       menuItemDanhMuc.addActionListener( new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemDanhMucActionPerformed(evt);
            }
        });
       
       menuHangHoa.add(menuItemDanhMuc);
       //------------Menu GIAODICH--------------
       //Them menu hóa đơn
       JMenu menuGiaoDich = new JMenu("Giao dịch");
       menuBar.add(menuGiaoDich);
       
       //Menuitem trong menu Giao dịch
       JMenuItem menuItemHoaDon = new JMenuItem("Hóa đơn", KeyEvent.VK_T);
       menuItemHoaDon.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                 menuItemDanhMucActionPerformed(evt);              
            }
        });
       
       
       return menuBar;
    }
    
}
