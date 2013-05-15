/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lagrangeinterpolation;

/**
 *
 * @author alex
 */
public class LaGrangeInterpolation {
    
    void out(String s) {
        System.out.println(s);
    }
    
    void out(double s) {
        System.out.println(s);
    }
    
    void outTable(FunctionObject func, double[] x) {
        int n = x.length;
        
        int len = 9;
        
        double[] buf = new double[5];
        
        System.out.print(String.format("%"+len+"s | %"+len+"s | %"+len+"s | %"+len+"s | %"+len+"s", 
                    "x[i]" , 
                    "f(x[i])" , 
                    "L[n](f,x)" , 
                    "|f-L[n]|" , 
                    "A"  
                    ));
            System.out.println();
        
        for (int i = 0; i < n; i++) {
            buf[0] = x[i];
            buf[1] = func.getFunctionResult(x[i]);
            buf[2] = func.computeLagrangeInterpolation(x[i]);
            buf[3] = Math.abs(func.getFunctionResult(x[i]) - func.computeLagrangeInterpolation(x[i]));
            buf[4] = func.getA(x[i]);
            System.out.print(String.format("%"+len+"s | %"+len+"s | %"+len+"s | %"+len+"s | %"+len+"s", 
                    String.format("%.5f", buf[0]) , 
                    String.format("%.5f", buf[1]) , 
                    String.format("%.5f", buf[2]) , 
                    String.format("%.5f", buf[3]) , 
                    String.format("%.5f", buf[4])  
                    ));
            System.out.println();
        }
    }
    
    double[] get20Point(double x1, double x2) {
        double[] res = new double[20];
        
        double dx = (x2 - x1) / 19;
        
        for (int i = 0; i < 20; i++) {
            res[i] = x1 + i * dx;
            //out(res[i]);
        }
        
        return res;
    }
    
    void run() {
        double pi = Math.PI;
        
        double[] xi1 = {pi / 5, pi / 3, 3 * pi / 4, pi};
        double[] xiwiki = {-1.5, -0.75, 0, 0.75, 1.5};
        
        FunctionObject func1 = new FunctionObject(xi1) {

            @Override
            double getFunctionResult(double x) {
                return Math.sin(x);
            }

            @Override
            double getMaxFunctionDerivative(int n) {
                return 1;
            }
        };
    
        FunctionObject func2 = new FunctionObject(xi1) {

            @Override
            double getFunctionResult(double x) {
                return Math.sin(8*x);
            }

            @Override
            double getMaxFunctionDerivative(int i) {
                return 0;
            }
        };
        
        double[] xiI = get20Point(0, pi / 2);
        double[] xiII = get20Point(pi / 2, pi);
        double[] xiIII = get20Point(pi / 4, pi * 3 / 4);
        double[] xiIV = get20Point(0, pi);
        
        outTable(func1, xiI);
        out("");
        outTable(func1, xiII);
        out("");
        outTable(func1, xiIII);
        out("");
        outTable(func1, xiIV);
        
        FunctionObject funcwiki = new FunctionObject(xiwiki) {

            @Override
            double getFunctionResult(double x) {
                return Math.tan(x);
            }

            @Override
            double getMaxFunctionDerivative(int n) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        };
        //double[] p = {0.25, 0.5, 0.75};
        //outTable(funcwiki, p);
    }
    
    public static void main(String[] args) {         
        new LaGrangeInterpolation().run();
    }
}
