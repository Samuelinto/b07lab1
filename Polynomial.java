import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.PrintStream;

public class Polynomial {
	double[] poly;
	int [] expo;
	
	public Polynomial() {
		this.poly = new double[1];
		this.expo = new int[1];
	}
	
	/*
	public Polynomial(double[] array) {
		this.poly = new double[array.length];
		this.expo = new int[array.length];
		for(int i=0; i < array.length; i++) {
			this.poly[i]=array[i];
			this.expo[i]=i;
		}
	}
	*/
	
	public Polynomial(File file) throws Exception{
		Scanner sc = new Scanner(file);
		String equation_string = sc.nextLine();
		
		//splits equation by operators
		String[] separate_negative = equation_string.split("[-]+",0);
		
		//splits by negative and puts the negative back
		for (int i=1; i<separate_negative.length; i++) {
			separate_negative[i] = "-"+separate_negative[i];
			
			//System.out.println(separate_negative[i]);
		}
		
		String[] separate_empty = new String[0];
		for (int i=0; i<separate_negative.length; i++) {
			if(separate_negative[i].length() != 0) {
				int N = separate_empty.length;
				separate_empty = Arrays.copyOf(separate_empty, N+1);
				separate_empty[N]=separate_negative[i];
			}
		}
		
		//splits by other operators
		String[] separated = new String[0];
		for (int i=0; i<separate_empty.length; i++) {
			String[] temporal = separate_empty[i].split("[+/\\*]+",0);
			for (int j=0; j<temporal.length;j++) {
				int N = separated.length;
				separated = Arrays.copyOf(separated, N+1);
				separated[N]=temporal[j];
			}
		}
		
		
		//String[] separated = equation_string.split("[+/\\*]+");
		
		/*
		for(int i=0; i<separated.length;i++) {
			System.out.println(separated[i] + " at " + i + "|");
		}
		System.out.println("size of separated"+separated.length);
		*/
		
		//initializes size of poly and expo
		this.poly = new double[separated.length];
		this.expo = new int[separated.length];
		
		//splits by coefficient and exponent, and adds them to poly and expo
		for (int i =0; i<separated.length; i++) {
			for(int j=0; j<separated[i].length();j++) {
				if (separated[i].charAt((separated[i].length() - 1)) == 'x')
				{
					separated[i]=separated[i]+"1";
				}
			}
			
			String[] separate_x = separated[i].split("x",0);
			
			if (separate_x.length < 2) {
				this.poly[i]= Double.parseDouble(separate_x[0]);
			}
			else {
				this.poly[i]= Double.parseDouble(separate_x[0]);
				this.expo[i]= Integer.parseInt(separate_x[1]);
			}
		}
		
	}
	
	public void saveToFile(String file_name) throws Exception{
		PrintStream ps = new PrintStream(file_name + ".txt");
		
		for (int i=0; i < this.poly.length; i++) {
			// if the coefficient is negative, it already has a sign, if it isn't, and it
						// isn't the first variable nor the last, we add the plus sign
						if (this.poly[i]>0 && i>0) {
							ps.append('+');
						}
			
			
			//if exponent is 0, we don't add the x
			if (this.expo[i] == 0) {
				String coefficient = Double.toString(this.poly[i]);
				// go over all characters of the coefficient and add them
				for (int j=0; j<coefficient.length(); j++) {
					ps.append(coefficient.charAt(j));
				}
			}
			// if expo = 1 we don't add the 1 after the x
			else if (this.expo[i]==1) {
				String coefficient = Double.toString(this.poly[i]);
				// go over all char of coefficient and add them
				for (int j=0; j<coefficient.length(); j++) {
					ps.append(coefficient.charAt(j));
				}
				// add the x
				ps.append('x');
			}
			
			// if it isn't exponent 0, we add coeficient + x + exponent
			else {
				String coefficient = Double.toString(this.poly[i]);
				String exponent = Integer.toString(this.expo[i]);
				
				// go over all char of coefficient and add them
				for (int j=0; j<coefficient.length(); j++) {
					ps.append(coefficient.charAt(j));
				}
				// add the x
				ps.append('x');
				
				// go over all char of exponent and add them 
				for (int k=0; k<exponent.length(); k++) {
					ps.append(exponent.charAt(k));
				}
				
			}
		}
	}
	
	//checks if any element in second array is in first array
	public boolean check_int_array(int[] first, int[] second) {
		for (int i=0; i<first.length;i++) {
			for (int j=0; j<second.length; j++) {
				if (first[i]== second[j]) {
					return true;
				}
			}
		}
		return false;
	}
	// checks if value is in the array of int
	public boolean check_int(int[] array, int value) {
		for (int i=0; i<array.length;i++) {
			if (array[i]== value) {
				return true;
			}
		}
		return false;
	}
	
	// Checks if any element in second array is in first array
	public boolean check_double_array(double[] first, double[] second) {
		for (int i=0; i<first.length;i++) {
			for (int j=0; j<second.length; j++) {
				if (first[i]== second[j]) {
					return true;
				}
			}
		}
		return false;
	}
	
	// checks if value is in the array of doubles
	public boolean check_double(double[] array, double value) {
		for (int i=0; i<array.length;i++) {
			if (array[i]== value) {
				return true;
			}
		}
		return false;
	}
	
	
	public Polynomial add(Polynomial adding) {
		Polynomial added = new Polynomial();
		
		
		//
		for(int i=0; i < adding.expo.length; i++) {
			boolean inside = adding.check_int(added.expo, adding.expo[i]);
			
			//System.out.println("exponent to add is "+adding.expo[i]+" | inside? "+ inside);
			//if value is not in array, add it
			if (inside == false) {
				int N = added.expo.length;
				added.expo = Arrays.copyOf(added.expo, N+1);
				added.expo[N]=adding.expo[i];
			//	System.out.println("exponent from adding is " + added.expo[N]+"at"+N);
				}
			
			/*for(int j=0; j<added.expo.length; j++) {
				System.out.println("exponents so far are: " + j + "|" + added.expo[j]);
			}*/
		}
		/*
		for(int i=0; i<added.expo.length; i++) {
			System.out.println("1st exponents are: " + i + "|" + added.expo[i]);
		}*/
		
		//
		for(int i=0; i < this.expo.length; i++) {
			boolean inside = this.check_int(added.expo, this.expo[i]);
			if (inside == false) {
				int N = added.expo.length;
				added.expo = Arrays.copyOf(added.expo, N+1);
				added.expo[N]=this.expo[i];
			//	System.out.println("exponent from this is "+added.expo[N]+ "at" + N);
				//inside = true;
				}
			}
		/*
		for(int i=0; i<added.expo.length; i++) {
			System.out.println("Final exponents are: " + i + "|" + added.expo[i]);
		}*/
		
		added.poly = new double[added.expo.length];
		
		//checks if this or adding has a coefficient to add and adds it
		for (int i=0; i < added.poly.length; i++) {
			for (int j=0; j<adding.poly.length; j++) {
				if (adding.expo[j] == added.expo[i]) {
					added.poly[i] = added.poly[i] + adding.poly[j];
				}
			}
			
			for (int k=0; k<this.poly.length; k++) {
				if (this.expo[k] == added.expo[i]) {
					added.poly[i] = added.poly[i] + this.poly[k];
				}
			}
		}
		return added;
	}
	
	/*public Polynomial add(Polynomial adding) {
		Polynomial added;
		if(this.poly.length > adding.poly.length) {
			added = new Polynomial(this.poly);
			for(int i=0; i < adding.poly.length; i++) {
				added.poly[i]=this.poly[i]+adding.poly[i];
			}
		}
		else {
			added = new Polynomial(adding.poly);
			for(int i=0; i < this.poly.length; i++) {
				//System.out.println("before" + added.poly[i]);
				added.poly[i]=this.poly[i]+adding.poly[i];
				//System.out.println("after" + added.poly[i]);
			}
		}
		return added;
	}*/
	
	public double evaluate(double value) {
		double result = 0.0;
		for(int i=0; i < poly.length; i++) {
			result = result + poly[i]*(Math.pow(value,expo[i]));
			//System.out.println("Result at i =" + i + "is" + result);
		}
		return result;
	}
	
	public boolean hasRoot(double value) {
		double result = this.evaluate(value);
		//System.out.println("result for root is" + result);
		
		//double result = 0;
		//for(int i=0; i < poly.length; i++) {
			//result += poly[i]*(Math.pow(value,(i)));
		//}
		return result == 0;
	}
	
	public Polynomial multiply(Polynomial foil) {
		Polynomial temporal = new Polynomial();
		Polynomial empty = new Polynomial();
		Polynomial result = new Polynomial();
		
		//foils the whole equation but doesn't add repeated coefficients
		for (int i=0; i<this.poly.length; i++) {
			for (int j=0; j<foil.poly.length; j++) {
				int N = temporal.poly.length;
				temporal.poly = Arrays.copyOf(temporal.poly, N+1);
				temporal.poly[N]=(this.poly[i] * foil.poly[j]);
				
			//	System.out.println("polynomials foiled at "+N+" is "+temporal.poly[N]);
				
				temporal.expo = Arrays.copyOf(temporal.expo, N+1);
				temporal.expo[N]=(this.expo[i] + foil.expo[j]);
				
			//	System.out.println("exponents foiled at "+N+" is "+temporal.expo[N]);
			}
		}
		
		result = temporal.add(empty);
		
		return result;
		
		/*
		int k =0;
		while (k<temporal.expo.length){
			for(int j=0; j<result.expo.length;j++) {
				
					
			}
			int N = result.expo.length;
			result.expo = Arrays.copyOf(result.expo, N+1);
			result.expo[N]=temporal.expo[i];
			for (int i = 1; i<(temporal.expo.length - k); i++) {
				if temporal.expo[k] == temporal.expo[k+1] {
					
				}
			}
		}*/
		
	}

}
