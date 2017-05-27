/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  giaodien.hanghoa;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author USER
 */
public class JFrame_Hanghoa extends JFrame{
            //public JFrame_Hanghoa(){
//}
public static void main (String[] args) {
    JFrame frame = new JFrame("DANH MỤC HÀNG HÓA");
    JPanel_Danhmuchanghoa jp1 = new JPanel_Danhmuchanghoa();
    frame.setVisible(true);
    frame.setSize(1300,800);
    frame.add(jp1);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
     
    
}
     
}
