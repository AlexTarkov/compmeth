/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package differential;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
//import javax.swing.*;

/**
 *
 * @author alex
 */
public class MainFrame extends javax.swing.JFrame {

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new Panel2();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 770, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 508, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MainFrame mf = new MainFrame();
                mf.setVisible(true);
                mf.go();
            }
        });
    }
    
    public void go() {
        //myInit();
    }
    
    public void myInit() {
        
        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 575, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 328, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}

class Panel2 extends javax.swing.JPanel {

    double[] e1, e2, e3, ad, rk;
    double x0,y0;
    
    int W = 400;
    int H = 400;
    
    int n;
    
    public static void  pa(double[] x) {
        for (int i = 0; i < x.length; i++){
            System.out.print(x[i] + " ");
        }
        System.out.println();
    }
    
    public Panel2() {
        
        double x0 = 0;
        double y0 = 0;
        
        this.x0 = x0;
        this.y0 = y0;
        
        double a = 1;
        double k = 0.5;
        
        int n = 10;
        this.n = n;
        
        double h = 0.1;
        
        FunctionObject f = new FunctionObject(a, k);
        
        e1 = Euler.compute(f, x0, y0, n, h);
        e2 = Euler.compute(f, x0, y0, n * 2, h/2);
        e3 = Euler.compute(f, x0, y0, n / 2, h*2);
        
        rk = RungeKutt.compute(f, x0, y0, n, h);
        
        ad = Adams.compute(f, x0, y0, n, h);
        
        pa(e1);
        pa(e2);
        pa(e3);
        pa(rk);
        pa(ad);
        //pa(Adams.compute(f, 0, 0, 10, 0.1));
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        double x = x0; 
        double y = y0;
        
        Point p1, p2;
        p1 = gc(0,0);
        p2 = gc(2,0);
        
        g.drawLine(p1.x, p1.y, p2.x, p2.y);
        
        p1 = gc(0,0);
        p2 = gc(0,2);
        
        g.drawLine(p1.x, p1.y, p2.x, p2.y);
        
        
        //pGr(g, rk, 0.1);
        
        //g.setColor(Color.red);
        
        pGr(g, e1, 0.1);
        
        pGr(g, e2, 0.05);
        
        pGr(g, e3, 0.2);
        
        g.setColor(Color.red);
        
        paGr(g, ad, 0.1);
        
//        Point p1, p2;
//        
//        p1 = gc(x0, y0);
//        
//        for (int i = 0; i < e1.length; i++) {
//            //p1 = gc(x,y);
//            p2 = gc(x + 0.1, e1[i]);
//            g.drawLine(p1.x, p1.y, p2.x, p2.y);
//            p1 = p2;
//        }
//        
//        p1 = gc(x0, y0);
//        
//        for (int i = 0; i < e2.length; i++) {
//            //p1 = gc(x,y);
//            p2 = gc(x + 0.05, e2[i]);
//            g.drawLine(p1.x, p1.y, p2.x, p2.y);
//            p1 = p2;
//        }
//        
//        p1 = gc(x0, y0);
//        
//        for (int i = 0; i < e1.length; i++) {
//            //p1 = gc(x,y);
//            p2 = gc(x + 0.1, e1[i]);
//            g.drawLine(p1.x, p1.y, p2.x, p2.y);
//            p1 = p2;
//        }
        
        
        
    }
    
    void pGr(Graphics g, double[] y, double h) {
        Point p1, p2;
        
        p1 = gc(x0, y0);
        
        double x = x0;
        
        for (int i = 0; i < y.length; i++) {
            x = x + h;
            p2 = gc(x + h, y[i]);
            g.drawLine(p1.x, p1.y, p2.x, p2.y);
            p1 = p2;
        }
    }
    
    void paGr(Graphics g, double[] y, double h) {
        Point p1, p2;
        
        p1 = gc(x0, y0);
        
        double x = x0;
        
        for (int i = 1; i < y.length; i++) {
            x = x + h;
            p2 = gc(x + h, y[i]);
            g.drawLine(p1.x, p1.y, p2.x, p2.y);
            p1 = p2;
        }
    }
    
    private Point gc(double x, double y) {
        return new Point(20 + (int)(x*W/2),(int)(H-(y*H/2))); // 2 - MAGIC NUMBER
    }
}
