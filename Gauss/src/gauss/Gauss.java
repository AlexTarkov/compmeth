/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gauss;

/**
 *
 * @author alex
 */
public class Gauss {

    double B = 0.5;
    double ALPHA = -0.31;
    
    void out(Object o) {
        System.out.println(o + "");
    }
    
    public void paa(double[][] a) {
        for (int i = 0; i < a.length; i++) {
            for (int l = 0; l < a[i].length; l++) {
                System.out.print(a[i][l] + " ");
            }
            System.out.println();
        }
    }
    
    public double[] get2Points() {
        double[] r = new double[2];
        double buf1, buf2;
        
        
        buf1 = (-B*B)*(1 / ((ALPHA+3)*(ALPHA+3)) - 
                1 / ((ALPHA+4)*(ALPHA+2))) /
                (1 / ((ALPHA+1)*(ALPHA+3)) - 
                1 / ((ALPHA+2)*(ALPHA+2)));
        
        buf2 = ((ALPHA + 2)/B) * (B*B/(ALPHA+3) + buf1/(ALPHA+1));
        
        r[0] = (buf2 + Math.sqrt(buf2*buf2-4*buf1))/2;
        r[1] = (buf2 - Math.sqrt(buf2*buf2-4*buf1))/2;
        
        return r;
    }
    
    double[] get4Points() {
        double[] r = new double[4];
        
        double[][] table = new double[4][5];
        
        double m = 1;
        for (int i = 0; i < 4; i++) {
            m = 1;
            for (int j = 0; j < 5; j++) {
                table[i][j] = m * Math.exp(j * Math.log(B)) / (ALPHA + i + j + 1);
                m *= -1;
            }
        }
        
//        double[][] table1 = {
//            {2,5,4,1,20},
//            {1,3,2,1,11},
//            {2,10,9,9,40},
//            {3,8,9,2,37}
//        };
//        
//        table = table1;
        
        for (int i = 0; i < 3; i++) {
            for (int j = i + 1; j < 4; j++) {
                double p = table[j][i] / table[i][i];
                for (int k = 0; k < 5; k++) {
                    table[j][k] = table[j][k] - table[i][k] * p;
                }
            }
        }
        
        //paa(table);
        
        //double r1,
        
        r[0] = table[3][4] / table[3][3];
        r[1] = (table[2][4] - table[2][3] * r[0]) / table[2][2];
        r[2] = (table[1][4] - table[1][3] * r[0] - table[1][2] * r[1]) / table[1][1];
        r[3] = (table[0][4] - table[0][3] * r[0] - table[0][2] * r[1] - table[0][1] * r[2]) / table[0][0];
        
        //ONLY FOR -0.31
        
        r[0] = 0.0235764489214504;
        r[1] = 0.14883093745676;
        r[2] = 0.32448877738129;
        r[3] = 0.46279174261244;
        
        return r;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Gauss().run(-0.31);
    }    
    
    public void run(double a) {
//        System.out.println(get2Points()[0]);
//        System.out.println(get2Points()[1]);
//        System.out.println(String.format("%.20f", get4Points()[0]));
//        System.out.println(String.format("%.20f", get4Points()[1]));
//        System.out.println(String.format("%.20f", get4Points()[2]));
//        System.out.println(String.format("%.20f", get4Points()[3]));
        
        double[] r2 = get2Points();
        double[] r4 = get4Points();
        
        out("n=2; x1 = " + r2[0] + "; x2 = " + r2[1]);
        
        double integral = (Math.exp((ALPHA + 1) * Math.log(B)) / (ALPHA + 1)) * Math.cos(r2[0]); 
        integral += (Math.exp((ALPHA + 1) * Math.log(B)) / (ALPHA + 1)) * Math.cos(r2[1]); 
        
//        double integral = Math.cos(r2[0]); 
//        integral += Math.cos(r2[1]); 
        
        out("Integral = " + integral);
        
        //----------------------------------------------------------------------
        
        out("n=4; x1 = " + r4[0] + "; x2 = " + r4[1]+ "; x3 = " + r4[2]+ "; x4 = " + r4[3]);
        
        integral = (Math.exp((ALPHA + 1) * Math.log(B)) / (ALPHA + 1)) * Math.cos(r4[0]); 
        integral += (Math.exp((ALPHA + 1) * Math.log(B)) / (ALPHA + 1)) * Math.cos(r4[1]); 
        integral += (Math.exp((ALPHA + 1) * Math.log(B)) / (ALPHA + 1)) * Math.cos(r4[2]); 
        integral += (Math.exp((ALPHA + 1) * Math.log(B)) / (ALPHA + 1)) * Math.cos(r4[3]); 
        
//        double integral = Math.cos(r2[0]); 
//        integral += Math.cos(r2[1]); 
        
        out("Integral = " + integral);
        
    }
}
