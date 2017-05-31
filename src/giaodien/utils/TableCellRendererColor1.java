/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giaodien.utils;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author USER
 */
public class TableCellRendererColor1 extends DefaultTableCellRenderer{
    private JLabel componente1;
    
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        componente1 = (JLabel)super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); //To change body of generated methods, choose Tools | Templates.
        if (row %2 ==0)
        {
         componente1.setBackground(Color.white);
        } else {
            componente1.setBackground(Color.white);
        }
        if(isSelected)
        {
            componente1.setBackground(Color.LIGHT_GRAY);
        }
        return componente1;
    
    } 
}
