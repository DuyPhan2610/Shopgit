package giaodien.main;


import giaodien.baocao.ManHinhBaoCaoBanHang1;
import giaodien.baocao.ManHinhBaoCaoHangHoa;
import giaodien.baocao.ManHinhBaoCaoHangHoa1;
import giaodien.giaodich.TraHangNhap;
import giaodien.doitac.DoiTacKhachHang;
import giaodien.doitac.DoiTacNhaCungCap;
import giaodien.giaodich.HoaDon;
import giaodien.giaodich.PhieuNhapHang;
import giaodien.giaodich.TraHang;
import giaodien.hanghoa.DanhMuc;
import giaodien.hanghoa.ThietLapGia;
import giaodien.tongquan.TongQuan;
import java.awt.Container;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author phamh
 */
public class MainWindow extends javax.swing.JFrame {

    /**
     * Creates new form MainWindow
     */
    public MainWindow() {

        
        initComponents();
        
        
        this.veLaiFrame(new TongQuan());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelContent = new javax.swing.JPanel();
        jMenuBar = new javax.swing.JMenuBar();
        jMenuTongQuan = new javax.swing.JMenu();
        jMenuItemGioiThieu = new javax.swing.JMenuItem();
        jMenuItemTongQuan = new javax.swing.JMenuItem();
        jMenuHangHoa = new javax.swing.JMenu();
        jMenuItemDanhMuc = new javax.swing.JMenuItem();
        jMenuItemThietLapGia = new javax.swing.JMenuItem();
        jMenuGiaoDich = new javax.swing.JMenu();
        jMenuItemHoaDon = new javax.swing.JMenuItem();
        jMenuItemTraHang = new javax.swing.JMenuItem();
        jMenuItemNhapHang = new javax.swing.JMenuItem();
        jMenuItemTraHangNhap = new javax.swing.JMenuItem();
        jMenuDoiTac = new javax.swing.JMenu();
        jMenuItemKhachHang = new javax.swing.JMenuItem();
        jMenuItemNhaCungCap = new javax.swing.JMenuItem();
        jMenuBaoCao = new javax.swing.JMenu();
        jMenuItemCuoiNgay = new javax.swing.JMenuItem();
        jMenuItemBanNgay = new javax.swing.JMenuItem();
        jMenuItemHangHoa = new javax.swing.JMenuItem();
        jMenuItemBCKhachHang = new javax.swing.JMenuItem();
        jMenuItemBCNCC = new javax.swing.JMenuItem();
        jMenuItemNhanVien = new javax.swing.JMenuItem();
        jMenuItemTaiChinh = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(100, 50));

        jPanelContent.setLayout(new java.awt.CardLayout());

        jMenuBar.setPreferredSize(new java.awt.Dimension(326, 25));

        jMenuTongQuan.setBorder(null);
        jMenuTongQuan.setText("Tổng quan");
        jMenuTongQuan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuTongQuanActionPerformed(evt);
            }
        });

        jMenuItemGioiThieu.setText("Giới thiệu");
        jMenuTongQuan.add(jMenuItemGioiThieu);

        jMenuItemTongQuan.setText("Tổng quan");
        jMenuItemTongQuan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemTongQuanActionPerformed(evt);
            }
        });
        jMenuTongQuan.add(jMenuItemTongQuan);

        jMenuBar.add(jMenuTongQuan);

        jMenuHangHoa.setText("Hàng hóa");
        jMenuHangHoa.setMinimumSize(new java.awt.Dimension(100, 19));

        jMenuItemDanhMuc.setText("Danh mục");
        jMenuItemDanhMuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemDanhMucActionPerformed(evt);
            }
        });
        jMenuHangHoa.add(jMenuItemDanhMuc);

        jMenuItemThietLapGia.setText("Thiết lập giá");
        jMenuItemThietLapGia.setActionCommand("");
        jMenuItemThietLapGia.setPreferredSize(new java.awt.Dimension(100, 22));
        jMenuItemThietLapGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemThietLapGiaActionPerformed(evt);
            }
        });
        jMenuHangHoa.add(jMenuItemThietLapGia);

        jMenuBar.add(jMenuHangHoa);

        jMenuGiaoDich.setText("Giao dịch");

        jMenuItemHoaDon.setText("Hóa đơn");
        jMenuItemHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemHoaDonActionPerformed(evt);
            }
        });
        jMenuGiaoDich.add(jMenuItemHoaDon);

        jMenuItemTraHang.setText("Trả hàng");
        jMenuItemTraHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemTraHangActionPerformed(evt);
            }
        });
        jMenuGiaoDich.add(jMenuItemTraHang);

        jMenuItemNhapHang.setText("Nhập hàng");
        jMenuItemNhapHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemNhapHangActionPerformed(evt);
            }
        });
        jMenuGiaoDich.add(jMenuItemNhapHang);

        jMenuItemTraHangNhap.setText("Trả hàng nhập");
        jMenuItemTraHangNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemTraHangNhapActionPerformed(evt);
            }
        });
        jMenuGiaoDich.add(jMenuItemTraHangNhap);

        jMenuBar.add(jMenuGiaoDich);

        jMenuDoiTac.setText("Đối tác");

        jMenuItemKhachHang.setText("Khách hàng");
        jMenuItemKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemKhachHangActionPerformed(evt);
            }
        });
        jMenuDoiTac.add(jMenuItemKhachHang);

        jMenuItemNhaCungCap.setText("Nhà cung cấp");
        jMenuItemNhaCungCap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemNhaCungCapActionPerformed(evt);
            }
        });
        jMenuDoiTac.add(jMenuItemNhaCungCap);

        jMenuBar.add(jMenuDoiTac);

        jMenuBaoCao.setText("Báo cáo");

        jMenuItemCuoiNgay.setText("Cuối ngày");
        jMenuBaoCao.add(jMenuItemCuoiNgay);

        jMenuItemBanNgay.setText("Bán hàng");
        jMenuItemBanNgay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemBanNgayActionPerformed(evt);
            }
        });
        jMenuBaoCao.add(jMenuItemBanNgay);

        jMenuItemHangHoa.setText("Hàng hóa");
        jMenuItemHangHoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemHangHoaActionPerformed(evt);
            }
        });
        jMenuBaoCao.add(jMenuItemHangHoa);

        jMenuItemBCKhachHang.setText("Khách hàng");
        jMenuItemBCKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemBCKhachHangActionPerformed(evt);
            }
        });
        jMenuBaoCao.add(jMenuItemBCKhachHang);

        jMenuItemBCNCC.setText("Nhà cung cấp");
        jMenuBaoCao.add(jMenuItemBCNCC);

        jMenuItemNhanVien.setText("Nhân viên");
        jMenuBaoCao.add(jMenuItemNhanVien);

        jMenuItemTaiChinh.setText("Tài chính");
        jMenuItemTaiChinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemTaiChinhActionPerformed(evt);
            }
        });
        jMenuBaoCao.add(jMenuItemTaiChinh);

        jMenuBar.add(jMenuBaoCao);

        setJMenuBar(jMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelContent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelContent, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemThietLapGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemThietLapGiaActionPerformed
        // TODO add your handling code here:
        this.veLaiFrame(new ThietLapGia());
    }//GEN-LAST:event_jMenuItemThietLapGiaActionPerformed

    private void jMenuItemDanhMucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemDanhMucActionPerformed
        // TODO add your handling code here:
        this.veLaiFrame(new DanhMuc());
    }//GEN-LAST:event_jMenuItemDanhMucActionPerformed

    private void jMenuTongQuanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuTongQuanActionPerformed
        
        // TODO add your handling code here:
        System.out.println("You clicked the JMenu");
        JOptionPane.showMessageDialog(null, "My Goodness, this is so concise");
        
        
    }//GEN-LAST:event_jMenuTongQuanActionPerformed

    private void jMenuItemHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemHoaDonActionPerformed
        // TODO add your handling code here:
        this.veLaiFrame(new HoaDon());
    }//GEN-LAST:event_jMenuItemHoaDonActionPerformed

    private void jMenuItemTraHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemTraHangActionPerformed
        // TODO add your handling code here:
        this.veLaiFrame(new TraHang());
    }//GEN-LAST:event_jMenuItemTraHangActionPerformed

    private void jMenuItemTraHangNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemTraHangNhapActionPerformed
        // TODO add your handling code here:
        this.veLaiFrame(new TraHangNhap());
    }//GEN-LAST:event_jMenuItemTraHangNhapActionPerformed

    private void jMenuItemNhaCungCapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemNhaCungCapActionPerformed
        // TODO add your handling code here:
        this.veLaiFrame(new DoiTacNhaCungCap());
    }//GEN-LAST:event_jMenuItemNhaCungCapActionPerformed

    private void jMenuItemBCKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemBCKhachHangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItemBCKhachHangActionPerformed

    private void jMenuItemTaiChinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemTaiChinhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItemTaiChinhActionPerformed

    private void jMenuItemTongQuanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemTongQuanActionPerformed
        // TODO add your handling code here:
        
        this.veLaiFrame(new TongQuan());
    }//GEN-LAST:event_jMenuItemTongQuanActionPerformed

    private void jMenuItemNhapHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemNhapHangActionPerformed
        // TODO add your handling code here:
        this.veLaiFrame(new PhieuNhapHang());
    }//GEN-LAST:event_jMenuItemNhapHangActionPerformed

    private void jMenuItemKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemKhachHangActionPerformed
        // TODO add your handling code here:
        this.veLaiFrame(new DoiTacKhachHang());
    }//GEN-LAST:event_jMenuItemKhachHangActionPerformed

    private void jMenuItemBanNgayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemBanNgayActionPerformed
        // TODO add your handling code here:
        this.veLaiFrame(new ManHinhBaoCaoBanHang1());
    }//GEN-LAST:event_jMenuItemBanNgayActionPerformed

    private void jMenuItemHangHoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemHangHoaActionPerformed
        // TODO add your handling code here:
        this.veLaiFrame(new ManHinhBaoCaoHangHoa1());
    }//GEN-LAST:event_jMenuItemHangHoaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }
    
    private void veLaiFrame(JComponent component){
        //xóa panel hiện tại
        jPanelContent.removeAll();
        jPanelContent.repaint();
        jPanelContent.revalidate();

        //ve panel danh muc
        jPanelContent.add(component);
        jPanelContent.repaint();
        jPanelContent.revalidate();
    }
    
    
    private DoiTacKhachHang jPanelKhachHang;
    private DoiTacNhaCungCap jPanelNhaCungCap;
    
    private TraHangNhap jPanelTraHangNhap;
    private PhieuNhapHang jPanelNhapHang;
    private TraHang jPanelTraHang;
    private HoaDon jPanelHoaDon;
            
    private ThietLapGia jPanelThietLapGia;
    private DanhMuc jPanelDanhMuc;
    
    private TongQuan jPanelTongQuan;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenuBaoCao;
    private javax.swing.JMenuBar jMenuBar;
    private javax.swing.JMenu jMenuDoiTac;
    private javax.swing.JMenu jMenuGiaoDich;
    private javax.swing.JMenu jMenuHangHoa;
    private javax.swing.JMenuItem jMenuItemBCKhachHang;
    private javax.swing.JMenuItem jMenuItemBCNCC;
    private javax.swing.JMenuItem jMenuItemBanNgay;
    private javax.swing.JMenuItem jMenuItemCuoiNgay;
    private javax.swing.JMenuItem jMenuItemDanhMuc;
    private javax.swing.JMenuItem jMenuItemGioiThieu;
    private javax.swing.JMenuItem jMenuItemHangHoa;
    private javax.swing.JMenuItem jMenuItemHoaDon;
    private javax.swing.JMenuItem jMenuItemKhachHang;
    private javax.swing.JMenuItem jMenuItemNhaCungCap;
    private javax.swing.JMenuItem jMenuItemNhanVien;
    private javax.swing.JMenuItem jMenuItemNhapHang;
    private javax.swing.JMenuItem jMenuItemTaiChinh;
    private javax.swing.JMenuItem jMenuItemThietLapGia;
    private javax.swing.JMenuItem jMenuItemTongQuan;
    private javax.swing.JMenuItem jMenuItemTraHang;
    private javax.swing.JMenuItem jMenuItemTraHangNhap;
    private javax.swing.JMenu jMenuTongQuan;
    private javax.swing.JPanel jPanelContent;
    // End of variables declaration//GEN-END:variables
}
