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
                return 1 / (Math.exp(x) - 0.7);
            }

            @Override
            double getMaxFunctionDerivative(int n) {
                if (n==4) return 5940;
                if (n==2) return 63;
                return 0;
            }
        };
        
        int n = 5;
        
        int len = 10;
        int lendouble = 7;
        
        double a = 0;
        double b = 0.4;
        
        double[] buf;
        
        System.out.println(String.format("%"+len+"s | %"+len+"s | %"+len+"s | %"+len+"s | %"+len+"s", 
                    "n=8" , 
                    "n=16" , 
                    "Rooney" , 
                    "A8" , 
                    "A16"  
                    ));
        System.out.println("Rectangle");
        buf = getRectangleCenterInterpolation(func1, a, b);
        outTable(buf, len, lendouble);
        
        System.out.println("Trapeze");
        buf = getTrapezeInterpolation(func1, a, b);
        outTable(buf, len, lendouble);
        
        System.out.println("Simpson");
        buf = getSimpsonInterpolation(func1, a, b);
        outTable(buf, len, lendouble);
        
        //outTable(func1, 0, 0.4);
        
    }
    //[CHECK]
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
            y16 += func.getFunctionResult((a + (h2/2)) + i*h2);
        }
        
        for (int i = 0; i < n1; i++) {
            y8 += func.getFunctionResult((a + (h1/2)) + (i*h1));
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
        double h1 = (b - a) / n1;
        int n2 = 16; 
        double h2 = (b - a) / n2;
        
        double y8 = (func.getFunctionResult(a) + func.getFunctionResult(b)) / 2;
        double y16 = (func.getFunctionResult(a) + func.getFunctionResult(b)) / 2;
        double yr;
        
        for (int i = 1; i < n1; i++) {
            y8 += func.getFunctionResult(a + h1*i);
        }
        
        for (int i = 1; i < n2; i++) {
            y16 += func.getFunctionResult(a + h2*i);
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
        double h1 = (b - a) / n1;
        int n2 = 16; 
        double h2 = (b - a) / n2;
        
        double y8 = func.getFunctionResult(a) + func.getFunctionResult(b);
        double y16 = y8;
        double yr;
        
        for (int i = 1; i < n1; i++) {
            y8 += 4 * func.getFunctionResult(a-h1/2 + i*h1) + 2 * func.getFunctionResult(a + i*h1);
        }
        y8 += 4 * func.getFunctionResult(a-h1/2 + n1*h1);
        for (int i = 1; i < n2; i++) {
            y16 += 4 * func.getFunctionResult(a-h2/2 + i*h2) + 2 * func.getFunctionResult(a + i*h2);
        }
        y16 += 4 * func.getFunctionResult(a-h2/2 + n2*h2);
        
        y8 *= h1 / 6;
        y16 *= h2 / 6;
        
        result[0] = y8;
        result[1] = y16;
        
        yr = (y16 * 16 - y8) / 15;
        
        //(b-a) ^ 5
        double buf = (b-a)*(b-a);
        buf *= buf * (b-a);
        
        double a8 = -buf / (2880 * n1*n1* n1*n1) * func.getMaxFunctionDerivative(4);
        double a16 = -buf / (2880 * n2*n2* n2*n2) * func.getMaxFunctionDerivative(4);
        
        result[2] = yr;
        result[3] = a8;
        result[4] = a16;
        
        return result;
    }
    
//    void outTable(FunctionObject func, double a, double b) {
////        int n = 5;
////        
////        int len = 9;
////        int lendouble = 7;
////        
////        double[] buf;
////        
////        System.out.println(String.format("%"+len+"s | %"+len+"s | %"+len+"s | %"+len+"s | %"+len+"s", 
////                    "8" , 
////                    "16" , 
////                    "YR" , 
////                    "A8" , 
////                    "A16"  
////                    ));
////        System.out.println("Rectangle");
////        buf = getRectangleCenterInterpolation(func, a, b);
////        
//        //outTable(buf, len, lendouble);
//        
////        System.out.println(String.format("%"+len+"s | %"+len+"s | %"+len+"s | %"+len+"s | %"+len+"s", 
////                    String.format("%."+lendouble+"f", buf[0]) , 
////                    String.format("%."+lendouble+"f", buf[1]) , 
////                    String.format("%."+lendouble+"f", buf[2]) , 
////                    String.format("%."+lendouble+"f", buf[3]) , 
////                    String.format("%."+lendouble+"f", buf[4])  
////                    ));
//        //System.out.println();
////        
////        System.out.println("Trapeze");
////        buf = getTrapezeInterpolation(func, a, b);
////        System.out.println(String.format("%"+len+"s | %"+len+"s | %"+len+"s | %"+len+"s | %"+len+"s", 
////                    String.format("%.5f", buf[0]) , 
////                    String.format("%.5f", buf[1]) , 
////                    String.format("%.5f", buf[2]) , 
////                    String.format("%.5f", buf[3]) , 
////                    String.format("%.5f", buf[4])  
////                    ));
////        //System.out.println();
////        
////        System.out.println("Simpson");
////        buf = getSimpsonInterpolation(func, a, b);
////        System.out.println(String.format("%"+len+"s | %"+len+"s | %"+len+"s | %"+len+"s | %"+len+"s", 
////                    String.format("%.5f", buf[0]) , 
////                    String.format("%.5f", buf[1]) , 
////                    String.format("%.5f", buf[2]) , 
////                    String.format("%.5f", buf[3]) , 
////                    String.format("%.5f", buf[4])  
////                    ));
//        //System.out.println();
//    }
    
    public void outTable(double[] buf,int len,int lendouble) {
        //String format = "";
        for (int i = 0; i < buf.length; i++) {
            System.out.print(String.format("%"+len+"s | ", String.format("%."+lendouble+"f", buf[i])));
            //format += "%"+len+"s | ";
        }
        System.out.println();
//        System.out.println(String.format(format, 
//                    String.format("%."+lendouble+"f", buf[0]) , 
//                    String.format("%."+lendouble+"f", buf[1]) , 
//                    String.format("%."+lendouble+"f", buf[2]) , 
//                    String.format("%."+lendouble+"f", buf[3]) , 
//                    String.format("%."+lendouble+"f", buf[4])  
//                    ));
    }
    
}
