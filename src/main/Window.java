/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import giaodien.doitac.DoiTacNhaCungCap;
import giaodien.giaodich.JPanel_Nhaphang1;
import giaodien.giaodich.JPanel_Trahang;
import giaodien.giaodich.JPanel_Trahangnhap;
import giaodien.tongquan.TongQuan;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
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
       //Them menu GIAO DỊCH
       JMenu menuGiaoDich = new JMenu("Giao dịch");
       menuBar.add(menuGiaoDich);
       
       //Menuitem hóa đơn trong menu Giao dịch
       JMenuItem menuItemHoaDon = new JMenuItem("Hóa đơn", KeyEvent.VK_T);
       menuItemHoaDon.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                 menuItemHoaDonActionPerformed(evt);              
            }
        });
       menuGiaoDich.add(menuItemHoaDon);
       //Menuitem trả hàng trong menu giao dịch
       JMenuItem menuItemTraHang = new JMenuItem("Trả hàng", icon);
       menuItemTraHang.setMnemonic(KeyEvent.VK_B);
       menuGiaoDich.add(menuItemTraHang);
       
       //Menuitem nhập hàng trong menu giao dịch
       JMenuItem menuItemNhapHang= new JMenuItem("Nhập hàng", icon);
       menuItemNhapHang.setMnemonic(KeyEvent.VK_C);
       menuGiaoDich.add(menuItemNhapHang);
       
       //Menuitem trả hàng nhập trong menu giao dịch
       //JMenuItem menuItemTraHangNhap = new JMenuItem("Trả hàng nhập", icon);
       //menuItemTraHangNhap.setMnemonic(KeyEvent.VK_A);
       //menuGiaoDich.add(menuItemTraHangNhap);
       
       //------------Menu ĐỐI TÁC--------------
       //Them menu ĐỐI TÁC
       JMenu menuDoiTac = new JMenu("Đối tác");
       menuBar.add(menuDoiTac);
       
       //Menuitem khách hàng trong menu Đối tác
       JMenuItem menuItemKhachHang = new JMenuItem("Khách hàng", KeyEvent.VK_D);
       menuItemKhachHang.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                 menuItemKhachHangActionPerformed(evt);              
            }
        });
       menuDoiTac.add(menuItemKhachHang);
       
       JMenuItem menuItemNhaCungCap = new JMenuItem("Nhà cung cấp", icon);
       menuItemNhaCungCap.setMnemonic(KeyEvent.VK_E);
       menuDoiTac.add(menuItemNhaCungCap);
       
       //Thêm menu BÁO CÁO
        JMenu menuBaoCao = new JMenu("Báo cáo");
        menuBar.add(menuBaoCao);
       
        
        
       return menuBar;
    }
    
 //
    public Container setContentPane(Container container){
        //Create the content pane to be
        contentPane = container;
        return contentPane;
    }
    
    public void itemStateChanged(ItemEvent e) {
        JMenuItem source = (JMenuItem) (e.getSource());
        String s = "Item event detected."
                    +newline
                    + "     Event source: " + source.getText()
                    + "  (an instance of" + getClassName(source) + ")"
                    + newline
                    + "     New state: "
                    + ((e.getStateChange() == ItemEvent.SELECTED) ?
                        "selected":"unselected");
            output.append(s+ newline);
            output.setCaretPosition(output.getDocument().getLength());
    }
    
    //Returns just the class name -- no package info.
    protected String getClassName(Object o) {
        String classString =o.getClass().getName();
        int dotIndex = classString.lastIndexOf(".");
        return classString.substring(dotIndex+1);
    }
    
    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = Window.class.getResource(path);
        
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
    
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI(){
        //Create and set up the window
        frame = new JFrame ("SHOP MANAGEMENT");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Create and set up the content pane
        Window demo = new Window();
        frame.setJMenuBar(demo.createMenuBar());
        
        frame.setContentPane(demo.setContentPane(new TongQuan()));
        
        //Display the window
        frame.setSize(450,260);
        frame.setVisible(true);
    }
    
    //Thêm sự kiện cho menuItemDanhMucActionPerformed
    private void menuItemDanhMucActionPerformed(java.awt.event.ActionEvent evt) {     
    }
    private void menuItemHoaDonActionPerformed(java.awt.event.ActionEvent evt)  {
        frame.removeAll();
        frame.repaint();
        frame.revalidate();
        
        frame.add(new JPanel_Trahang());
        frame.repaint();
        frame.revalidate();
        
        //nhaphang
        frame.removeAll();
        frame.repaint();
        frame.revalidate();
        
        frame.add(new JPanel_Nhaphang1());
        frame.repaint();
        frame.revalidate();
        
        //trả hàng nhập
        //frame.removeAll();
        //frame.repaint();
        //frame.revalidate();
        
        //frame.add( new JPanel_Trahangnhap());
        //frame.repaint();
        //frame.revalidate();
    }
    
    private void menuItemKhachHangActionPerformed(java.awt.event.ActionEvent evt) {
        //nha cung cap
        frame.removeAll();
        frame.repaint();
        frame.revalidate();
        
        frame.add(new DoiTacNhaCungCap());
        frame.repaint();
        frame.revalidate();
    }
    
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
               createAndShowGUI();
            }
        });
    }
    
}
