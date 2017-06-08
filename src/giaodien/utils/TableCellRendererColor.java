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
public class TableCellRendererColor extends DefaultTableCellRenderer{

    private JLabel componente;
    
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        componente = (JLabel)super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); //To change body of generated methods, choose Tools | Templates.
        if (row %2 ==0)
        {
         componente.setBackground(Color.white);
        } else {
            componente.setBackground(Color.getHSBColor(16, 32, 90));
        }
        if(isSelected)
        {
            componente.setBackground(Color.lightGray);
        }
        return componente;
    
    } 
}

