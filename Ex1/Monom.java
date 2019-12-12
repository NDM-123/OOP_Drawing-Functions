
package Ex1;

import java.util.Comparator;
import java.util.InputMismatchException;

import javax.management.RuntimeErrorException;

/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real number and a is an integer (summed a none negative), 
 * see: https://en.wikipedia.org/wiki/Monomial 
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply. 
 * @author Boaz
 *
 */
public class Monom implements function{
	public static final Monom ZERO = new Monom(0,0);
	public static final Monom MINUS1 = new Monom(-1,0);
	public static final double EPSILON = 0.0000001;
	public static final Comparator<Monom> _Comp = new Monom_Comperator();
	public static Comparator<Monom> getComp() {return _Comp;}
	public Monom(double a, int b){					//constructor that receives numbers that represent the coefficient and power and initialize as a monom
		this.set_coefficient(a);
		this.set_power(b);
	}
	public Monom(Monom ot) {						//copy constructor
		this(ot.get_coefficient(), ot.get_power());
	}

	public double get_coefficient() {				//getters
		return this._coefficient;
	}
	public int get_power() {
		return this._power;
	}
	/** 
	 * this method returns the derivative monom of this.
	 * @return
	 */
	public Monom derivative() {						//monom derivative
		if(this.get_power()==0) {return getNewZeroMonom();}
		return new Monom(this.get_coefficient()*this.get_power(), this.get_power()-1);
	}
	public double f(double x) {						//the value of a given x in a function
		double ans=0;
		double p = this.get_power();
		ans = this.get_coefficient()*Math.pow(x, p);
		return ans;
	} 
	public boolean isZero() {						//checks if the coefficient monom equals to zero 

		return this.get_coefficient() == 0;

	}
	// ***************** add your code below **********************
	public Monom(String s) { 						//constructor that receives a string and initialize it as a monom 
		try {
			if(s.charAt(0)=='+')s=s.substring(1);
			s=s.toLowerCase();
			s=s.replaceAll("\\s+","");
			if( !Character.isDigit(s.charAt(0)) &&s.charAt(0)!='x'&& s.charAt(0)!= '^'&& s.charAt(0)!= '-' ) {
				throw new InputMismatchException();
			}
			for(int i=1; i<s.length(); i++ ) {
				if( !Character.isDigit(s.charAt(i)) &&s.charAt(i)!='x'&& s.charAt(i)!= '^'&& s.charAt(i)!= '.' ) {
					throw new InputMismatchException();
				}
			}
			if(s.length()==1 && !Character.isDigit(s.charAt(0)) && s.charAt(0)!= 'x' )throw new InputMismatchException();
			
			String a ="";												//building the coefficient
			int i=0;
			while(i<s.length() && s.charAt(i) != 'x') {
				a+=s.charAt(i);
				i++;
			}
			if(a == "")a = "1";
			if(a.charAt(0)=='-' && a.length()==1)a = "-1";
			if(a.charAt(0)=='-') {this.set_coefficient(Double.parseDouble(a.substring(1))*-1);}
			else {
				this.set_coefficient(Double.parseDouble(a));					//setting the coefficient
			}
			if(s.charAt(s.length()-1)=='x') {
				this.set_power(1);
				}
			else if(i+2<s.length() ){
				i+=2;
				String b = "";
				while(i<s.length()) {
					b += s.charAt(i);
					i++;
				}
				this.set_power(Integer.parseInt(b));
			}
		}
		catch(Exception e) {
			
			System.out.println("ERR isn't Monom, input isnt correct");
			
		}
		
	
	}
	public void add(Monom m) {								//adding monom to monom
		if(this._power==m._power) {
			this._coefficient +=m._coefficient;
		}
	}

	public void multipy(Monom d) {							//multiplying monom with monom
		this._power+=d._power;
		this._coefficient*=d._coefficient;
		}
	public Monom multipy2(Monom d) {						//a function that multiplies monom with other monom and returns monom (was made for polynom class use) 
		double a = this._coefficient*d._coefficient;
		int b = this._power+d._power;
		Monom m = new Monom( a,b);
		return m;
		}


	public String toString() {								//to string function
		String ans = this.get_coefficient()+"x^"+this.get_power();
		return ans;
	}
	public boolean equals(Object o) {						//checks if the monoms are equal in a certain range(0.000001)
	if(o instanceof Monom) {
	Monom o2 = (Monom)o; 
		if(this._coefficient >= o2._coefficient-0.000001 && this._coefficient<=o2._coefficient+0.000001 && this._power==o2._power)return true;
		else return false;
	}
	return false;
	}
	public double area(double x0, double x1, double eps) {		//Calculate the area in between the function and the x axis
		double sum = 0;
		for (double i = x0; i < x1; i += eps) {
			if(f(i)>0) {												//above the x axis
				sum +=  f(i)*eps;
			}
		}
		return sum;
	}
	public double root(double x0, double x1, double eps) {        //find the root of the function(where the function meets the x axis)
		try {
			if(f(x0)*f(x1)>0) throw new RuntimeException();
			double low = x0;
			double high = x1;
			double mid=(high+low)/2;
			double lowf;
			while((high-low)>eps) {
				mid = (high+low)/2;
				lowf=f(low);
				if((lowf*f(mid))>0) {			//the same sign
					high=mid;
				}
				else {							//different sign
					low=mid;
				}
			}
			return mid;
		}catch(Exception e) {
			System.out.println("err the details aren't in the range:f(x0)*f(x1)<=0");
			return 0;
		}
	}
	// you may (always) add other methods.

	//****************** Private Methods and Data *****************


	private void set_coefficient(double a){					//setters
		this._coefficient = a;
	}
	private void set_power(int p) {
		if(p<0) {throw new RuntimeException("ERR the power of Monom should not be negative, got: "+p);}
		this._power = p;
	}
	private static Monom getNewZeroMonom() {return new Monom(ZERO);}
	private double _coefficient; 
	private int _power;
	
	public function initFromString(String s) {
		function a = new Monom(s);
		return a;
	}
	public function copy() {// clone
		function a = new Monom(this._coefficient,this._power);
	return a;
	}
	

}
