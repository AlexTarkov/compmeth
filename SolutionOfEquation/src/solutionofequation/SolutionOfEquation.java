/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package solutionofequation;

/**
 *
 * @author alex
 */
public class SolutionOfEquation {
    
    private final double EPS = 0.0000001;
    
    private final double FIRST_STEP = 0.5;
    
    private final double MIN_STEP = 0.1;
    
    private final int MAX_DEGREE = 2; //( =2(MAX_DEGREE)+1 )
    
    void errorOutput(String message) {
        System.out.println(message);
        System.exit(0);
    }
    
    void printArray(double[] x) {
        for (int i = 0; i < x.length; i++){
            System.out.print(x[i] + " ");
        }
        System.out.println();
    }
    
    void printPoly(double[] x) {
        System.out.print(x[x.length - 1] + " * x^" + (x.length - 1));
        for (int i = x.length - 2; i > -1; i--){
            System.out.print(" + " + x[i] + " * x^" + i);
        }
        System.out.println(" = 0");
    }
    
    void out(String a) {
        System.out.println(a);
    }
    
    void tst(String about, double x) {
        //System.out.println(about + " " + x);
    }
    
    double maklorenMethod(double[] x) {
        double maxminus = 0;
        int firstminusdegree = -1;
        
        int degree = x.length - 1;
        
        for (int i = 0; i <= degree; i++) {
            maxminus = (x[i] < maxminus) ? x[i] : maxminus;
            firstminusdegree = (x[i] < 0) ? i : firstminusdegree;
        }
        
        if (firstminusdegree == -1) errorOutput("firstminusdegree = -1");
        
        tst("firstminusdegree", firstminusdegree);
        tst("maxminus", maxminus);
        tst("-maxminus / x[degree]", -maxminus / x[degree]);
        tst("x[degree]", x[degree]);
        
        double result;
        result = 1 + Math.exp((1 / (degree - firstminusdegree + 0.0)) * Math.log(-maxminus / x[degree]));
        
        return result;
    }
    
    double[] getRootLimits(double[] x) {
        double[] buf, result;
        double k;
        result = new double[4];
        
        int degree = x.length - 1;
        
        k = (x[0] < 0) ? -1 : 1; //??
        // верхняя положительная граница
        result[3] = maklorenMethod(x);
        
        //нижняя положительная граница
        buf = new double[degree + 1];
        for (int i = 0; i <= degree; i++) {
            buf[i] = k * x[degree-i];
        }
        //out("//нижняя положительная граница "); printArray(buf);
        result[2] = 1 / maklorenMethod(buf);
        
        //нижняя отрицательная граница
        buf = new double[degree + 1];
        //k = (degree % 2 == 1) ? -1 : 1;
        for (int i = 0; i <= degree; i++) {
            buf[i] =  ((i % 2 == 1) ? -x[i] : x[i]);
        }
        //out("//нижняя отрицательная граница "); printArray(buf);
        result[0] = -maklorenMethod(buf);
        
        //верхняя отрицательная граница
        buf = new double[degree + 1];
        //k = ((degree % 2 == 1) & (x[0] < 0)) ? -1 : 1; //??
        for (int i = 0; i <= degree; i++) {
            buf[degree-i] =  ((i % 2 == 1) ? -x[i] : x[i]) * k;
        }
        //out("//верхняя отрицательная граница "); printArray(buf);
        result[1] = -1 / maklorenMethod(buf);
        return result;
    }

    double getPolynomialResult(double[] polynom, double x) {
        double res = 0;
        for (int i = 0; i < polynom.length; i++) {
            res += polynom[i] * Math.pow(x, i);//Math.exp(i * Math.log(x));
            //out("Math.pow(x, i) : " + Math.pow(x, i));
        }
        return res;
    }
    
    double[] getPolynomialDerivative(double[] polynom) {
        double res = 0;
        double[] buf = new double[polynom.length-1];
        for (int i = 1; i < polynom.length; i++) {
            buf[i-1] = polynom[i] * i;
        }
        return buf;
    }
    
    double[] getFirstApproximation(double[] poly) {
        double result[] = null;
        
        double[] rootlimits = getRootLimits(poly);
        
        //printArray(rootlimits);
        
        //
        
        int rootcount = 0;
        
        double step = FIRST_STEP;
        
        while ((rootcount < (poly.length - 1)) && (step >= MIN_STEP)) {
            result = new double[poly.length]; // неэффективное решение
            result[0] = 0;
            //int k = 0;
            double nowx = rootlimits[0];
            double y1, y2 = getPolynomialResult(poly, nowx);
            //out("q");
            while (nowx < rootlimits[1]) {
                y1 = y2;
                nowx += step;
                y2 = getPolynomialResult(poly, nowx);
                //out("nowx:"+nowx+"|y2:"+y2);
                if ((y1 == 0) || (y2 / y1 <= 0)) {
                    result[0]++;
                    result[(int)(result[0])] = nowx - step * 0.5;
                }
            }
            
            //k += 2;
            
            nowx = rootlimits[2];
            //y1 = getPolynomialResult(poly, nowx);
            y2 = getPolynomialResult(poly, nowx);
            
            while (nowx < rootlimits[3]) {
                y1 = y2;
                nowx += step;
                y2 = getPolynomialResult(poly, nowx);
                if ((y1 == 0) || (y2 / y1 <= 0)) {
                    result[0]++;
                    result[(int)(result[0])] = nowx - step * 0.5; 
                }
            }
            step /= 2;
            rootcount = (int)result[0];
            
        }
        
        
        return result;
    }
    
    double approximateRoot(double x, double[] poly) {
        double[] polyderiv = getPolynomialDerivative(poly);
        //double result = x;
        int k = 0;
        double x0 = x;
        double x1 = x0 - getPolynomialResult(poly, x0) / getPolynomialResult(polyderiv, x0);
//        out("x0 = " + x0);
//        out("getPolynomialResult(poly, x0) / getPolynomialResult(polyderiv, x0) " + 
//                getPolynomialResult(poly, x0) / getPolynomialResult(polyderiv, x0));
        while (((x1 - x0)>=0?(x1 - x0):(x0 - x1)) > EPS) {
//          out("::" + (x1 - x0));
            k++;
            x0 = x1;
            x1 = x0 - getPolynomialResult(poly, x0) / getPolynomialResult(polyderiv, x0);
        }
        out("k = " + k + "; x = " + x1 + " ( " + x0 + " ) " +
                "; poly(x) = " + 
                String.format("%.12f", getPolynomialResult(poly, x1)) + " ( " +
                String.format("%.12f", getPolynomialResult(poly, x0)) + " )"); 
        return x1;
    }
    
    double[] getRoots(double[] fa, double[] poly) {
        double result[] = new double[(int)fa[0]];
        for (int i = 1; i <= fa[0]; i++) {
            result[i-1] = approximateRoot(fa[i], poly);
        }
        return result;
    }
    
    
    //-------------------------------------------------------------specific func
    
    public double getFuncResult(double x) {
        return 1.5 * x * x - Math.sin(x) - 3;
    }
    
    public double getDerivativeResult(double x, int n) {
        if (n == 1) { return 3 * x - Math.cos(x); }
        if (n == 2) { return 3 + Math.sin(x); }
        n = (n - 3) % 4;
        
        switch (n) {
            case 0 : return Math.cos(x);
            case 1 : return -Math.sin(x); 
            case 2 : return -Math.cos(x);
            case 3 : return Math.sin(x);
        }
        return 0;        
        
    }
    
    double[] getRootLimits() {
        double[] result = new double[4];
        
        result[1] = - 2.0 / 3;
        result[2] = 1 / (1 + Math.sqrt(0.5));
        
        int fact = 1;
        for (int i = 2; i <= (2*MAX_DEGREE+1); i++) {
            fact *= i;
        }
        
        result[0] = - 1 - Math.sqrt(1.5 * fact);
        result[3] = 1 + Math.sqrt(3 * fact);
        
        return result;
    }
    
    double[] getFuncFirstApproximation() {
        double result[] = null;
        
        double[] rootlimits = getRootLimits();
        
        //printArray(rootlimits);
        
        //
        
        int rootcount = 0;
        
        double step = FIRST_STEP;
        
        while ((rootcount < (2)) && (step >= MIN_STEP)) {
            result = new double[3]; // неэффективное решение
            result[0] = 0;
            //int k = 0;
            double nowx = rootlimits[0];
            double y1, y2 = getFuncResult(nowx);
            //out("q");
            while (nowx < rootlimits[1]) {
                y1 = y2;
                nowx += step;
                y2 = getFuncResult(nowx);
                //out("nowx:"+nowx+"|y2:"+y2);
                if ((y1 == 0) || (y2 / y1 <= 0)) {
                    result[0]++;
                    result[(int)(result[0])] = nowx - step * 0.5;
                }
            }
            
            //k += 2;
            
            nowx = rootlimits[2];
            //y1 = getPolynomialResult(poly, nowx);
            y2 = getFuncResult(nowx);
            
            while (nowx < rootlimits[3]) {
                y1 = y2;
                nowx += step;
                y2 = getFuncResult(nowx);
                if ((y1 == 0) || (y2 / y1 <= 0)) {
                    result[0]++;
                    result[(int)(result[0])] = nowx - step * 0.5; 
                }
            }
            step /= 2;
            rootcount = (int)result[0];
            
        }
        
        
        return result;
    }
    
    double approximateFuncRoot(double x) {
        int k = 0;
        double x0 = x;
        double x1 = x0 - getFuncResult(x0) / getDerivativeResult(x0, 1);
//        out("x0 = " + x0);
//        out("getPolynomialResult(poly, x0) / getPolynomialResult(polyderiv, x0) " + 
//                getPolynomialResult(poly, x0) / getPolynomialResult(polyderiv, x0));
        while (((x1 - x0)>=0?(x1 - x0):(x0 - x1)) > EPS) {
//          out("::" + (x1 - x0));
            k++;
            x0 = x1;
            x1 = x0 - getFuncResult(x0) / getDerivativeResult(x0, 1);
        }
        out("k = " + k + "; x = " + x1 + " ( " + x0 + " ) " +
                "; func(x) = " + 
                String.format("%.12f", getFuncResult(x1)) + " ( " +
                String.format("%.12f", getFuncResult(x0)) + " )"); 
        return x1;
    }
    
    double[] getFuncRoots(double[] fa) {
        double result[] = new double[(int)fa[0]];
        for (int i = 1; i <= fa[0]; i++) {
            result[i-1] = approximateFuncRoot(fa[i]);
        }
        return result;
    }
    
    //--------------------------------------------------------------------------
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SolutionOfEquation obj = new SolutionOfEquation();
        //double[] poly = {1, -36, -9, 0, 9};
        double[][] polynoms = {{-4.2, 5.2, 0.1, -2.9, 1}, {24, -2, -25, 2, 1}};
        //obj.printArray(obj.getFirstApproximation(poly));
        for (int i = 0; i < polynoms.length; i++) {
            obj.printPoly(polynoms[i]);
            obj.getRoots(obj.getFirstApproximation(polynoms[i]), polynoms[i]);
        }
        
        //---------------------------------------------------------specific func
        
        obj.out("1.5x*x - sin(x) - 3 = 0");
        
        obj.getFuncRoots(obj.getFuncFirstApproximation());
        
        //----------------------------------------------------------------------
    }
}
