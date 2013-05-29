/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package differential;

/**
 *
 * @author alex
 */
public class FunctionObject {
    
    double k = 0.5;
    double a = 1;
    
    public FunctionObject(double a, double k) {
        this.a = a;
        this.k = k;
    }
    
    private double getValue(double x, double y) {
        return Math.cos(a * x + y) + k * (x - y);
    }
    
    double gv(double x, double y) {
        return getValue(x, y);
    }
}
