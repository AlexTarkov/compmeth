package solutionofequation;

public abstract class FunctionObject {
    
    public double a, k;
    
    public FunctionObject(double a, double k) {
        this.a = a;
        this.k = k;
    }
    
    abstract double getFunctionResult(double x, double y);
    abstract double getFunctionDerivativeX(double x, double y);
    abstract double getFunctionDerivativeY(double x, double y);
    
}
