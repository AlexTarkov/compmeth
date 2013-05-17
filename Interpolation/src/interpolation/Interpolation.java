/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interpolation;

/**
 *
 * @author alex
 */
public class Interpolation {
    
    static void out(String a) {
        //System.out.println(a);
    }
    
    static void out(double a) {
        //out("" + a);
    }
    
    static void printArray(double[] x) {
        for (int i = 0; i < x.length; i++){
            System.out.print(x[i] + " ");
        }
        System.out.println();
    }
    
    private static int DEG = 4;
    private static double EPS = 0.00001;
    
    private static double K = 0;
//==============================================================================
//= WORK TO EXAMPLE FROM BOOK
//    public static double getT(double x, double[] setx) {
//        double t = 10000;
//        double diffx = Math.abs(setx[0] - setx[1]);
//        
//        for (int i = 0; i < setx.length; i++) {
//            t = (Math.abs(x - setx[i]) < t) ? x - setx[i] : t;
//        }
//        return t / diffx; //NO (t/diffx)
//    }
//    
//    static int getSetXNumber(double[] setx, double x, double t) {
//        double newx = x - t * Math.abs(setx[0] - setx[1]);
//        //out("!" + newx);
//        for (int i = 0; i < setx.length; i++) {
//            //out("setx["+i+"] - newx: " + (setx[i] - newx));
//            if ( Math.abs(setx[i] - newx) < 0.0001 ) {return i; }
//        }
//        return 0; 
//    }
//==============================================================================
    public static double getT(double x, double[] setx) { //накладывает ограничение, при котором х только возрастающий
        double t = 10000;
        double diffx = Math.abs(setx[0] - setx[1]);
//        double buff1, buff2;
//        for (int i = 0; i < setx.length; i++) {
//            buff1 = Math.abs(x - setx[i]);
//            buff1 = (buff1 > 0) ? buff1 : 10000;
//            buff2 = Math.abs(setx[setx.length - i - 1] - x);
//            buff2 = (buff2 > 0) ? buff2 : 10000;
//            out("buf1: " + buff1);
//            out("buf2: " + buff2);
//            t = (buff1 < Math.abs(t)) ? x - setx[i] : t;
//            t = (buff2 < Math.abs(t)) ? x - setx[setx.length - i - 1] : t;
//        }
//        out("T:" + t);
        
        for (int i = 1; i < setx.length; i++) {
            if (setx[i] > x) {
                t = x - setx[i-1]; break;
            }
            if (setx[setx.length-1 - i] < x) {
                t = x - setx[setx.length-1 - i + 1]; break;
            }
        }
        out("t: " + (t / diffx));
        return t / diffx;
    }
    
    
    public static double getTEnd(double x, double[] setx) { //накладывает ограничение, при котором х только возрастающий
        double t = 10000;
        double diffx = Math.abs(setx[0] - setx[1]);

        for (int i = 1; i < setx.length; i++) {
//            if (setx[i] > x) {
//                t = x - setx[i-1]; break;
//            }
            if (setx[setx.length-1 - i] < x) {
                t = x - setx[setx.length-1 - i + 1]; break;
            }
        }
        out("t: " + (t / diffx));
        return t / diffx;
    }
    
    
    
//==============================================================================

    static int getSetXNumber(double[] setx, double x, double t) {
        double newx = x - t * Math.abs(setx[0] - setx[1]);
        //out("!" + newx);
        for (int i = 0; i < setx.length; i++) {
            //out("setx["+i+"] - newx: " + (setx[i] - newx));
            if ( Math.abs(setx[i] - newx) < 0.0001 ) { 
                out("num: " + i); 
                return i; 
            }
        }
        return 0; 
    }
    
    public static double[] GetDiff(double[] inp) {
        double[] res = new double[inp.length-1];
        for (int i = 0; i < inp.length - 1; i++) {
            res[i] = inp[i+1]-inp[i];
        }
        return res;
    }
    
    public static double[][] getDiffTable(double[] sety) {
        double[][] result = new double[DEG+1][];
        double[] buf = sety;
        result[0] = sety;
        for (int i = 1; i <= DEG; i++) {
            buf = GetDiff(buf);
            result[i] = buf;
        }
        return result;
    }
    
    public static double[] getSetC(int n, double t) {
        
        double p = 1;
        double tt = 1;
        double[] setc = new double[n+1];
        
        setc[0] = 1;
        for (int i = 1; i <= n; i++) {
            tt = tt * (t - i + 1);
            p *= i;
            setc[i] = tt / p;
        }
        return setc;
    }
    
    public static double[] getSetCenterC(int n, double t) {
        
        double p = 1;
        double tt = 1;
        double[] setc = new double[n+1];
        
        setc[0] = tt / p;
        
        int m = -1;
        for (int i = 1; i <= n; i++) {
            m *= -1;
            tt = tt * (t + m * (i / 2));
            p *= i;
            setc[i] = tt / p;
        }
        return setc;
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // Пример из учебника
//        double[] setx = { 0, 0.1, 0.2, 0.3, 0.4, 0.5, 0.6 };
//        double[] sety = { 1, 0.995 , 0.98007, 0.95534, 0.92106, 0.87758, 0.82534 };
//        double bx = 0.048;
//        double cx = ;
//        double ex = 0.575;
        
        double[] setx = { 0, 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 07, 0.8, 0.9 };
        double[] sety = { 1.623250, 1.664792, 1.701977, 1.734832, 
            1.763404, 1.787764, 1.808002, 1.824230, 1.836580, 1.845201};
        
        double bx = 0.172544;
        double cx = 0.269765;
        double ex = 0.815445;
        
        double y = 1.739372;
        
        System.out.println("IntB: " + getBeginInterpolationY(bx, setx, sety)); // [B]
        
        System.out.println("IntC(B): " + getBeginInterpolationY(cx, setx, sety)); // [B]
        System.out.println("IntC: " + getCenterInterpolationY(cx, setx, sety)); // [?]
        System.out.println("IntC(E): " + getEndInterpolationY(cx, setx, sety)); // [B]
        
        System.out.println("IntC(Begin) - IntC = " + String.format("%.10f", getBeginInterpolationY(cx, setx, sety) - 
                getCenterInterpolationY(cx, setx, sety))); // [B]
        
        System.out.println("IntC(End) - IntC = " + String.format("%.10f", getEndInterpolationY(cx, setx, sety) - 
                getCenterInterpolationY(cx, setx, sety))); // [B]
        
        System.out.println("IntE: " + getEndInterpolationY(ex, setx, sety)); // [B]
        
        
        double revint = getReverseInterpolation(y, setx, sety);
        System.out.println("RevInt: " + revint + " (k = " + K + ")"); // [?]
        double begint = getBeginInterpolationY(revint, setx, sety);
        System.out.println("(CheckRevInt: " + begint + ")" ); // [?]
        System.out.println("Полученное - Заданное = " + String.format("%.10f", begint - y)); // [?]
        
        //printArray(getSetC(4, 0.48));  
    }
    
    public static double getBeginInterpolationY(double x, double[] setx, double[] sety) {
        double y = 0;
        
        double diffx = Math.abs(setx[0] - setx[1]);
        double t = getT(x, setx);
        //out("t: " + t);
        double[][] difftable = getDiffTable(sety);
        
        double[] setc = getSetC(DEG, t);
        
        int num = getSetXNumber(setx, x, t);
        
        for (int i = 0; i <= DEG; i++) {
            y += setc[i] * difftable[i][num];
        }
        
        return y;
    }
    
    public static double getEndInterpolationY(double x, double[] setx, double[] sety) {
        double y = 0;
        
        double diffx = Math.abs(setx[0] - setx[1]);
        double t = getT(x, setx);
        //out("t: " + t);
        double[][] difftable = getDiffTable(sety);
        
        double[] setc = getSetC(DEG, -t);
        
        int m = -1;
        int num = getSetXNumber(setx, x, t);
        
        for (int i = 0; i <= DEG; i++) {
            //System.out.println("i = " + i + " : " + (num-i));
            if((i < 0) || (num-i)<0) break;
            m *= -1; // возможно не нужно. в конспекте нет изменения знака.
            y += m * setc[i] * difftable[i][num-i];
        }
        
        return y;
    }
    
    public static double getCenterInterpolationY(double x, double[] setx, double[] sety) {
        double y = 0;
        
        double diffx = Math.abs(setx[0] - setx[1]);
        double t = getT(x, setx);
        double[][] difftable = getDiffTable(sety);
        
        double[] setc = getSetCenterC(DEG, t);
        
        int num = getSetXNumber(setx, x, t);
        int p = 0;
        for (int i = 0; i <= DEG; i++) {
            y += setc[i] * difftable[i][num - (p / 2)];
            p++;
        }
        
        return y;
    }
    
    //--------------------------------------------------------------------------
    
    public static double getReverseInterpolation(double y, double[] setx, double[] sety) {
        double result;
        
        int yindex = getNearestYIndex(y, sety);
        //out("yt = " + ty);
        double p = sety[yindex];
        double t = 0;
        
        double x = setx[yindex];
        int k = 0;   
        
        double lasty = p;
        
        while ((p > EPS)) {
            k++;
            t = getNextFiX(y, t, sety, yindex);
            p = Math.abs(lasty - getBeginInterpolationY(setx[yindex] + t * (setx[1] - setx[0]), setx, sety));
            lasty = getBeginInterpolationY(setx[yindex] + t * (setx[1] - setx[0]), setx, sety);
            out("delP: " + p);
            System.out.print("RevInt: " + (setx[yindex] + t * (setx[1] - setx[0])) + " (k = " + k + ")"); // [?]
            System.out.println("(CheckRevInt: " + lasty + ")"); // [?]
        }
        out("K: " + k);
        K = k;
        result = setx[yindex] + t * (setx[1] - setx[0]);
        return result;
    }
    
    public static int getNearestYIndex(double y, double[] sety) {
        for (int i = 0; i < sety.length; i++) {
            if (sety[i] > y) {
                return i - 1;
            }
        }
        return -1;
    }
    
    public static double getNextFiX(double y, double t, double[] sety, int yindex) {
        double result = y - sety[yindex];
        
        double[][] difftable = getDiffTable(sety);
        
        result -= (t * (t - 1) / 2) * difftable[2][yindex];
        result -= (t * (t - 1) * (t - 2) / 6) * difftable[3][yindex];
        result -= (t * (t - 1) * (t - 2) * (t - 3) / 24) * difftable[4][yindex];
        
        result = result / difftable[1][yindex];
        
        return result;
    }
    
}
