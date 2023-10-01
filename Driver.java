import java.io.*;

public class Driver {
	public static void main(String [] args) throws Exception{
		
		//check no argument constructor
		Polynomial p = new Polynomial();
		System.out.println("Size of p poly is |" + p.poly.length + "| with value |" + p.poly[0]);
		System.out.println("Size of p expo is |" + p.expo.length + "| with value |" + p.expo[0]);
		
		//Check constructor with file
		File test_file = new File("C:\\Users\\Samuel\\Documents\\b07lab1\\test.txt");
		Polynomial test = new Polynomial(test_file);
		System.out.println("Poly is:");
		for (int i = 0; i<test.poly.length; i++) {
			System.out.println("Poly= " + test.poly[i] + " at " + i);
		}
		System.out.println("Expo is:");
		for (int i = 0; i<test.expo.length; i++) {
			System.out.println("Expo= " + test.expo[i] + " at " + i);
		}
		
		//Check saveToFile
		test.saveToFile("output_file");
		
		File p1_f = new File("C:\\Users\\Samuel\\Documents\\b07lab1\\test 2.txt");
		Polynomial p1 = new Polynomial(p1_f);
		
		File p2_f = new File("C:\\Users\\Samuel\\Documents\\b07lab1\\test 3.txt");
		Polynomial p2 = new Polynomial(p2_f);
		
		Polynomial sum = p1.add(p2);
		
		for (int i=0; i<sum.poly.length; i++) {
			System.out.println("Exponent at i=" + i +" is " + sum.expo[i]);
			System.out.println("Poly at i=" + i +" is " + sum.poly[i]);
		}
		
		sum.saveToFile("sum");
		
	
		// Check evaluate
		double result = p1.evaluate(2);
		System.out.println("result of evaluating p1 at x=2 is "+result);
		
		double result2 = p2.evaluate(2);
		System.out.println("result of evaluating p2 at x=2 is "+result2);
		
		
		//Check hasRoot
		boolean root_result = p1.hasRoot(1);
		System.out.println("is 1 root?" + root_result);
		
		File p3_f = new File("C:\\Users\\Samuel\\Documents\\b07lab1\\test 4.txt");
		Polynomial p3 = new Polynomial(p3_f);
		boolean root_result3 = p3.hasRoot(3);
		double result3=p3.evaluate(3);
		System.out.println("evaluate at 3=" +result3+ "is 3 root?" + root_result3);
		
		//check multiply
		Polynomial multiplied = p1.multiply(p2);
		
		System.out.println("multiplied poly is:");
		for (int i=0; i<multiplied.poly.length;i++) {
			System.out.print(multiplied.poly[i]+",");
		}
		
		System.out.println("multiplied expo is:");
		for (int i=0; i<multiplied.expo.length;i++) {
			System.out.print(multiplied.expo[i]+",");
		}
		
		multiplied.saveToFile("multiplied");
		
		/*
		Polynomial p = new Polynomial();
		System.out.println(p.evaluate(3));
		double [] c1 = {6,0,0,5};
		Polynomial p1 = new Polynomial(c1);
		double [] c2 = {0,-2,0,0,-9};
		Polynomial p2 = new Polynomial(c2);
		Polynomial s = p1.add(p2);
		System.out.println("s(0.1) = " + s.evaluate(0.1));
		if(s.hasRoot(1))
			System.out.println("1 is a root of s");
		else
			System.out.println("1 is not a root of s");
		*/

  }
}
