package giaodien.tongquan;

import DatabaseConnection.ConnectionUtils;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.DefaultCategoryDataset;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Lê Cường
 */
public class TongQuan extends javax.swing.JPanel {

    Connection con;
    /**
     * Creates new form index
     */
    int thang = 1 + Calendar.getInstance().get(Calendar.MONTH);
    
    public TongQuan() {
        
        
        
        try {
            this.con = new ConnectionUtils().getMySQLConnection();
        } catch (SQLException ex) {
            Logger.getLogger(TongQuan.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TongQuan.class.getName()).log(Level.SEVERE, null, ex);
        }
        initComponents();
        
        showdata();
        try {
            GraphTongDoanhThu();
        } catch (SQLException ex) {
            Logger.getLogger(TongQuan.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            GraphTop5MatHangBanChay();
        } catch (SQLException ex) {
            Logger.getLogger(TongQuan.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
  
    
     public void showdata(){
      try{
     
     

     PreparedStatement tongcachoadon =  con.prepareStatement("select sum(TONGTIEN) as total from hoadonbanhang");
     ResultSet rstongcachoadon = tongcachoadon.executeQuery();
     rstongcachoadon.first();
     int sum = rstongcachoadon.getInt("total");
     System.out.println(sum);
     tfDoanhSo.setText(""+sum + "  VND ");
     
     
     PreparedStatement demhoadon =  con.prepareStatement("select count(MAHOADONBANHANG) from hoadonbanhang");
     ResultSet rsdemhoadon = demhoadon.executeQuery();
     rsdemhoadon.first();
     int count = rsdemhoadon.getInt("count(MAHOADONBANHANG)");
     System.out.println(count);
     tfHoaDon.setText(""+count);
     
     
    
    
    
     PreparedStatement sovoihomqua =  con.prepareStatement("SELECT TONGTIEN FROM shop.hoadonbanhang WHERE THOIGIAN >= '2017-05-25 00:00:00' AND THOIGIAN <= '2017-05-25 23:59:59'");
     ResultSet rssovoihomqua = sovoihomqua.executeQuery();
     rstongcachoadon.first();
     int sum1 = rssovoihomqua.getInt("total");
     System.out.println(sum);
     //tfDoanhSo.setText(""+sum);

    } catch(Exception exc){
        System.out.println(exc.getMessage());
    }

     }
     
     
    int doanhthutrongngay;
    String ngay;
    public void GraphTongDoanhThu() throws SQLException
    {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        PreparedStatement doanhthu =  con.prepareStatement("SELECT sum(TONGTIEN) as DoanhThu, day(date(THOIGIAN)) As Ngay FROM shop.hoadonbanhang where month(date(THOIGIAN))= "+ thang +" group by day(date(THOIGIAN)) ");
        ResultSet rsdoanhthu = doanhthu.executeQuery();
        while (rsdoanhthu.next())
        {   
            
            doanhthutrongngay = rsdoanhthu.getInt("DoanhThu");
            ngay = rsdoanhthu.getString("Ngay");
            
            dataset.setValue(doanhthutrongngay, "", ngay);
        }
        
              
        JFreeChart chart = ChartFactory.createBarChart3D(null, null, "VNĐ", dataset, PlotOrientation.VERTICAL, false, false, false);
        CategoryPlot catePlot = chart.getCategoryPlot();
        catePlot.setRangeGridlinePaint(Color.BLACK);
        
        //set  bar chart color

        ((BarRenderer)catePlot.getRenderer()).setBarPainter(new StandardBarPainter());

        BarRenderer r = (BarRenderer)chart.getCategoryPlot().getRenderer();
        r.setSeriesPaint(0, Color.GREEN);
        
        ChartPanel chartpanel = new ChartPanel(chart);
        graph.removeAll();
        graph.add(chartpanel, BorderLayout.CENTER);
        graph.setBackground(getBackground());
        graph.validate();
        
    }
    
    
    
    String maHH;
    int soluong;
    DefaultCategoryDataset dataset1 = new DefaultCategoryDataset();
    public void GraphTop5MatHangBanChay() throws SQLException
    {
        
        PreparedStatement LAYTOP5 =  con.prepareStatement("SELECT chitiethoadonbanhang.MAHANGHOA, TENHANGHOA, sum(chitiethoadonbanhang.SOLUONG) as TongSoLuong"
                + "                                        FROM chitiethoadonbanhang"
                + "                                        INNER JOIN hanghoa ON hanghoa.MAHANGHOA = chitiethoadonbanhang.MAHANGHOA "
                + "                                        group by MAHANGHOA order by sum(SOLUONG) desc limit 5");
        ResultSet rsLAYTOP5 = LAYTOP5.executeQuery();
        while (rsLAYTOP5.next())
        {   
            
            maHH = rsLAYTOP5.getString("TENHANGHOA");
            soluong = rsLAYTOP5.getInt("TongSoLuong");
           
            dataset1.setValue(soluong, "", maHH);
        }
        
        JFreeChart chart1 = ChartFactory.createBarChart3D(null, null, null, dataset1, PlotOrientation.HORIZONTAL, false, false, false);
        CategoryPlot catePlot1 = chart1.getCategoryPlot();
        catePlot1.setRangeGridlinePaint(Color.BLACK);
        ((BarRenderer)catePlot1.getRenderer()).setBarPainter(new StandardBarPainter());

        BarRenderer r1 = (BarRenderer)chart1.getCategoryPlot().getRenderer();
        r1.setSeriesPaint(0, Color.BLUE);
        
        ChartPanel chartpanel1 = new ChartPanel(chart1);
        graph1.removeAll();
        graph1.add(chartpanel1, BorderLayout.CENTER);
        graph1.validate();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        Toppanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tfDoanhSo = new javax.swing.JTextField();
        tfHoaDon = new javax.swing.JTextField();
        hinhdoanhthu = new javax.swing.JLabel();
        hinhhoadon = new javax.swing.JLabel();
        List = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        Detailpanel = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jcbThang = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        graph = new javax.swing.JPanel();
        graph1 = new javax.swing.JPanel();

        setLayout(new java.awt.BorderLayout());

        Toppanel.setBackground(new java.awt.Color(51, 153, 0));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("KẾT QUẢ BÁN HÀNG HÔM NAY:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel3.setText("HOÁ ĐƠN :");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel4.setText("DOANH THU :");

        tfDoanhSo.setEditable(false);
        tfDoanhSo.setBackground(new java.awt.Color(51, 153, 0));
        tfDoanhSo.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        tfDoanhSo.setBorder(null);
        tfDoanhSo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfDoanhSoActionPerformed(evt);
            }
        });

        tfHoaDon.setEditable(false);
        tfHoaDon.setBackground(new java.awt.Color(51, 153, 0));
        tfHoaDon.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        tfHoaDon.setBorder(null);
        tfHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfHoaDonActionPerformed(evt);
            }
        });

        hinhdoanhthu.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout ToppanelLayout = new javax.swing.GroupLayout(Toppanel);
        Toppanel.setLayout(ToppanelLayout);
        ToppanelLayout.setHorizontalGroup(
            ToppanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ToppanelLayout.createSequentialGroup()
                .addGroup(ToppanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(ToppanelLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(ToppanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(hinhhoadon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(hinhdoanhthu, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(ToppanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(65, 65, 65)
                        .addGroup(ToppanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfDoanhSo, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(122, Short.MAX_VALUE))
        );
        ToppanelLayout.setVerticalGroup(
            ToppanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ToppanelLayout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(ToppanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ToppanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(ToppanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tfHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(ToppanelLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(hinhhoadon, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(ToppanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ToppanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(tfDoanhSo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(hinhdoanhthu, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        add(Toppanel, java.awt.BorderLayout.PAGE_START);

        List.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        List.setPreferredSize(new java.awt.Dimension(200, 250));
        List.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("CÁC HOẠT ĐỘNG GẦN ĐÂY");
        List.add(jLabel1, new java.awt.GridBagConstraints());

        jScrollPane1.setBorder(null);

        jList1.setBackground(new java.awt.Color(240, 240, 240));
        jList1.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { " ", " ", "Quang Duy vừa bán", "\tSơ mi DS2 nhật bản size M", " ", " ", "Ngọc trinh vừa bán ", "\tỐp đt LV chữ 7", " ", " ", "Phú yên vừa ", "\tchỉnh sửa giá bán", " ", " ", "Huyền My vừa nhập hàng", " ", " ", "(Khách hàng VIP) Ronaldo vừa mua", "\t Ốp lưng Gucci hoa iphone 7" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        List.add(jScrollPane1, gridBagConstraints);

        add(List, java.awt.BorderLayout.LINE_END);

        Detailpanel.setLayout(new java.awt.GridBagLayout());

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("NHẬT KÍ DOANH THU");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 66;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        Detailpanel.add(jLabel5, gridBagConstraints);

        jcbThang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12" }));
        jcbThang.setSelectedIndex( Calendar.getInstance().get(Calendar.MONTH));
        jcbThang.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcbThangItemStateChanged(evt);
            }
        });
        jcbThang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbThangActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        Detailpanel.add(jcbThang, gridBagConstraints);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("TOP 5 SẢN PHẨM BÁN CHẠY NHẤT");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 66;
        Detailpanel.add(jLabel6, gridBagConstraints);

        graph.setLayout(new java.awt.BorderLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.4;
        gridBagConstraints.insets = new java.awt.Insets(4, 10, 2, 0);
        Detailpanel.add(graph, gridBagConstraints);

        graph1.setLayout(new java.awt.BorderLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 0.4;
        gridBagConstraints.insets = new java.awt.Insets(4, 11, 2, 0);
        Detailpanel.add(graph1, gridBagConstraints);

        add(Detailpanel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jcbThangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbThangActionPerformed
        // TODO add your handling code here:
        thang = 1 + jcbThang.getSelectedIndex();
        System.out.println(thang);
        try {
            GraphTongDoanhThu();
        } catch (SQLException ex) {
            Logger.getLogger(TongQuan.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jcbThangActionPerformed

    private void tfDoanhSoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfDoanhSoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfDoanhSoActionPerformed

    private void tfHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfHoaDonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfHoaDonActionPerformed

    private void jcbThangItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcbThangItemStateChanged
        // TODO add your handling code here:
        
        
    }//GEN-LAST:event_jcbThangItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Detailpanel;
    private javax.swing.JPanel List;
    private javax.swing.JPanel Toppanel;
    private javax.swing.JPanel graph;
    private javax.swing.JPanel graph1;
    private javax.swing.JLabel hinhdoanhthu;
    private javax.swing.JLabel hinhhoadon;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> jcbThang;
    private javax.swing.JTextField tfDoanhSo;
    private javax.swing.JTextField tfHoaDon;
    // End of variables declaration//GEN-END:variables
public static void main (String[] args) {
    JFrame frame_tongquan = new JFrame("TỔNG QUAN");
    TongQuan jp_tongquan = new TongQuan();
    frame_tongquan.setVisible(true);
    frame_tongquan.setSize(1500, 1300);
    frame_tongquan.add(jp_tongquan);
    frame_tongquan.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
}
}
