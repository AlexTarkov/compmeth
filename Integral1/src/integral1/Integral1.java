/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package integral1;

/**
 *
 * @author alex
 */
public class Integral1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        (new Integral1()).run();
    }
    
    public void run() {
        FunctionObject func1 = new FunctionObject() {

            @Override
            double getFunctionResult(double x) {
                return Math.sin(x);
            }

            @Override
            double getMaxFunctionDerivative(int n) {
                return 1;
            }
        };
        
        outTable(func1, 0, Math.PI);
        
    }
    
    public double[] getRectangleCenterInterpolation(FunctionObject func, double a, double b) {
        double[] result = new double[5];
        // Y8, Y16, YR, A8, A16
        
        int n1 = 8; 
        double h1 = (b - a) / 8;
        int n2 = 16; 
        double h2 = (b - a) / 16;
        
        double y8 = 0;
        double y16 = 0;
        double yr;
        
        for (int i = 0; i < n2; i++) {
            y16 += func.getFunctionResult(a + (h2/2) + i * h2);
            System.out.println(((i % 2 == 0) ? func.getFunctionResult(a + (h1/2) + i * h1) : 0));
            y8 = y8 + ((i % 2 == 0) ? func.getFunctionResult(a + (h1/2) + (i/2) * h1) : 0);
        }
        
        y8 *= h1;
        y16 *= h2;
        
        result[0] = y8;
        result[1] = y16;
        
        yr = (y16 * 4 - y8) / 3;
        
        double a8 = (b-a)*(b-a)*(b-a) / (24 * n1*n1) * func.getMaxFunctionDerivative(2);
        double a16 = (b-a)*(b-a)*(b-a) / (24 * n2*n2) * func.getMaxFunctionDerivative(2);
        
        result[2] = yr;
        result[3] = a8;
        result[4] = a16;
        
        return result;
    }
    
    public double[] getTrapezeInterpolation(FunctionObject func, double a, double b) {
        double[] result = new double[5];
        // Y8, Y16, YR, A8, A16
        
        int n1 = 8; 
        double h1 = (b - a) / 8;
        int n2 = 16; 
        double h2 = (b - a) / 16;
        
        double y8 = (func.getFunctionResult(a) + func.getFunctionResult(b)) / 2;
        double y16 = (func.getFunctionResult(a) + func.getFunctionResult(b)) / 2;;
        double yr;
        
        for (int i = 1; i < n2; i++) {
            y16 += func.getFunctionResult(a + h2 * i);
            y8 += (i % 2 == 1) ? func.getFunctionResult(a + h1 * (i/2+1)) : 0;
        }
        
        y8 *= h1;
        y16 *= h2;
        
        result[0] = y8;
        result[1] = y16;
        
        yr = (y16 * 4 - y8) / 3;
        
        double a8 = -(b-a)*(b-a)*(b-a) / (12 * n1*n1) * func.getMaxFunctionDerivative(2);
        double a16 = -(b-a)*(b-a)*(b-a) / (12 * n2*n2) * func.getMaxFunctionDerivative(2);
        
        result[2] = yr;
        result[3] = a8;
        result[4] = a16;
        
        return result;
    }
    
    public double[] getSimpsonInterpolation(FunctionObject func, double a, double b) {
        double[] result = new double[5];
        // Y8, Y16, YR, A8, A16
        
        int n1 = 8; 
        double h1 = (b - a) / 8;
        int n2 = 16; 
        double h2 = (b - a) / 16;
        
        double y8 = (func.getFunctionResult(a) + func.getFunctionResult(b));
        double y16 = (func.getFunctionResult(a) + func.getFunctionResult(b));
        double yr;
        
        for (int i = 1; i < n2; i++) {
            y16 += 4*func.getFunctionResult(a + (2*i-1)*h2/2) + 2*func.getFunctionResult(a + i*h2);
            y8 += (i % 2 == 1) ? 4*func.getFunctionResult(a + (2*(i/2+1)-1)*h1/2) + 2*func.getFunctionResult(a + (i/2+1)*h1) : 0;
            //y8 += ((i % 2 == 0) ? func.getFunctionResult(a + h1 * i) : 0) * (i % 4 == 2 ? 4 : 2);
        }
        
        y8 *= h1 / 6;
        y16 *= h2 / 6;
        
        result[0] = y8;
        result[1] = y16;
        
        yr = (y16 * 16 - y8) / 15;
        
        double a8 = -(b-a)*(b-a)*(b-a)*(b-a)*(b-a) / (2880 * n1*n1* n1*n1) * func.getMaxFunctionDerivative(4);
        double a16 = -(b-a)*(b-a)*(b-a)*(b-a)*(b-a) / (2880 * n2*n2* n2*n2) * func.getMaxFunctionDerivative(4);
        
        result[2] = yr;
        result[3] = a8;
        result[4] = a16;
        
        return result;
    }
    
    void outTable(FunctionObject func, double a, double b) {
        int n = 5;
        
        int len = 9;
        
        double[] buf;
        
        System.out.println(String.format("%"+len+"s | %"+len+"s | %"+len+"s | %"+len+"s | %"+len+"s", 
                    "8" , 
                    "16" , 
                    "YR" , 
                    "A8" , 
                    "A16"  
                    ));
        System.out.println("Rectangle");
        buf = getRectangleCenterInterpolation(func, a, b);
        
        System.out.println(String.format("%"+len+"s | %"+len+"s | %"+len+"s | %"+len+"s | %"+len+"s", 
                    String.format("%.5f", buf[0]) , 
                    String.format("%.5f", buf[1]) , 
                    String.format("%.5f", buf[2]) , 
                    String.format("%.5f", buf[3]) , 
                    String.format("%.5f", buf[4])  
                    ));
        //System.out.println();
        
        System.out.println("Trapeze");
        buf = getTrapezeInterpolation(func, a, b);
        System.out.println(String.format("%"+len+"s | %"+len+"s | %"+len+"s | %"+len+"s | %"+len+"s", 
                    String.format("%.5f", buf[0]) , 
                    String.format("%.5f", buf[1]) , 
                    String.format("%.5f", buf[2]) , 
                    String.format("%.5f", buf[3]) , 
                    String.format("%.5f", buf[4])  
                    ));
        //System.out.println();
        
        System.out.println("Simpson");
        buf = getSimpsonInterpolation(func, a, b);
        System.out.println(String.format("%"+len+"s | %"+len+"s | %"+len+"s | %"+len+"s | %"+len+"s", 
                    String.format("%.5f", buf[0]) , 
                    String.format("%.5f", buf[1]) , 
                    String.format("%.5f", buf[2]) , 
                    String.format("%.5f", buf[3]) , 
                    String.format("%.5f", buf[4])  
                    ));
        //System.out.println();
    }
    
}
