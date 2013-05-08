/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lagrangeinterpolation;

/**
 *
 * @author alex
 */
public abstract class FunctionObject {
    
    public double[] fi;
    public double[] xi;
    
    public int n;
    
    abstract double getFunctionResult(double x);
    abstract double getFunctionDerivative(double x);

    public FunctionObject(double[] x) {
        xi = x;
        this.n = xi.length;
        fi = new double[n];
        for (int i = 0; i < x.length; i++) {
            fi[i] = getFunctionResult(x[i]);
        }
    }
    
    
    private double li(double x, int k) {
        double result = 1;
        
        for (int i = 0; i < xi.length; i++) {
            if (k != i) 
            result *= (x - xi[i]) / (xi[k] - xi[i]);
        }
        return result;
    }
    
    public double computeLagrangeInterpolation(double x) {
        double result = 0;
        for (int i = 0; i < n; i++) {
            result += li(x, i) * fi[i];
        }
        return result;
    }
    
    
}
