/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package differential;

/**
 *
 * @author alex
 */
public class Euler {
    public static double[] compute(FunctionObject f, double x0, double y0, int n, double h) {
        
        double[] r = new double[n];
        
        double x = x0;
        double y = y0;
        
        for (int i = 0; i < n; i++) {
            y = y + h * f.gv(x, y);
            r[i] = y;
            x = x + h;
        }
        
        return r;
    }  
}
