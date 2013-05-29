/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package differential;

/**
 *
 * @author alex
 */
public class RungeKutt {
    public static double[] compute(FunctionObject f, double x0, double y0, int n, double h) {
        
        double[] k = new double[4];
        
        double[] r = new double[n];
        
        double x = x0;
        double y = y0;
        
        for (int i = 0; i < n; i++) {
            k[0] = h * f.gv(x, y);
            k[1] = h * f.gv(x + h/2, y + k[0]/2);
            k[2] = h * f.gv(x + h/2, y + k[1]/2);
            k[3] = h * f.gv(x + h, y + k[2]);
            
            y = y + (1.0/6) * (k[0] + 2 * (k[1] + k[2]) + k[3]);
            x = x + h;
            r[i] = y;
        }
        
        return r;
    }
}
