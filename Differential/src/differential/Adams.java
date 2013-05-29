/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package differential;

//import java.util.*;
import static differential.Test.*;

/**
 *
 * @author alex
 */
public class Adams {
    public static double[] compute(FunctionObject f, double x0, double y0, int n, double h) {
        double ys[] = RungeKutt.compute(f, x0, y0, 4, 0.1);
        
        double[] r = new double[n];
        
        r[0] = y0;
        System.arraycopy(ys, 0, r, 1, ys.length);
        
        pa(ys);
        
        double[] nu = new double[10];
        
        double x, y;
        x = x0;
        y = y0;
        
        for (int i = 0; i < 4; i++) {
            nu[i] = h * f.gv(x, y);
            //System.out.println(nu[i]);
            x +=h;
            y = ys[i];
        }
        nu[4] = h * f.gv(x, y);
        
        pa(nu);
        
        double[][] nutable = new double[10][10];
//        for (int i = 0; i < 5; i++) {
//            nutable[0][i] = nu[i];
//        }
        nutable[0] = nu;
        
        for (int i = 1; i < 5; i++) {
            for (int l = 0; l < 5 - i; l++) {
                nutable[i][l] = nutable[i-1][l+1] - nutable[i-1][l];
            }
        }
        
        for (int i = 0; i < 10; i++) {            
            for (int l = 0; l < 10; l++) 
                System.out.print(String.format("%.6f | ", Math.abs(nutable[i][l])));
            System.out.println();
        }
        
        for (int i = 5; i < n; i++) {
            r[i] = 0;
            double b = 0;
            b += nutable[i-1][i-5] * 251 / 720.0;
            System.out.println("1-"+nutable[i-1][i-5]);
            b += nutable[i-2][i-4] * 3 / 8.0;
            b += nutable[i-3][i-3] * 5 / 12.0;
            b += nutable[i-4][i-2] * 0.5;
            b += nutable[i-5][i-1];
            
            System.out.println(b);
            
            r[i] = r[i-1] + b;
            
            nutable[0][i] = h * f.gv(x0+h*i, r[i]);
            
            for (int l = i; l > l-5; l--) {
                nutable[i-l][l] = nutable[i-l-1][l+1] - nutable[i-l-1][l];
            }
//            for (int l = 0; l < 5; l++) {
//                 b += nutable[i-1][l]
//            }
        }
        
//        for (int i = 0; i < 10; i++) {            
//            for (int l = 0; l < 10; l++) 
//                System.out.print(nutable[i][l] + " ");
//            System.out.println();
//        }
        
        return r;
    }
}
