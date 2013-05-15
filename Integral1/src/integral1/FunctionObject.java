/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package integral1;

/**
 *
 * @author alex
 */
public abstract class FunctionObject {
    
//    public double[] fi;
//    public double[] xi;
    
//    private int n;
    
    abstract double getFunctionResult(double x);
    abstract double getMaxFunctionDerivative(int n);

    public FunctionObject() {
//        fi = new double[n];
//        for (int i = 0; i < x.length; i++) {
//            fi[i] = getFunctionResult(x[i]);
//        }
    }
    
    
//    private double li(double x, int k) {
//        double result = 1;
//        
//        for (int i = 0; i < n; i++) {
//            if (k != i) 
//            result *= (x - xi[i]) / (xi[k] - xi[i]);
//        }
//        return result;
//    }
    
//    public double computeLagrangeInterpolation(double x) {
//        double result = 0;
//        for (int i = 0; i < n; i++) {
//            result += li(x, i) * fi[i];
//        }
//        return result;
//    }
    
//    public double getA(double x) {
//        double res = 1;
//        int fact = 1;
//        for (int i = 0; i < n; i++) {
//            fact++;
//            res *= (x - xi[i]) / fact;
//        }
//        return Math.abs(res) * getMaxFunctionDerivative(n + 1);
//    }
    
    
}
