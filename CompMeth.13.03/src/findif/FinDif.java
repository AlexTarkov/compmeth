/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package findif;

/**
 *
 * @author alex
 */
public class FinDif {
    
    static final int DEG = 4;
    
    public static double[] GetDiff(double[] inp) {
        double[] res = new double[inp.length-1];
        for (int i = 0; i < inp.length - 1; i++) {
            res[i] = inp[i+1]-inp[i];
        }
        return res;
    }
    
    public static void WriteResult(double[][] inp) {
        System.out.println("    f         df          ddf       dddf       ddddf");
        for (int i = 0; i < inp[0].length; i++) {
            int l = 0;
            while ((l <= DEG) && (inp[l].length > i)) {
                System.out.print(String.format("%.6f", inp[l][i])  + " | ");
                l++;
            }
            System.out.println();
            
        }
    }
    
    public static void main(String[] args) {
        
        double[] buf = {1.623250, 1.664792, 
            1.701977, 1.734832, 
            1.763404, 1.787764,
            1.808002, 1.824230, 
            1.836580, 1.845201};
        
        double[][] result = new double[DEG+1][];
        
        result[0] = buf;
        for (int i = 1; i <= DEG; i++) {
            buf = GetDiff(buf);
            result[i] = buf;
        }
        
        WriteResult(result);
        
    }
}
