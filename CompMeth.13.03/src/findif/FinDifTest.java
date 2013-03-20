/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package findif;

/**
 *
 * @author alex
 */
public class FinDifTest {
    
    static final int DEG = 6;
    
    public static long[] GetDiff(long[] inp) {
        long[] res = new long[inp.length-1];
        for (int i = 0; i < inp.length - 1; i++) {
            res[i] = inp[i+1]-inp[i];
        }
        return res;
    }
    
    public static void WriteResult(long[][] inp) {
        for (int i = 0; i < inp[0].length; i++) {
            int l = 0;
            while ((l <= DEG) && (inp[l].length > i)) {
                //System.out.print(String.format("%", inp[l][i])  + " | ");
                System.out.print(Long.toString(inp[l][i])  + " | ");
                //System.out.print(String.format("######## | ",inp[l][i]));
                l++;
            }
            System.out.println();
            
        }
    }
    
    public static void main(String[] args) {
        
//        long[] buf = {1623250, 1664792, 
//            1701977, 1734832, 
//            1763404, 1787764,
//            1808002, 1824230, 
//            1836580, 1845201};
        
        long[] buf = {-4, -2, // from class example
            12, 44, 
            95, 186,
            308, 472, 
            684, 950};
        
        long[][] result = new long[DEG+1][];
        
        result[0] = buf;
        for (int i = 1; i <= DEG; i++) {
            buf = GetDiff(buf);
            result[i] = buf;
        }
        
        WriteResult(result);
        
    }
}
