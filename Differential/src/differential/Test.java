/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package differential;

/**
 *
 * @author alex
 */
public class Test {
    
    public static void  pa(double[] x) {
        for (int i = 0; i < x.length; i++){
            System.out.print(x[i] + " ");
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        new Test().run();
    }
    
    public void run() {
        FunctionObject f = new FunctionObject(1, 0.5);
        Adams.compute(f, 0, 0, 10, 0.1);
    }
}
