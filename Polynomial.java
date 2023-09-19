public class Polynomial {
	double[] poly;
	
	public Polynomial() {
		this.poly = new double[0];
	}
	
	public Polynomial(double[] array) {
		this.poly = new double[array.length];
		for(int i=0; i < array.length; i++) {
			this.poly[i]=array[i];
		}
	}
	
	public Polynomial add(Polynomial adding) {
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
	}
	
	public double evaluate(double value) {
		double result = 0.0;
		for(int i=0; i < poly.length; i++) {
			result = result + poly[i]*(Math.pow(value,(i)));
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

}
