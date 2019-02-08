//-----------------------------------------------------------
// Roots.java
// Rohith Lukkoor, Jacky Hua
// rlukkoor, jahua
// pa4
// find the roots of a polynomial within a specified interval
//-----------------------------------------------------------

import java.util.Scanner;

class Roots {

      public static void main( String[] args ){
            double resolution = 0.01;
            double tolerance = 0.0000001;
            double threshold = 0.001;
            double endpointL;
            double endpointR;
            
            int degree;
    		int coeffNum;

            Scanner sc = new Scanner(System.in);

            System.out.print("Enter the degree: ");
            degree = sc.nextInt();
            
            coeffNum = degree + 1;
            double[] polyArray = new double[coeffNum];
           
            System.out.print("Enter " + coeffNum + " coefficients: ");
          	for(int i = 0; i<polyArray.length; i++){
            polyArray[i] = sc.nextInt();
            }
            
            double[] diffArray = diff(polyArray); 
            System.out.print("Enter the left and right endpoints: ");
            endpointL = sc.nextDouble();
            endpointR = sc.nextDouble();
            
            System.out.println();
            
            double x = endpointL; 
            double y = endpointL + resolution; 
            
            int noRoot = 0;
            while(x<endpointR){
            	double a1 = poly(polyArray, x);
            	double a2 = poly(polyArray, y);
            	double a3 = poly(diffArray, x);
            	double a4 = poly(diffArray, y);
            
        	
            if(a1*a2<0){	
            	double root = findRoot(polyArray, x, y, tolerance);
            	
            	System.out.printf("Root found at %.5f%n", root);
            noRoot += 1;
            }
            else if(a3*a4<0){
            	double derivRoot = findRoot(diffArray, x, y, tolerance);
            	if(Math.abs(poly(polyArray, derivRoot)) < threshold){
            		
            		System.out.printf("Root found at %.5f%n", derivRoot);
            noRoot += 1;
            	}
            }
            
            x += resolution;
            y += resolution;
            }
            
            if(noRoot == 0){
          
            	System.out.println("No roots were found in the specified range.");	
            }
            }

      static double poly(double[] C, double x){
            double polySum = 0;
            for (int i = 0; i < C.length; i++){
                  polySum += C[i]*(Math.pow(x, i));
            }
            return(polySum);

      }

      static double[] diff(double[] C){ 
      		double[] deriv = new double [C.length - 1];
            for (int i = 0; i < deriv.length; i++){
                  deriv[i] = (i+1)*C[i+1];
            }
            return(deriv);
      }

      static double findRoot(double[] C, double a, double b, double tolerance)
      {
      		double w = b-a;
            double mid = 0.0;
            while ( w > tolerance )
             {
                  mid = (a + b) / 2.0;

                  if (poly(C, a) * poly(C, mid) < 0)
                		b = mid;
                             
                    else
                        a = mid;
            			
            		w = b-a;
      		}
      		  return(mid);
      	}
}
	
	
