/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inventory.Salles;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import com.inventory.Salles.DataDetail;
import java.awt.AWTException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author deidine
 */
public class DashboardSalle extends javax.swing.JFrame {

    /**
     * Creates new form DashboardSalle
     */
    public static boolean tutup = false;
    String username;

    public DashboardSalle(String username, String level) {
        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.username = username;
        lb_nama.setText(username);
        lb_level.setText(level);
        execute();
        Date();
        System.out.println("eheh" + this.username);
    }

    private void Date() {
        Date TanggalSekarang = new Date();
        SimpleDateFormat TanggalWaktu = new SimpleDateFormat("yyyy-MM-dd");
        String tanggal = TanggalWaktu.format(TanggalSekarang);
        lb_tanggal.setText(tanggal);
    }

    JButton btnNewButton = new JButton("Logout");

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_content = new javax.swing.JPanel();
        pn_utama = new javax.swing.JPanel();
        panel_sidebar = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        pn_menu = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        lb_level = new javax.swing.JLabel();
        lb_nama = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lb_tanggal = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        lb_tanggal1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        panel_content.setBackground(new java.awt.Color(255, 255, 255));

        pn_utama.setBackground(new java.awt.Color(255, 255, 255));
        pn_utama.setLayout(new java.awt.CardLayout());

        panel_sidebar.setBackground(new java.awt.Color(255, 255, 255));
        panel_sidebar.setPreferredSize(new java.awt.Dimension(250, 432));

        jScrollPane1.setBorder(null);

        pn_menu.setBackground(new java.awt.Color(255, 255, 255));
        pn_menu.setLayout(new javax.swing.BoxLayout(pn_menu, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane1.setViewportView(pn_menu);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        lb_level.setBackground(new java.awt.Color(255, 255, 255));
        lb_level.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        lb_level.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_level.setText("Level");
        lb_level.setPreferredSize(new java.awt.Dimension(47, 30));

        lb_nama.setBackground(new java.awt.Color(255, 255, 255));
        lb_nama.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        lb_nama.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_nama.setText("Nama");
        lb_nama.setPreferredSize(new java.awt.Dimension(47, 30));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb_nama, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
                    .addComponent(lb_level, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb_nama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(lb_level, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panel_sidebarLayout = new javax.swing.GroupLayout(panel_sidebar);
        panel_sidebar.setLayout(panel_sidebarLayout);
        panel_sidebarLayout.setHorizontalGroup(
            panel_sidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
        );
        panel_sidebarLayout.setVerticalGroup(
            panel_sidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_sidebarLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1))
        );

        jPanel2.setBackground(new java.awt.Color(43, 42, 76));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/awal.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Point De Vente");

        lb_tanggal.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        lb_tanggal.setForeground(new java.awt.Color(255, 255, 255));
        lb_tanggal.setText("Date");

        jButton1.setText("Profile");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        lb_tanggal1.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        lb_tanggal1.setForeground(new java.awt.Color(255, 255, 255));
        lb_tanggal1.setText("Date :");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(jLabel1)
                .addGap(84, 84, 84)
                .addComponent(jLabel2)
                .addGap(228, 228, 228)
                .addComponent(lb_tanggal1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap(605, Short.MAX_VALUE)
                    .addComponent(lb_tanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jButton1)
                    .addGap(51, 51, 51)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel2))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lb_tanggal1)))
                .addContainerGap())
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(14, 14, 14)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1)
                        .addComponent(lb_tanggal))
                    .addContainerGap(12, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout panel_contentLayout = new javax.swing.GroupLayout(panel_content);
        panel_content.setLayout(panel_contentLayout);
        panel_contentLayout.setHorizontalGroup(
            panel_contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_contentLayout.createSequentialGroup()
                .addComponent(panel_sidebar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pn_utama, javax.swing.GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE))
            .addGroup(panel_contentLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panel_contentLayout.setVerticalGroup(
            panel_contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_contentLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(panel_contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pn_utama, javax.swing.GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE)
                    .addComponent(panel_sidebar, javax.swing.GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE)))
        );

        getContentPane().add(panel_content, java.awt.BorderLayout.CENTER);

        setSize(new java.awt.Dimension(896, 582));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        pn_utama.add(new DataDetail());
        pn_utama.repaint();
        pn_utama.revalidate();
        // if (user.getJenisUser().equals("PEMILIK")) {
        // } else if (user.getJenisUser().equals("KARYAWAN")) {
        //     pn_utama.add(new Form_DasbordKaryawan());
        //     pn_utama.repaint();
        //     pn_utama.revalidate();
        // }
    }//GEN-LAST:event_formWindowOpened

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
//        new Form_Akun().setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(DashboardSalle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DashboardSalle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DashboardSalle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DashboardSalle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DashboardSalle("deidine", "level").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb_level;
    private javax.swing.JLabel lb_nama;
    private javax.swing.JLabel lb_tanggal;
    private javax.swing.JLabel lb_tanggal1;
    private javax.swing.JPanel panel_content;
    private javax.swing.JPanel panel_sidebar;
    private javax.swing.JPanel pn_menu;
    private javax.swing.JPanel pn_utama;
    // End of variables declaration//GEN-END:variables

    private void execute() {
        // ImageIcon IconHome = new ImageIcon(getClass().getResource("/icon/add.png"));
        ImageIcon IconMaster = new ImageIcon(getClass().getResource("/icon/ok.png"));
        ImageIcon IconTransaksi = new ImageIcon(getClass().getResource("/icon/add.png"));
        ImageIcon IconBarang = new ImageIcon(getClass().getResource("/icon/money.png"));
        ImageIcon IconLogout = new ImageIcon(getClass().getResource("/icon/user.png"));

        MenuItem salleMenu = new MenuItem(null, true, IconBarang, "Afficher A_Cash", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pn_utama.removeAll();
                pn_utama.add(new InformationSale());
                pn_utama.repaint();
                pn_utama.revalidate();
            }
        });
        MenuItem loanMenu = new MenuItem(null, true, IconBarang, "Afficher A_Terme", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pn_utama.removeAll();
                pn_utama.add(new InformationLoan());
                pn_utama.repaint();
                pn_utama.revalidate();
            }
        });
        MenuItem devisMenu = new MenuItem(null, true, IconBarang, "Afficher A_Devis", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pn_utama.removeAll();
                pn_utama.add(new InformationDevis(username));
                pn_utama.repaint();
                pn_utama.revalidate();
            }
        });
        MenuItem tvaMenu = new MenuItem(null, true, IconBarang, "Afficher TVA", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pn_utama.removeAll();
                pn_utama.add(new InformationTVA());
                pn_utama.repaint();
                pn_utama.revalidate();
            }
        });
        MenuItem CaisseloanMenu = new MenuItem(null, true, IconBarang, "Caisse Cash", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pn_utama.removeAll();
                pn_utama.add(new Caisse());
                pn_utama.repaint();
                pn_utama.revalidate();
            }
        });
        MenuItem CaisseSalleMenu = new MenuItem(null, true, IconBarang, "Caisse A_Terme", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pn_utama.removeAll();
                pn_utama.add(new CaisseLoan());
                pn_utama.repaint();
                pn_utama.revalidate();
            }
        });
        MenuItem menuReporSalle = new MenuItem(null, true, IconMaster, "Vende A_Cash", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pn_utama.removeAll();
                pn_utama.add(new DataDetail());
                pn_utama.repaint();
                pn_utama.revalidate();
            }
        });
        MenuItem menuMasterPemilik = new MenuItem(IconMaster, false, null, "Master", null);//, masKategory, masMerek, masSupplier, masUser);
        MenuItem menuTransaction = new MenuItem(IconTransaksi, false, null, "Transaction", null, salleMenu, loanMenu, devisMenu, tvaMenu);//, Sell, Restock);
        MenuItem menuCaisse = new MenuItem(IconTransaksi, false, null, "La Caisse", null, CaisseloanMenu, CaisseSalleMenu);//, Sell, Restock);
        MenuItem menuDataRaport = new MenuItem(IconTransaksi, false, null, "raport Aujourd'huit", null, menuReporSalle);//, Sell, Restock);

        MenuItem menuLogOut = new MenuItem(IconLogout, false, null, "Log Out", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int a = JOptionPane.showConfirmDialog(null, "tu veux fermer le system?", "Select", JOptionPane.YES_NO_OPTION);
                JOptionPane.setRootFrame(null);
                if (a == JOptionPane.YES_OPTION) {
                    dispose();
                    new SallePage(username).setVisible(true);
//                    System.exit(0);
                }
            }
        });

        MenuItem menuBackup = new MenuItem(IconLogout, false, null, "sauvegarde", new ActionListener() {
 
                   @Override
             
            public void actionPerformed(ActionEvent e) {
                   JOptionPane.showMessageDialog(null, "L'operation est Terminer");
           
                try {
                     Utils.Backupdbtosql();
                } catch (AWTException ex) {
                    Logger.getLogger(DashboardSalle.class.getName()).log(Level.SEVERE, null, ex);
                }
                 
                
            }
            
        });

        addMenu(menuMasterPemilik, menuDataRaport, menuTransaction, menuCaisse, menuLogOut, menuBackup);

    }

    private void addMenu(MenuItem... menu) {
        for (MenuItem menu1 : menu) {
            pn_menu.add(menu1);
            ArrayList<MenuItem> subMenu = menu1.getSubMenu();
            for (MenuItem m : subMenu) {
                addMenu(m);
            }
        }
        pn_menu.revalidate();
    }

}