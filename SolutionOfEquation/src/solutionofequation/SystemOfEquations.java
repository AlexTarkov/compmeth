/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package solutionofequation;

import java.util.ArrayList;

/**
 *
 * @author alex
 */
public class SystemOfEquations {
    
    private double EPS = 0.001;
    
    public static void main(String[] args) {
        new SystemOfEquations().run();
    }
    
    public void run() {
        double a = 0.5;
        double k = 0.2;
        
        FunctionObject f = new FunctionObject(a, k) {

            @Override
            double getFunctionResult(double x, double y) {
                return Math.tan(x-y+k) - x*y;
            }

            @Override
            double getFunctionDerivativeX(double x, double y) {
                return 1 / (Math.cos(x-y+k)*Math.cos(x-y+k)) - y;
            }
            
            @Override
            double getFunctionDerivativeY(double x, double y) {
                return -1 / (Math.cos(x-y+k)*Math.cos(x-y+k)) - x;
            }
        };
        
        FunctionObject g = new FunctionObject(a, k) {

            @Override
            double getFunctionResult(double x, double y) {
                return a * x * x + 2 * y*y - 1;
            }

            @Override
            double getFunctionDerivativeX(double x, double y) {
                return 2 * x * a;
            }
            
            @Override
            double getFunctionDerivativeY(double x, double y) {
                return 4 * y;
            }
        };
    
        Double[] xy = getFirstXY(f, g, -1, 1, -1, 1);
        
        
        double x,y;
        for (int i = 0; i < xy.length; i+=2) {
            x = xy[i];
            y = xy[i+1];
            
            double x0,x1,y0,y1;
            
            x1 = x; y1 = y;
            
            x0 = x1 + 1;
            y0 = y1 + 1;
            int p = 0;
            while (Math.abs(x1 - x0)>EPS && Math.abs(y1-y0)>EPS) {
                
                p++;
                
                x0 = x1;
                y0 = y1;
                //if (Math.abs(x1 - x0)>EPS)
                x1 = x0 + ( -f.getFunctionResult(x1, y1) * g.getFunctionDerivativeY(x1, y1) +
                        g.getFunctionResult(x1, y1) * f.getFunctionDerivativeY(x1, y1)) / 
                        (f.getFunctionDerivativeX(x1, y1) * g.getFunctionDerivativeY(x1, y1) -
                        g.getFunctionDerivativeX(x1, y1) * f.getFunctionDerivativeY(x1, y1));
                
                y1 = y0 + ( -g.getFunctionResult(x1, y1) * f.getFunctionDerivativeX(x1, y1) +
                        f.getFunctionResult(x1, y1) * g.getFunctionDerivativeX(x1, y1)) / 
                        (f.getFunctionDerivativeX(x1, y1) * g.getFunctionDerivativeY(x1, y1) -
                        g.getFunctionDerivativeX(x1, y1) * f.getFunctionDerivativeY(x1, y1));
                
            }
            System.out.println("k = " + p + "; x = " + String.format("%.9f", x1) +
                    " y = "+ String.format("%.9f", y1));
            System.out.println("f(x,y) = " + String.format("%.9f", f.getFunctionResult(x1, y1)) +
                    " ; g(x,y) = " + String.format("%.9f", g.getFunctionResult(x1, y1)));
        }
        
        
//        
    }
    
    public Double[] getFirstXY(FunctionObject f, FunctionObject g, double a, double b, double c, double d) {
        double b1,b2;
        ArrayList<Double> result = new ArrayList();
        for (double k = 0.1; k > EPS; k *= 0.1) {
            if (result.size() == 2) {
                break;
            } else {
                result = new ArrayList();
            }
            for (double i = a; i <= b; i += k) {
                for (double j = c; j <= d; j += k) {
                    b1 = Math.abs(f.getFunctionResult(i, j));
                    b2 = Math.abs(g.getFunctionResult(i, j));
                    if (b1 <= 0.1 && b2 <= 0.1) {
                        result.add(i);
                        result.add(j);
                    }
                }
            }
        }
        //System.out.print(result.size());
        return result.toArray(new Double[0]);
    }
    
}
